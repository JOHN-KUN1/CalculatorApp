package com.john.calculatorapp

import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun main(args: Array<String>) {
//    val a = arrayListOf(1,2,3,"8")
//    if ("8" in a){
//        println("it is in")
//    }
    val a = 44545.878F
    val number = DecimalFormat("#,###.##")
    val b = number.format(a)
    println(b.toString())
}