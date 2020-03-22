package com.example.gitcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitcat.model.CatsCollectionModel
import com.example.gitcat.retrofit.RetrofitCreator
import kotlinx.android.synthetic.main.activity_diary.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiaryActivity : AppCompatActivity() {

    var diaryList = arrayListOf<Diary>(
        Diary("냥이","Java","우수한 성적으로 대기업 취직"),
        Diary("냥냥","HTML","졸업 후 취업을 포기하고 치킨집 운영"),
        Diary("철수","CSS","우수한 성적으로 카카오 들어감"),
        Diary("영희","Kotlin","성공한 개발자가 됨"),
        Diary("깃캣","C","서울역 길거리에 앉아버림"),
        Diary("후후","Swift","우수한 성적으로 대기업 취직")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        backButton.setOnClickListener {
            onBackPressed()
        }

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


                    }
                }
            }
        )
        val diary_recyclerview = findViewById(R.id.diary_recyclerview) as RecyclerView
        val listAdapter = DiaryAdapter(this,diaryList)
        diary_recyclerview.adapter = listAdapter
        diary_recyclerview.layoutManager = GridLayoutManager(this,2)

        listAdapter.notifyDataSetChanged()

    }
}
