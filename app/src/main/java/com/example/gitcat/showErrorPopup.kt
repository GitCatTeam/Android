package com.example.gitcat

import android.app.AlertDialog
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

fun showErrorPopup(errorMessage: String, context: Context){
    val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflater.inflate(R.layout.error_popup,null)
    val textView: TextView = view.findViewById(R.id.error_text)
    textView.text = errorMessage

    val alertDialog = AlertDialog.Builder(context)
        .setTitle("Error")
        .create()

    val errorOk = view.findViewById<Button>(R.id.error_ok)
    errorOk.setOnClickListener {
        alertDialog.dismiss()
    }

    alertDialog.setView(view)
    alertDialog.show()
}