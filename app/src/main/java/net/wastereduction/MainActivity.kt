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
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import net.wastereduction.databinding.ActivityMainBinding
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.newFixedThreadPoolContext


class MainActivity : AppCompatActivity() {

    private var requestCamera: ActivityResultLauncher<String>? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
<<<<<<< HEAD
=======
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
        binding.btnBc.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.baseline_photo_camera_24),null,null,null)





        binding.button1.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_home),null,null,null)

        binding.button1.setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 1

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
>>>>>>> 67856858d9fade2d94ccd30b7818bb35b06ff1e7
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
<<<<<<< HEAD
=======




>>>>>>> 67856858d9fade2d94ccd30b7818bb35b06ff1e7
class SomeActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout;
    private lateinit var navigationView: NavigationView;
    private lateinit var drawerToggle: ActionBarDrawerToggle;
    private var isDrawerOpen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();






        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle navigation item clicks here
            when (menuItem.itemId) {
                R.id.home -> {
                    // Handle item 1 click
                    true
                }

                R.id.settings -> {
                    // Handle item 2 click
                    true
                }

                R.id.home -> {
                    // Handle item 1 click
                    true
                }

                R.id.home3 -> {
                    // Handle item 1 click
                    true
                }

                R.id.home4 -> {
                    // Handle item 1 click
                    true
                }

                R.id.home6 -> {
                    // Handle item 1 click
                    true
                }

                R.id.home7 -> {
                    // Handle item 1 click
                    true
                }
                // Add more cases for other menu items
                else -> false
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle navigation icon click
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            // Close the navigation drawer if it's open
            drawerLayout.closeDrawer(navigationView)
        } else {
            // Handle back press as needed for your app
            super.onBackPressed()
        }
    }

    class OtherActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            // Assuming you have a reference to the view you want to bring to the front
            val myView = findViewById<View>(R.id.drawer_layout)

            // Bring the view to the front
            myView.bringToFront()
        }
    }
}



