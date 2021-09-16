package com.example.android.testing.espresso.BasicSample;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecycleViewTest {
    private static final int ITEM_TO_SCROLL_INTO = 99;

    @Rule public ActivityScenarioRule<RecycleViewActivity> recycleViewActivity =
            new ActivityScenarioRule<>(RecycleViewActivity.class);


    @Test(expected = PerformException.class)
    public void itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains the special text.
        onView(withId(R.id.recyclerView))
                // scrollTo will fail the test if no item matches.
                .perform(scrollTo(
                        hasDescendant(withText("UN EXIST ITEM"))
                ));
    }

    @Test
    public void scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(ITEM_TO_SCROLL_INTO, click()));

        // Match the text in an item below the fold and check that it's displayed.
        String itemElementText = getApplicationContext().getResources().getString(
                R.string.item_element_text) + (ITEM_TO_SCROLL_INTO + 1);
        onView(withText(itemElementText)).check(matches(isDisplayed()));
    }

    @Test
    public void itemInMiddleOfList_hasSpecialText() {
        // First, scroll to the view holder using the isInTheMiddle matcher.
        onView(withId(R.id.recyclerView))
                .perform(scrollToHolder(new RecycleViewMiddleMatcher()));

        // Check that the item has the special text.
        String middleElementText =
                getApplicationContext().getResources().getString(R.string.middle);
        onView(withText(middleElementText)).check(matches(isDisplayed()));
    }


    class RecycleViewMiddleMatcher extends TypeSafeMatcher<CustomAdapter.ViewHolder> {

        @Override
        public void describeTo(Description description) {
            description.appendText("item in the middle");
        }

        @Override
        protected boolean matchesSafely(CustomAdapter.ViewHolder customHolder) {
            return customHolder.getIsInTheMiddle();
        }
    }
}


