package com.example.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.R
import com.example.studentapp.databinding.DrinkListItemBinding
import com.example.studentapp.databinding.StudentListItemBinding
import com.example.studentapp.model.Drink
import com.example.studentapp.model.Student

class DrinkListAdapter(val drinkList:ArrayList<Drink>)
    :RecyclerView.Adapter<DrinkListAdapter.DrinkViewHolder>() { //isinya view holder
    class DrinkViewHolder(var binding: DrinkListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val binding = DrinkListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return DrinkViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        holder.binding.txtName.text = drinkList[position].drinkName
        holder.binding.txtType.text = drinkList[position].type
        holder.binding.txtFlavor1.text = drinkList[position].flavors.toString()
        holder.binding.txtFlavor2.text = drinkList[position].flavors.toString()
        holder.binding.txtFlavor3.text = drinkList[position].flavors.toString()
        holder.binding.txtCompany.text = drinkList[position].manufacturer?.companyName
        holder.binding.txtCountry.text = drinkList[position].manufacturer?.country

    }

    fun updateDrinkList(newDrinkList: ArrayList<Drink>) {
        drinkList.clear()
        drinkList.addAll(newDrinkList)
        notifyDataSetChanged()
    }
}
