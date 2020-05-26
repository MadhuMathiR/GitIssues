package com.ocs.gitissues.issues_list

import IssuesList
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ocs.gitissues.R
import com.ocs.gitissues.adapter.MyIssuesListRecyclerViewAdapter
import com.ocs.gitissues.databinding.FragmentIssuesBinding


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [IssuesListFragment.OnListFragmentInteractionListener] interface.
 */
class IssuesListFragment : Fragment(), IssuesListContract.View {

    // TODO: Customize parameters
    private var columnCount = 1
    private var issuesListPresenter: IssuesListPresenter? = null
    lateinit var layoutManager: LinearLayoutManager
    lateinit var issuesResponse: List<IssuesList>
    lateinit var fragmentIssuesBinding: FragmentIssuesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        fragmentIssuesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false)
        issuesListPresenter = IssuesListPresenter(this);
        layoutManager = LinearLayoutManager(requireContext())
        issuesListPresenter!!.requestDataFromServer()

        return fragmentIssuesBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */



    override fun showProgress() {
        fragmentIssuesBinding.pbIssuesList.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        fragmentIssuesBinding.pbIssuesList.visibility = View.VISIBLE

    }

    override fun onResponseFailure(throwable: Throwable?) {
        fragmentIssuesBinding.tvErrorMessage.visibility = View.VISIBLE
        fragmentIssuesBinding.rvIssuesList.visibility = View.GONE
        fragmentIssuesBinding.tvErrorMessage.setText(throwable!!.message.toString())
    }

    override fun setDataToRecyclerView(issuesResponse: List<IssuesList?>?) {
        this.issuesResponse = issuesResponse as List<IssuesList>
        // Set the adapter
        if (issuesResponse.size > 0) {
            fragmentIssuesBinding.rvIssuesList.layoutManager= layoutManager
            var adapter = MyIssuesListRecyclerViewAdapter(issuesResponse)
            fragmentIssuesBinding.rvIssuesList.visibility = View.VISIBLE

            fragmentIssuesBinding.rvIssuesList.adapter = adapter
        }else{
            fragmentIssuesBinding.tvErrorMessage.visibility = View.VISIBLE
            fragmentIssuesBinding.rvIssuesList.visibility = View.GONE
            fragmentIssuesBinding.tvErrorMessage.setText(getString(R.string.no_text))
        }

    }
}
