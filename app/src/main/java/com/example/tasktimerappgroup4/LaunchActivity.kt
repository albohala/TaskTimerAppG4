package com.example.tasktimerappgroup4

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tasktimerappgroup4.Activity.MainActivity

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        var launchShared = getSharedPreferences("start", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = launchShared.edit()
        val isFirst = launchShared.getBoolean("first", true)
        Log.d("editor", " $isFirst")

        //show instructions if first start
        if (isFirst) {
            //show instructions
            //set to false
            editor.putBoolean("first", false)
            editor.apply()
            Log.d("editor2", "$editor, $isFirst")
        } else if(!isFirst){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}