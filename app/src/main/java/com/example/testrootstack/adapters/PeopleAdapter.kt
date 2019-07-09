package com.example.testrootstack.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.example.testrootstack.R
import com.example.testrootstack.models.PeopleBean
import kotlinx.android.synthetic.main.people_list_item.view.*


class PeopleAdapter(private var items: ArrayList<PeopleBean.People> , private var peopleListFiltered: ArrayList<PeopleBean.People>, val context : Context) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>(), Filterable {

    //var peopleListFiltered : ArrayList<PeopleBean.People>? = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.people_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return peopleListFiltered.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(peopleListFiltered[position], position, context)
    }

    fun setList(peopleList : ArrayList<PeopleBean.People>){
        peopleListFiltered = peopleList
        items = peopleList
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    peopleListFiltered = items
                } else {
                    val filteredList = ArrayList<PeopleBean.People>()
                    for (row in items) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.name!!.toLowerCase().contains(charString.toLowerCase()))
                         {
                            filteredList.add(row)
                        }
                    }

                    peopleListFiltered = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = peopleListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                peopleListFiltered = filterResults.values as ArrayList<PeopleBean.People>

                // refresh the list with filtered data
                notifyDataSetChanged()
            }
        }
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