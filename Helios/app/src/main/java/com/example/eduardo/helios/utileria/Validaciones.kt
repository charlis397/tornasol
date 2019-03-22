package com.example.eduardo.helios.utileria

import java.util.regex.Pattern

class Validaciones(){

    /**
     * Metodo que permite validar un nombre
     */
    fun validarNombre(nombre: String): Boolean {
        val patron = Pattern.compile("^[a-zA-Z0-9 ]+$")
        return patron.matcher(nombre).matches()
    }


    /**
     * Metodo que permite validar si un campo esta vacio
     */
    fun validarVacio(campo:Unit):Boolean{
        return campo.toString().isEmpty()
    }

}