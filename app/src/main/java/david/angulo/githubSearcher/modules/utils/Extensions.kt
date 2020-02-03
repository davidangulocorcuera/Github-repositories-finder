package david.angulo.githubSearcher.modules.utils


import android.view.View

/**
 * © Class created by David Angulo
 * */

fun View.setVisible(show: Boolean, invisible: Boolean = false) {
    when {
        show -> this.visibility = View.VISIBLE
        invisible -> this.visibility = View.INVISIBLE
        else -> this.visibility = View.GONE
    }
}






