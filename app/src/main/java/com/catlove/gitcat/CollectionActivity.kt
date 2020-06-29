package com.catlove.gitcat

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catlove.gitcat.model.CatsCollectionModel
import com.catlove.gitcat.retrofit.RetrofitCreator
import kotlinx.android.synthetic.main.activity_collection.*
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
        val settings: SharedPreferences = getSharedPreferences("gitcat", MODE_PRIVATE)

        NewToken(this)
        val call: Call<CatsCollectionModel> = RetrofitCreator.service.getCatsCollection(settings.getString("token",""))
        call.enqueue(
            object : Callback<CatsCollectionModel> {
                override fun onFailure(call: Call<CatsCollectionModel>, t: Throwable) {
                    Log.e("*+*+", "error: $t")
                    showErrorPopup("재로그인을 해주세요!",this@CollectionActivity)
                }

                override fun onResponse(
                    call: Call<CatsCollectionModel>,
                    response: Response<CatsCollectionModel>
                ) {
                    if(response.isSuccessful){

                        val data = response.body()!!

                        if(data.data.isEmpty()){
                            noCollection.visibility = View.VISIBLE
                            collection_scroll.visibility = View.GONE
                        }else{//데이터가 들어있다면
                            noCollection.visibility = View.GONE
                            collection_scroll.visibility = View.VISIBLE

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
                        }//데이터 들어있다면 end

                    }else{
                        if(response.code()>=500){
                            showErrorPopup("[네트워크 오류] 재로그인을 해주세요!",this@CollectionActivity)
                        }else{
                            showErrorPopup("재로그인을 해주세요!",this@CollectionActivity)
                        }
                    }
                }
            }
        )



    }
}
