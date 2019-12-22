package com.devyk.common.ext

import com.google.gson.Gson


inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, T::class.java)

//inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)