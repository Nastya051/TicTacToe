package com.example.tictactoe

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.tictactoe.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayBinding
    var name1 = ""
    var name2 = ""
    var figure1: Int? = -1
    var figure2: Int? = -1
    var score1 = 0
    var score2 = 0
    var table = ArrayList<Int>()
    var images: ArrayList<Int> = ArrayList<Int>()
    var step = 1
    var imViews: ArrayList<ImageView> = ArrayList<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for(i in 0..8){
            table.add(-1)
        }

        images.add(binding.imageView11.id)
        images.add(binding.imageView12.id)
        images.add(binding.imageView13.id)
        images.add(binding.imageView21.id)
        images.add(binding.imageView22.id)
        images.add(binding.imageView23.id)
        images.add(binding.imageView31.id)
        images.add(binding.imageView32.id)
        images.add(binding.imageView33.id)

        imViews.add(binding.imageView11)
        imViews.add(binding.imageView12)
        imViews.add(binding.imageView13)
        imViews.add(binding.imageView21)
        imViews.add(binding.imageView22)
        imViews.add(binding.imageView23)
        imViews.add(binding.imageView31)
        imViews.add(binding.imageView32)
        imViews.add(binding.imageView33)

        imViews.forEach {
            it.setOnClickListener(::onClickImage)
        }


        name1 = intent.getStringExtra("KEY1").toString()
        name2 = intent.getStringExtra("KEY2").toString()
        figure1 = intent.getStringExtra("us1")?.toInt()
        figure2 = intent.getStringExtra("us2")?.toInt()


        binding.tvName1.text = name1
        binding.tvName2.text = name2
        val str = resources.getString(R.string.score) + "0"
        binding.tvScore1.text = str
        binding.tvScore2.text = str
    }

    fun chooseNextImage(index: Int): Int {
        if(table[index] == -1){
            if(step == 1) {
                table[index] = step
                step = 0
                return R.drawable.krestik
            }
            else {
                table[index] = step
                step = 1
                return R.drawable.nolik
            }
        }
        return 0
    }

    fun whoWinner(){
        val result1 = "Поздравляю! Победил игрок $name1"
        val result2 = "Поздравляю! Победил игрок $name2"
        val result3 = "Поздравляю! Ничья"
        if (step == 0){
            if(figure1==1) {
                score1++
                val str = resources.getString(R.string.score) + score1
                binding.tvScore1.text = str
                showDialog(result1)
            }else{
                score2++
                val str = resources.getString(R.string.score) + score2
                binding.tvScore2.text = str
                showDialog(result2)
            }
        }
        else {
            if(figure1==0) {
                score1++
                val str = resources.getString(R.string.score) + score1
                binding.tvScore1.text = str
                showDialog(result1)
            }else{
                score2++
                val str = resources.getString(R.string.score) + score2
                binding.tvScore2.text = str
                showDialog(result2)
            }
        }
    }

    fun checkTable(){
        val result1 = "Поздравляю! Победил игрок $name1"
        val result2 = "Поздравляю! Победил игрок $name2"
        val result3 = "Поздравляю! Ничья"
        var a = false

        for(i in 0..8 step 3){
                if (table[i] != -1 && table[i] == table[i + 1] && table[i] == table[i + 2]) {
                    whoWinner()
                }
        }
        for(i in 0..2 step 1){
            if(table[i] != -1 && table[i] == table[i+3] && table[i] == table[i+6]){
                whoWinner()
            }
        }
        if(table[0] != -1 && table[0] == table[4] && table[0] == table[8]){
            whoWinner()
        }
        if(table[2] != -1 && table[2] == table[4] && table[2] == table[6]){
            whoWinner()
        }else {
            for(i in table){
                if(i==-1)
                    a=true
            }
        }
        if(!a){
            showDialog(result3)
        }

    }

    fun showDialog(winnerName: String){
        var builder = AlertDialog.Builder(this)
        builder.setMessage(" $winnerName!")
            .setCancelable(false)
            .setPositiveButton("Продолжить", {dialog, which ->
                Toast.makeText(this, "Вы сделали правильный выбор :)", Toast.LENGTH_SHORT).show()
            })
            .setNegativeButton("В главное меню") { dialog, which ->
                Toast.makeText(applicationContext, "До скорой встречи!", Toast.LENGTH_SHORT).show()
                score1 = 0
                score2 = 0
            finish()}
            .show()

        for(i in 0..8){
            table[i] = -1
        }
        step = 1


        imViews.forEach {
            it.setImageResource(R.drawable.white)
        }

    }

    fun onClickImage(view: View){
        with (view as ImageView) {
            Log.d("TAG", "$id")
            when(id) {
                images[0] -> {
                    binding.imageView11.setImageResource(chooseNextImage(0))
                    checkTable()
                }
                images[1] -> {
                    binding.imageView12.setImageResource(chooseNextImage(1))
                    checkTable()
                }
                images[2] -> {
                    binding.imageView13.setImageResource(chooseNextImage(2))
                    checkTable()
                }
                images[3] -> {
                    binding.imageView21.setImageResource(chooseNextImage(3))
                    checkTable()
                }
                images[4] -> {
                    binding.imageView22.setImageResource(chooseNextImage(4))
                    checkTable()
                }
                images[5] -> {
                    binding.imageView23.setImageResource(chooseNextImage(5))
                    checkTable()
                }
                images[6] -> {
                    binding.imageView31.setImageResource(chooseNextImage(6))
                    checkTable()
                }
                images[7] -> {
                    binding.imageView32.setImageResource(chooseNextImage(7))
                    checkTable()
                }
                images[8] -> {
                    binding.imageView33.setImageResource(chooseNextImage(8))
                    checkTable()
                }
                else -> {}
            }
            }
        }
}