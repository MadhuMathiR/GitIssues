package com.ocs.gitissues.issues_list

import IssuesList


interface IssuesListContract {

    interface Model {
        interface OnFinishedListener {
            fun onFinished(issuesResponse: List<IssuesList>)
            fun onFailure(t: Throwable)
        }

        fun getIssuesList(onFinishedListener: OnFinishedListener)
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setDataToRecyclerView(issuesResponse: List<IssuesList>)
        fun onResponseFailure(throwable: Throwable)
    }

    interface Presenter {
        fun requestDataFromServer()
    }
}