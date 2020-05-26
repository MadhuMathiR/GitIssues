package com.ocs.gitissues.adapter


import IssuesList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ocs.gitissues.R
import kotlinx.android.synthetic.main.fragment_issues_list.view.*

class MyIssuesListRecyclerViewAdapter(
        private val mValues: List<IssuesList>)
    : RecyclerView.Adapter<MyIssuesListRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_issues_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.tvIssueTitle.text = item.title
        holder.tvIssueNumber.text = item.number.toString()
        holder.tvIssueSubmittedBy.text = item.user.login
/*

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
*/
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val tvIssueTitle: TextView = mView.tvIssueTitle
        val tvIssueNumber: TextView = mView.tvIssueNumber
        val tvIssueSubmittedBy: TextView = mView.tvIssueSubmittedBy

    }
}
