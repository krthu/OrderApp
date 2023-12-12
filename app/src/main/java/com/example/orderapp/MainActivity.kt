package com.example.orderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val departmentFragment = DepartmentFragment()
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener{
            supportFragmentManager.popBackStack()
        }


        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, departmentFragment)
            .commit()

    }
}