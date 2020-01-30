package david.angulo.githubSearcher.modules.ui.home.dialog

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import david.angulo.githubSearcher.R
import david.angulo.githubSearcher.databinding.DialogRepositoryDetailBinding
import david.angulo.githubSearcher.model.GithubRepository
import david.angulo.githubSearcher.modules.base.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_repository_detail.*

class RepositoryDetailDialog :
    BaseDialogFragment<RepositoryDetailViewModel, DialogRepositoryDetailBinding>(
        RepositoryDetailViewModel::class.java
    ) {

    private lateinit var mRepository: GithubRepository


    override fun getLayoutRes(): Int {
        return R.layout.dialog_repository_detail
    }

    override fun viewCreated(view: View?) {
        viewModel.getArguments(arguments)
        initDialogStatesObserver()
    }

    private fun initDialogStatesObserver() {
        viewModel.mRepositoryDetailDialogState.observe(this, Observer { state ->
            when (state) {
                is RepositoryDetailState.DialogCreatedState -> {
                    mRepository = state.repository
                    setView()
                }
            }
        })
    }

    private fun setView(){
        context?.let {
            Glide.with(it)
                .load(mRepository.owner?.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(dialog?.ivUserImage!!)
        }
        dialog?.tvName?.text = mRepository.fullName
        dialog?.tvUrl?.text = mRepository.url
    }
}