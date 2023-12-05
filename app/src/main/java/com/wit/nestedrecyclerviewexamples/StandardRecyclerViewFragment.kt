package com.wit.nestedrecyclerviewexamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.wit.nestedrecyclerviewexamples.databinding.FragmentStandardRecyclerViewBinding
import com.wit.nestedrecyclerviewexamples.databinding.ListItemBinding

class StandardRecyclerViewFragment : Fragment() {
	private val listAdapter = ListAdapter()
	private lateinit var viewBinding: FragmentStandardRecyclerViewBinding

	override fun onCreateView(
			inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBinding = FragmentStandardRecyclerViewBinding.inflate(inflater, container, false)

		return viewBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewBinding.recyclerView.adapter = listAdapter
		viewBinding.recyclerView.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

		var i = 0
		val listItems = MutableList(MainActivity.ITEM_COUNT) {
			"Item ${++i}"
		}
		listAdapter.setList(listItems)
	}

	private inner class ListAdapter : Adapter<ListAdapter.ViewHolder>() {
		private var listItems = emptyList<String>()
		private var viewHolderCreationCount = 0

		override fun getItemCount(): Int {
			return listItems.size
		}

		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			val listItem = listItems[position]
			holder.listItemBinding.label.text = listItem
		}

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			val layoutInflater = LayoutInflater.from(parent.context)
			val listItemBinding = ListItemBinding.inflate(layoutInflater, parent, false)
			val viewHolder = ViewHolder(listItemBinding)

			++viewHolderCreationCount
			viewBinding.viewHolderCreationCount.text =
				"View Holder Creation Count: $viewHolderCreationCount"

			return viewHolder
		}

		fun setList(listItems: List<String>) {
			this.listItems = listItems
			notifyDataSetChanged()
		}

		private inner class ViewHolder(val listItemBinding: ListItemBinding) :
				RecyclerView.ViewHolder(listItemBinding.root)
	}
}