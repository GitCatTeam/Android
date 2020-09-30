package com.catlove.gitcat

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.catlove.gitcat.model.LogoutModel
import com.catlove.gitcat.retrofit.RetrofitCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun showRepoPopup(context: Context){
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val settings: SharedPreferences = context.getSharedPreferences("gitcat", AppCompatActivity.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = settings.edit()
    val view = inflater.inflate(R.layout.repo_popup,null)

    val alertDialog = AlertDialog.Builder(context)
        .create()

    val repoYes = view.findViewById<Button>(R.id.repo_yes)
    val repoNo = view.findViewById<Button>(R.id.repo_no)

    alertDialog.setView(view)
    alertDialog.show()

    repoYes.setOnClickListener {//레포 그대로
        alertDialog.dismiss()
        val intent = Intent(context,HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    repoNo.setOnClickListener {//다시 되돌리기
        alertDialog.dismiss()

        val call: Call<LogoutModel> = RetrofitCreator.service.putAuthScope(settings.getString("token","")!!)
        call.enqueue(
            object : Callback<LogoutModel> {
                override fun onFailure(call: Call<LogoutModel>, t: Throwable) {
                    Log.e("*+*+", "error: $t")
                    showErrorPopup("재로그인을 해주세요!",context)
                }

                override fun onResponse(
                    call: Call<LogoutModel>,
                    response: Response<LogoutModel>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()!!

                        if(data.message=="scope 복귀 성공"){
                            var intent = Intent(context,RepoAuthActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(intent)
                        }else{
                            showErrorPopup("[네크워크 오류] 재로그인을 해주세요!",context)
                        }

                    }else{
                        if(response.code()>=500){
                            showErrorPopup("[네트워크 오류] 재로그인을 해주세요!",context)
                        }else{
                            showErrorPopup("재로그인을 해주세요!",context)
                        }
                    }
                }
            }
        )
    }
}