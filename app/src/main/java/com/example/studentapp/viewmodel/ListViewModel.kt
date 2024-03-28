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
import com.example.studentapp.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>() //live data yang berjenis ArrayListofStudent
    //kenapa bikin arraylist? karena recylcler view kita punya adapter yang minta arraylist
    val loadingLD = MutableLiveData<Boolean>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    // WEEK 5
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    //view model biasanya punya function yang menghasilkan data
    fun refresh() {
//        studentsLD.value = arrayListOf( //harusnya ini udah ga hardcode soalnya pake volley dan gson
//            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100" +
//                    ".jpg/cc0000/ffffff"),
//            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
//                    ".jpg/5fa2dd/ffffff"),
//            Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
//        )
        //kita anggap tdk ada loading - loading nya karena hardcode
//        studentLoadErrorLD.value = false
//        loadingLD.value = false

        studentLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue( getApplication() ) //cara membuat request volley yang baru
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {//ini jika volley sukses
                loadingLD.value = false
                Log.d("show_volley", it)
                val sType = object : TypeToken<List<Student>>(){}.type //object ini dibuat untuk gson
                val result = Gson().fromJson<List<Student>>(it, sType) //akan berusaha mengconvert sesuai dengan object
                studentsLD.value = result as ArrayList<Student>?
            },
            {
                loadingLD.value = false
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


