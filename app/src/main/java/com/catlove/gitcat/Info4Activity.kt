package com.catlove.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info4.*
import android.view.ViewGroup
import com.catlove.gitcat.retrofit.RetrofitCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.View
import android.view.WindowManager
import androidx.viewpager.widget.ViewPager
import com.catlove.gitcat.model.DataModel

class Info4Activity : AppCompatActivity(){

    private var catId: Int = 0
    private lateinit var newDialogFragment :NewDialogFragment
    private lateinit var prepareDialogFragment: PrepareDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_info4)

        img_back_btn.setOnClickListener {
            onBackPressed()
        }


        //통신
        val settings: SharedPreferences = getSharedPreferences("gitcat", AppCompatActivity.MODE_PRIVATE)

        val token = settings.getString("token","")
        val call: Call<DataModel> = RetrofitCreator.service.getCats(token)
        call.enqueue(object : Callback<DataModel>{
            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                showErrorPopup("재로그인을 해주세요!",applicationContext)
            }

            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if(response.isSuccessful) {
                    val data = response.body()!!.data

                    val fragmentAdapter = ChooseCatAdapter(supportFragmentManager,2, data)
                    vp_information_cat.adapter = fragmentAdapter
                    tl_information_cat.setupWithViewPager(vp_information_cat)
                    vp_information_cat.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
                        override fun onPageSelected(position: Int) {
                            if(position == 1){
                                prepareDialogFragment = PrepareDialogFragment()
                                prepareDialogFragment.show(supportFragmentManager,"prepare_fragment")
                            }
                        }

                        override fun onPageScrollStateChanged(state: Int) {

                        }

                        override fun onPageScrolled(
                            position: Int,
                            positionOffset: Float,
                            positionOffsetPixels: Int
                        ) {

                        }
                    })

                    val tabgroup = tl_information_cat.getChildAt(0) as ViewGroup
                    val tab: View =tabgroup.getChildAt(0)
                    val margin :ViewGroup.MarginLayoutParams = tab.layoutParams as ViewGroup.MarginLayoutParams
                    margin.setMargins(0,0,20,0)

                    val tab2: View =tabgroup.getChildAt(1)
                    val margin2 :ViewGroup.MarginLayoutParams = tab2.layoutParams as ViewGroup.MarginLayoutParams
                    margin2.setMargins(20,0,0,0)


                    if(data.isNewExist){
                        //새로운 고양이 모달창
                        newDialogFragment = NewDialogFragment(data.new)
                        newDialogFragment.show(supportFragmentManager,"newDialogFragment")
                    }

                }else{
                    //showErrorPopup(response.message(),applicationContext)
                    if(response.code()>=500){
                        showErrorPopup("[네트워크 오류] 재로그인을 해주세요!",this@Info4Activity)
                    }else{
                        showErrorPopup("[내부 서버 오류] 재로그인을 해주세요!",this@Info4Activity)
                    }
                }
            }
        })
        btn_choose_cat_next.setOnClickListener {
            val intent = Intent(this, Info5Activity::class.java)
            startActivity(intent)
        }
    }

}



