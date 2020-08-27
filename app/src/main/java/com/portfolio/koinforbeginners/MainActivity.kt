package com.portfolio.koinforbeginners

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var allNames: AllNames
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvTestName = findViewById<TextView>(R.id.tv_test_name)
        tvTestName.text =  allNames.getAllNames()
    }
}


class AllNames @Inject constructor(val lastName: LastName){
    fun getAllNames(): String {
        return "paul ${lastName.lastNam()}"
    }
}

class LastName @Inject constructor(){
    fun lastNam():  String{
        return "njoroge"
    }
}