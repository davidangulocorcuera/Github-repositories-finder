package david.angulo.gitHubSearcher.modules.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * © Class created by David Angulo
 * */

class BaseResponse<T>(
    @SerializedName("data")
    @Expose
    val data: T? = null
) : Serializable