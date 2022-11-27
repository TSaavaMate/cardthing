package com.example.cardthing

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var Name:EditText
    private lateinit var Cardnumber:EditText
    private lateinit var Date:EditText
    private lateinit var CVV:EditText
    private lateinit var Button:Button
    private lateinit var fordate:TextView
    private lateinit var fortime:TextView
    private lateinit var secbutton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Name=findViewById(R.id.Name)
        Cardnumber=findViewById(R.id.Cardnumber)
        Date=findViewById(R.id.editTextDate)
        CVV=findViewById(R.id.CVV)
        Button=findViewById(R.id.button)
        fordate=findViewById(R.id.textView7)
        fortime=findViewById(R.id.timeTitle)
        secbutton=findViewById(R.id.secbutton)

        Button.setOnClickListener {
            when{
                TextUtils.isEmpty(Name.text.toString().trim())->{Name.error="input name"}

                TextUtils.isEmpty(Cardnumber.text.toString().trim())->{Cardnumber.error="input cardnum"}

                TextUtils.isEmpty(Date.text.toString().trim())->{Date.error="input date"}

                TextUtils.isEmpty(CVV.text.toString().trim())->{CVV.error="input Cvv"}

                Name.text.toString().isNotEmpty() && Cardnumber.text.toString().isNotEmpty()&&
                        Date.text.toString().isNotEmpty()  && CVV.text.toString().isNotEmpty() ->{
                    if(Name.text.toString().contains(Regex("^[a-zA-Z]+\\s[a-zA-Z]+\$"))){
                        if(Cardnumber.text.length==16){
                            if(Date.text.matches(Regex("^(0[1-9]|1[0-2])\\/?(0[1-9]|1[0-4]|2[0-9]|3[0-1])\$"))){
                                if (CVV.text.length==3){
                                    Toast.makeText(this,"succesful",Toast.LENGTH_SHORT).show()
                                }else{
                                    CVV.error="input cvv writely"
                                }
                            }else{
                                Date.error="input writely date"
                            }
                        }else{
                            Cardnumber.error="input writely number"
                        }
                    }else{
                        Name.error="input name writely"
                    }

                }
            }
//            Name.text= fortime.text as Editable? //editable????
        }
    }

    fun opendatepicker(view: View) {
        val c=Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view:DatePicker, mYear, mMonth, mDay ->
            fordate.setText(""+mDay+"/"+mMonth+"/"+mYear)
        },year,month,day)
        dpd.show()
    }

    fun openTimepicker(view: View) {
        val T=Calendar.getInstance()
        val OnTimeSetListener=TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            T.set(Calendar.HOUR_OF_DAY,hour)
            T.set(Calendar.MINUTE,minute)
            fortime.text=SimpleDateFormat("HH:mm").format(T.time)
        }
        TimePickerDialog(this,OnTimeSetListener,T.get(Calendar.HOUR_OF_DAY),T.get(Calendar.MINUTE),true).show()
    }
}