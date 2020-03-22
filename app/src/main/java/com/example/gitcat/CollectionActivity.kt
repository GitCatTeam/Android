package com.example.gitcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitcat.model.CatsCollectionModel
import com.example.gitcat.retrofit.RetrofitCreator
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.collection_data_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectionActivity : AppCompatActivity() {

    var collectionList = arrayListOf<Collection>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)

        backButton.setOnClickListener {
            onBackPressed()
        }

        var cName:String = ""
        var cDoing:String = ""

        /*FIXME: Token 수정*/
        val call: Call<CatsCollectionModel> = RetrofitCreator.service.getCatsCollection("token")
        call.enqueue(
            object : Callback<CatsCollectionModel> {
                override fun onFailure(call: Call<CatsCollectionModel>, t: Throwable) {
                    Log.e("*+*+", "error: $t")
                }

                override fun onResponse(
                    call: Call<CatsCollectionModel>,
                    response: Response<CatsCollectionModel>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()!!

                        for(c in data.data){
                            cName = "- 이름 : "+c.name
                            cDoing = "- "+c.endingMent
                            collectionList.add(Collection(cName,cDoing,c.isMedal,c.img))
                        }

                        val collection_recyclerview = findViewById(R.id.collection_recyclerview) as RecyclerView
                        val listAdapter = CollectionAdapter(this@CollectionActivity,collectionList)
                        collection_recyclerview.adapter = listAdapter
                        collection_recyclerview.layoutManager = GridLayoutManager(this@CollectionActivity,2)

                        listAdapter.notifyDataSetChanged()
                    }
                }
            }
        )



    }
}
