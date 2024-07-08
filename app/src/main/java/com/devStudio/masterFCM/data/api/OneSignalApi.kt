package com.devStudio.masterFCM.data.api

import android.util.Log
import com.devStudio.masterFCM.AppClass
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException

object OneSignalApi {

    @OptIn(DelicateCoroutinesApi::class)
    fun send(title: String, content: String) {
        val client = OkHttpClient()

        val mediaType = MediaType.parse("application/json")
        val body = RequestBody.create(
            mediaType, """
        {
            "app_id": "${AppClass.ONESIGNAL_APP_ID}",
            "included_segments": ["All"],
            "small_icon": "ic_launcher_foreground",
            "contents": {"en": "$content"},
            "headings": {"en": "$title"},
            "priority": 10,
            "android_category": "msg"
         }
    """.trimIndent()
        )
        val request = Request.Builder()
            .url("https://api.onesignal.com/notifications")
            .post(body)
            .addHeader("Authorization", "Basic ${AppClass.ONESIGNAL_REST_API_KEY}")
            .addHeader("content-type", "application/json")
            .build()


        try {
            GlobalScope.launch(Dispatchers.IO) {
                val response = client.newCall(request).execute()
                if (!response.isSuccessful) {
                    Log.i("send: ", "..${response.code()} ${response.message()}")
                    Log.i("send: ", "..${response.body()} ${response.message()}")
                } else {
                    Log.i("send: ", "else ${response.body()?.string()}")
                }
            }
        } catch (e: IOException) {
            Log.i("send: ", ",  ${e.message}")
        }
    }


}