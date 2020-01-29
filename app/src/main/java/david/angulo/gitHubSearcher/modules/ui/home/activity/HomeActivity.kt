package david.angulo.gitHubSearcher.modules.ui.home.activity

import android.os.Bundle
import david.angulo.gitHubSearcher.R
import david.angulo.gitHubSearcher.databinding.ActivityHomeBinding
import david.angulo.gitHubSearcher.modules.base.BaseActivity
import david.angulo.gitHubSearcher.modules.ui.home.HomeFragment

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
