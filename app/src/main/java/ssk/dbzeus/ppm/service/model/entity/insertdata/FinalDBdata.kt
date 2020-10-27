package ssk.dbzeus.ppm.service.model.entity.insertdata

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity
@TypeConverters(WorkorderConverter::class,SpareDataConverter::class)
data class FinalDBdata(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AssetFrequencyDetailId") val AssetFrequencyDetailId: Int,
    @ColumnInfo(name = "AssetFrequencyDetailKey") val AssetFrequencyDetailKey: String,
    @ColumnInfo(name = "Description") val Description: String,
    @ColumnInfo(name = "Remark") val Remark: String,
    @ColumnInfo(name = "LogInUserId") val LogInUserId: Int,
    @ColumnInfo(name = "AssetBeforeMaintenanceImage") val AssetBeforeMaintenanceImage: String,
    @ColumnInfo(name = "AssetAfterMaintenanceImage") val AssetAfterMaintenanceImage: String,
    @ColumnInfo(name = "AttachmentImage") val AttachmentImage: String,
    @ColumnInfo(name = "WorkOrderList") val WorkOrderList: WorkorderList,
    @ColumnInfo(name = "SpareList") val SpareList: SpareDataList,
    @ColumnInfo(name = "AttachementName") val AttachementName: String,
)

class WorkorderConverter {
    private var gson = Gson()

    @TypeConverter
    fun listToJson(value: WorkorderList?): String? = gson.toJson(value)

    @TypeConverter
    fun jsonToList(value: String): WorkorderList {
        val listType: Type = object : TypeToken<WorkorderList?>() {}.type
        return gson.fromJson(value, listType)
    }
}
class SpareDataConverter {
    private var gson = Gson()

    @TypeConverter
    fun listToJson(value: SpareDataList?): String? = gson.toJson(value)

    @TypeConverter
    fun jsonToList(value: String): SpareDataList {
        val listType: Type = object : TypeToken<SpareDataList?>() {}.type
        return gson.fromJson(value, listType)
    }
}

