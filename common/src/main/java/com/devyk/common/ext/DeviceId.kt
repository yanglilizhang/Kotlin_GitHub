package com.devyk.common.ext

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings


val Context.deviceId: String
    @SuppressLint("HardwareIds")
    get() = Settings.Secure.getString(contentResolver,Settings.Secure.ANDROID_ID)