package ssk.dbzeus.ppm.service.model.entity.insertdata

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@TypeConverters(SpareDataListConverter::class)
class SpareDataList(spareListitem: ArrayList<SpareDataItem>) : ArrayList<SpareDataItem>()


class SpareDataListConverter {
    private var gson = Gson()

    @TypeConverter
    fun sparelistToJson(value: ArrayList<SpareDataItem>?): String? = gson.toJson(value)

    @TypeConverter
    fun jsonTospareList(value: String): ArrayList<SpareDataItem> {
        val listType: Type = object : TypeToken<ArrayList<SpareDataItem?>?>() {}.type
        return gson.fromJson(value, listType)
    }
}
