package david.angulo.gitHubSearcher.modules.ui.home


import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import david.angulo.gitHubSearcher.R
import david.angulo.gitHubSearcher.databinding.FragmentHomeBinding
import david.angulo.gitHubSearcher.model.GitHubRepository
import david.angulo.gitHubSearcher.modules.base.BaseFragment
import david.angulo.gitHubSearcher.modules.ui.home.adapter.RepositoriesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.view.*


class HomeFragment : BaseFragment<HomeFragmentViewModel, FragmentHomeBinding>(
    HomeFragmentViewModel::class.java
) {

    private lateinit var mRepositoriesAdapter: RepositoriesAdapter
    private var mGitHubRepositories: ArrayList<GitHubRepository> = ArrayList()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun viewCreated(view: View?) {
        setListeners()
        initHomeStatesObserver()
        initRepositoriesAdapter()
        initRepositoriesList()
    }

    private fun initHomeStatesObserver(){
        viewModel.mHomeState.observe(this, Observer { state ->
            when (state) {
                is HomeState.RepositoriesLoaded -> {
                    mRepositoriesAdapter.addAll(state.gitHubRepositories)
                }
                is HomeState.RepositoriesLoadFailed -> {
                }

            }
            showProgress(false,false)

        })
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

    private fun setListeners(){
        homeToolbar.ivSearch.setOnClickListener {
            showProgress(true,true)
            viewModel.searchRepositories(homeToolbar.etSearcher.text.toString())
        }
    }



}
