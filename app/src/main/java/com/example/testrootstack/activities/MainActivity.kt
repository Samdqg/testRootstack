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

    }

    override fun populateRecylcer(peopleList: ArrayList<PeopleBean.People>) {

        peopleAdapter = PeopleAdapter(peopleList, this)
        rvPeople.adapter = peopleAdapter

    }
}
