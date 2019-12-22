package com.devyk.kotlin_github.api

import com.devyk.common.network.RETROFIT
import com.devyk.kotlin_github.config.settings.Configs
import com.devyk.kotlin_github.mvp.m.entity.AuthorizationReq
import com.devyk.kotlin_github.mvp.m.entity.AuthorizationRsp
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthApi {

    @PUT("/authorizations/clients/${Configs.Account.clientId}/{fingerPrint}")
    fun createAuthorization(
        @Body req: AuthorizationReq,
        @Path("fingerPrint") fingerPrint: String = Configs.Account.fingerPrint
    )
            : Observable<AuthorizationRsp>

    @DELETE("/authorizations/{id}")
    fun deleteAuthorization(@Path("id") id: Int): Observable<Response<Any>>

}

//以 object 定义成单例
// public <T> T create(final Class<T> service) {
object AuthService : AuthApi by RETROFIT.create(AuthApi::class.java)