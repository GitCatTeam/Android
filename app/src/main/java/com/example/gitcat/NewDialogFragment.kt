package com.example.gitcat

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitcat.model.ChooseCatNewModel
import kotlinx.android.synthetic.main.fragment_new_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class NewDialogFragment(private val data: ArrayList<ChooseCatNewModel>) : DialogFragment() {
    private lateinit var recyclerAdapter: NewCatRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_dialog, container, false)

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
        //
        img_btn_new_cat_close.setOnClickListener {
            dismiss()
        }
        //recycler
        recyclerAdapter = NewCatRecyclerAdapter(context!!)
        recycler_new_cat.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        }
        recyclerAdapter.data = data
        recyclerAdapter.notifyDataSetChanged()
    }
}
