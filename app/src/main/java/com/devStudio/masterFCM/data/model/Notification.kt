package com.devStudio.masterFCM.data.model

data class Notification(
    val title: String,
    val body: String
)

data class NotificationPayload(
    val appId: String,
    val includedSegments: List<String>,
    val headings: Map<String, String>,
    val contents: Map<String, String>
)

data class OneSignalResponse(
    val id: String,
    val recipients: Int,
    val errors: List<String>?
)
