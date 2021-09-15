package com.example.android.testing.espresso.BasicSample;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;


import androidx.test.espresso.DataInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LongListActivityTest {
    private static final String LAST_ITEM_TEXT = "item: 99";
    private static final String TEXT_ITEM_30 = "item: 30";
    private static final String TEXT_ITEM_30_SELECTED = "30";
    private static final String TEXT_ITEM_60 = "item: 60";

    @Rule public ActivityScenarioRule<LongListActivity> activityScenarioRule =
            new ActivityScenarioRule<>(LongListActivity.class);

    @Test
    public void shouldNotShowLastItem() {
        onView(withText(LAST_ITEM_TEXT)).check(doesNotExist());
    }

    @Test
    public void shouldBeAbleToScrollToLastItem() {
        onRow(LAST_ITEM_TEXT).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void shouldUpdateSelectedRowAfterTap() {
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowContentTextView)).perform(click());
        onView(withId(R.id.selectedRowValue)).check(matches(withText(TEXT_ITEM_30_SELECTED)));
    }

    @Test
    public void shouldUpdateToggleStateAfterTap() {
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowToggle))
                .perform(click())
                .check(isChecked());
    }

    @Test
    public void shouldNotUpdateSelectedRowAfterTappingToggle() {
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowContentTextView)).perform(click());
        onRow(TEXT_ITEM_60).onChildView(withId(R.id.rowToggle)).perform(click());
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowToggle)).perform(click());
        onView(withId(R.id.selectedRowValue)).check(matches(withText(TEXT_ITEM_30_SELECTED)));
    }

    private DataInteraction onRow(String withText) {
        return onData(hasEntry(equalTo(LongListActivity.ROW_TEST), is(withText)));
    }
}
