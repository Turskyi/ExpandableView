package io.github.turskyi.expandableview

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import java.util.*

class CustomExpandableListAdapter(
    private val context: Context, private val expandableListTitle: List<String>,
    private val expandableListDetail: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
    override fun getChild(listPosition: Int, expandedListPosition: Int) =
        expandableListDetail[expandableListTitle[listPosition]]?.get(expandedListPosition)

    override fun getChildId(listPosition: Int, expandedListPosition: Int) =
        expandedListPosition.toLong()

    override fun getChildView(
        listPosition: Int, expandedListPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup
    ): View {
        var view = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as String
        if (view == null) {
            val layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = layoutInflater.inflate(R.layout.list_item, parent, false)
        }
        val expandedListTextView = view
            ?.findViewById<View>(R.id.expandedListItem) as TextView
        expandedListTextView.text = expandedListText
        return view
    }

    override fun getChildrenCount(listPosition: Int) =
        expandableListDetail[expandableListTitle[listPosition]]?.size ?: 0

    override fun getGroup(listPosition: Int) = expandableListTitle[listPosition]

    override fun getGroupCount() = expandableListTitle.size
    override fun getGroupId(listPosition: Int) = listPosition.toLong()

    override fun getGroupView(
        listPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup
    ): View {
        var view = convertView
        val listTitle = getGroup(listPosition)
        if (view == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = layoutInflater.inflate(R.layout.list_group, parent, false)
        }
        val listTitleTextView = view
            ?.findViewById<View>(R.id.listTitle) as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return view
    }

    override fun hasStableIds() = false
    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int) = true
}