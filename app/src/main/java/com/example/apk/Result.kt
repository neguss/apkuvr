package com.example.apk

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
        val pers=findViewById<TextView>(R.id.pers_text)
        val tab=findViewById<LinearLayout>(R.id.tab)
        val save=findViewById<Button>(R.id.save)
        Toast.makeText(this, "123", Toast.LENGTH_SHORT).show()
        save.setOnClickListener{
            var tmp=""
            //addduorow()
            for(i in questions!!.indices){
                if (answers!![i] =="n")
                    tmp="Не соответствует"
                else
                    tmp="Соответствует"
                addduorow(questions[i],tmp)
           }
        }
    }
    private fun addrow(name:String,result:String){
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
    private fun addduorow(name:String,result:String){
        val tab=findViewById<LinearLayout>(R.id.tab)
        val inflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val textfield:View= inflater.inflate(R.layout.textfield_duo,null)
        tab.addView(textfield,tab.childCount-1)
        textfield.id=View.generateViewId()
        var tmp=findViewById<ConstraintLayout>(textfield.id)
        var txt1=tmp.getChildAt(tmp.childCount-2)
        var txt2=tmp.getChildAt(tmp.childCount-1)
        var txt1t=findViewById<TextView>(txt1.id)
        var txt2t=findViewById<TextView>(txt2.id)
        txt1t.text="1"
        txt2t.text="2"

        Toast.makeText(this, "321", Toast.LENGTH_SHORT).show()

    }
}