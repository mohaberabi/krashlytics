package com.mohaberabi.kmp.krashlytics.data

import com.mohaberabi.kmp.krashlytics.data.database.dao.UnCaughtErrorDao
import com.mohaberabi.kmp.krashlytics.data.database.mapper.tDomain
import com.mohaberabi.kmp.krashlytics.data.database.mapper.toEntity
import com.mohaberabi.kmp.krashlytics.domain.model.UncaughtErrorModel
import com.mohaberabi.kmp.krashlytics.domain.source.AppCrashSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

class RoomAppCrashSource(
    private val dao: UnCaughtErrorDao,
) : AppCrashSource {
    override suspend fun addAppCrash(error: UncaughtErrorModel) {

        try {
            withContext(Dispatchers.IO) {
                dao.addUnCaughtError(error.toEntity())
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
        }
    }

    override fun getAppCrashReport(): Flow<List<UncaughtErrorModel>> =
        dao.getAllUnCaughtErrors().map { list -> list.map { it.tDomain() } }.flowOn(Dispatchers.IO)
}