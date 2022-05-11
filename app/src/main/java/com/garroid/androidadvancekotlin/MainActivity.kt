package com.garroid.androidadvancekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("GaryNote", "Done")
        val textInputButton: Button = findViewById(R.id.textInputBtn)
        textInputButton.setOnClickListener(this)
        val geoquizBtn: Button = findViewById(R.id.geoquizBtn)
        geoquizBtn.setOnClickListener(this)
        val drawerBtn: Button = findViewById(R.id.navigationDrawerBtn)
        drawerBtn.setOnClickListener(this)
        val eventBusBtn: Button = findViewById(R.id.eventBusBtn)
        eventBusBtn.setOnClickListener(this)
        val rxJavaBtn: Button = findViewById(R.id.RxJavaBtn)
        rxJavaBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.textInputBtn -> {
                val toast = Toast.makeText(this, "success",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP and Gravity.LEFT , 0, 0)
                toast.show()
                val intent = Intent(this, TextInputLayoutTest::class.java)
                startActivity(intent)
            }
            R.id.geoquizBtn -> {
                val intent = Intent(this, GeoQuiz::class.java)
                startActivity(intent)
            }
            R.id.navigationDrawerBtn -> {
                startActivity(Intent(this, DrawerActivity::class.java))
            }
            R.id.eventBusBtn -> {
                startActivity(Intent(this, EventBusActivityOne::class.java))
            }
            R.id.RxJavaBtn -> {
                startActivity(Intent(this, RxJavaActivity::class.java))
            }
        }

    }
}