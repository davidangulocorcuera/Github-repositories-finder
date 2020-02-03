package david.angulo.githubSearcher.modules.ui.home.activity

import android.os.Bundle
import david.angulo.githubSearcher.R
import david.angulo.githubSearcher.databinding.ActivityHomeBinding
import david.angulo.githubSearcher.modules.base.BaseActivity
import david.angulo.githubSearcher.modules.ui.home.HomeFragment

/**
 * © Class created by David Angulo
 * */

class HomeActivity : BaseActivity<HomeActivityViewModel, ActivityHomeBinding>(
    HomeActivityViewModel::class.java
) {
    override fun getLayoutRes(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(viewModel: HomeActivityViewModel) {
        binding.homeActivityViewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.addFragment(HomeFragment(), R.id.fragmentContainerHome)
    }
}
