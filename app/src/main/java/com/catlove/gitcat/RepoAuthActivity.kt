package com.catlove.gitcat

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Base64
import android.util.Log
import android.util.Log.d
import android.view.View
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.RadioButton
import android.widget.RadioGroup
import com.auth0.android.jwt.JWT
import com.catlove.gitcat.model.DeviceTokenModel
import com.catlove.gitcat.retrofit.RetrofitCreator
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import kotlinx.android.synthetic.main.activity_repo_auth.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.UnsupportedEncodingException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class RepoAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_auth)

        var pp:String = ""
        val settings: SharedPreferences = getSharedPreferences("gitcat", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()

        backButton.setOnClickListener{
            onBackPressed()
        }

        public_radio.setOnClickListener {
            pp = "public"
            //버튼 활성화
            buttonGo.isEnabled = true
            buttonGo.setBackgroundResource(R.drawable.info_next_after)

            //색깔변화
            public_radio_img.visibility = View.VISIBLE
            public_radio_title.setTextColor(Color.parseColor("#88cdf6"))
            public_radio_content.setTextColor(Color.parseColor("#646464"))
            public_radio.setBackgroundResource(R.drawable.info_list_checked)
            //색깔변화2
            private_radio_img.visibility = View.GONE
            private_radio_title.setTextColor(Color.parseColor("#c0c0c0"))
            private_radio_content.setTextColor(Color.parseColor("#c0c0c0"))
            private_radio.setBackgroundResource(R.drawable.info_list)

            repo_ex.text = "** Private Repository의 커밋은 반영되지 않습니다 **"
        }

        private_radio.setOnClickListener {
            pp = "private"
            //버튼 활성화
            buttonGo.isEnabled = true
            buttonGo.setBackgroundResource(R.drawable.info_next_after)

            //색깔변화
            private_radio_img.visibility = View.VISIBLE
            private_radio_title.setTextColor(Color.parseColor("#88cdf6"))
            private_radio_content.setTextColor(Color.parseColor("#646464"))
            private_radio.setBackgroundResource(R.drawable.info_list_checked)
            //색깔변화2
            public_radio_img.visibility = View.GONE
            public_radio_title.setTextColor(Color.parseColor("#c0c0c0"))
            public_radio_content.setTextColor(Color.parseColor("#c0c0c0"))
            public_radio.setBackgroundResource(R.drawable.info_list)

            repo_ex.text = "** Private Repository 접근을 위해 읽기/쓰기가 포함된 **\n" +
                            "Full-Access 권한이 요청되지만, 실제로는 읽기 권한만 활용됩니다."
        }

        //등록완료 버튼을 누르면
        buttonGo.setOnClickListener {
            webview.visibility = View.VISIBLE
            buttonGo.visibility = View.GONE

            val myWebView: WebView = findViewById(R.id.webview)
            var myURL = ""

            if(pp=="public"){//public
                myURL = "https://a.gitcat.app/api/auth/github-public"
            }else{//private
                myURL = "https://a.gitcat.app/api/auth/github"
            }

            editor.putString("repoAuth",pp)
            editor.commit()

            //캐시 삭제
            CookieManager.getInstance().removeSessionCookies(null)
            CookieManager.getInstance().removeAllCookies(null)

            myWebView.settings.javaScriptEnabled = true

            myWebView.settings.setSupportZoom(true)
            myWebView.settings.domStorageEnabled = true

            myWebView.addJavascriptInterface(WebPasser2(this,myWebView),"java")
            myWebView.loadUrl(myURL)
            myWebView.webViewClient = object : WebViewClient(){
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.clearCache(true)
                    view?.clearFormData()
                    view?.clearHistory()
                    view?.loadUrl(url)
                    return true
                }
            }
        }

    }
}

class WebPasser2(val mContext: Activity?, val mWebView: WebView?) {

    @JavascriptInterface
    fun sendAuthInfo(datas: String?, msg: String?){
        val ivb = byteArrayOf(
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00
        )
        val secretkey: String = "huandyoonandyoungandheeisthebest"
        var textBytes : ByteArray = Base64.decode(datas!!,0)
        var ivs = IvParameterSpec(ivb)
        var newKey = SecretKeySpec(secretkey.toByteArray(Charsets.UTF_8),"AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.DECRYPT_MODE,newKey,ivs)
        val decryptedByteValue = String(cipher.doFinal(textBytes),Charsets.UTF_8)
        val jsonString = "{\""+decryptedByteValue.substring(20)

        val jsonObject = JSONObject(jsonString)

        val settings: SharedPreferences = mContext!!.getSharedPreferences("gitcat",
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = settings.edit()
        editor.putString("githubId",jsonObject.getString("githubId"))
        editor.putString("profileImg",jsonObject.getString("profileImg"))
        editor.putString("token",jsonObject.getString("token"))
        editor.putString("refreshToken",jsonObject.getString("refreshToken"))
        editor.putString("isFirst",jsonObject.getString("isFirst"))
        editor.putBoolean("newPeople",false)
        editor.putBoolean("isMatchScope",jsonObject.getBoolean("isMatchScope"))

        Log.e("token",jsonObject.getString("token"))

        //디바이스토큰 넣어주기
        var deviceToken : String = ""
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(mContext, OnSuccessListener<InstanceIdResult> {
            deviceToken = it.token
        })
        //uuid(androidid) 찾기
        var androidId = "" + android.provider.Settings.Secure.getString(mContext.contentResolver,android.provider.Settings.Secure.ANDROID_ID)
        editor.putString("androidId",androidId)
        editor.putString("deviceToken",deviceToken)

        val dt = DeviceTokenModel(deviceToken,androidId)
        val call: Call<DeviceTokenModel> = RetrofitCreator.service.putDeviceToken(jsonObject.getString("token"),dt)
        call.enqueue(
            object : Callback<DeviceTokenModel> {
                override fun onFailure(call: Call<DeviceTokenModel>, t: Throwable) {
                    Log.e("*+*+", "error: $t")
                    showErrorPopup("재로그인을 해주세요!",mContext)
                }

                override fun onResponse(
                    call: Call<DeviceTokenModel>,
                    response: Response<DeviceTokenModel>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()!!
                        d("*+*+디바이스토큰","성공적")
                        editor.putBoolean("alarm",true)

                    }else{
                        if(response.code()==503){
                            ServerCheckPopup(mContext)
                        }else if(response.code()>=500){
                            showErrorPopup("[네트워크 오류] 재로그인을 해주세요!",mContext)
                        }else{
                            showErrorPopup("재로그인을 해주세요!",mContext)
                        }
                    }
                }
            }
        )


        val jwt = JWT(jsonObject.getString("token"))
        val issuedAt = jwt.issuedAt//시작
        val expiresAt = jwt.expiresAt//마감
        editor.putLong("expire",expiresAt!!.time)
        editor.commit()

        if(jsonObject.getString("isFirst").compareTo("true")==0){
            //사용자가 처음일 때
            val intent = Intent(mContext,Info1Activity::class.java)
            mContext?.startActivity(intent)
        }else{
            //사용자가 처음이 아닐 때
            if(jsonObject.getBoolean("isMatchScope")==false){//레포 달라
                showRepoPopup(mContext)
            }else{//레포 똑같아
                val intent = Intent(mContext,HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext?.startActivity(intent)
            }

        }
    }
}


