package com.ocs.gitissues.issues_list

import IssuesList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ocs.gitissues.R
import com.ocs.gitissues.adapter.MyIssuesListRecyclerViewAdapter
import com.ocs.gitissues.databinding.FragmentIssuesBinding

class IssuesListFragment : Fragment(), IssuesListContract.View {

    private var issuesListPresenter: IssuesListPresenter? = null
    lateinit var layoutManager: LinearLayoutManager
    lateinit var issuesResponse: List<IssuesList>
    lateinit var fragmentIssuesBinding: FragmentIssuesBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        fragmentIssuesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false)
        issuesListPresenter = IssuesListPresenter(this);
        layoutManager = LinearLayoutManager(requireContext())
        fragmentIssuesBinding.rvIssuesList.layoutManager = layoutManager

        issuesListPresenter!!.requestDataFromServer()

        return fragmentIssuesBinding.root
    }

    override fun showProgress() {
        fragmentIssuesBinding.pbIssuesList.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        fragmentIssuesBinding.pbIssuesList.visibility = View.GONE

    }

    override fun onResponseFailure(throwable: Throwable) {
        fragmentIssuesBinding.tvErrorMessage.visibility = View.VISIBLE
        fragmentIssuesBinding.rvIssuesList.visibility = View.GONE
        fragmentIssuesBinding.tvErrorMessage.setText(throwable.message.toString())
    }

    override fun setDataToRecyclerView(issuesResponse: List<IssuesList>) {
        this.issuesResponse = issuesResponse as List<IssuesList>
        // Set the adapter
        if (issuesResponse.size > 0) {

            val adapter = MyIssuesListRecyclerViewAdapter(issuesResponse)
            fragmentIssuesBinding.rvIssuesList.visibility = View.VISIBLE
            fragmentIssuesBinding.tvErrorMessage.visibility = View.GONE

            fragmentIssuesBinding.rvIssuesList.adapter = adapter
        } else {
            fragmentIssuesBinding.tvErrorMessage.visibility = View.VISIBLE
            fragmentIssuesBinding.rvIssuesList.visibility = View.GONE
            fragmentIssuesBinding.tvErrorMessage.setText(getString(R.string.no_text))
        }

    }
}
