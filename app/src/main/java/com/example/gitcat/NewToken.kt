package com.example.gitcat

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.jwt.JWT
import com.example.gitcat.model.RefreshTokenModel
import com.example.gitcat.retrofit.RetrofitCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

fun NewToken(context: Context){

    val settings: SharedPreferences = context.getSharedPreferences("gitcat", AppCompatActivity.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = settings.edit()

    if(Date(settings.getLong("expire", 0)) < Calendar.getInstance().time){ //현재시간이 만료시간을 뛰어넘을때
        val call: Call<RefreshTokenModel> = RetrofitCreator.service.getRefreshToken(settings.getString("refreshToken",""))
        call.enqueue(
            object : Callback<RefreshTokenModel> {
                override fun onFailure(call: Call<RefreshTokenModel>, t: Throwable) {
                    Log.e("*+*+", "error: $t")
                    showErrorPopup(t.toString(),context)
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

                    }else{
                        showErrorPopup("["+response.code().toString()+"] "+response.message(),context)
                    }
                }
            }
        )
    }

}