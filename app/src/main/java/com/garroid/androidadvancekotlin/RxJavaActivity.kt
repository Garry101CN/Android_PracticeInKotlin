package com.garroid.androidadvancekotlin
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import rx.Observable
import rx.Subscriber

const val mTAG: String = "RxJava"

class RxJavaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)

        var subscriber = object : Subscriber<String>(){
            override fun onCompleted() {
                Log.d(mTAG, "onCompleted.")
            }

            override fun onError(e: Throwable?) {
                Log.d(mTAG, "onError")
            }

            override fun onNext(t: String?) {
                Log.d(mTAG, "onNext" + t)
            }

        }


        // 被观察者的实现方法1
        var observable = Observable.create(Observable.OnSubscribe<String> { t ->
            t?.apply {
                onNext("No.1")
                onNext("No.2")
                onCompleted()
            }
        })
        /*
        // 被观察者的实现方法2
        var strs = arrayOf("No.1", "No.2")
        var observable2 = Observable.from(strs)
        */

        observable.subscribe(subscriber)

    }
}