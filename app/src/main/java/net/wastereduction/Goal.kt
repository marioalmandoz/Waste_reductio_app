package net.wastereduction

import android.Manifest
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import net.wastereduction.adapter.RemainderAdapter
import net.wastereduction.databinding.ActivityMainBinding

class Goal : AppCompatActivity() {
    private var requestCamera: ActivityResultLauncher<String>? = null
    private lateinit var binding: ActivityMainBinding  // para acceder a la vista de la app
    private lateinit var drawerLayout: DrawerLayout;
    private lateinit var navigationView: NavigationView;
    private lateinit var drawerToggle: ActionBarDrawerToggle;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_goal)

        drawerLayout = findViewById(R.id.drawer_layout);    //gets the drawerlayout id from XML
        navigationView = findViewById(R.id.nav_view);       //gets the nav id from XML
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);    //Create a new ActionBarDrawerToggle
        drawerLayout.addDrawerListener(drawerToggle);   //makes the drawerlayout open and close
        drawerToggle.syncState();


        val btnTopLeft: ImageButton = findViewById(R.id.menuButton)

        btnTopLeft.setOnClickListener {
            Log.d("Button Click", "btnTopLeft clicked")
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }


        val circularProgressBar = findViewById<CircularProgressBar>(R.id.progress_bar)
        circularProgressBar.apply {
            // Set Progress
            progressMax = 180f
            progress = 0f
            progressBarWidth = 20f
            backgroundProgressBarWidth = 12f
            progressBarColor = Color.rgb(158, 178, 59)
            startAngle = 180f
            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
            setProgressWithAnimation(90f, 1000)
        }

        val circularProgressBar2 = findViewById<CircularProgressBar>(R.id.progress_bar2)
        circularProgressBar2.apply {
            // Set Progress
            progressMax = 180f
            progress = 0f
            progressBarWidth = 20f
            backgroundProgressBarWidth = 12f
            progressBarColor = Color.rgb(158, 178, 59)
            startAngle = 180f
            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
            setProgressWithAnimation(180f, 1000)
        }

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

//        binding.btnMaps.setOnClickListener { abrirMaps() }

        findViewById<Button>(R.id.button1).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_home),null,null,null)
            findViewById<Button>(R.id.button1).text = ""
        findViewById<Button>(R.id.button1).setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 1
            goHome()
        }
//        findViewById<Button>(R.id.button2).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_info),null,null,null)
//
//        findViewById<Button>(R.id.button2).setOnClickListener {
//            // Acción a realizar cuando se hace clic en el Botón 2
//            goInfo()
//        }
//
//        findViewById<Button>(R.id.button4).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_calendar),null,null,null)
//        findViewById<Button>(R.id.button4).setOnClickListener {
//            // Acción a realizar cuando se hace clic en el Botón 4
//            goSchedule()
//        }

        findViewById<Button>(R.id.button5).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_reward),null,null,null)

        findViewById<Button>(R.id.button5).setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 5
            goGoal()
        }
//        findViewById<Button>(R.id.btnTopLeft).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_menu),null,null,null)
//        binding.btnTopLeft.setOnClickListener{
//
////        }
//
        findViewById<Button>(R.id.btnTopRight).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_profile),null,null,null)
        findViewById<Button>(R.id.btnTopRight).setOnClickListener{
            goProfile()
        }
    }
    //Funciones para ir a las diferentes ventanas
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
    private fun goProfile(){
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle navigation icon click
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // Close the navigation drawer if it's open
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            // Handle back press as needed for your app
            super.onBackPressed()
        }
    }
}

