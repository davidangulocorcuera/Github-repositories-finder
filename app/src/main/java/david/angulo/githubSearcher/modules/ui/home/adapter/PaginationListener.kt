package david.angulo.githubSearcher.modules.ui.home.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * © Class created by David Angulo
 * */

abstract class PaginationListener(
    private val linearLayoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {


    override
    fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount: Int = linearLayoutManager.itemCount
        val lastVisibleItemPosition: Int = linearLayoutManager.findLastVisibleItemPosition()


        if (lastVisibleItemPosition == totalItemCount - 1 && !isLoading()) {
            loadMoreItems()
        }

    }

    abstract fun loadMoreItems()
    abstract fun isLoading(): Boolean


}