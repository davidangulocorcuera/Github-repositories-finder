package david.angulo.gitHubSearcher.modules.ui.home


import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import david.angulo.gitHubSearcher.R
import david.angulo.gitHubSearcher.databinding.FragmentHomeBinding
import david.angulo.gitHubSearcher.model.GitHubRepository
import david.angulo.gitHubSearcher.modules.base.BaseFragment
import david.angulo.gitHubSearcher.modules.ui.home.adapter.PaginationListener
import david.angulo.gitHubSearcher.modules.ui.home.adapter.RepositoriesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.view.*


class HomeFragment : BaseFragment<HomeFragmentViewModel, FragmentHomeBinding>(
    HomeFragmentViewModel::class.java
) {

    private lateinit var mRepositoriesAdapter: RepositoriesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var mGitHubRepositories: ArrayList<GitHubRepository> = ArrayList()

    private var itemCount = 1
    private var currentPage = 1


    private var isSearchCall = false
    private var isGetAllRepositoriesCall = false
    private var isNewSearch = false
    private var isLoading = false
    private val isLastPage = false


    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun viewCreated(view: View?) {
        setListeners()
        initHomeStatesObserver()
        initRepositoriesAdapter()
        initRepositoriesList()
        setEtSearchKeyListener()
        onTextEmpty()
        isNewSearch = true
        getAllPublicRepositories()
        setRecyclerListener()
    }

    private fun initHomeStatesObserver() {
        viewModel.mHomeState.observe(this, Observer { state ->
            when (state) {
                is HomeState.RepositoriesLoadedState -> {
                    addRepositories(state.gitHubRepositories)
                }
                is HomeState.RepositoriesLoadFailedState -> {
                    showSnackbar(view,state.error,context,R.color.colorRed)
                }
                is HomeState.EmptyRepositoriesState -> {
                    llNotFoundContainer.visibility = View.VISIBLE
                }
            }
            isScreenEnabled(true)
            isAnimationVisible(false)
            homeToolbar.etSearcher.clearFocus()
            hideKeyboard(context, view)
            swrRepositoriesContainer.isRefreshing = false
        })
    }



    private fun isScreenEnabled(enable: Boolean) {
        homeToolbar.etSearcher.isEnabled = enable
        homeToolbar.ivSearch.isEnabled = enable
    }

    /**
     * Is necessary know the id of the last repository searched for pagination.
     **/

    private fun addRepositories(repositories: ArrayList<GitHubRepository>) {

        if (isNewSearch) {
            mGitHubRepositories.clear()
        }

        repositories[repositories.size - 1].id?.let {
            itemCount = it
        }

        mGitHubRepositories.addAll(repositories)
        mRepositoriesAdapter.notifyDataSetChanged()
    }


    private fun initRepositoriesList() {
        val layoutManager =
            GridLayoutManager(activity?.applicationContext, 1)
        rvRepositories.layoutManager = layoutManager
        rvRepositories.adapter = mRepositoriesAdapter
    }

    private fun initRepositoriesAdapter() {
        mRepositoriesAdapter = RepositoriesAdapter(mGitHubRepositories, context) { repository ->

        }
    }

    private fun setListeners() {
        homeToolbar.ivSearch.setOnClickListener {
            searchRepositories()
        }
    }

    private fun searchRepositories() {
        if(isNewSearch) currentPage = 1
        llNotFoundContainer.visibility = View.GONE
        isScreenEnabled(false)
        isAnimationVisible(true)
        isSearchCall = true
        isGetAllRepositoriesCall = false
        viewModel.searchRepositories(homeToolbar.etSearcher.text.toString(), currentPage)
    }


    private fun getAllPublicRepositories() {
        if(isNewSearch) itemCount = 1
        llNotFoundContainer.visibility = View.GONE
        isAnimationVisible(true)
        isGetAllRepositoriesCall = true
        isSearchCall = false
        viewModel.getAllPublicRepositories(itemCount)
        homeToolbar.etSearcher.clearFocus()
    }

    /**
     * function for show or hide github cat animation
     * */

    private fun isAnimationVisible(isVisible: Boolean) {
        if (isVisible && isNewSearch) {
            lottieLoading.playAnimation()
            lottieLoading.visibility = View.VISIBLE
        } else {
            lottieLoading.pauseAnimation()
            lottieLoading.visibility = View.GONE
        }

    }

    private fun setRecyclerListener() {
        linearLayoutManager = rvRepositories.layoutManager as LinearLayoutManager
        swrRepositoriesContainer.setOnRefreshListener {
            isNewSearch = true
            getRepositoriesOnRefresh()
        }
        rvRepositories.addOnScrollListener(object : PaginationListener(linearLayoutManager) {
            override fun loadMoreItems() {
                isNewSearch = false
                isLoading = true
                if (!isGetAllRepositoriesCall) currentPage++
                getRepositoriesOnRefresh()

            }
            override fun isLastPage(): Boolean = isLastPage
            override fun isLoading(): Boolean = isLoading
        })
    }

    /**
     * Function for search using the "done" key from device keyboard
     */

    private fun setEtSearchKeyListener() {
        homeToolbar.etSearcher.setOnEditorActionListener { _, actionId, _ ->
            if (actionId and EditorInfo.IME_MASK_ACTION != 0) {
                searchRepositories()
                true
            } else {
                false
            }
        }
    }


    /**
     * If user delete the text from the search , the app will load all public repositories.
     * */

    private fun onTextEmpty() {
        homeToolbar.etSearcher.addTextChangedListener {
            if (homeToolbar.etSearcher.text.toString().isEmpty()) {
                getAllPublicRepositories()
            }
        }
    }


    /**
     * If last call was a search app will refresh searching else will refresh showing all public repositories.
     * */

    private fun getRepositoriesOnRefresh() {
        swrRepositoriesContainer.isRefreshing = true
        if (isGetAllRepositoriesCall) {
            getAllPublicRepositories()
        } else if (isSearchCall) {
            searchRepositories()
        }
    }


}
