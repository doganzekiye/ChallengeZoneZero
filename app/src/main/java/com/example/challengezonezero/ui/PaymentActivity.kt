package com.example.challengezonezero.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.challengezonezero.R

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val textView = intent.getStringExtra("textView")
        val titleTextView: TextView = findViewById(R.id.paymentView)
        titleTextView.text = textView
    }
}