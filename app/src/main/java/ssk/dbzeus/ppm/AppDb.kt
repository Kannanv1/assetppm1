package ssk.dbzeus.ppm


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ssk.dbzeus.ppm.service.model.asset.Category
import ssk.dbzeus.ppm.service.model.asset.Subcategory
import ssk.dbzeus.ppm.service.model.dao.*
import ssk.dbzeus.ppm.service.model.entity.asset.*
import ssk.dbzeus.ppm.service.model.entity.insertdata.FinalDBdata
import ssk.dbzeus.ppm.service.model.entity.userdata.Client
import ssk.dbzeus.ppm.service.model.entity.userdata.Department
import ssk.dbzeus.ppm.service.model.entity.userdata.Facility
import ssk.dbzeus.ppm.service.model.entity.userdata.UserInfo
import ssk.dbzeus.ppm.service.model.entity.weekassets.Detail
import ssk.dbzeus.ppm.utils.Converters


@Database(
    entities = [(UserInfo::class), (Client::class), (Department::class), (Facility::class),
        (Building::class), (Buildinglang::class), (Floor::class), (Wing::class), (Space::class),
        (Asset::class), (Assetsmap::class), (Category::class), (Categorylang::class),
        (Subcategory::class), (Subcategorylang::class), (Detail::class), (Frequency::class),
        (Frequencylang::class), (Workingstatus::class), (Assetworkorder::class), FinalDBdata::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun clientDao(): ClientDao
    abstract fun departmentDao(): DepartmentDao
    abstract fun facilityDao(): FacilityDao
    abstract fun buildingDao(): BuildingDao
    abstract fun buildingLangDao(): BuildingLangDao
    abstract fun floorDao(): FloorDao
    abstract fun wingDao(): WingDao
    abstract fun spaceDao(): SpaceDao
    abstract fun assetDao(): AssetDao
    abstract fun assetMapDao(): AssetMapDao
    abstract fun categoryDao(): CategoryDao
    abstract fun subCategoryDao(): SubCategoryDao
    abstract fun detailDao(): DetailDAO
    abstract fun frequencyDao(): FrequencyDAO
    abstract fun workingStatusDao(): WorkingStatusDao
    abstract fun workOrderDao(): AssetWorkOrderDAO
    abstract fun finalDataDao(): FinalDataDao

    companion object {
        private var sInstance: AppDb? = null
        private var dbPath = "/sdcard/AssetPPM/ASSETPPMDB"

        @Synchronized
        fun getInstance(
            context: Context,
            scope: CoroutineScope
        ): AppDb {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDb::class.java, dbPath)
                    .fallbackToDestructiveMigration()
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
            }
            return sInstance!!
        }

        @Synchronized
        fun getInstance(context: Context): AppDb {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDb::class.java, dbPath)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }


        private class AppDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                sInstance?.let {
                    scope.launch(Dispatchers.IO) {
                    }
                }
            }
        }

    }
}