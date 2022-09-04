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
        val answers=intent.getIntArrayExtra(Q_ARR)
        val questions=intent.getStringArrayExtra(RES_ARR)
        val tab=findViewById<LinearLayout>(R.id.tab)
        val save=findViewById<Button>(R.id.save)

        save.setOnClickListener{
            var tmp=""
            for(i in answers!!.indices){
                if (answers[i]==0)
                    tmp="Не соответствует"
                else
                    tmp="Соответствует"
                addrow(questions!![i],tmp)
            }
        }

    }
    private fun addrow(name:String,result:String){
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
    }
}