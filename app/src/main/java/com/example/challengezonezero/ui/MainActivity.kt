package com.example.challengezonezero.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.challengezonezero.R
import com.example.challengezonezero.model.Doctor
import com.example.challengezonezero.model.DoctorsResponse
import com.example.challengezonezero.ui.adapter.DoctorsAdapter
import com.example.challengezonezero.websevices.DoctorApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

    var doctorList: List<Doctor> = emptyList()
    val apiInterface = DoctorApiInterface.create().getDoctors()
    lateinit var usersRecyclerView: RecyclerView
    lateinit var maleCheckBox: CheckBox
    lateinit var femaleCheckBox: CheckBox
    lateinit var editTextDoctor: EditText
    lateinit var adapter: DoctorsAdapter
    lateinit var emptyUserView: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersRecyclerView = findViewById(R.id.usersRecyclerView)
        maleCheckBox = findViewById(R.id.checkBoxMale)
        femaleCheckBox = findViewById(R.id.checkBoxFemale)
        editTextDoctor = findViewById(R.id.editTextSearchFilter)
        emptyUserView = findViewById(R.id.emptyLayout)
        setListener()
        getDoctors()
    }

    private fun getDoctors() {
        apiInterface.enqueue(object : Callback<DoctorsResponse> {
            override fun onResponse(
                call: Call<DoctorsResponse>?,
                response: Response<DoctorsResponse>?
            ) {
                if (response?.body() != null) {
                    doctorList = response?.body()?.doctors!!
                    adapter = DoctorsAdapter(doctorList.toMutableList())
                    // Attach the adapter to the recyclerview to populate items
                    usersRecyclerView.adapter = adapter

                }
            }

            override fun onFailure(call: Call<DoctorsResponse>?, t: Throwable?) {
                Log.d("error", t.toString())
            }
        })
    }

    private fun setListener() {

        maleCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this, "Male Checked", Toast.LENGTH_SHORT).show()
            var tempFilterList = mutableListOf<Doctor>()
            if (isChecked) {
                femaleCheckBox.isChecked = false
                for (item in doctorList) {
                    if (item.gender.equals("male")) {
                        tempFilterList.add(item)
                    }
                }
                adapter.filterList(tempFilterList)
            } else {
                adapter.filterList(doctorList)
            }

        }

        femaleCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this, "Female Checked", Toast.LENGTH_SHORT).show()
            var tempFilterList = mutableListOf<Doctor>()
            if (isChecked) {
                maleCheckBox.isChecked = false
                for (item in doctorList) {
                    if (item.gender.equals("female")) {
                        tempFilterList.add(item)
                    }
                }
                adapter.filterList(tempFilterList)
            } else {
                adapter.filterList(doctorList)
            }
        }

        editTextDoctor.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                filterName(s.toString())
            }
        })

    }

    private fun filterName(text: String) {
        var filteredList = mutableListOf<Doctor>()

        var gender = getGender()

        for (item in doctorList) {
            if (item.full_name.lowercase(Locale.getDefault())
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                if (gender.isNotEmpty()) {
                    if (item.gender.equals(gender))
                        filteredList.add(item)
                } else {
                    filteredList.add(item)
                }
            }
        }

        if (filteredList.isEmpty()) {
            emptyUserView.visibility = View.VISIBLE
            usersRecyclerView.visibility = View.GONE
        } else {
            emptyUserView.visibility = View.GONE
            usersRecyclerView.visibility = View.VISIBLE
        }
        adapter.filterList(filteredList)
    }

    private fun getGender(): String {
        var gender = String()
        if (maleCheckBox.isChecked) {
            gender = "male"
        } else if (femaleCheckBox.isChecked) {
            gender = "female"
        }
        return gender
    }

}