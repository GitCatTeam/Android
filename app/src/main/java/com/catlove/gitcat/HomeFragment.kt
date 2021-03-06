package com.catlove.gitcat

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.auth0.android.jwt.JWT
import com.bumptech.glide.Glide
import com.catlove.gitcat.model.HomeData
import com.catlove.gitcat.model.HomeModel
import com.catlove.gitcat.model.LogoutModel
import com.catlove.gitcat.model.RefreshTokenModel
import com.catlove.gitcat.retrofit.RetrofitCreator
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    var adapterViewPager: FragmentPagerAdapter? = null
    var token: String = ""
    var handler: Handler?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        //NewToken(context!!)

        Log.e("fragment","create view")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("fragment","view created")

        init()
    }


    fun init(){
        Glide.with(this@HomeFragment).load(R.raw.gif_cat_loading).into(activity?.findViewById<ImageView>(R.id.img_home_cat_loading)!!)
        Glide.with(this@HomeFragment).load(R.raw.gif_loading).into(activity?.findViewById<ImageView>(R.id.img_home_refresh_loading)!!)

        val settings: SharedPreferences = activity!!.getSharedPreferences("gitcat", AppCompatActivity.MODE_PRIVATE)

        if(settings.getBoolean("doAPI",true)){
            newtoken(1)
        }
        //newtoken(1)

        img_btn_score_explain.setOnClickListener {
            val scoreDialog = ScoreDialogFragment()
            scoreDialog.show(fragmentManager!!,"score_fragment")
        }

        //하단 탭 버튼 리스너
        diaryIcon.setOnClickListener { view ->
            val intent = Intent(activity,CollectionActivity::class.java)
            startActivity(intent)
        }

        settingsIcon.setOnClickListener{ view ->
            val intent = Intent(activity,SettingsActivity::class.java)
            startActivity(intent)
        }
        //새로고침 이미지, 텍스트 선택 가능
        img_btn_new_data.setOnClickListener {
            handler?.removeMessages(1000)
            newtoken(2)
        }
        btn_new_data.setOnClickListener {
            handler?.removeMessages(1000)
            newtoken(2)
        }
        //Seekbar 터치 막음
        home_progress.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return true
            }
        })

        //home_progress.progressDrawable = SeekBarDrawable(home_progress.progress.toString())
        val bgProgress = SeekBarDrawable(4)
        home_progress.progressDrawable = bgProgress
    }

    private fun callApi(token: String, check: Int){
        if(check==1){
            activity?.findViewById<ImageView>(R.id.img_home_cat_loading)!!.visibility = View.VISIBLE
        }else{
            activity?.findViewById<ImageView>(R.id.img_home_refresh_loading)!!.visibility = View.VISIBLE
        }

        val call: Call<LogoutModel> = RetrofitCreator.service.getCommitsUpdate(token)
        call.enqueue(
            object : Callback<LogoutModel>{
                override fun onFailure(call: Call<LogoutModel>, t: Throwable) {
                    showErrorPopup("재로그인을 해주세요!",context!!)
                }

                override fun onResponse(call: Call<LogoutModel>, response: Response<LogoutModel>) {
                    if(response.isSuccessful){
                        afterCallApi(token,check)
                    }else{
                        if(response.code()==503){
                            ServerCheckPopup(context!!)
                        }else if(response.code()>=500){
                            showErrorPopup("[네트워크 오류] 재로그인을 해주세요!",context!!)
                        }else{
                            showErrorPopup("재로그인을 해주세요!",context!!)
                        }
                    }
                }
            }
        )
    }
    private fun afterCallApi(token: String,check: Int){
        val settings: SharedPreferences = activity!!.getSharedPreferences("gitcat", AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()
        val call: Call<HomeModel> = RetrofitCreator.service.getHomeMain(token)
        call.enqueue(
            object : Callback<HomeModel>{
                override fun onFailure(call: Call<HomeModel>, t: Throwable) {
                    showErrorPopup("재로그인을 해주세요!",context!!)
                }

                override fun onResponse(call: Call<HomeModel>, response: Response<HomeModel>) {
                    if(check==1){
                        activity?.findViewById<ImageView>(R.id.img_home_cat_loading)!!.visibility = View.GONE
                    }else{
                        activity?.findViewById<ImageView>(R.id.img_home_refresh_loading)!!.visibility = View.GONE
                    }

                    //첫 로그인 시 튜토리얼
                    val settings: SharedPreferences = context!!.getSharedPreferences("gitcat",AppCompatActivity.MODE_PRIVATE)
                    val isFirst = settings.getString("isFirst","")
                    if(isFirst.equals("true")){
                        val editor: SharedPreferences.Editor = settings.edit()
                        editor.putString("isFirst","false")
                        editor.commit()
                        val tuDialog = TuDialogFragment()
                        //튜토리얼
                        tuDialog.setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light)
                        tuDialog.show(fragmentManager!!,"addons_fragment")
                    }
                    if(response.isSuccessful){
                        val data = response.body()?.data
                        if(response.body()==null){
                            nullCat()
                        }else{
                            if (data?.isGraduate!!) {
                                nullCat()
                                //졸업 다이얼로그
                                val graduateDialog = GraduateDialogFragment(data?.catName)
                                graduateDialog.show(fragmentManager!!, "graduate_fragment")
                            }else if(data?.isLeave!!) {
                                nullCat()
                                val leaveCatFragment = LeaveCatFragment(data?.catName)
                                leaveCatFragment.show(fragmentManager!!, "leave_fragment")
                            }else if(data?.isLevelUp!!){
                                homeCat(data)
                                val upgradeDialog = UpgradeDialogFragment(data?.catName)
                                upgradeDialog.show(fragmentManager!!,"upgrade_fragment")
                            }else{
                                homeCat(data)
                            }
                        }
                        editor.putBoolean("doAPI",false)
                        editor.apply()
                    }else{
                        if(response.code()==503){
                            ServerCheckPopup(context!!)
                        }else if(response.code()>=500){
                            showErrorPopup("[네트워크 오류] 재로그인을 해주세요!",context!!)
                        }else{
                            showErrorPopup("재로그인을 해주세요!",context!!)
                        }
                    }
                }
            }
        )
    }
    private fun startTimerTask(ments: ArrayList<String>){
        handler = object: Handler(){
            var index = 0
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                txt_bubble_content.text = ments[index]
                index++
                if(index==ments.size) index = 0
                sendEmptyMessageDelayed(1000,6000)
            }
        }
        handler?.sendEmptyMessage(1000)
    }

    override fun onPause() {
        super.onPause()
        handler?.removeMessages(1000)
    }

    override fun onResume() {
        super.onResume()
        NewToken(context!!)
    }

    private fun homeCat(data: HomeData){
        //홈화면 보여주기
        txt_home_commit_count.text = data?.todayCommitCount.toString()
        //홈 gif 처리
        Glide.with(context!!).load(data?.catImg).into(img_home_cat_gif)
        //닉네임줄
        txt_home_levelname.text = "Lv" + data?.currentLevel.toString() +". " + data?.currentItem + " | "
        txt_home_nickname.text = data?.catName
        //SeekBar
        progressIcon(data?.currentLevel)
        home_progress.max = 100
        home_progress.progress = data?.progressPer
        //첫번째줄
        txt_home_now_score.text = "- 총 " + data?.totalScore.toString() + getString(R.string.home_now_score)
        txt_home_today_score.text = getString(R.string.home_today_score) + " " + data?.todayScore.toString() + ")"
        //두번째줄
        txt_home_next_level_item.text = "Lv" + data?.nextLevel.toString() +". " + data?.nextLevelItem
        txt_home_next_level_score.text = getString(R.string.home_item_score1) + " " + data?.nextLevelScore.toString() + getString(R.string.home_item_score2)
        //세번째줄
        txt_home_gradu_score.text = getString(R.string.home_gradu_score) + " " + data?.graduScore.toString() + getString(R.string.home_item_score2)
        //멘트 바꿔주기
        startTimerTask(data?.ments)
    }

    private fun progressIcon(level: Int){
        if(level==1) home_progress.thumb = context!!.getDrawable(R.drawable.ic_pencil)
        else if(level==2) home_progress.thumb = context!!.getDrawable(R.drawable.ic_mac)
        else if(level==3) home_progress.thumb = context!!.getDrawable(R.drawable.ic_macpro)
        else if(level==4) home_progress.thumb = context!!.getDrawable(R.drawable.ic_gaugebarricon)
    }

    private fun nullCat(){
        cl_home_info_content.visibility = View.INVISIBLE
        btn_home_choose_cat_again.visibility = View.VISIBLE
        txt_bubble_content.visibility = View.GONE
        Glide.with(context!!).load(R.drawable.img_cat_null).into(img_home_cat_gif)
        txt_home_commit_count.text = "-"
        btn_home_choose_cat_again.setOnClickListener {
            val intent = Intent(context, Info4Activity::class.java)
            startActivity(intent)
        }
    }

    private fun newtoken(check: Int){
        val settings: SharedPreferences = context!!.getSharedPreferences("gitcat", AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()

        if(Date(settings.getLong("expire", 0)) < Calendar.getInstance().time){ //현재시간이 만료시간을 뛰어넘을때
            val call: Call<RefreshTokenModel> = RetrofitCreator.service.getRefreshToken(settings.getString("refreshToken","")!!)
            call.enqueue(
                object : Callback<RefreshTokenModel> {
                    override fun onFailure(call: Call<RefreshTokenModel>, t: Throwable) {
                        Log.e("*+*+", "error: $t")
                        showErrorPopup("재로그인을 해주세요!",context!!)
                    }

                    override fun onResponse(
                        call: Call<RefreshTokenModel>,
                        response: Response<RefreshTokenModel>
                    ) {
                        if(response.isSuccessful){
                            val data = response.body()!!

                            editor.putString("token",data.data.accessToken)
                            editor.putString("refreshToken",data.data.refreshToken)

                            val jwt = JWT(data.data.accessToken)
                            val issuedAt = jwt.issuedAt//시작
                            val expiresAt = jwt.expiresAt//마감

                            editor.putLong("expire",expiresAt!!.time)
                            editor.apply()

                            Log.e("token","refresh token")
                            callApi(settings.getString("token","")!!,check)
                        }else{
                            if(response.code()==502){
                                ServerCheckPopup(context!!)
                            }else{
                                showErrorPopup("[오류] 재로그인을 해주세요!",context!!)
                            }

                        }
                    }
                }
            )
        }else{
            Log.e("token","no refresh token")
            callApi(settings.getString("token","")!!,check)
        }
    }
    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }


}
