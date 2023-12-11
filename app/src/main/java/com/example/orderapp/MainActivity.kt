package com.example.orderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val departmentFragment = DepartmentFragment()



        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, departmentFragment)
            .commit()

    }
}