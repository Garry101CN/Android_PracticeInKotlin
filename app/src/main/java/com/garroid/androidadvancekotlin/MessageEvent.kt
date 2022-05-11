package com.garroid.androidadvancekotlin

class MessageEvent(var message: String){

    @JvmName("getMessage1")
    public fun getMessage(): String{
        return message
    }

    @JvmName("setMessage1")
    public fun setMessage(message: String){
        this.message = message
    }

}