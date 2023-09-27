package net.wastereduction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Maps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map:GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        createFragment()
    }

    private fun createFragment(){// para crear el fragmento
        val mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {// es un metodo creado por añadir OnMapReadyCallback
        map = googleMap
        createMarker()
    }

    private fun createMarker(){ // para añadir puntos en el mapa y animaciones de enfoque automaticos
        val coordinates = LatLng(51.451587, 5.453913)
        val marker : MarkerOptions = MarkerOptions().position(coordinates).title("ICT University")
        map.addMarker(marker)
        map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordinates, 18f), 4000,null   // 4000 es un segundo
        )
    }
}