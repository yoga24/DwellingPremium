package com.cyorg.uiic.dwellingpremium

import android.text.Editable
import android.widget.EditText

/**
 * Created by yorga on 07-07-2017.
 */
fun EditText.isValidLong(): Boolean {
    val value :Long = this.toLong()
    if(value == -99L || value == 0L)
        return false
    return true
}

fun EditText.isValidInt(): Boolean {
    val value :Int? = this.toInt()
    if(value == -99 || value == 0)
        return false
    return true
}

fun EditText.toLong(): Long {
    try {
        return this.text.toString().toLong()
    }catch(exc : Exception) {
        return -99
    }
}

fun EditText.toInt(): Int {
    try {
        return this.text.toString().toInt()
    }catch(exc : Exception) {
        return -99
    }
}
