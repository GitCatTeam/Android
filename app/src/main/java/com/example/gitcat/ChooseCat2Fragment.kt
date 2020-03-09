package com.example.gitcat

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.yuxingxin.library.MultiRadioGroup
import kotlinx.android.synthetic.main.activity_info4.*
import kotlinx.android.synthetic.main.fragment_choose_cat2.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChooseCat2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var dataPasser: OnDataPass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_choose_cat2, container, false)

        //투명도 주기
//        val cat1D = cat1.compoundDrawables
//        cat1D[0].alpha = 50

        //고양이 버튼 누르면
        var catId: Int = 0
        var radioGroupCat = view.findViewById<MultiRadioGroup>(R.id.radioGroupCat)
        radioGroupCat.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = view.findViewById(checkedId)
            catId = radio.id

            passData(catId)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    fun passData(catId: Int){
        dataPasser.onDataPass(catId)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChooseCat2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
