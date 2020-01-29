package david.angulo.gitHubSearcher.modules.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import david.angulo.gitHubSearcher.R
import david.angulo.gitHubSearcher.modules.utils.setVisible


abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) :
    AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    val navigator: Navigator by lazy { Navigator(this) }

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProviders.of(this).get(mViewModelClass)
    }

    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel(viewModel)

        super.onCreate(savedInstanceState)

        onInject()
    }

    abstract fun initViewModel(viewModel: VM)

    fun showProgress(show: Boolean, hasShade: Boolean) {
        val progress = findViewById<ProgressBar>(R.id.progress)
        val progressContainer = findViewById<View>(R.id.progressContainer)
        progressContainer?.setVisible(show && hasShade)
        progress?.setVisible(show)
    }

    fun showSnackbar(view: View?, text: String, context: Context?, color: Int) {
        view?.let {
            context?.let {
                val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
                snackbar.view.setBackgroundColor(ContextCompat.getColor(context, color))
                snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.colorWhite))
                snackbar.show()
            }
        }
    }
}
