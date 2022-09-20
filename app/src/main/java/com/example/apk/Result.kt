package com.example.apk

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class Result : AppCompatActivity() {

    companion object{
        const val RES_ARR="RES_ARR"
        const val Q_ARR="Q_ARR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val answers=intent.getStringArrayExtra("RES_ARR")
        val questions=intent.getStringArrayExtra("Q_ARR")
        val cred=intent.getStringArrayExtra("CRED")
        val tab=findViewById<LinearLayout>(R.id.tab)
        val save=findViewById<Button>(R.id.save)
        val objtext=findViewById<TextView>(R.id.Obj_Val)
        val perstext=findViewById<TextView>(R.id.Pers_Val)
        val datetext=findViewById<TextView>(R.id.Date_Val)
        val cancelbutton=findViewById<Button>(R.id.cancel)
        objtext.text=cred!![0]
        perstext.text=cred!![1]
        datetext.text=cred!![2]
        Toast.makeText(this, "123", Toast.LENGTH_SHORT).show()
        var tmp=""
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
        cancelbutton.setOnClickListener{
            finish()
        }
    }
    @SuppressLint("ResourceAsColor")
    private fun addduorow(name:String, result:String, clr:Boolean){
        var tab=findViewById<LinearLayout>(R.id.tab)
        var inflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var textfield:View= inflater.inflate(R.layout.textfield_duo,null)
        tab.addView(textfield,tab.childCount)
        textfield.id=View.generateViewId()
        var tmp=findViewById<ConstraintLayout>(textfield.id)
        if(clr)
            textfield.setBackgroundColor(resources.getColor(R.color.good))
        else
            textfield.setBackgroundColor(resources.getColor(R.color.bad))
        var txt1=tmp.getChildAt(tmp.childCount-2)
        var txt2=tmp.getChildAt(tmp.childCount-1)
        txt1.id=View.generateViewId()
        txt2.id=View.generateViewId()
        var txt1t=findViewById<TextView>(txt1.id)
        var txt2t=findViewById<TextView>(txt2.id)
        txt1t.text=name
        txt2t.text=result

        Toast.makeText(this, "321", Toast.LENGTH_SHORT).show()

    }
}