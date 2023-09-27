package net.wastereduction

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import net.wastereduction.databinding.ActivityMainBinding

class Info : AppCompatActivity() {
    private var requestCamera : ActivityResultLauncher<String>? = null
    private lateinit var binding : ActivityMainBinding  // para acceder a la vista de la app

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//############################################################ESTO PAR LA CAMARA #########################################
        requestCamera = registerForActivityResult(
            ActivityResultContracts
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
    }
    private fun abrirMaps(){
        val intent = Intent(this, Maps::class.java)
        startActivity(intent)
    }
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
}