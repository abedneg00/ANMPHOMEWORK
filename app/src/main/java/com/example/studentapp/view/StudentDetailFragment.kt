package com.example.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studentapp.R
import com.example.studentapp.databinding.FragmentStudentDetailBinding
import com.example.studentapp.model.Student
import com.example.studentapp.viewmodel.DetailViewModel
import com.example.studentapp.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
//        val id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
//        viewModel.fetch(id)
//        observeViewModel()

        binding.student = Student("","","","", "https://randomwordgenerator.com/")
        if (arguments!=null){
            val id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(id)
        }
        observeViewModel()
    }

    fun observeViewModel() {
        //bertujuan untuk mendengarkan dari live data. jika data baru muncul, maka UI akan menanggapi
//        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//            binding.txtID.setText(it.id)
//            binding.txtName.setText(it.name)
//            binding.txtBod.setText(it.dob)
//            binding.txtPhone.setText(it.phone)
//
//            var student = it
//            binding.btnUpdate?.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_person_add_24)
//                    }
//            }
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.student = it
        })
    }
}