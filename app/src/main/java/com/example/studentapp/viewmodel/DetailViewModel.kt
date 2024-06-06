package com.example.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentapp.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class DetailViewModel(application: Application, savedStateHandle: SavedStateHandle): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    // PR !! MENGUBAH INI MENJADI VOLLEY KASIH QUERY STRING NAMA DAN STUDENT ID
//    fun fetch() {
//        val student1 = Student("16055","Nonie","1998/03/28",
//            "5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1
//    }

    fun fetch(id:String){
        queue = Volley.newRequestQueue( getApplication() ) //cara membuat request volley yang baru
        val url = "http://adv.jitusolution.com/student.php?=id=${id}"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {//ini jika volley sukses
                Log.d("show_volley", it)
                val sType = object : TypeToken<Student>(){}.type //object ini dibuat untuk gson
                val result = Gson().fromJson<Student>(it, sType) //akan berusaha mengconvert sesuai dengan object
                val student1 = result as Student
                studentLD.value = student1
            },
            {
                Log.e("show_volley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

}

