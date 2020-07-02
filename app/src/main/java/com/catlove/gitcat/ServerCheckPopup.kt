package com.catlove.gitcat

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

fun ServerCheckPopup(context: Context){
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflater.inflate(R.layout.error_popup,null)
    val textView: TextView = view.findViewById(R.id.error_text)
    val textView2: TextView = view.findViewById(R.id.error_title)
    textView.text = "더 나은 서비스를 위해 서버 점검 중입니다:)"
    textView2.text = "서버 점검"

    val alertDialog = AlertDialog.Builder(context)
        .create()

    val errorOk = view.findViewById<Button>(R.id.error_ok)
    errorOk.setOnClickListener {
        alertDialog.dismiss()
    }

    alertDialog.setView(view)
    alertDialog.show()
}