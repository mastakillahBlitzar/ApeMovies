package com.example.apemovies.interfaces

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessRecyclerViewScrollListener : RecyclerView.OnScrollListener() {

    var mPrevious = 0;
    var mLoading = true;

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager!!.itemCount
        val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        val lastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()


        if (mLoading) {
            if (totalItemCount > mPrevious) {
                mLoading = false
                mPrevious = totalItemCount
            }
        }
        if (!mLoading && (totalItemCount == lastVisibleItem + 1)) {
            onLoadMore();
            mLoading = true;
        }


    }

    abstract fun onLoadMore()
}