package com.ocs.gitissues.issues_list

import IssuesList


class IssuesListPresenter(var issuesListContract: IssuesListContract.View) : IssuesListContract.Presenter, IssuesListContract.Model.OnFinishedListener {
var issueListModel:IssuesListContract.Model

    init {
        issueListModel= IssueListModel()
}

    override fun onFinished(issuesResponse: List<IssuesList?>?) {
        issuesListContract.setDataToRecyclerView(issuesResponse);
        if (issuesListContract != null) {
            issuesListContract.hideProgress();
        }    }

    override fun onFailure(t: Throwable?) {
        issuesListContract.onResponseFailure(t);
        if (issuesListContract != null) {
            issuesListContract.hideProgress();
        }    }

    override fun requestDataFromServer() {
        if (issuesListContract != null) {
            issuesListContract.showProgress();
        }
        issueListModel.getIssuesList(this);    }


}