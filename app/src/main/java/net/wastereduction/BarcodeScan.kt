package net.wastereduction

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import net.wastereduction.databinding.ActivityBarcodeScanBinding
import net.wastereduction.databinding.ActivityMainBinding
import java.io.IOException

class BarcodeScan : AppCompatActivity() {
    private lateinit var binding: ActivityBarcodeScanBinding
    private lateinit var barcodeDetector: BarcodeDetector
    private lateinit var cameraSource: CameraSource
    var intentData  = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarcodeScanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnBc.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.baseline_photo_camera_24),null,null,null)

        binding.button1.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_home),null,null,null)

        binding.button1.setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 1
            goHome()
        }
//        binding.button2.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_info),null,null,null)
//
//        binding.button2.setOnClickListener {
//            // Acción a realizar cuando se hace clic en el Botón 2
//            goInfo()
//        }
//
//        binding.button4.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_calendar),null,null,null)
//        binding.button4.setOnClickListener {
//            // Acción a realizar cuando se hace clic en el Botón 4
//            goSchedule()
//        }

        binding.button5.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_reward),null,null,null)

        binding.button5.setOnClickListener {
            // Acción a realizar cuando se hace clic en el Botón 5
            goGoal()
        }
//        findViewById<Button>(R.id.menuButton).setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_menu),null,null,null)
//        binding..setOnClickListener{
//
//        }
        binding.btnTopRight.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.icon_profile),null,null,null)
        binding.btnTopRight.setOnClickListener{
            goProfile()
        }
    }
    private fun iniBc(){
        barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()
        cameraSource = CameraSource.Builder(this,barcodeDetector)
            .setRequestedPreviewSize(1920,1080)
            .setAutoFocusEnabled(true)
            //.setFacing(CameraSource.CAMERA_FACING_FRONT)
            .build()
        binding.surfaceView!!.holder.addCallback(object :SurfaceHolder.Callback{
            @SuppressLint("MissingPermission")
            override fun surfaceCreated(p0: SurfaceHolder) {
                try {
                    cameraSource.start(binding.surfaceView!!.holder)

                }catch(e:IOException){
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {
                cameraSource.stop()
            }
        })
        barcodeDetector.setProcessor(object :Detector.Processor<Barcode>{
            override fun release() {
                Toast.makeText(applicationContext, "barcode scanner has been stopped",
                    Toast.LENGTH_SHORT).show()
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcode = detections.detectedItems
                if(barcode.size()!= 0){
                    binding.txtBarcodeValue!!.post{
                        binding.btnAction!!.text = "SEARCH ITEM"
                        intentData = barcode.valueAt(0).displayValue
                        binding.txtBarcodeValue.setText(intentData)
                        //////////AQUI VOY A EMPEZAR A COGER EL NUEVO DEL CODIGO DE BARRAS PARA PONER INFO
                        if(intentData!=null){
                            //TODO pra hacer un popUp
                            if(intentData=="5000112648102"){
                                goScan_Info()
                            }else if(intentData=="90453533"){
                                goScan_Info2()
                            }



                        }
                        //finish()
                    }
                }
            }
        })

    }

    private fun goScan_Info2() {
        val intent = Intent(this, ScanInfo2::class.java)
        intent.putExtra("barcodeData", intentData)// Aquí pasamos el valor de intentData
        startActivity(intent)
    }

    private fun goScan_Info() {
        val intent = Intent(this, ScanInfo::class.java)
        intent.putExtra("barcodeData", intentData)// Aquí pasamos el valor de intentData
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        cameraSource!!.release()
    }

    override fun onResume() {
        super.onResume()
        iniBc()
    }


    //Funciones para ir a las diferentes ventanas
    private fun abrirMaps(intentData: String){
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
}