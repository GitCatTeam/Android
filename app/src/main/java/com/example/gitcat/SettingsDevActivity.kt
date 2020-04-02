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

        var settingDevList = arrayListOf<SettingsDev>()

        settingDevList.add(
            SettingsDev(getDrawable(R.drawable.img_soyoung),"Server Developer","깜냥이","콘솔 창에 푹 빠져 깜장냥이가 되었어요!\n" +
                    "홍차와 스콘을 참 좋아한답니다:)")
        )
        settingDevList.add(
            SettingsDev(getDrawable(R.drawable.img_yeji),"Server Developer","비올렛","저는 클린-코드를 좋아하는 백엔드 개발자에요.\n" +
                    "항상 더 나은 개발을 하기 위해 노오력을 하고 있죠!")
        )
        settingDevList.add(
            SettingsDev(getDrawable(R.drawable.img_yoonyoung),"IOS Developer","슈크림","개발을 하는 동안 수많은 고양씨와 함께해서 정말\n" +
                    "행복했어요. 아 물론 저도 고양이에옹 =^ㅅ^=")
        )
        settingDevList.add(
            SettingsDev(getDrawable(R.drawable.img_jihu),"Android Developer","후니","안냐세요 후니에요~ 모두 안드로이드 핸드폰을\n" +
                    "세 번 문지르고 '후니야~'를 외쳐 주세요. 뾰로롱!")
        )
        settingDevList.add(
            SettingsDev(getDrawable(R.drawable.img_hyesun),"Android Developer","바바리","고독한 안드로이드 개발자 바바리에요.\n"+
                    "차근차근 꾸준히 발전하는 중이랍니다:)")
        )
        settingDevList.add(
            SettingsDev(getDrawable(R.drawable.img_juhee),"Designer","얼룩냥이","디자인을 좋아하지만 아직 많이 서툰 아마추어에요.\n" +
                    "부족하지만 예쁘게 봐주세옹='ㅅ'=")
        )

        val dev_recyclerview = findViewById(R.id.dev_recyclerView) as RecyclerView
        val listAdapter = SettingsDevAdapter(this@SettingsDevActivity,settingDevList)
        dev_recyclerview.adapter = listAdapter
        dev_recyclerview.layoutManager = LinearLayoutManager(this@SettingsDevActivity)

        listAdapter.notifyDataSetChanged()
    }
}
