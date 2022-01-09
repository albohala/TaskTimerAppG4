package com.example.tasktimerappgroup4

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_launch.*

class launchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        var launchShared = getSharedPreferences("start", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = launchShared.edit()
        val isFirst = launchShared.getBoolean("first",true)
        frag_instruction.visibility=View.INVISIBLE
        Log.d("editor"," $isFirst")

        //show instructions if first start
        if (isFirst){
            //show instructions
            frag_instruction.visibility= View.VISIBLE

            //set to false
            launchShared.edit().putBoolean("first", false)
            Log.d("editor2","$editor, $isFirst")
        }
        //        else{
//            frag_instruction.visibility=View.GONE
//
//        }
    }
}