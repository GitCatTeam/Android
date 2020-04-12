package com.example.gitcat


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gitcat.model.ChooseCatBasicModel
import com.example.gitcat.model.DataModel
import com.example.gitcat.retrofit.RetrofitCreator
import kotlinx.android.synthetic.main.fragment_basic_cat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

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
        recycler_choose_cat_basic.apply{
            adapter= chooseCatRecycleradapter
            layoutManager = GridLayoutManager(context,3)
        }

        //통신
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJHaXRDYXQiLCJzdWIiOiJvbmlzbGV5IiwiaWF0IjoxNTg1MDY2MDI0MTYxLCJleHAiOjE1ODUxNTI0MjQxNjF9.YkZxDrK4oeAf4olQEyJww8KoVgKRyFcUnklq3ZxA4qE"
        val call: Call<DataModel> = RetrofitCreator.service.getCats(token)
        call.enqueue(object : Callback<DataModel>{
            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                showErrorPopup(t.toString(),activity!!)
            }

            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if(response.isSuccessful) {
                    val data = response.body()!!.data
                    chooseCatRecycleradapter.data = data.normal
                    chooseCatRecycleradapter.notifyDataSetChanged()
                }else{
                    showErrorPopup(response.message(),activity!!)
                }
            }
        })
    }
}
