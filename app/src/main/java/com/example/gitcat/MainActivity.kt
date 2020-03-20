package com.example.gitcat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.net.URL
import org.json.JSONObject
import com.google.gson.Gson
import android.R.attr.data
import android.app.Activity
import android.webkit.*
import java.util.*
import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.webkit.JavascriptInterface
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log.d
import android.view.WindowManager
import android.webkit.CookieSyncManager.createInstance
import android.webkit.CookieSyncManager.getInstance
import androidx.core.content.ContextCompat.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import okhttp3.internal.userAgent
import okio.Utf8
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_main)

        startButton.setOnClickListener{


            webview.visibility = View.VISIBLE
            startButton.visibility = View.GONE

            val myWebView: WebView = findViewById(R.id.webview)
            val myURL = "https://a.gitcat.app/api/auth/github"

            myWebView.clearCache(true)
            myWebView.clearHistory()
            myWebView.settings.javaScriptEnabled = true

            myWebView.settings.setSupportZoom(true)
            myWebView.settings.domStorageEnabled = true

            myWebView.addJavascriptInterface(WebPasser(this,myWebView),"java")
            myWebView.loadUrl(myURL)
            myWebView.webViewClient = object : WebViewClient(){
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

                    view?.loadUrl(url)
                    return true
                }
            }
        }
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
        val jsonString = "{"+decryptedByteValue.substring(20)
        val jsonObject = JSONObject(jsonString)

        if(jsonObject.getString("isFirst").compareTo("true")==0){
            val intent = Intent(mContext,Info1Activity::class.java)
            mContext?.startActivity(intent)
        }
    }
}


