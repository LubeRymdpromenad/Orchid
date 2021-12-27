package com.example.orchid.views

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.orchid.R
import com.example.orchid.data.PlantData
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PlantDetailFragmentTest {

    @Test
    fun plantDetailFragment() {
        val description = "The avocado (Persea americana) is a tree..."

        val plantData = PlantData(
            "persea-americana",
            "Avocado",
            description,
            "https://upload.wikimedia.org/wikipedia/commons/e/e4/Branch_and_fruit_of_the_Maluma_avocado_cultivar.jpg"
        )
        val fragmentArgs = bundleOf("arg_plant" to plantData)

        val scenario = launchFragmentInContainer<PlantDetailFragment>(fragmentArgs)

        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withText("Avocado")).check(matches(isDisplayed()))

        onView(withText(description)).check(matches(isDisplayed()))

        onView(withId(R.id.description)).check(matches(isDisplayed()))
        
    }
}
