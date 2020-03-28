package com.niv.factsapp.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.niv.factsapp.R
import com.niv.factsapp.databinding.ActivityFactsListBinding
import com.niv.factsapp.models.FactsListItem
import com.niv.factsapp.models.ListingResponse
import com.niv.factsapp.repository.Resource
import com.niv.factsapp.repository.Status
import com.niv.factsapp.ui.adapters.FactsListAdapter
import com.niv.factsapp.viewmodels.FactsListingViewModel
import kotlinx.android.synthetic.main.activity_facts_list.*

/**
 * FactsListingActivity - Class implementation of listing the facts
 *
 * @author Nivedith
 * @since 2020-03-27.
 */
class FactsListingActivity : AppCompatActivity() {

    private lateinit var factsListingViewModel: FactsListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    /**
     * Method to initialize the view
     */
    private fun initView() {
        val binding: ActivityFactsListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_facts_list)

        factsListingViewModel = ViewModelProviders.of(this).get(FactsListingViewModel::class.java)
        factsListingViewModel.loadPage().observeList()

        binding.viewmodel = factsListingViewModel
    }

    /**
     * Method that observes the livedata
     */
    private fun LiveData<Resource<ListingResponse>>.observeList() {
        this.observe(this@FactsListingActivity, Observer<Resource<ListingResponse>> {

            it?.let { res ->

                if (res.isSuccess()) {

                    setUpRecyclerView(res.data?.rows?.toMutableList())
                    setToolbar()
                }

                processStatus(res)

            }
        })
    }

    /**
     * Method to set up the recycler view
     *
     * @param list list of facts
     */
    private fun setUpRecyclerView(list: MutableList<FactsListItem>?) {

        rv_facts_list.apply {
            layoutManager = LinearLayoutManager(this@FactsListingActivity)
            adapter = list?.let { FactsListAdapter(context, it) }
        }
    }

    /**
     * Method to process the status from the API
     *
     * @param resource fact resource
     */
    private fun processStatus(resource: Resource<Any?>) {

        when (resource.status) {
            Status.SUCCESS -> {
                progress_bar.visibility = View.GONE
                layout_swipe.isRefreshing = false
            }
            Status.EMPTY_RESPONSE -> {
                progress_bar.visibility = View.GONE
                layout_swipe.isRefreshing = false
            }
            Status.PROGRESSING -> {
                progress_bar.visibility = View.VISIBLE
            }
            Status.SWIPE_RELOADING -> {
                progress_bar.visibility = View.GONE
                layout_swipe.isRefreshing = true
            }
            Status.ERROR -> {

                Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                progress_bar.visibility = View.GONE
                layout_swipe.isRefreshing = false
            }
            Status.LOADING_MORE -> {
                progress_bar.visibility = View.VISIBLE
                layout_swipe.isRefreshing = false
            }
        }
    }

    /**
     * Method to set the toolbar
     */
    private fun setToolbar() {

        setSupportActionBar(toolbar)
        toolbar.title = factsListingViewModel.title
        toolbar.setTitleTextColor(
            ContextCompat.getColor(
                this@FactsListingActivity,
                R.color.colorWhite
            )
        )
    }
}