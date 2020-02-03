package david.angulo.githubSearcher.modules.ui.home.dialog

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import david.angulo.githubSearcher.R
import david.angulo.githubSearcher.databinding.DialogRepositoryDetailBinding
import david.angulo.githubSearcher.model.GithubRepository
import david.angulo.githubSearcher.modules.base.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_repository_detail.*

/**
 * © Class created by David Angulo
 * */

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
                    onUrlClicked()
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
    }

    private fun onUrlClicked(){
        dialog?.btnOpenInBrowser?.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mRepository.url))
            startActivity(browserIntent)
        }
    }
}