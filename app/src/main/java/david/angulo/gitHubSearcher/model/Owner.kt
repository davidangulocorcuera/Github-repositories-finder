package david.angulo.gitHubSearcher.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Owner(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("avatar_url")
    @Expose
    var avatar: String? = null

)