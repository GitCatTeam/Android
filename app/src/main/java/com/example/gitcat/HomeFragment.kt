package com.example.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.bumptech.glide.Glide
import com.example.gitcat.model.HomeData
import com.example.gitcat.model.HomeModel
import com.example.gitcat.model.LogoutModel
import com.example.gitcat.retrofit.RetrofitCreator
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    var adapterViewPager: FragmentPagerAdapter? = null
    val tuDialog = TuDialogFragment()
    var token: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
    }

    fun init(){

        Glide.with(this@HomeFragment).load(R.raw.gif_cat_loading).into(activity?.findViewById<ImageView>(R.id.img_home_cat_loading)!!)
        NewToken(context!!)
        val settings: SharedPreferences = context!!.getSharedPreferences("gitcat",AppCompatActivity.MODE_PRIVATE)
        token = settings.getString("token","")
        Log.e("token","$token")
        callApi(token)

        //튜토리얼
        //tuDialog.setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light)
        //tuDialog.show(fragmentManager!!,"addons_fragment")


        //하단 탭 버튼 리스너
        diaryIcon.setOnClickListener { view ->
            val intent = Intent(activity,CollectionActivity::class.java)
            startActivity(intent)
        }

        settingsIcon.setOnClickListener{ view ->
            val intent = Intent(activity,SettingsActivity::class.java)
            startActivity(intent)
        }
        //새로고침
        img_btn_new_data.setOnClickListener {
            callApi(token)
        }
    }

    override fun onResume() {
        super.onResume()
        callApi(token)
    }

    private fun callApi(token: String){
        activity?.findViewById<ImageView>(R.id.img_home_cat_loading)!!.visibility = View.VISIBLE
        val call: Call<LogoutModel> = RetrofitCreator.service.getCommitsUpdate(token)
        call.enqueue(
            object : Callback<LogoutModel>{
                override fun onFailure(call: Call<LogoutModel>, t: Throwable) {
                    showErrorPopup(t.toString(),context!!)
                }

                override fun onResponse(call: Call<LogoutModel>, response: Response<LogoutModel>) {
                    if(response.isSuccessful){
                        afterCallApi(token)
                    }else{
                        showErrorPopup("["+response.code().toString()+"] "+response.message(),context!!)
                    }
                }
            }
        )
    }
    private fun afterCallApi(token: String){
        val call: Call<HomeModel> = RetrofitCreator.service.getHomeMain(token)
        call.enqueue(
            object : Callback<HomeModel>{
                override fun onFailure(call: Call<HomeModel>, t: Throwable) {
                    showErrorPopup(t.toString(),context!!)
                }

                override fun onResponse(call: Call<HomeModel>, response: Response<HomeModel>) {
                    activity?.findViewById<ImageView>(R.id.img_home_cat_loading)!!.visibility = View.GONE
                    if(response.isSuccessful){
                        val data = response.body()?.data
                        if(response.body()==null){
                            nullCat()
                        }else{
                            if (data?.isGraduate!!) {
                                nullCat()
                                //졸업 다이얼로그
                                val graduateDialog = GraduateDialogFragment()
                                graduateDialog.show(fragmentManager!!, "graduate_fragment")
                            }else if(data?.isLeave!!) {
                                nullCat()
                                val leaveCatFragment = LeaveCatFragment()
                                leaveCatFragment.show(fragmentManager!!, "leave_fragment")
                            }else if(data?.isLevelUp!!){
                                homeCat(data)
                                val upgradeDialog = UpgradeDialogFragment()
                                upgradeDialog.show(fragmentManager!!,"upgrade_fragment")
                            }else{
                                homeCat(data)
                            }
                        }
                    }else{
                        showErrorPopup("["+response.code().toString()+"] "+response.message(),context!!)
                    }
                }
            }
        )
    }

    private fun homeCat(data: HomeData){
        //홈화면 보여주기
        txt_home_commit_count.text = data?.todayCommitCount.toString()
        //홈 gif 처리
        Glide.with(context!!).load(data?.catImg).into(img_home_cat_gif)
        txt_home_nickname.text = data?.catName
        txt_home_today_score.text = data?.todayScore.toString()
        txt_home_next_level_score.text = data?.nextLevelScore.toString()
        txt_home_next_level_item.text = "(${data?.nextLevelStr})"
    }

    private fun nullCat(){
        cl_home_info_content.visibility = View.INVISIBLE
        btn_home_choose_cat_again.visibility = View.VISIBLE
        img_bubble_line.visibility = View.GONE
        txt_bubble_content.visibility = View.GONE
        Glide.with(context!!).load(R.drawable.img_cat_null).into(img_home_cat_gif)
        txt_home_commit_count.text = "-"
        btn_home_choose_cat_again.setOnClickListener {
            val intent = Intent(context, Info4Activity::class.java)
            startActivity(intent)
        }
    }
    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }


}
