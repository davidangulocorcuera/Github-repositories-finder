package david.angulo.githubSearcher.modules.base

import androidx.fragment.app.Fragment

/**
 * © Class created by David Angulo
 * */

class Navigator(activity: BaseActivity<*, *>) {

    private val fragmentManager = activity.supportFragmentManager

    fun addFragment(fragment: Fragment, container: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(
            container,
            fragment
        )
        transaction.commit()
    }
}