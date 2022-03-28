package com.example.wecareapp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wecareapp.R
import com.example.wecareapp.model.EventGet
import com.example.wecareapp.model.User

class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var userList= mutableListOf<User>()
    var EventList= mutableListOf<EventGet>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerViewAdapter.MyViewHolder{
        val inflater=LayoutInflater.from(parent.context).inflate(R.layout.activity_main,parent,false)
        return MyViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(userList[position]);
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){

        val tv_userN=view.findViewById<TextView>(R.id.tv_emaillog)
        val tv_userP=view.findViewById<TextView>(R.id.tv_passwordlog)

        fun bind(data: User){
            tv_userN.text=data.Email
            tv_userP.text=data.Password
            /*if (data.eventResult=="Bien"){
                ib_eventF.setImageResource(R.drawable.ic_baseline_thumb_up_26)
            }else  ib_eventF.setImageResource(R.drawable.ic_baseline_thumb_down_26)

            when (event.eventScore) {
                0 ->  ib_eventS.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24)
                1 ->  ib_eventS.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24)
                2 ->  ib_eventS.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24)
                3 ->  ib_eventS.setImageResource(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                4 ->  ib_eventS.setImageResource(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                5 ->  ib_eventS.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_24)
                6 ->  ib_eventS.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_24)
                7 ->  ib_eventS.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
                8 ->  ib_eventS.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
                9 ->  ib_eventS.setImageResource(R.drawable.ic_baseline_tag_faces_24)
                10 -> ib_eventS.setImageResource(R.drawable.ic_baseline_tag_faces_24)

                else -> { // Note the block
                    print("x is neither 1 nor 2")
                }
            }*/

        }
    }
}