package com.example.testrootstack.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testrootstack.R
import com.example.testrootstack.models.PeopleBean
import kotlinx.android.synthetic.main.people_list_item.view.*

class PeopleAdapter(private val items: ArrayList<PeopleBean.People> , val context : Context) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.people_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position, context)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(people: PeopleBean.People, position: Int, context: Context){
            itemView.txtName.text = people.name
            itemView.txtHair.text = itemView.resources.getString(R.string.litle_name,people.skin_color, people.mHeight)
            if(position % 2 == 0){
                itemView.imgLogo.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.logo_cableonda))
            }else{
                itemView.imgLogo.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.mas_movil_orange))
            }
        }
    }
}