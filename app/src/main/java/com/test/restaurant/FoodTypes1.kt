package com.test.restaurant

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.test.restaurant.model.UserData
import com.test.restaurant.view.UserAdapter
import kotlinx.android.synthetic.main.add_item.*
import kotlinx.android.synthetic.main.food_types1_items.*

class FoodTypes1 : AppCompatActivity() {

    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_types1)

        /* set find Id */
        addsBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.mRecycler)
        userList = ArrayList()
        userAdapter = UserAdapter(this, userList)
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter
        addsBtn.setOnClickListener {
            addinfo()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun addinfo() {

        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item, null)

        val restaurantName = v.findViewById<EditText>(R.id.restaurantName)
        val foodName = v.findViewById<EditText>(R.id.foodName)

        val addDialog = AlertDialog.Builder(this)
            addDialog.setView(v)
            .setPositiveButton("Ok"){ _, _ ->
                val names = restaurantName.text.toString()
                val kind = foodName.text.toString()
                userList.add(UserData("식당이름: $names", "음식종류: $kind"))
                userAdapter.notifyDataSetChanged()
                Toast.makeText(this, "저장됨", Toast.LENGTH_SHORT).show()
            }

            .setNegativeButton("Cancel"){ _, _ ->
                Toast.makeText(this, "취소됨", Toast.LENGTH_SHORT).show()
            }
        addDialog.create()
        addDialog.show()

    }
}