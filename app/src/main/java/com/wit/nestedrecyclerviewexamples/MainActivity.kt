package com.wit.nestedrecyclerviewexamples

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.wit.nestedrecyclerviewexamples.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val fragmentTag = "fragmentTag"

		binding.standardRecyclerView.setOnClickListener {
			supportFragmentManager.beginTransaction().run {
				replace(R.id.fragment_container, StandardRecyclerViewFragment(), fragmentTag)
				setReorderingAllowed(true)
				commit()
			}
		}

		binding.nestedRecyclerView.setOnClickListener {
			supportFragmentManager.beginTransaction().run {
				replace(R.id.fragment_container, NestedRecyclerViewFragment(), fragmentTag)
				setReorderingAllowed(true)
				commit()
			}
		}

		binding.clear.setOnClickListener {
			val fragment = supportFragmentManager.findFragmentByTag(fragmentTag)

			if (fragment != null) {
				supportFragmentManager.beginTransaction().run {
					remove(fragment)
					setReorderingAllowed(true)
					commit()
				}
			}
		}
	}

	companion object {
		// The number of items we want to render in each fragment.
		const val ITEM_COUNT = 500
	}
}