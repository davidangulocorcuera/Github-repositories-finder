package david.angulo.githubSearcher.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * © Class created by David Angulo
 * */

class SearchResponse(

    @SerializedName("total_count")
    @Expose
    var count: Int? = null,

    @SerializedName("incomplete_result")
    @Expose
    var incompleteResult: Boolean? = null,

    @SerializedName("items")
    @Expose
    var items: ArrayList<GithubRepository>? = null
) : Serializable
