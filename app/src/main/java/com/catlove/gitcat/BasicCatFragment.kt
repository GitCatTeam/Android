package com.catlove.gitcat


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.catlove.gitcat.model.ChooseCatBasicModel
import kotlinx.android.synthetic.main.activity_info4.*
import kotlinx.android.synthetic.main.fragment_basic_cat.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class BasicCatFragment(private val data: ArrayList<ChooseCatBasicModel>) : Fragment() {

    lateinit var chooseCatRecycleradapter: ChooseCatRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_cat, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
    }

    private fun init(){
        chooseCatRecycleradapter = ChooseCatRecyclerAdapter(context!!,activity?.btn_choose_cat_next!!)
        recycler_choose_cat_basic.apply{
            adapter= chooseCatRecycleradapter
            layoutManager = GridLayoutManager(context,3)
            addItemDecoration(ItemDecoration(14,14))
        }
        chooseCatRecycleradapter.data = data
        chooseCatRecycleradapter.notifyDataSetChanged()
    }
}
