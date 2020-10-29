package io.github.turskyi.expandableview

import android.os.Bundle
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    var expandableListView: ExpandableListView? = null
    var expandableListAdapter: ExpandableListAdapter? = null
    var expandableListTitle: List<String>? = null
    var expandableListDetail: HashMap<String, List<String>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        expandableListView = findViewById(R.id.expandableListView)
        expandableListDetail = ExpandableListDataPump.data
        expandableListTitle = ArrayList(expandableListDetail?.keys as MutableCollection<out String>)
        expandableListAdapter = CustomExpandableListAdapter(
            this, expandableListTitle as ArrayList<String>,
            ExpandableListDataPump.data
        )
        expandableListView?.setAdapter(expandableListAdapter)
        expandableListView?.setOnGroupExpandListener { groupPosition ->
            Toast.makeText(
                applicationContext,
                (expandableListTitle as ArrayList<String>)[groupPosition] + " List Expanded.",
                Toast.LENGTH_SHORT
            ).show()
        }
        expandableListView?.setOnGroupCollapseListener { groupPosition ->
            Toast.makeText(
                applicationContext,
                (expandableListTitle as ArrayList<String>)[groupPosition] +
                        " List Collapsed.",
                Toast.LENGTH_SHORT
            ).show()
        }
        expandableListView?.setOnChildClickListener { _, _,
                                                      groupPosition, childPosition, _
            ->
            Toast.makeText(
                applicationContext,
                (expandableListTitle as ArrayList<String>)[groupPosition] + " -> "
                        + (expandableListDetail?.get(
                    (expandableListTitle as ArrayList<String>)
                            [groupPosition]
                )
                    ?.get(childPosition)),
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }
}
