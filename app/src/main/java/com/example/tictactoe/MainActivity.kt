package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var us1: user
    lateinit var us2: user
    var figure1 = 1
    var figure2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioButtonX1.isChecked = true
        binding.radioButtonO2.isChecked = true

        binding.buttonStart.setOnClickListener {
            if(binding.editFirst.text.toString() == "" || binding.editSecond.text.toString() == ""){
                Toast.makeText(this, "Заполните оба имени!", Toast.LENGTH_SHORT).show()
            }else {
                us1 = user(binding.editFirst.text.toString(), figure1)
                us2 = user(binding.editSecond.text.toString(), figure2)

                val intent = Intent(this, PlayActivity::class.java)
                intent.putExtra("KEY1", binding.editFirst.text.toString())
                intent.putExtra("KEY2", binding.editSecond.text.toString())
                intent.putExtra("us1", figure1.toString())
                intent.putExtra("us2", figure2.toString())
                startActivity(intent)
            }
        }

        binding.radioButtonX1.setOnClickListener {
            if(binding.radioButtonX1.isChecked) {
                binding.radioButtonO1.isChecked = false
                binding.radioButtonX2.isChecked=false
                binding.radioButtonO2.isChecked = true
            }
            figure1 = 1
            figure2 = 0
        }
        binding.radioButtonO1.setOnClickListener{
            if(binding.radioButtonO1.isChecked){
                binding.radioButtonX1.isChecked = false
                binding.radioButtonO2.isChecked=false
                binding.radioButtonX2.isChecked=true
            }
            figure1 = 0
            figure2 = 1
        }
        binding.radioButtonX2.setOnClickListener {
            if(binding.radioButtonX2.isChecked){
                binding.radioButtonO2.isChecked = false
                binding.radioButtonX1.isChecked=false
                binding.radioButtonO1.isChecked=true
            }
            figure1 = 0
            figure2 = 1
        }
        binding.radioButtonO2.setOnClickListener{
            if(binding.radioButtonO2.isChecked) {
                binding.radioButtonX2.isChecked = false
                binding.radioButtonO1.isChecked=false
                binding.radioButtonX1.isChecked=true
            }
            figure1 = 1
            figure2 = 0
        }


    }
}