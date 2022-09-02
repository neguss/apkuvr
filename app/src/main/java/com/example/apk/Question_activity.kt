package com.example.apk

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.selects.select


class Question_activity : AppCompatActivity() {

    companion object{
        const val OBJ_ID= ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question)
        val resultintent= Intent(this,Result::class.java)
        val complete_button=findViewById<Button>(R.id.complete)
        val no_button=findViewById<Button>(R.id.no_button)
        val yes_button=findViewById<Button>(R.id.yes_button)
        val back=findViewById<Button>(R.id.back_button)
        val forw=findViewById<Button>(R.id.forward_button)
        val counter=findViewById<TextView>(R.id.count_text)
        val qtext=findViewById<TextView>(R.id.question_text)
        val seek=findViewById<ConstraintLayout>(R.id.seek)
        val select=findViewById<SeekBar>(R.id.select_question)
        var curr_question=0
        var answers= emptyArray<Int>()

        val o_id=intent.getStringArrayExtra(OBJ_ID)
        counter.text="1/"+o_id!!.size.toString()
        if(o_id!=null) {
            answers = Array<Int>(o_id.size) { -1 }
            qtext.text = o_id[0]//фокус на первый вопрос в списке
        }
        no_button.setOnClickListener{
            if (curr_question+1>o_id!!.size-1) {
                answers[curr_question]=0
                Toast.makeText(this, "Последний вопрос", Toast.LENGTH_LONG).show()
            }
            else{
                answers[curr_question]=0
                qtext.text= o_id!![curr_question+1]
                curr_question +=1
                counter.text=(curr_question+1).toString()+"/"+(o_id!!.size).toString()
            }
            when (answers[curr_question]){
                0 -> {
                    no_button.setBackgroundColor(resources.getColor(R.color.chosen))
                    yes_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
                1 ->{
                    yes_button.setBackgroundColor(resources.getColor(R.color.chosen))
                    no_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
                else -> {
                    no_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                    yes_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
            }
        }
        yes_button.setOnClickListener{
            if (curr_question+1>o_id!!.size-1){
                answers[curr_question]=1
                Toast.makeText(this,"Последний вопрос",Toast.LENGTH_LONG).show()
            }
            else{
                answers[curr_question]=1
                qtext.text= o_id!![curr_question+1]
                curr_question +=1
                counter.text=(curr_question+1).toString()+"/"+(o_id!!.size).toString()
            }
            when (answers[curr_question]){
                0 -> {
                    no_button.setBackgroundColor(resources.getColor(R.color.chosen))
                    yes_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
                1 ->{
                    yes_button.setBackgroundColor(resources.getColor(R.color.chosen))
                    no_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
                else -> {
                    no_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                    yes_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
            }
        }
        back.setOnClickListener{
            if (curr_question-1<0) {
                qtext.text = o_id[o_id.size-1]
                curr_question = o_id.size-1
                counter.text=(curr_question+1).toString()+"/"+(o_id!!.size).toString()
            }
            else {
                qtext.text = o_id!![curr_question - 1]
                curr_question -= 1
                counter.text=(curr_question+1).toString()+"/"+(o_id!!.size).toString()
            }
            when (answers[curr_question]){
                0 -> {
                    no_button.setBackgroundColor(resources.getColor(R.color.chosen))
                    yes_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
                1 ->{
                    yes_button.setBackgroundColor(resources.getColor(R.color.chosen))
                    no_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
                else -> {
                    no_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                    yes_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
            }

        }
        forw.setOnClickListener{
            if (curr_question+1>o_id!!.size-1) {
                qtext.text = o_id!![0]
                curr_question = 0
                counter.text=(curr_question+1).toString()+"/"+(o_id!!.size).toString()
            }
            else {
                qtext.text = o_id!![curr_question + 1]
                curr_question += 1
                counter.text=(curr_question+1).toString()+"/"+(o_id!!.size).toString()
            }
            when (answers[curr_question]){
                0 -> {
                    no_button.setBackgroundColor(resources.getColor(R.color.chosen))
                    yes_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
                1 ->{
                    yes_button.setBackgroundColor(resources.getColor(R.color.chosen))
                    no_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
                else -> {
                    no_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                    yes_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                }
            }

        }
        counter.setOnClickListener {
            seek.visibility = View.VISIBLE
        }
        complete_button.setOnClickListener{
            resultintent.putExtra(Result.Q_ARR,o_id)
            resultintent.putExtra(Result.RES_ARR,answers)
            startActivity(resultintent)
        }
        select.min=1
        select.max=o_id.size
        select.progress=0

        select.setOnSeekBarChangeListener(
            object:SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(select: SeekBar, p1: Int, p2: Boolean) {
                    counter.text=select.progress.toString()+"/"+(o_id!!.size).toString()
                }
                override fun onStartTrackingTouch(select: SeekBar) {}
                override fun onStopTrackingTouch(select: SeekBar) {
                    qtext.text = o_id!![select.progress-1]
                    curr_question = select.progress-1
                    when (answers[curr_question]){
                        0 -> {
                            no_button.setBackgroundColor(resources.getColor(R.color.chosen))
                            yes_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                        }
                        1 ->{
                            yes_button.setBackgroundColor(resources.getColor(R.color.chosen))
                            no_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                        }
                        else -> {
                            no_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                            yes_button.setBackgroundColor(resources.getColor(R.color.purple_500))
                        }
                    }
                    seek.visibility = View.INVISIBLE
                }
            }
        )
    }

}

