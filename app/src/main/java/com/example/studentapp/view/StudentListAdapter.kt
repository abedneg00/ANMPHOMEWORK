package com.example.studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.R
import com.example.studentapp.databinding.StudentListItemBinding
import com.example.studentapp.model.Student
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener { //isinya view holder
    class StudentViewHolder(var binding: StudentListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)

//        val inflater = LayoutInflater.from(parent.context)
//        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)
//        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
//        holder.binding.txtID.text = studentList[position].id
//        holder.binding.txtName.text = studentList[position].name
//        holder.binding.btnDetail.setOnClickListener{
//            val action = StudentListFragmentDirections.actionStudentDetailFragment()
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception ->
//            exception.printStackTrace()
//        }
//        picasso.build().load(studentList[position].photoUrl).into(holder.binding.imageView, object:Callback {
//            override fun onSuccess() {
//                holder.binding.progressImage.visibility = View.INVISIBLE
//                holder.binding.imageView.visibility = View.VISIBLE
//            }
//            override fun onError(e: Exception?) {
//                Log.e("picasso_error", e.toString())
//            }
//        })

        holder.binding.student = studentList[position]
        holder.binding.listener = this
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetailFragment(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }


}
