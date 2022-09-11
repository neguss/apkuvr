package com.example.apk

import android.app.ActionBar
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

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
        val tab=findViewById<LinearLayout>(R.id.tab)
        val save=findViewById<Button>(R.id.save)
        save.setOnClickListener{
            var tmp=""
            for(i in questions!!.indices){
                if (answers!![i] =="n")
                    tmp="Не соответствует"
                else
                    tmp="Соответствует"
                addrow(questions[i],tmp)
            }
        }
    }
    private fun addrow(name:String,result:String){
        /*
        val tabcol1=findViewById<LinearLayout>(R.id.col1)
        val tabcol2=findViewById<LinearLayout>(R.id.col2)
        val inflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val textfield1:View= inflater.inflate(R.layout.textfield,null)
        val textfield2:View= inflater.inflate(R.layout.textfield,null)
        tabcol1.addView(textfield1,tabcol1.childCount-1)
        tabcol2.addView(textfield2,tabcol2.childCount-1)
        textfield1.id=View.generateViewId()
        textfield2.id=View.generateViewId()
        val txt1= findViewById<TextView>(textfield1.id)
        val txt2= findViewById<TextView>(textfield2.id)
        txt1.text=name
        txt2.text=result
        */
        val tab=findViewById<LinearLayout>(R.id.tab)
        val inflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val textfield:View= inflater.inflate(R.layout.text_row,null)
        tab.addView(textfield,tab.childCount-1)
        textfield.id=View.generateViewId()
        val cont=findViewById<LinearLayout>(textfield.id)
        val tmp1= cont.getChildAt(cont.childCount-2)
        val tmp2= cont.getChildAt(cont.childCount-1)
        tmp2.textAlignment=View.TEXT_ALIGNMENT_GRAVITY
        tmp1.id=View.generateViewId()
        tmp2.id=View.generateViewId()
        val txt1=findViewById<TextView>(tmp1.id)
        val txt2=findViewById<TextView>(tmp2.id)
        txt1.text=name
        txt2.text=result


    }
}