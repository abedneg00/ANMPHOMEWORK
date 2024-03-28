package com.example.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentapp.model.Drink
import com.example.studentapp.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DrinkViewModel(application: Application): AndroidViewModel(application) {
    val drinkLD = MutableLiveData<ArrayList<Drink>>()
    // WEEK 5
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        queue = Volley.newRequestQueue( getApplication() )
        val url = "http://10.0.2.2:8080/drinks/drinks.json"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {//ini jika volley sukses
                Log.d("show_volley", it)
                val sType = object : TypeToken<List<Drink>>(){}.type
                val result = Gson().fromJson<List<Drink>>(it, sType)
                drinkLD.value = result as ArrayList<Drink>?
            },
            {
                Log.e("show_volley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest) // karena queue bisa null, kita harus tambahkan ?
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}


