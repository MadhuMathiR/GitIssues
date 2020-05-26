package com.ocs.gitissues.issues_list

import IssuesList


class IssuesListPresenter(var issuesListContract: IssuesListContract.View) : IssuesListContract.Presenter, IssuesListContract.Model.OnFinishedListener {
    var issueListModel: IssuesListContract.Model

    init {
        issueListModel = IssueListModel()
    }

    override fun onFinished(issuesResponse: List<IssuesList>) {
        issuesListContract.setDataToRecyclerView(issuesResponse);
        issuesListContract.hideProgress();
    }

    override fun onFailure(t: Throwable) {
        issuesListContract.onResponseFailure(t);
        issuesListContract.hideProgress();
    }

    override fun requestDataFromServer() {
        issuesListContract.showProgress();

        issueListModel.getIssuesList(this); }


}