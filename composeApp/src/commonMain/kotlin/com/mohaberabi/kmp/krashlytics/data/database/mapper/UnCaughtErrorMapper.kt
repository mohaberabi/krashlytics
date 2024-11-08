package com.mohaberabi.kmp.krashlytics.data.database.mapper

import com.mohaberabi.kmp.krashlytics.data.database.entity.UncaughtErrorEntity
import com.mohaberabi.kmp.krashlytics.domain.model.UncaughtErrorModel


fun UncaughtErrorModel.toEntity() = UncaughtErrorEntity(
    errorMessage = errorMessage,
    cause = cause,
    causedAtMillis = causedAtMillis,
    deviceInfo = deviceInfo.toEntity(),
)

fun UncaughtErrorEntity.tDomain() = UncaughtErrorModel(
    id = "${id}",
    errorMessage = errorMessage,
    cause = cause,
    causedAtMillis = causedAtMillis,
    deviceInfo = deviceInfo.toDomain(),
)