package net.wastereduction

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import net.wastereduction.databinding.ActivityMainBinding

class Profile : AppCompatActivity() {
    private var requestCamera : ActivityResultLauncher<String>? = null
    private lateinit var binding : ActivityMainBinding  // para acceder a la vista de la app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_profile)
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

        findViewById<ImageView>(R.id.button3).setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 1
            goHome()
        }
    }

    private fun abrirMaps(){
        val intent = Intent(this, Maps::class.java)
        startActivity(intent)
    }
    //Funciones para ir a las diferentes ventanas
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
}