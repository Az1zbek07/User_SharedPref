package com.example.homeworkuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homeworkuser.databinding.ActivityMainBinding
import com.example.homeworkuser.utils.SharedPrefManager
import com.example.homeworkuser.utils.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val pref by lazy { SharedPrefManager(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews(){
        checkUser()
        binding.btnSave.setOnClickListener {
            if (binding.editAge.text.isNotEmpty() && binding.editAge.text.toString().toInt() > 0 && binding.editName.text.isNotEmpty()){
                saveUser()
                getUser()
            } else{
                Toast.makeText(this, "Please enter dates correctly", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUser(){
            val name = binding.editName.text.toString().trim()
            val age = binding.editAge.text.toString().toInt()
            val gender = if (binding.switchGender.isChecked){
                "Female"
            } else{
                "Male"
            }

            val user = User(name, age, gender)
            pref.saveUser(user)
    }

    private fun getUser(){
        val user = pref.getUser()
        binding.tvName.text = user?.name.toString().trim()
        binding.tvAge.text = user?.age.toString().trim()
        binding.tvGender.text = user?.gender.toString().trim()
    }

    private fun checkUser(){
        if (pref.getUser() != null){
            getUser()
        }
    }
}