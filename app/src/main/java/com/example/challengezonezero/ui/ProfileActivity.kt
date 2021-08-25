package com.example.challengezonezero.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.challengezonezero.R
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val userStatusPremium = "premium"
        val userStatusFree = "free"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val fullName = intent.getStringExtra("doctorName")
        val userStatus = intent.getStringExtra("userStatus")
        val userImageUrl = intent.getStringExtra("imageUrl")

        val fullNameTextView: TextView = findViewById(R.id.userNameText)
        fullNameTextView.text = fullName
        val printStatus: TextView = findViewById(R.id.userStatusText)
        printStatus.text = userStatus
        val doctorImageView: ImageView = findViewById(R.id.imageDoctor)
        Picasso.with(this).load(userImageUrl).into(doctorImageView)

        val appointTitleTextView: TextView = findViewById(R.id.appointmentTitle)

        if (userStatus.equals(userStatusPremium)) {
            appointTitleTextView.text = getString(R.string.make_appointment)

            appointTitleTextView.setOnClickListener {
                val intent = Intent(this, AppointmentActivity::class.java)
                intent.putExtra("textView", "Randevu Ekranı")
                startActivity(intent)
            }
        } else if (userStatus.equals(userStatusFree)) {
            appointTitleTextView.text = "Premium Paket Al"

            appointTitleTextView.setOnClickListener {
                val intent = Intent(this, PaymentActivity::class.java)
                intent.putExtra("textView", "Ödeme Ekranı")
                startActivity(intent)
            }
        }

    }
}