package com.example.eduardo.helios.activities

import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.text.InputType
import android.view.View
import android.widget.TextView
import com.example.eduardo.helios.R
import kotlinx.android.synthetic.main.activity_orientacion_optima.*
import java.text.SimpleDateFormat
import java.util.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*



class OrientacionOptima : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {


    /**
     * Instancia de FusedLocationProviderClient
     */
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    /**
     * Ultima ubicacion del usuario
     */
    private lateinit var  lastLocation: Location

    companion object {

        /**
         *  Determina que 1 es que los permisos de localizacion estan activados
         */
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    /**
     * El control del marcador del mapa se asigna a la clase
     */
    override fun onMarkerClick(p0: Marker?) = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orientacion_optima)

        val btnCerrar = btnCerrarMapa
        val fechaTxt: TextView  = findViewById(R.id.nombreT)
        val ubicacionTxt: TextView = findViewById(R.id.ubicationET)

        val map = supportFragmentManager
                .findFragmentById(R.id.mapF) as SupportMapFragment

        val fragmentT = supportFragmentManager.beginTransaction()
        fragmentT.hide(map)
        fragmentT.commit()


        var cal = Calendar.getInstance()
        val dateSetListener = cargarCalendario()



        fechaTxt.setOnClickListener {
            fechaTxt.inputType = InputType.TYPE_NULL
            DatePickerDialog(this@OrientacionOptima, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        ubicacionTxt.setOnClickListener {
            ubicacionTxt.inputType = InputType.TYPE_NULL
            btnCerrar.visibility = View.VISIBLE
            btnCalcular.visibility = View.INVISIBLE
            val mapFragment = supportFragmentManager
                    .findFragmentById(R.id.mapF) as SupportMapFragment
            mapFragment.getMapAsync(this)
            val fragmentManager = supportFragmentManager.beginTransaction()
            fragmentManager.show(mapFragment).commit()


            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        }

        btnCerrar.setOnClickListener {
            btnCerrar.visibility = View.INVISIBLE
            btnCalcular.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction().hide(map).commit()
        }


    }

    /**
     * Metodo que carga el mapa correspondiente al campo ubicacion
     */
    override fun onMapReady(map: GoogleMap) {
        map.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) {  location ->

            if(location != null){
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                placeMarker(currentLatLong,map)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong,5f))
            }
        }


        map.setOnMarkerClickListener(this)
        map.setOnMarkerDragListener(this)
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isRotateGesturesEnabled = false
        setUpMap()
    }


    /**
     * Metodo que determina si los permisos de ubicacion estan habilitados, si no es asi los solicita
     */
    private fun setUpMap(){
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }

    /**
     * Metodo que permite colocar un marcador en el mapa
     */
    private fun placeMarker(location: LatLng, map:GoogleMap){
        var markerOption = MarkerOptions().position(location)
        map.clear()
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        markerOption.draggable(true)
        map.addMarker(markerOption)
        var geocoder = Geocoder(this)
        var ubicacion = geocoder.getFromLocation(markerOption.position.latitude,markerOption.position.longitude,1)
        ubicationET.setText("${ubicacion.first().countryName}, ${ubicacion.first().adminArea}")
    }

    /**
     * Metodo que muestra la direccion de un marcador
     */
    override fun onMarkerDragEnd(marker: Marker) {
        var geocoder = Geocoder(this)
        var ubicacion = geocoder.getFromLocation(marker.position.latitude,marker.position.longitude,1)
        ubicationET.setText("${ubicacion.first().countryName}, ${ubicacion.first().adminArea}")

    }


    /**
     * Metodo que carga el calendario para el campo de fecha
     */
    private fun cargarCalendario(): DatePickerDialog.OnDateSetListener {

        val fechaTxt: TextView  = findViewById(R.id.nombreT)


        fechaTxt.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())


        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            fechaTxt.text = sdf.format(cal.time)
        }
        return dateSetListener

    }



    override fun onMarkerDragStart(p0: Marker) {
        p0.position
    }

    override fun onMarkerDrag(p0: Marker) {
        p0.position
    }


}
