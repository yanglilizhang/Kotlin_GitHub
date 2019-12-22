package com.devyk.common.network.interceptor

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AcceptInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        //Request request();
        val original = chain.request()
        //Response proceed(Request request) throws IOException;
        return chain.proceed(original.newBuilder()
                .apply {
                    header("accept", "application/vnd.github.v3.full+json, ${original.header("accept") ?: ""}")
                }
                .build())
    }
}