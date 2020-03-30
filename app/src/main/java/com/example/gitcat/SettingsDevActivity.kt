package com.example.gitcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_settings_dev.*

class SettingsDevActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_dev)

        devBack.setOnClickListener{
            onBackPressed()
        }

        //TODO: 데이터 넣기~
        //var settingDevList = listOf<SettingsDev>((png 파일,"Server Developer","깜냥이","콘솔 창에 푹 빠져 깜장냥이가 되었어요!\n" +
        //        "홍차와 스콘을 참 좋아한답니다:)"))

        //FIXME: 임시~
        var settingDevList = arrayListOf<SettingsDev>()

        val dev_recyclerview = findViewById(R.id.dev_recyclerView) as RecyclerView
        val listAdapter = SettingsDevAdapter(this@SettingsDevActivity,settingDevList)
        dev_recyclerview.adapter = listAdapter
        dev_recyclerview.layoutManager = LinearLayoutManager(this@SettingsDevActivity)

        listAdapter.notifyDataSetChanged()
    }
}
