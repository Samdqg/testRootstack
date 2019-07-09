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
import android.widget.Toast

class MainActivity : AppCompatActivity() , MainActivityPresenter.Recycler {

    private lateinit var textMessage: TextView
    private var peopleAdapter: PeopleAdapter? = null
    private lateinit var myPeopleList: ArrayList<PeopleBean.People>
    private var page : Int = 1
    private val context = this
    lateinit var presenter: MainActivityPresenter
    private var loading = true

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

        presenter = MainActivityPresenter(this)
        myPeopleList = ArrayList()
        initRecyclerView()
        initSearch()
        presenter.getPeople(page)
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

        rvPeople.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                /*if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(context, "Last", Toast.LENGTH_SHORT).show()

                }*/
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

                if(loading){
                    if (dy > 0){
                        val visibleItemCount = linearLayoutManager.getChildCount();
                        val totalItemCount = linearLayoutManager.getItemCount();
                        val pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()

                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false
                            page++
                            presenter.getPeople(page)
                        }

                    }
                }

            }
        })


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

        loading = true
        if(myPeopleList != null){
            myPeopleList.addAll(peopleList)
        }else{
            myPeopleList = peopleList
        }
        peopleAdapter!!.setList(myPeopleList)
        peopleAdapter!!.notifyDataSetChanged()

    }

    override fun showToast() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
    }
}
