package com.test.restaurant.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.test.restaurant.R
import com.test.restaurant.model.UserData

class UserAdapter(val c:Context, val userLIst:ArrayList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>()
{
    inner class UserViewHolder(val v: View) : RecyclerView.ViewHolder(v){
        var name:TextView
        var subName:TextView
        var mMenus:ImageView


        init {
            name = v.findViewById<TextView>(R.id.mTitle) //식당이름
            subName = v.findViewById<TextView>(R.id.mSubTitle) //음식이름
            mMenus = v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener{ popupMenus(it) }
        }

        private fun popupMenus(v:View) {
            val position = userLIst[absoluteAdapterPosition]

            val popupMenus = PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editText -> {
                        val v = LayoutInflater.from(c).inflate(R.layout.add_item,null)
                        val name = v.findViewById<EditText>(R.id.restaurantName)
                        val kind = v.findViewById<EditText>(R.id.foodName)
                                AlertDialog.Builder(c)
                                    .setView(v)
                                    .setPositiveButton("ok"){ _, _ ->
                                        position.name = name.text.toString()
                                        position.subName = kind.text.toString()
                                        notifyDataSetChanged()
                                        Toast.makeText(c, "수정되었습니다", Toast.LENGTH_SHORT).show()
                                    }
                                    .setNegativeButton("cancel"){ _, _ ->

                                    }
                                    .create()
                                    .show()
                        true
                    }
                    R.id.deleteText -> {
                        AlertDialog.Builder(c)
                            .setTitle("삭제")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("정말로 삭제하시겠습니까?")
                            .setPositiveButton("예"){ _, _ ->
                                userLIst.removeAt(absoluteAdapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c, "삭제되었습니다", Toast.LENGTH_SHORT).show()
                            }
                            .setNegativeButton("아니요"){ _, _ ->

                            }
                            .create()
                            .show()
                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.food_types1_items,parent,false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userLIst.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.name.text = userLIst.get(position).name
        holder.subName.text = userLIst.get(position).subName
    }

}