package com.example.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapp.R
import com.example.studentapp.databinding.FragmentDrinkListBinding
import com.example.studentapp.databinding.FragmentStudentListBinding
import com.example.studentapp.viewmodel.DrinkViewModel
import com.example.studentapp.viewmodel.ListViewModel

class DrinkListFragment : Fragment() {
    private lateinit var viewModel: DrinkViewModel
    private val drinkListAdapter  = DrinkListAdapter(arrayListOf())
    private lateinit var binding:FragmentDrinkListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDrinkListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DrinkViewModel::class.java)
        viewModel.refresh()

        //set layoutManager supaya bisa jalankan recycle view
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = drinkListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.drinkLD.observe(viewLifecycleOwner, Observer {
            drinkListAdapter.updateDrinkList(it)
        })


    }
}