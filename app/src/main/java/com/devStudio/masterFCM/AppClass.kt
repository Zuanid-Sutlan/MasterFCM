package com.devStudio.masterFCM

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class AppClass:Application() {
    companion object{
        // NOTE: Replace the below with your own ONESIGNAL_APP_ID
        const val ONESIGNAL_APP_ID = "9ed64ba4-53a1-4c81-a4dc-3c64a6a25baa"
        const val ONESIGNAL_REST_API_KEY = "ODgwZjYxMmQtNGNkYy00YjE5LTg2MzUtZWQ0OGIwMmUzZWE0"
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = this
        FirebaseApp.initializeApp(this)


        // Verbose Logging set to help debug issues, remove before releasing your app.
        OneSignal.Debug.logLevel = LogLevel.VERBOSE

        // OneSignal Initialization
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)

        // requestPermission will show the native Android notification permission prompt.
        // NOTE: It's recommended to use a OneSignal In-App Message to prompt instead.
        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(false)
        }

    }
}