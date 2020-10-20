package com.catlove.gitcat

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_hide_cat_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class HideCatDialogFragment(private val profileImg: String, private val description: String) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hide_cat_dialog, container, false)

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
        Glide.with(this).load(profileImg).into(img_hide_cat)
        //TODO: 영어버전일때
        var txt:List<String> = description.split("면")
        txt_hide_cat_content.text = txt[0] + "면" + "\n" + txt[1]

        img_btn_hide_cat_close.setOnClickListener {
            dismiss()
        }
    }
}
