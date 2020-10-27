package ssk.dbzeus.ppm.service.model.entity.insertdata

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class SpareDataItem(
    @ColumnInfo(name = "AssetFrequencyDetailId") val AssetFrequencyDetailId: String,
    @ColumnInfo(name = "Cost") val Cost: String,
    @ColumnInfo(name = "Qty") val Qty: String,
    @ColumnInfo(name = "Spare") val Spare: String
)