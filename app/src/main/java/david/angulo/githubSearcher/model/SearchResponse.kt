package david.angulo.githubSearcher.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
)
