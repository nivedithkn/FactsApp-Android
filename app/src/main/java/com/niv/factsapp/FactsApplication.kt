package com.niv.factsapp

import android.app.Application

class FactsApplication : Application() {

    companion object {
        lateinit var instance: FactsApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this@FactsApplication
    }


}