package com.example.eduardo.helios.activities

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.example.eduardo.helios.R
import com.example.eduardo.helios.bs.BateriaBs
import com.example.eduardo.helios.enums.ErroresEnum
import com.example.eduardo.helios.modelo.Bateria
import kotlinx.android.synthetic.main.activity_agregar_bateria.*




class AgregarBateria : AppCompatActivity() {

    val bateriaBs:BateriaBs = BateriaBs()

    var modelo: Bateria = Bateria()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_bateria)
        val btnAceptar = findViewById<Button>(R.id.btnAceptar)

        buttonEffect(btnAceptar)

        obtenerCampos()


        btnAceptar.setOnClickListener {
            modelo.nombre = campo_nombre.text.toString()
            modelo.voltajeMax = campo_voltajeMaximo.text.toString().toDouble()
            modelo.voltajeMin = campo_voltajeMinimo.text.toString().toDouble()
            modelo.numeroCeldas = campo_celdas.text.toString().toInt()
            modelo.temperatura = campo_temperatura.text.toString().toDouble()
            modelo.hasMemoria = campo_memoria.toString().toBoolean()

            var errores = bateriaBs.validateRegistrar(modelo)

            if(errores.isEmpty()){
                if(!bateriaBs.registrar(modelo)){
                    Toast.makeText(this," ERROR EN LA CONEXION ", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this," BIEN EN LA CONEXION ", Toast.LENGTH_LONG).show()
                }

            }else{
                for ((value, key) in errores) {
                    if(key == ErroresEnum.NOMBRE_INVALIDO.identificador){
                        campo_nombre.error = value
                    }
                    if(key == ErroresEnum.CAMPO_VACIO.identificador) {
                        campo_nombre.error = value
                    }
                }
            }
        }
    }


    /**
     * Metodo que obtiene los campos
     */
    fun obtenerCampos(): ArrayList<LinearLayout>{

        var listLinearLayout = ArrayList<LinearLayout>()
        var listTextInputLayout = ArrayList<TextInputLayout>()
        var listEditText = ArrayList<EditText>()
        var listFrameLayout = ArrayList<FrameLayout>()

        val constrainLayout: ConstraintLayout = findViewById(R.id.constraint)

        var countConstraintLayout = constrainLayout.childCount
        var countInputLayout:Int
        var countFrameLayout:Int
        var countEditText:Int




        //Obtiene todos los LINEAR LAYOUT
        for (i in 0 until countConstraintLayout) {
            var linearLayout  = constrainLayout.getChildAt(i)
            if(linearLayout is LinearLayout) {
                listLinearLayout.add(linearLayout)
            }
        }

        // Por cada LinearLayout obtiene su TEXT INPUT LAYOUT

        listLinearLayout.forEach {
            countInputLayout = it.childCount
            for (i in 0 until countInputLayout){
                var inputLayout = it.getChildAt(i)
                if(inputLayout is TextInputLayout){
                    listTextInputLayout.add(inputLayout)
                }
            }
        }

        // Por cada TextInputLayout obtiene su FRAME LAYOUT

        listTextInputLayout.forEach {
            countFrameLayout = it.childCount
            for (j in 0 until countFrameLayout){
                var frameLayout = it.getChildAt(j)
                if(frameLayout is FrameLayout){
                    listFrameLayout.add(frameLayout)
                }
            }
        }


        // Por cada FrameLayout obtiene su EditText

        listFrameLayout.forEach {
            countEditText = it.childCount
            for (j in 0 until countEditText){
                var editText = it.getChildAt(j)
                if(editText is EditText){
                    listEditText.add(editText)
                }
            }
        }


        println(listEditText.size)
        return listLinearLayout
    }





    /**
     * Metodo que permite hacer la animacion del boton al ser presionado
     */
    fun buttonEffect(button: View) {
        button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.background.setColorFilter(-0x320000, PorterDuff.Mode.SRC_ATOP)
                    v.invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    v.background.clearColorFilter()
                    v.invalidate()
                }
            }
            false
        }
    }
}
