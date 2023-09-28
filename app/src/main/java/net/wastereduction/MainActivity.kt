package net.wastereduction

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import net.wastereduction.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {

    private var requestCamera : ActivityResultLauncher<String>? = null
    private lateinit var binding : ActivityMainBinding  // para acceder a la vista de la app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//############################################################ESTO PAR LA CAMARA #########################################
        requestCamera = registerForActivityResult(ActivityResultContracts
            .RequestPermission(),){
                if(it){
                    val intent = Intent(this,BarcodeScan::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Permission denied",
                        Toast.LENGTH_SHORT).show()
                }
        }
        binding.btnBc.setOnClickListener(){requestCamera?.launch(Manifest.permission.CAMERA)}
        //codido para poner un icono en un boton
        binding.btnBc.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.baseline_photo_camera_24),null,null,null)

        binding.btnMaps.setOnClickListener { abrirMaps() }

        binding.button1.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_home),null,null,null)

        binding.button1.setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 1
            goHome()
        }
        binding.button2.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_info),null,null,null)

        binding.button2.setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 2
            goInfo()
        }

        binding.button4.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_calendar),null,null,null)
        binding.button4.setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 4
            goSchedule()
        }

        binding.button5.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_goal),null,null,null)

        binding.button5.setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 5
            goGoal()
        }
        binding.btnTopLeft.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_menu),null,null,null)
        binding.btnTopLeft.setOnClickListener{

        }
        binding.btnTopRight.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_profile),null,null,null)
        binding.btnTopRight.setOnClickListener{
            goProfile()
        }
    }
    private fun abrirMaps(){
        val intent = Intent(this, Maps::class.java)
        startActivity(intent)
    }
// funciones para ir a las diferentes ventanas
    private fun goHome(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun goInfo(){
        val intent = Intent(this, Info::class.java)
        startActivity(intent)
    }
    private fun goSchedule(){
        val intent = Intent(this, Schedule::class.java)
        startActivity(intent)
    }
    private fun goGoal(){
        val intent = Intent(this, Goal::class.java)
        startActivity(intent)
    }
    private fun goProfile(){
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

    /*private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //recyclerView.adapter= SuperHeroAdapter(SuperHeroProvider.superHeroList)
    }*/
}