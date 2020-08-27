package com.portfolio.hiltforbeginners

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //field injection using android hilt
    @Inject lateinit var allNames: AllNames
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvTestName = findViewById<TextView>(R.id.tv_test_name)
        tvTestName.text =  allNames.getAllNames()
    }
}

//constructor injection using android hilt
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