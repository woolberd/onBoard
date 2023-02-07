package com.example.onboard

import android.app.Application
import com.example.onboard.utils.PreferenceHelper

class App: Application() {

    companion object {
        val preferenceHelper = PreferenceHelper()
    }

    override fun onCreate() {
        super.onCreate()
        initPref()
    }

    private fun initPref() {
        preferenceHelper.unit(this)
    }
}