package com.example.gitcat

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        view.diaryIcon.setOnClickListener { view ->

            Toast.makeText(activity, "제발", Toast.LENGTH_LONG).show()
            //val intent = Intent(activity,DiaryActivity::class.java)
            //startActivity(intent)
        }

        view.commitCount.setOnClickListener { view ->
            Toast.makeText(activity, "제발", Toast.LENGTH_LONG).show()
        }

        return view
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }


}
