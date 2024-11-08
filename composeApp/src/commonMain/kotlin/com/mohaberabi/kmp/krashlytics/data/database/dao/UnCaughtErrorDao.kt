package com.mohaberabi.kmp.krashlytics.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.kmp.krashlytics.data.database.entity.DeviceBreadCrumbEntity
import com.mohaberabi.kmp.krashlytics.data.database.entity.UncaughtErrorEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UnCaughtErrorDao {
    @Upsert
    suspend fun addUnCaughtError(error: UncaughtErrorEntity)

    @Query("SELECT * FROM uncaughtError")
    fun getAllUnCaughtErrors(): Flow<List<UncaughtErrorEntity>>
}