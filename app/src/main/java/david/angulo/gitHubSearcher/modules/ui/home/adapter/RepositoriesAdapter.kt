package david.angulo.gitHubSearcher.modules.ui.home.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import david.angulo.gitHubSearcher.R
import david.angulo.gitHubSearcher.model.GitHubRepository
import david.angulo.gitHubSearcher.modules.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_repository.view.*

/**
 * © Class created by David Angulo
 * */

class RepositoriesAdapter(
    users: ArrayList<GitHubRepository>,
    val context: Context?,
    var onRepositoryItemClick: (GitHubRepository) -> Unit
) :
    BaseRecyclerAdapter<GitHubRepository, RepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)

        return ViewHolder(view)
    }

    init {
        list = users
    }


    inner class ViewHolder(var view: View) : BaseRecyclerAdapter.ViewHolder(view) {
        private lateinit var current: GitHubRepository


        override fun bind(position: Int) {
            current = getItem(position)
            setValues()

            itemView.setOnClickListener {
                onRepositoryItemClick(current)
            }
        }

        private fun setValues() {
            itemView.tvRepositoryName.text = current.fullName.toString()
            if (current.description.isNullOrEmpty()) itemView.tvRepositoryLanguage.text =
                context?.getString(
                    R.string.no_description
                )
            else itemView.tvRepositoryLanguage.text = current.description.toString()

            context?.let {
                Glide.with(it)
                    .load(current.owner?.avatar)
                    .apply(RequestOptions.circleCropTransform())
                    .into(itemView.ivUserImage)
            }
        }

    }
}