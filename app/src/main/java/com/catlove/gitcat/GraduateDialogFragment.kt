package com.catlove.gitcat

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_graduate_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class GraduateDialogFragment(val name: String) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_graduate_dialog, container, false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
        dialog?.window?.setGravity(Gravity.CENTER)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }
    fun init(){
        txt_graduate_nickname.text=name
        btn_graduate_check.setOnClickListener {
            dismiss()
            val intent = Intent(context,CollectionActivity::class.java)
            startActivity(intent)
        }
        img_btn_graduate_exit.setOnClickListener {
            dismiss()
        }
    }
}
