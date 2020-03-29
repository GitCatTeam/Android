package com.example.gitcat


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gitcat.model.ChooseCatBasicModel
import kotlinx.android.synthetic.main.fragment_basic_cat.*

/**
 * A simple [Fragment] subclass.
 */
class BasicCatFragment : Fragment() {

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
        chooseCatRecycleradapter = ChooseCatRecyclerAdapter(context!!)
        chooseCatRecycleradapter.data = listOf(
            ChooseCatBasicModel("https://avatars2.githubusercontent.com/u/40830852?v=4"),
            ChooseCatBasicModel("https://avatars2.githubusercontent.com/u/40830852?v=4"),
            ChooseCatBasicModel("https://avatars2.githubusercontent.com/u/40830852?v=4"),
            ChooseCatBasicModel("https://avatars2.githubusercontent.com/u/40830852?v=4")
        )
        recycler_choose_cat_basic.apply{
            adapter= chooseCatRecycleradapter
            layoutManager = GridLayoutManager(context,3)
        }
        chooseCatRecycleradapter.notifyDataSetChanged()
    }
}
