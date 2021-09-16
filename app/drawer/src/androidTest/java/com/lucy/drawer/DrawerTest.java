package com.lucy.drawer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isOpen;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DrawerTest {

    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void shouldOpenDrawer() {
        int drawerId = R.id.drawer_layout;
        onView(withId(drawerId))
                .perform(DrawerActions.open())
                .check(matches(isOpen()));

        onView(withText("Gallery")).perform(click());
        onView(withId(R.id.text_gallery)).check(matches(isDisplayed()));

        onView(withId(drawerId))
                .perform(DrawerActions.open())
                .check(matches(isOpen()));

        onView(withText("Slideshow")).perform(click());
        onView(withId(R.id.text_slideshow)).check(matches(isDisplayed()));
    }
}
