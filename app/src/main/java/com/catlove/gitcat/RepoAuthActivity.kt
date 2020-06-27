package com.catlove.gitcat

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_repo_auth.*

class RepoAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_auth)

        backButton.setOnClickListener{
            onBackPressed()
        }

        public_radio.setOnClickListener {
            //버튼 활성화
            buttonGo.isEnabled = true
            buttonGo.setBackgroundResource(R.drawable.info_next_after)

            //색깔변화
            public_radio_img.visibility = View.VISIBLE
            public_radio_title.setTextColor(Color.parseColor("#88cdf6"))
            public_radio_content.setTextColor(Color.parseColor("#646464"))
            public_radio.setBackgroundResource(R.drawable.info_list_checked)
            //색깔변화2
            private_radio_img.visibility = View.GONE
            private_radio_title.setTextColor(Color.parseColor("#c0c0c0"))
            private_radio_content.setTextColor(Color.parseColor("#c0c0c0"))
            private_radio.setBackgroundResource(R.drawable.info_list)

            repo_ex.text = "** Private Repository의 커밋은 반영되지 않습니다 **"
        }

        private_radio.setOnClickListener {
            //버튼 활성화
            buttonGo.isEnabled = true
            buttonGo.setBackgroundResource(R.drawable.info_next_after)

            //색깔변화
            private_radio_img.visibility = View.VISIBLE
            private_radio_title.setTextColor(Color.parseColor("#88cdf6"))
            private_radio_content.setTextColor(Color.parseColor("#646464"))
            private_radio.setBackgroundResource(R.drawable.info_list_checked)
            //색깔변화2
            public_radio_img.visibility = View.GONE
            public_radio_title.setTextColor(Color.parseColor("#c0c0c0"))
            public_radio_content.setTextColor(Color.parseColor("#c0c0c0"))
            public_radio.setBackgroundResource(R.drawable.info_list)

            repo_ex.text = "** Private Repository 접근을 위해 읽기/쓰기가 포함된 **\n" +
                            "Full-Access 권한이 요청되지만, 실제로는 읽기 권한만 활용됩니다."
        }

        //등록완료 버튼을 누르면
        buttonGo.setOnClickListener {

        }

//        repo_radio.setOnCheckedChangeListener(
//            RadioGroup.OnCheckedChangeListener { group, checkedId ->
//                //val radio: RadioButton = findViewById(checkedId)
//                if(checkedId==2131362094){//public 눌렸을 때
//                    repo_ex.text = "** Private Repository의 커밋은 반영되지 않습니다 **"
//                }else{//private 눌렸을 때
//                    repo_ex.text = "** Private Repository 접근을 위해 읽기/쓰기가 포함된 **\n" +
//                            "Full-Access 권한이 요청되지만, 실제로는 읽기 권한만 활용됩니다."
//                }
//
//
//                //devCareer = radio.text.toString()
//
//                //버튼 활성화
//                buttonGo.isEnabled = true
//                buttonGo.setBackgroundResource(R.drawable.info_next_after)
//            })


    }
}
