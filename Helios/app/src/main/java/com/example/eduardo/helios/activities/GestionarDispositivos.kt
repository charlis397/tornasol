package com.example.eduardo.helios.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TableRow
import com.example.eduardo.helios.EditarBateria
import com.example.eduardo.helios.R
import kotlinx.android.synthetic.main.activity_gestionar_dispositivos.*



class GestionarDispositivos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestionar_dispositivos)


        var listBotones: List<TableRow> = listOf(row1, row2,row3,row4)



        fun agregarPanel() = startActivity(Intent(this, RegistrarPanel::class.java))
        fun aregarBateria() = startActivity(Intent(this, AgregarBateria::class.java))

        addPanel.setOnClickListener { agregarPanel() }
        addBateria.setOnClickListener { aregarBateria() }
        registerForContextMenu(row1)
        registerForContextMenu(row3)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        if(v.id == R.id.row1){
            inflater.inflate(R.menu.panel, menu)
        }else if(v.id == R.id.row3){
            inflater.inflate(R.menu.bateria, menu)
        }


    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.editarPanelMC -> {
                startActivity(Intent(this, EditarPanel::class.java))
                return true
            }
            R.id.eliminarPanelMC->{
                startActivity(Intent(this, EditarPanel::class.java))
                return true
            }
            R.id.editarBateriaMC->{
                startActivity(Intent(this, EditarBateria::class.java))
                return true
            }
            R.id.eliminarBateriaMC->{
                startActivity(Intent(this, EditarBateria::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

