package com.ocs.gitissues.issues_list

import IssuesList
import android.content.ContentValues.TAG
import android.util.Log
import com.ocs.gitissues.network.ApiClient
import com.ocs.gitissues.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class IssueListModel : IssuesListContract.Model{
    override fun getIssuesList(onFinishedListener: IssuesListContract.Model.OnFinishedListener) {
        val apiService = ApiClient.getClient()!!.create(ApiInterface::class.java)

        val call: Call<List<IssuesList>> = apiService.getIssuesList()
        call.enqueue(object : Callback<List<IssuesList>>{
            override fun onFailure(call: Call<List<IssuesList>>, t: Throwable) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }

            override fun onResponse(call: Call<List<IssuesList>>, response: Response<List<IssuesList>>) {

                onFinishedListener.onFinished(response.body()!!)
            }

        })
    }

}