package com.catlove.gitcat

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView

fun ServerCheckPopup(errorMessage: String, context: Context){
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflater.inflate(R.layout.error_popup,null)
    val textView: TextView = view.findViewById(R.id.error_text)
    textView.text = "서버 점검 기간입니다. ( )" //errorMessage

    val alertDialog = AlertDialog.Builder(context)
        .setTitle("서버 점검")
        .create()

    val errorOk = view.findViewById<Button>(R.id.error_ok)
    errorOk.setOnClickListener {
        alertDialog.dismiss()
    }

    alertDialog.setView(view)
    alertDialog.show()
}