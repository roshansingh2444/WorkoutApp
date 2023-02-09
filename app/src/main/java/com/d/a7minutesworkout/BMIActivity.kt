package com.d.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.d.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibleView: String = METRIC_UNITS_VIEW

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

        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener{_,checkedId: Int ->
            if (checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUsUnitsView()
            }
        }

        binding?.btnCalculateUnits?.setOnClickListener{
            if (validateMetricUnits()){
                val heightValue : Float = binding?.etMetricUnitHeight?.text.toString().toFloat()/100

                val weightValue : Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                 val bmi = weightValue / (heightValue*heightValue)
                displayBMIResult(bmi)
            }else{
                Toast.makeText(this@BMIActivity,"Please enter  valid values.",Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW // Current View is updated here.
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE // METRIC  Height UNITS VIEW is Visible
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE // METRIC  Weight UNITS VIEW is Visible
        binding?.tilUsMetricUnitWeight?.visibility = View.GONE // make weight view Gone.
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.GONE // make height feet view Gone.
        binding?.tilMetricUsUnitHeightInch?.visibility = View.GONE // make height inch view Gone.

        binding?.etMetricUnitHeight?.text!!.clear() // height value is cleared if it is added.
        binding?.etMetricUnitWeight?.text!!.clear() // weight value is cleared if it is added.

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }
    // END


    // START
    private fun makeVisibleUsUnitsView() {
        currentVisibleView = US_UNITS_VIEW // Current View is updated here.
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE // METRIC  Height UNITS VIEW is GONE
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE // METRIC  Weight UNITS VIEW is GONE
        binding?.tilUsMetricUnitWeight?.visibility = View.VISIBLE // make weight view visible.
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE // make height feet view visible.
        binding?.tilMetricUsUnitHeightInch?.visibility = View.VISIBLE // make height inch view visible.

        binding?.etUsMetricUnitWeight?.text!!.clear() // weight value is cleared.
        binding?.etUsMetricUnitHeightFeet?.text!!.clear() // height feet value is cleared.
        binding?.etUsMetricUnitHeightInch?.text!!.clear() // height inch is cleared.

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
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

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription


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