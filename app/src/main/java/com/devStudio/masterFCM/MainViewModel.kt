package com.devStudio.masterFCM

import android.os.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devStudio.masterFCM.data.model.Notification
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch
import android.util.Log
//import com.devStudio.masterFCM.data.api.RetrofitClient
import com.devStudio.masterFCM.data.model.NotificationPayload
import com.devStudio.masterFCM.data.model.OneSignalResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class MainViewModel: ViewModel() {

/*
    fun sendNotification(notificationTitle: String, notificationMessage: String, userId: String) {
        val notificationPayload = NotificationPayload(
            appId = AppClass.ONESIGNAL_APP_ID,
            includedSegments = listOf(userId),
            headings = mapOf("en" to notificationTitle),
            contents = mapOf("en" to notificationMessage)
        )

        RetrofitClient.instance.sendNotification(notificationPayload).enqueue(object : Callback<OneSignalResponse> {
            override fun onResponse(call: Call<OneSignalResponse>, response: Response<OneSignalResponse>) {
                if (response.isSuccessful) {
                    Log.i("Notification", "Notification sent successfully: ${response.body()}")
                } else {
                    Log.i("Notification", "Failed to send notification: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<OneSignalResponse>, t: Throwable) {
                Log.e("Notification", "Error sending notification", t)
            }
        })
    }
*/


}