package ssk.dbzeus.ppm.service.repository


import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ssk.dbzeus.ppm.service.model.entity.asset.AssetData
import ssk.dbzeus.ppm.service.model.entity.insertdata.SubmitAssetMaintainceApiData
import ssk.dbzeus.ppm.service.model.entity.userdata.UserData
import ssk.dbzeus.ppm.service.model.entity.weekassets.WeekAssets
import ssk.dbzeus.ppm.utils.ApiUrls


interface APIService {

    @POST(ApiUrls.login)
    fun login(@Body fieldData: RequestBody): retrofit2.Call<UserData>

    @POST(ApiUrls.submitAssetMaintance)
    fun submitAssetMaintanceApi(@Body fieldData: RequestBody): retrofit2.Call<SubmitAssetMaintainceApiData>

    @GET(ApiUrls.getAssetbaseInfo)
    fun getAssets(@Query("UserId") userId: Int): retrofit2.Call<AssetData>

    @GET(ApiUrls.getAssetMaintainceDetails)
    fun getWeekAssets(@Query("UserId") userId: Int): retrofit2.Call<WeekAssets>
}

