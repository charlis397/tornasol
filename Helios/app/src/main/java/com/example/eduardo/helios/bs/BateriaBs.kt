package com.example.eduardo.helios.bs

import com.example.eduardo.helios.conexion.HttpPandler
import com.example.eduardo.helios.enums.ErroresEnum
import com.example.eduardo.helios.enums.OperacionesEnum
import com.example.eduardo.helios.modelo.Bateria
import com.example.eduardo.helios.utileria.Validaciones
import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair


class BateriaBs(){

    val validaciones:Validaciones = Validaciones()

    /**
     * Metodo que valida la informacion del formulario
     */
    fun validateRegistrar(modelo:Bateria):HashMap<String,Int> {
        var errors :HashMap<String,Int> = hashMapOf()

        if (!validaciones.validarNombre(modelo.nombre.toString())){
            errors["Nombre Invalido"] = ErroresEnum.NOMBRE_INVALIDO.identificador
        }


    return errors
    }

    /**
     * Metodo que permite registrar la informacion de una bateria
     */
    fun registrar(modelo:Bateria): Boolean{
        return enviarDatos(modelo, OperacionesEnum.REGISTRAR.identificador)
    }

    /**
     * Metodo que permite editar la informacion de una bateria
     */
    fun editar(modelo:Bateria){
        enviarDatos(modelo, OperacionesEnum.EDITAR.identificador)
    }

    /**
     * Metodo que permite hacer un eliminado logico de la informacion de una bateria
     */
    fun eliminar(modelo:Bateria){
        enviarDatos(modelo, OperacionesEnum.ELIMINAR.identificador)
    }

    /**
     * Metodo que permite obtener las baterias registradas.
     */
    fun obtener(modelo:Bateria){

    }

    /**
     * Metodo que envia los datos
     * 1 -> Registrar
     * 2 -> Editar
     * 3 -> Eliminar
     */
    fun  enviarDatos(modelo:Bateria, operacion:Int): Boolean{

        var respuesta : Boolean = true

        var params = ArrayList<NameValuePair>()
        params.add(BasicNameValuePair("operacion", operacion.toString()))
        params.add(BasicNameValuePair("nombre", modelo.nombre.toString()))
        params.add(BasicNameValuePair("voltajeMax", modelo.voltajeMax.toString()))
        params.add(BasicNameValuePair("voltajeMin", modelo.voltajeMin.toString()))
        params.add(BasicNameValuePair("numeroCeldas", modelo.numeroCeldas.toString()))
        params.add(BasicNameValuePair("temperatura", modelo.temperatura.toString()))
        params.add(BasicNameValuePair("memoria", modelo.hasMemoria.toString()))

        val httpand = HttpPandler()

        val nt = object : Thread() {
            override fun run() {
                respuesta = try {
                    httpand.enviarInformacion(params,"10.100.78.98")
                    true
                } catch (e: Exception) {
                    false
                }
            }
        }
        nt.start()

    return respuesta
    }

}