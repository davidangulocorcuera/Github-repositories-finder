package david.angulo.githubSearcher.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GithubRepository(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("full_name")
    @Expose
    var fullName: String? = null,
    @SerializedName("html_url")
    @Expose
    var url: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("owner")
    @Expose
    var owner: Owner? = null,
    @SerializedName("language")
    @Expose
    var language: String? = null
): Serializable