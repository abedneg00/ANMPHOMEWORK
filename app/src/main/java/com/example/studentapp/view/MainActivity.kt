package com.example.studentapp.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.studentapp.R
import com.example.studentapp.databinding.ActivityMainBinding
import com.example.studentapp.util.createNotificationChannel

class MainActivity : AppCompatActivity() {
    init {
        instance = this
        // init adalah bagian coding yg dipanggil pertama kali bahkan sebelum OnCreate
    }

    companion object {
        private var instance:MainActivity ?= null
        //instance ke dirinya sendiri, memperbolehkan instance jadi null, karena static

        fun showNotification(title:String, content:String, icon:Int) { //kenapa icon nya int? karena kita ambil id yang sudah di set
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            //chanelId nya nanti akan berisi "com.example.anmp_week1-ANMP-Week1"

            //CREATE NOTIF MENGGUNAKAN BUILDER
            // builder wajib berisi context. double tanda seru menunjukkan bahwa builder pasti tidak null
            val builder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }

            val manager = NotificationManagerCompat.from(instance!!.applicationContext)
            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(instance!!,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),1) //memaksa rquest permission ke user
                //requestPermissions diatas parameter pertama butuh activity
                return
            }
            manager.notify(1001, builder.build())  //ini coding penting untuk menampilkan notif ke layar
        }
    }

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // WEEK 7
        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")

    }

    override fun onRequestPermissionsResult( //function yang bisa mengetahui ketika user memencet Allow atau Deny
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray  //ke index 0 akan berisi [1=allow, 0=deny]
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode ==1){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("permission", "Allow")
                //create notif channel
                createNotificationChannel(this,
                    NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                    getString(R.string.app_name), "App notification channel.")

            } else {
                Log.d("permission", "Deny")
            }
        }
    }
}