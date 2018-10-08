package com.example.arifrahman.crudkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.student_list.*
import kotlinx.android.synthetic.main.student_list.view.*

class RVAdapterStudent(private val context: Context, private val arrayList:
ArrayList<Students>) :
RecyclerView.Adapter<RVAdapterStudent.Holder>(){

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.student_list,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.nimList.text = arrayList?.get(position)?.nim
        holder.view.nameList.text = "Nama : "+arrayList?.get(position)?.name
        holder.view.addressList.text = "Alamat : "+arrayList?.get(position)?.address
        holder.view.genderList.text = "Gender : "+arrayList?.get(position)?.gender
    }
}