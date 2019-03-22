package com.example.eduardo.helios.modelo

/**
 * Entidad Bateria
 */
data class Bateria(var id:Int?= null ,var idMecanismo:Int ?= null, var nombre:String ?=null, var voltajeMax:Double?=null,
                   var voltajeMin:Double?= null, var numeroCeldas:Int?= null, var temperatura:Double?= null, var hasMemoria:Boolean?= null)