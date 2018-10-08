package com.example.arifrahman.crudkotlin

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_manage_student.*
import org.json.JSONObject

class ManageStudentActivity : AppCompatActivity() {

    lateinit var i: Intent
    private var gender = "Laki-laki"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_student)

        i = intent
        if(i.hasExtra("editmode")){
            if (i.getStringExtra("editmode").equals(1)){
                onEditMode()
            }
        }

        rgGender.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.radioBoy->{
                    gender = "Laki-laki"
                }
                R.id.radioGirl->{
                    gender = "Perempuan"
                }
            }
        }

        btnCreate.setOnClickListener{
            create()
        }
    }

    private fun onEditMode(){
        txNim.setText(i.getStringExtra("nim"))
        txName.setText(i.getStringExtra("name"))
        txAddress.setText(i.getStringExtra("address"))
        txNim.isEnabled = false

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE

        gender = i.getStringExtra("gender")
        if(gender.equals("Laki-laki")){
            rgGender.check(R.id.radioBoy)
        } else{
            rgGender.check(R.id.radioGirl)
        }
    }

    private fun create(){
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.CREATE)
                .addBodyParameter("nim",txNim.text.toString())
                .addBodyParameter("name",txName.text.toString())
                .addBodyParameter("address",txAddress.text.toString())
                .addBodyParameter("gender",gender)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {

                    override fun onResponse(response: JSONObject?) {
                        loading.dismiss()
                        Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                        if(response?.getString("message")?.contains("Successfully")!!){
                            this@ManageStudentActivity.finish()
                        }
                    }

                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.d("ONERROR",anError?.errorDetail?.toString())
                        Toast.makeText(applicationContext,"Connection Failure",Toast.LENGTH_SHORT).show()
                    }


                })
    }
}
