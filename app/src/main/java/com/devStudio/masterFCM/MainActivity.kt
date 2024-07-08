package com.devStudio.masterFCM

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.devStudio.masterFCM.data.api.OneSignalApi
import com.devStudio.masterFCM.ui.theme.MasterFCMTheme

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var context: Context
    }

    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        context = this
        setContent {
            MasterFCMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding), contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                            Button(onClick = {
                                OneSignalApi.send("New Signal Available", "Tap to Open")
                            }) {
                                Text(text = "Send")
                            }

                            Spacer(modifier = Modifier.padding(10.dp))

                            NotificationButton(context)

                        }
                    }

                }
            }
        }
    }
}

/**in app notification like whatsapp testing*/
@RequiresApi(Build.VERSION_CODES.O)
private fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Channel Name"
        val descriptionText = "Channel Description"
        val importance =
            NotificationManager.IMPORTANCE_HIGH // Importance high for heads-up notification
        val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

private fun showHeadsUpNotification(context: Context) {
    val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("Notification Title")
        .setContentText("Notification Content")
        .setPriority(NotificationCompat.PRIORITY_HIGH) // Priority high for heads-up notification
        .setCategory(NotificationCompat.CATEGORY_STATUS) // Set category to message
        .setAutoCancel(true) // Dismisses the notification when the user taps on it

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notify(1, builder.build())
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationButton(context: Context) {
    Button(onClick = {
        createNotificationChannel(context)
        showHeadsUpNotification(context)
    }) {
        Text("Show Heads-up Notification")
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MasterFCMTheme {
        Greeting("Android")
    }
}