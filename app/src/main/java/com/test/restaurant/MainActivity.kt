package com.test.restaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_item.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Button1.setOnClickListener{
            val intent = Intent(this,FoodTypes1::class.java)
            startActivity(intent)

        }

        Button2.setOnClickListener{
            val intent = Intent(this,FoodTypes1::class.java)
            startActivity(intent)
        }

        Button3.setOnClickListener{
            val intent = Intent(this,FoodTypes1::class.java)
            startActivity(intent)
        }

        Button4.setOnClickListener{
            val intent = Intent(this,FoodTypes1::class.java)
            startActivity(intent)
        }

        Button5.setOnClickListener{
            val intent = Intent(this,FoodTypes1::class.java)
            startActivity(intent)
        }

        Button6.setOnClickListener{
            val intent = Intent(this,FoodTypes1::class.java)
            startActivity(intent)
        }

    }
}