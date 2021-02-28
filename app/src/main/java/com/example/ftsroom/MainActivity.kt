package com.example.ftsroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel.data.observe(this){ users ->
//            Log.e(TAG, "onCreate: users size -> ${users.size}")
//        }
//        viewModel.getData()
        val term = String.format("%s","amir")
        viewModel.filteredData.observe(this){filteredUsers ->
            Log.e(TAG, "onCreate: filteredUsers $filteredUsers")

        }

        viewModel.getFilteredData(term)

        viewModel.insertUser("amir", "karimi")
    }
}