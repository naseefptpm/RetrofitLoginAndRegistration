package com.nzf.retrofitexample.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.nzf.retrofitexample.Api.RetrofitClient
import com.nzf.retrofitexample.Models.DefaultResponse
import com.nzf.retrofitexample.R
import com.nzf.retrofitexample.Storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

        buttonSignUp.setOnClickListener(View.OnClickListener {

            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val name = editTextName.text.toString().trim()
            val school = editTextSchool.text.toString().trim()

             if(email.isEmpty()){
                 editTextEmail.error ="Email Required"
                 editTextEmail.requestFocus()
                 return@OnClickListener
             }
            if(password.isEmpty()){
                editTextPassword.error ="Password Required"
                editTextPassword.requestFocus()
                return@OnClickListener
            }
            if(name.isEmpty()){
                editTextName.error ="Name Required"
                editTextName.requestFocus()
                return@OnClickListener
            }
            if(school.isEmpty()){
                editTextSchool.error ="School Required"
                editTextSchool.requestFocus()
                return@OnClickListener
            }

            RetrofitClient.instance.createUser(email,password,name,school)
                .enqueue(object: Callback<DefaultResponse>{
                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    Toast.makeText(applicationContext,response.body()?.message,Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
                    }

                } )

        })
    }

    override fun onStart() {
        super.onStart()
        if (SharedPrefManager.getInstance(this)!!.isLoggedIn){
            val intent = Intent(applicationContext,ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}