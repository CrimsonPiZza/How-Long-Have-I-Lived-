package com.nara.dob_master

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculate_butt.setOnClickListener{
            var ip_txt = ip_box.text.toString()
            var arrob = ip_txt.split("/")
            var dateob = arrob[0].toInt()
            var monthob = arrob[1].toInt()
            var yearob = arrob[2].toInt()
            var current_date = Calendar.getInstance().get(Calendar.DATE)
            var current_month = Calendar.getInstance().get(Calendar.MONTH) + 1
            var current_year = Calendar.getInstance().get(Calendar.YEAR)
            var totalnoreduce = 0
            for (year in yearob..current_year){
                if (checkleapyear(year)){
                    totalnoreduce += 366
                }
                else{
                    totalnoreduce += 365
                }
            }
            var startyeartotalwasteday = checkstartyear(yearob,monthob,dateob)
            var endyeartotalincompleteday = checkendyear(current_year,current_month,current_date)
            var age_day = totalnoreduce - startyeartotalwasteday - endyeartotalincompleteday
            age_days_txt.text = "You are ${age_day} days old"
            age_months_txt.text = checkmonthage(age_day)
            age_years_txt.text = checkyearage(age_day)
            age_hours_txt.text = checkhourage(age_day)
        }
    }

    fun checkhourage(day: Int):String{
        var hour = (day-1)*24 + Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return "You are ${hour} hours old"
    }

    fun checkyearage(day: Int):String{
        var year = day/365
        return "You are ${year} years old"
    }

    fun checkmonthage(day:Int):String{
        var month = day/30
        return "You are ${month} months old"
    }

    fun checkendyear(year:Int,month:Int,day:Int):Int {
        var totalincompletedays = 0
        for (mn in (month + 1)..12) {
            totalincompletedays += checkfordayinmonth(mn, year)
        }

        return totalincompletedays + checkfordayinmonth(month, year) - day
    }

    fun checkstartyear(year:Int,month:Int,day:Int):Int{
        var totalunborndays = 0
        for (mn in 1..(month-1)){
            totalunborndays += checkfordayinmonth(mn,year)
        }

        return totalunborndays + day - 1
    }

    fun checkfordayinmonth(mn:Int,year:Int):Int{
        if (mn == 2){
            if (checkleapyear(year)) return 29 else return 28
        }
        else if (mn >= 1 && mn <= 7){
            if (mn % 2 == 0) return 30 else return 31
        }
        else if (mn >= 8 && mn <= 12){
            if (mn % 2 == 0) return 31 else return 30
        }
        return 0
    }

    fun checkleapyear(year:Int):Boolean{
        if (year%100 == 0)
            if (year%400 == 0) return true else return false
        else{
            if (year%4 == 0) return true else return false
        }
    }

}

