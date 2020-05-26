package com.ocs.gitissues.network

import IssuesList
import retrofit2.Call
import retrofit2.http.GET




interface ApiInterface {

    @GET("repos/square/okhttp/issues")
    fun getIssuesList(): Call<List<IssuesList>>

}