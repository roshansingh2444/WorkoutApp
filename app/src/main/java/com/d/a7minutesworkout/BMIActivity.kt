package com.d.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.d.a7minutesworkout.databinding.ActivityBmiBinding

class BMIActivity : AppCompatActivity() {
    private var binding: ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.btnCalculateUnits?.setOnClickListener{
            if (validateMetricUnits()){
                val heightValue : Float = binding?.etMetricUnitHeight?.text.toString().toFloat()/100

                val weightValue : Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                 val bmi = weightValue / (heightValue*heightValue)
            }else{
                Toast.makeText(this@BMIActivity,"Please enter  valid values.",Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    private fun displayBMIResult(bmi: Float){

        val bmiLabel : String
        val bmiDescription : String

        if (bmi.compareTo(15f) <= 0){
          bmiLabel = "Very Bery UnderWeight"
          bmiDescription = "EAT MOOOOOORE"
        }else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
            bmiLabel = "Little Very UnderWeight"
            bmiDescription = "EAT MORE"
        }
        else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0){
            bmiLabel = "UnderWeight"
            bmiDescription = "EAT"
        }
        else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "You are in good shape"
        }
        else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "OverWeight"
            bmiDescription = "Stop Eating"
        }
        else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Little obese"
            bmiDescription = "Stoooop Eating"
        }
        else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Fully Obese"
            bmiDescription = "Stooooooooop Eating"
        }
        else{
            bmiLabel = "whale"
            bmiDescription = "sigh"
        }


        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        

    }

    private fun validateMetricUnits() : Boolean{
        var isValid = true
    if(binding?.etMetricUnitWeight?.text.toString().isEmpty()) {
        isValid = false
    }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
        isValid = false
    }
        return isValid
    }
}