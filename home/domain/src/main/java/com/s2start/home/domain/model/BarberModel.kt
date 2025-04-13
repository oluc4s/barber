package com.s2start.home.domain.model

import java.io.Serializable

data class BarberModel(
    val userId:String = "",
    val name:String,
    val address:String,
    val image:Int,
    val services:String,
    val rating:Double = 0.0,
    val distance:Double = 0.0,
    val latitude:Double = 0.0,
    val longitude:Double = 0.0
): Serializable{
    @Suppress("unused")
    constructor() : this("","", "", 0, "", 0.0, 0.0, 0.0, 0.0)
}