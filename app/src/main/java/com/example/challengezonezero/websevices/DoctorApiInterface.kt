package com.example.challengezonezero.websevices

import com.example.challengezonezero.model.DoctorsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DoctorApiInterface {

    @GET("doctors.json")
    fun getDoctors() : Call<DoctorsResponse>//call doctor list

    companion object {//static values of the class

        var BASE_URL = "https://www.mobillium.com/android/"

        fun create() : DoctorApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()) //json dosyasindaki verileri modele baglamak icin
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(DoctorApiInterface::class.java)

        }
    }
}