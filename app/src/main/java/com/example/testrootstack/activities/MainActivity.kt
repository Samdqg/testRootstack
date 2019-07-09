package com.example.testrootstack.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.testrootstack.R
import com.example.testrootstack.adapters.PeopleAdapter
import com.example.testrootstack.models.PeopleBean
import com.example.testrootstack.presenters.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration
import android.text.Editable
import android.text.TextWatcher

class MainActivity : AppCompatActivity() , MainActivityPresenter.Recycler {

    private lateinit var textMessage: TextView
    private var peopleAdapter: PeopleAdapter? = null
    private var myPeopleList: ArrayList<PeopleBean.People>? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_list)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_explore)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_queues)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_profile -> {
                message.setText(R.string.title_profile)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val presenter = MainActivityPresenter(this)
        initRecyclerView()
        initSearch()
        presenter.getPeople(1)
    }

    fun initRecyclerView() {

        val layoutManager = LinearLayoutManager(this)
        rvPeople.layoutManager = layoutManager
        val mDividerItemDecoration = DividerItemDecoration(
            rvPeople.getContext(),
            layoutManager.getOrientation()
        )
        rvPeople.addItemDecoration(mDividerItemDecoration)
        peopleAdapter = PeopleAdapter(ArrayList(), ArrayList(), this)
        rvPeople.adapter = peopleAdapter
    }

    fun initSearch(){
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                peopleAdapter!!.getFilter().filter(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    override fun populateRecylcer(peopleList: ArrayList<PeopleBean.People>) {

        peopleAdapter!!.setList(peopleList)
        peopleAdapter!!.notifyDataSetChanged()

    }
}
