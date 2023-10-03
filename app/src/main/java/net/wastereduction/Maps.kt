package net.wastereduction

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Dot
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PatternItem
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class Maps : AppCompatActivity(), OnMapReadyCallback, OnMyLocationButtonClickListener,GoogleMap.OnMyLocationClickListener  {

    private lateinit var map: GoogleMap
    private lateinit var btnCalculate:Button

    private var start:String = ""
    private var end:String = ""

    var intentData:String = ""

    var poly: Polyline? = null

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        intentData = getIntent().getStringExtra("barcodeData").toString()
        if (intentData != null && intentData.isNotEmpty()) {
            // Hacer algo con el valor de barcodeData si es necesario
            Toast.makeText(this, "Código de barras escaneado: $intentData", Toast.LENGTH_SHORT).show()
        }


        btnCalculate= findViewById(R.id.btnCalculateRoute)
        btnCalculate.setOnClickListener {
            start = ""
            end = ""
            poly?.remove()
            poly = null
            Toast.makeText(this, "Selecciona punto de origen y final", Toast.LENGTH_SHORT).show()
            if(::map.isInitialized){
                map.setOnMapClickListener {
                    if(start.isEmpty()){
                        //La cordenada tiene que ser asi 3,344515 , 14,455153
                        start = "${it.longitude},${it.latitude}"
                    }else if(end.isEmpty()){
                        end = "${it.longitude},${it.latitude}"
                       // createMarker()
                        createRoute()
                    }
                }
            }
        }
        createFragment()
    }

    private fun createFragment() {// para crear el fragmento
        val mapFragment: SupportMapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {// es un metodo creado por añadir OnMapReadyCallback
        map = googleMap
        createMarker()
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
        enableLocation()

    }

    private fun createPolylines(routeResponse: RouteResponse){// funcion para crear lineas
        val polyLineOptions = PolylineOptions()
        routeResponse?.features?.first()?.geometry?.coordinates?.forEach {
            polyLineOptions.add(LatLng(it[1], it[0]))
        }
        runOnUiThread {
            poly = map.addPolyline(polyLineOptions)
        }
    }

    private fun createMarker() { // para añadir puntos en el mapa y animaciones de enfoque automaticos
        //val coordinates = LatLng(43.315617, -1.980494)// mi casa
        if(intentData=="840023235290"){
            val coordinates = LatLng(51.450527, 5.463714)
            val marker: MarkerOptions = MarkerOptions().position(coordinates).title("Movile container")
            map.addMarker(marker)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordinates, 18f), 4000, null   // 4000 es un segundo
            )
        }else if(intentData=="90453533"){
            val coordinates = LatLng(51.451357, 5.457867)
            val marker: MarkerOptions = MarkerOptions().position(coordinates).title("Plastic container")
            map.addMarker(marker)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordinates, 18f), 4000, null   // 4000 es un segundo
            )
        }else if(intentData==""){
            val coordinates = LatLng(51.448589, 5.452264)
            val marker: MarkerOptions = MarkerOptions().position(coordinates).title("Paper container")
            map.addMarker(marker)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordinates, 18f), 4000, null   // 4000 es un segundo
            )
        }


    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    @SuppressLint("MissingPermission")
    private fun enableLocation() {
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()) {
            map.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(this, "Ve a ajsutes y acepta los permisos bro", Toast.LENGTH_SHORT)
                .show()
        } else
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
    }


    @SuppressLint("MissingPermission", "MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    this,
                    "Para activar la localizacion ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    @SuppressLint("MissingPermission")
    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        if(!isLocationPermissionGranted()){
            map.isMyLocationEnabled = false
            Toast.makeText(this, "Para activar la notificacion ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Boton pulsado", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Estas en ${p0.latitude},${p0.longitude}", Toast.LENGTH_SHORT).show()
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.openrouteservice.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    private fun createRoute() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getRoute("5b3ce3597851110001cf62486591c2d88d34496898907edd0f5f77c0", start, end)
            if (call.isSuccessful) {
                call.body()?.let { createPolylines(it) }
            } else {
                Log.i("aris", "KO")
            }
        }
    }
}