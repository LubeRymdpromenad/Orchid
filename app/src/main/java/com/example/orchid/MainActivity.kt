package com.example.orchid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.orchid.views.PlantListFragment
import androidx.fragment.app.Fragment
import com.example.orchid.data.PlantData
import com.example.orchid.views.Navigator
import com.example.orchid.views.PlantDetailFragment
import com.example.orchid.views.UnsplashListFragment
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment(PlantListFragment.newInstance())
    }

    private fun openFragment(fragment: Fragment) {
        if (!supportFragmentManager.isStateSaved) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment, fragment.javaClass.canonicalName)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun openDetails(plantData: PlantData) {
        Log.i(TAG, "Open plant: $plantData")
        openFragment(PlantDetailFragment.newInstance(plantData))
    }

    override fun searchUnsplash(query: String) {
        Log.i(TAG, "Search unsplash for: $query")
        openFragment(UnsplashListFragment.newInstance(query))
    }
}
