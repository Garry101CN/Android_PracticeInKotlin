package com.garroid.androidadvancekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.greenrobot.eventbus.EventBus

class EventBusActivityTwo : AppCompatActivity() {

    private lateinit var bt_message : Button
    private lateinit var tv_message : TextView
    private lateinit var bt_message_sticky : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus_two)

        tv_message = findViewById(R.id.tv_message_2)
        tv_message.text = "SecondAvtivity"

        bt_message = findViewById(R.id.bt_message_2)
        bt_message.text = "发送事件"
        bt_message.setOnClickListener{
            EventBus.getDefault().post(MessageEvent("Welcome to Gary's Codebook."))
            finish()
        }

        bt_message_sticky = findViewById(R.id.bt_message_sticky)
        bt_message_sticky.setOnClickListener{
            EventBus.getDefault().postSticky(MessageEvent("Welcome to Gary's Codebook."))
            finish()
        }
    }
}