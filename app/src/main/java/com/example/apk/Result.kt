package com.example.apk

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.util.jar.Manifest

class Result : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val answers=intent.getStringArrayExtra("RES_ARR")
        val questions=intent.getStringArrayExtra("Q_ARR")
        val cred=intent.getStringArrayExtra("CRED")
        val objtext=findViewById<TextView>(R.id.Obj_Val)
        val perstext=findViewById<TextView>(R.id.Pers_Val)
        val datetext=findViewById<TextView>(R.id.Date_Val)
        val cancelbutton=findViewById<Button>(R.id.cancel)
        val donebutton=findViewById<Button>(R.id.save)
        objtext.text=cred!![0]
        perstext.text= cred[1]
        datetext.text=cred[2]
        var tmp =""
        var mark=false
        for(i in questions!!.indices){
            if (answers!![i] =="n") {
                tmp = "Не соответствует"
                mark=false
            }
            else {
                tmp = "Соответствует"
                mark=true
            }
            addduorow(questions[i],tmp,mark)
        }
        donebutton.setOnClickListener{
            if(ContextCompat.checkSelfPermission(applicationContext,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED) {
                val out = File("/storage/emulated/0/Download/11111.csv")
                if (!out.exists())
                    out.createNewFile()
            }
            else
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),12)
                Toast.makeText(this, "Нет разрешения на запись", Toast.LENGTH_SHORT).show()

        }
        cancelbutton.setOnClickListener{
            finish()
        }
    }
    @SuppressLint("ResourceAsColor")
    private fun addduorow(name:String, result:String, clr:Boolean){
        val tab=findViewById<LinearLayout>(R.id.tab)
        val inflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val textfield:View= inflater.inflate(R.layout.textfield_duo,null)
        tab.addView(textfield,tab.childCount)
        textfield.id=View.generateViewId()
        val tmp=findViewById<ConstraintLayout>(textfield.id)
        if(clr)
            textfield.setBackgroundColor(resources.getColor(R.color.good))
        else
            textfield.setBackgroundColor(resources.getColor(R.color.bad))
        val txt1=tmp.getChildAt(tmp.childCount-2)
        val txt2=tmp.getChildAt(tmp.childCount-1)
        txt1.id=View.generateViewId()
        txt2.id=View.generateViewId()
        val txt1t=findViewById<TextView>(txt1.id)
        val txt2t=findViewById<TextView>(txt2.id)
        txt1t.text=name
        txt2t.text=result

        Toast.makeText(this, "321", Toast.LENGTH_SHORT).show()

    }
}