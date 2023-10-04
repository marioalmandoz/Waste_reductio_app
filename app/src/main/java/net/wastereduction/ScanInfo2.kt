package net.wastereduction

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import net.wastereduction.databinding.ActivityMainBinding

class ScanInfo2 : AppCompatActivity() {
    private var requestCamera : ActivityResultLauncher<String>? = null
    private lateinit var binding : ActivityMainBinding  // para acceder a la vista de la app

    var intentData:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.scan_info)

        intentData = getIntent().getStringExtra("barcodeData").toString()
        if (intentData != null && intentData.isNotEmpty()) {
            // Hacer algo con el valor de barcodeData si es necesario
            Toast.makeText(this, "Código de barras escaneado: $intentData", Toast.LENGTH_SHORT).show()
        }
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
        findViewById<Button>(R.id.btnBc).setOnClickListener(){requestCamera?.launch(Manifest.permission.CAMERA)}
        findViewById<Button>(R.id.btnBc).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.baseline_photo_camera_24),null,null,null)

        findViewById<Button>(R.id.button1).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_home),null,null,null)

        findViewById<Button>(R.id.button1).setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 1
            goHome()
        }
        findViewById<Button>(R.id.button2).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_info),null,null,null)

        findViewById<Button>(R.id.button2).setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 2
            goInfo()
        }

        findViewById<Button>(R.id.button4).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_calendar),null,null,null)
        findViewById<Button>(R.id.button4).setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 4
            goSchedule()
        }

        findViewById<Button>(R.id.button5).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_reward),null,null,null)

        findViewById<Button>(R.id.button5).setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 5
            goGoal()
        }
//        findViewById<Button>(R.id.btnTopLeft).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_menu),null,null,null)
////        binding.btnTopLeft.setOnClickListener{
////
////        }
        findViewById<Button>(R.id.btnTopRight).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_profile),null,null,null)
        findViewById<Button>(R.id.btnTopRight).setOnClickListener{
            goProfile()
        }
        findViewById<Button>(R.id.mapButton).setOnClickListener {
            abrirMaps()
        }
        findViewById<Button>(R.id.closeButton).setOnClickListener {
            goBack()
        }
    }

    private fun goBack() {
        val intent = Intent(this, BarcodeScan::class.java)
        startActivity(intent)
    }

    private fun abrirMaps(){
        val intent = Intent(this, Maps::class.java)
        intent.putExtra("barcodeData", intentData)// Aquí pasamos el valor de intentData
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
    private fun goProfile(){
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

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
}