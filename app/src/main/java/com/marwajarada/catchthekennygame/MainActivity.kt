package com.marwajarada.catchthekennygame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var counter:Int=0;
    var timeUp:Boolean=false
    var images= ArrayList<ImageView>()
    var runnable= Runnable{}
    var handler=Handler()
    val random=Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        images= arrayListOf(kennyImg,kennyImg2,kennyImg3,kennyImg4,kennyImg5,kennyImg6,kennyImg7,kennyImg8,kennyImg9)
        hideImage()
        startCounting()
    }

    fun startCounting(){
        object: CountDownTimer(20000,1000){
            override fun onFinish() {
                timeTxt.text="Time's Up !"
                timeUp=true
                handler.removeCallbacks(runnable)
                for (image in images) {
                    image.visibility = View.INVISIBLE
                }
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes") {dialog, which ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }
                alert.show()

            }

            override fun onTick(millisUntilFinished: Long) {
                timeTxt.text="Time :"+ millisUntilFinished/1000
            }

        }.start()

    }


    fun kennyCatched(view: View){
        if (timeUp==false){
            counter++
            scoreTxt.text="Score: "+counter
        }

    }

    fun hideImage(){
            runnable=object : Runnable{
                override fun run() {
                    for (image in images){
                        image.visibility=View.INVISIBLE
                    }
                    val randomIndex=random.nextInt(9)
                    images[randomIndex].visibility=View.VISIBLE
                    handler.postDelayed(runnable,500)

                }


            }
            handler.post(runnable)








    }


}
