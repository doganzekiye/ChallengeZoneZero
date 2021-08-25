package com.example.challengezonezero.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.challengezonezero.R
import com.example.challengezonezero.model.Doctor
import com.example.challengezonezero.ui.ProfileActivity
import com.squareup.picasso.Picasso

class DoctorsAdapter(private var mDoctors: MutableList<Doctor>) :
    RecyclerView.Adapter<DoctorsAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // holds the widgets in the memory
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val doctorName = itemView.findViewById<TextView>(R.id.doctorName)
        val doctorImage = itemView.findViewById<ImageView>(R.id.doctorImage)
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsAdapter.ViewHolder {
        val context = parent.context
        //Layout inflater is a class that reads xml view description and converts them to java based View objects.
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val doctorView = inflater.inflate(R.layout.item_doctor, parent, false)
        // Return a new holder instance
        return ViewHolder(doctorView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: DoctorsAdapter.ViewHolder, position: Int) {

        // Get the data model based on position
        val doctor: Doctor = mDoctors[position]
        // Set item views based on your views and data model
        val doctorNameTextView = viewHolder.doctorName
        doctorNameTextView.setText(doctor.full_name)
        val doctorImage = viewHolder.doctorImage
        Picasso.with(viewHolder.doctorImage.context).load(doctor.image.url).into(doctorImage)

        viewHolder.itemView.setOnClickListener {
            Log.d("doctor", doctor.full_name)
            val context = viewHolder.doctorName.context
            val intent = Intent(context, ProfileActivity::class.java)

            intent.putExtra("doctorName", doctor.full_name)
            intent.putExtra("userStatus", doctor.user_status)
            intent.putExtra("imageUrl", doctor.image.url)
            context.startActivity(intent)
        }
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mDoctors.size
    }

    fun filterList(filteredList: List<Doctor>) {
        mDoctors.clear()
        mDoctors.addAll(filteredList)
        notifyDataSetChanged()
    }
}
