package com.garroid.androidadvancekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusActivityOne : AppCompatActivity() {

    private lateinit var tv_message: TextView
    private lateinit var bt_message: Button
    private lateinit var bt_subscription: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus_one)
        tv_message = findViewById(R.id.tv_message)
        tv_message.text = "MainActivity"
        bt_subscription = findViewById(R.id.bt_subscription)
        bt_subscription.text = "注册事件"
        bt_message = findViewById(R.id.bt_message)
        bt_message.text = "跳转到SecondActivity"

        bt_message.setOnClickListener{
            startActivity(Intent(this@EventBusActivityOne, EventBusActivityTwo::class.java))
        }

        bt_subscription.setOnClickListener{

            EventBus.getDefault().register(this@EventBusActivityOne)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onMoonEvent(messageEvent: MessageEvent){
        tv_message.text = messageEvent.getMessage()
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public fun onMoonStickyEvent(messageEvent: MessageEvent){
        tv_message.text = messageEvent.getMessage()
    }
}