package com.diegorios.vistatest

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment

class DatePickerFragment(val listener:(day:Int, month:Int,year:Int)-> Unit): DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val day:Int = c.get(Calendar.DAY_OF_MONTH)
        val month:Int = c.get(Calendar.MONTH)
        val year:Int = c.get(Calendar.YEAR)

        val picker = DatePickerDialog(activity as Context,R.style.datePickerTheme, this, year, month, day)
        picker.datePicker.minDate= c.timeInMillis
        return picker
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth, month, year)
    }


}