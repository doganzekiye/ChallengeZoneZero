package com.example.challengezonezero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.challengezonezero.R

class AppointmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)

        val textView = intent.getStringExtra("textView")
        val titleTextView: TextView = findViewById(R.id.appointmentView)
        titleTextView.text = textView
    }
}