package david.angulo.githubSearcher.modules.ui.home.dialog

import david.angulo.githubSearcher.model.GithubRepository

/**
 * © Class created by David Angulo
 * */

sealed class RepositoryDetailState {
    class DialogCreatedState(val repository: GithubRepository): RepositoryDetailState()
}