package david.angulo.gitHubSearcher.modules.ui.home.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class PaginationListener(
    private val linearLayoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    companion object {
        const val START_PAGE = 1
    }


    override
    fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount: Int = linearLayoutManager.itemCount
        val lastVisibleItemPosition: Int = linearLayoutManager.findLastVisibleItemPosition()


        if (lastVisibleItemPosition == totalItemCount - 1) {
            loadMoreItems()
        }

    }

    abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean


}