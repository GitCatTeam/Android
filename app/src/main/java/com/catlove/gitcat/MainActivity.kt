package com.catlove.gitcat

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.webkit.*
import android.webkit.JavascriptInterface
import com.auth0.android.jwt.JWT
import java.util.regex.Pattern
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_main)

        val settings: SharedPreferences = getSharedPreferences("gitcat", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()
        editor.putBoolean("doAPI",true)
        editor.apply()

        startButton.setOnClickListener{

            if(settings.getBoolean("newPeople",true)||settings.getBoolean("newPeople",true)==null){//처음이거나, 로그아웃, 회원탈퇴를 했을 경우
                var intent = Intent(this,RepoAuthActivity::class.java)
                startActivity(intent)
            }else{//오류 떴을 경우(newPeople이 false)
                webview.visibility = View.VISIBLE
                startButton.visibility = View.GONE

                val myWebView: WebView = findViewById(R.id.webview)
                var myURL = ""
                if(settings.getString("repoAuth","private")=="private"){//private일때
                    myURL = "https://a.gitcat.app/api/auth/github"
                }else{//public 일때
                    myURL = "https://a.gitcat.app/api/auth/github-public"
                }


                //캐시 삭제
                CookieManager.getInstance().removeSessionCookies(null)
                CookieManager.getInstance().removeAllCookies(null)

                myWebView.settings.javaScriptEnabled = true

                myWebView.settings.setSupportZoom(true)
                myWebView.settings.domStorageEnabled = true

                myWebView.addJavascriptInterface(WebPasser(this,myWebView),"java")
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

    override fun onDestroy() {
        super.onDestroy()
    }
}

class WebPasser(val mContext: Activity?, val mWebView: WebView?) {

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

        val settings: SharedPreferences = mContext!!.getSharedPreferences("gitcat", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings.edit()
        editor.putString("githubId",jsonObject.getString("githubId"))
        editor.putString("profileImg",jsonObject.getString("profileImg"))
        editor.putString("token",jsonObject.getString("token"))
        editor.putString("refreshToken",jsonObject.getString("refreshToken"))
        editor.putString("isFirst",jsonObject.getString("isFirst"))
        editor.putBoolean("isMatchScope",jsonObject.getBoolean("isMatchScope"))
        editor.putBoolean("newPeople",false)

        Log.e("token",jsonObject.getString("token"))
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
            val intent = Intent(mContext,HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            mContext?.startActivity(intent)
        }
    }
}


