package com.nzf.retrofitexample.Api

import android.provider.ContactsContract
import com.nzf.retrofitexample.Models.DefaultResponse
import com.nzf.retrofitexample.Models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("createuser")
    fun createUser(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("school") school: String


    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("userlogin")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<LoginResponse>

}