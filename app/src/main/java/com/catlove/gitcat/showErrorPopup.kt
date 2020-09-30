package com.catlove.gitcat

import android.app.AlertDialog
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

fun showErrorPopup(errorMessage: String, context: Context){
    val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflater.inflate(R.layout.error_popup,null)
    val textView: TextView = view.findViewById(R.id.error_text)
    textView.text = errorMessage

    val alertDialog = AlertDialog.Builder(context)
        .create()

    val errorOk = view.findViewById<Button>(R.id.error_ok)

    alertDialog.setView(view)
    alertDialog.show()

    errorOk.setOnClickListener {
        alertDialog.dismiss()
        val settings = context.getSharedPreferences("gitcat", AppCompatActivity.MODE_PRIVATE)
        settings.edit().clear().commit() //내부 db 초기화
        val intent = Intent(context,MainActivity::class.java)//재로그인 위해서
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }


}