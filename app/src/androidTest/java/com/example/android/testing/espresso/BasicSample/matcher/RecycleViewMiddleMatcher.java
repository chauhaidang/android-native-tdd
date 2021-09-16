package com.example.android.testing.espresso.BasicSample.matcher;

import com.example.android.testing.espresso.BasicSample.CustomAdapter;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class RecycleViewMiddleMatcher extends TypeSafeMatcher<CustomAdapter.ViewHolder> {

    @Override
    public void describeTo(Description description) {
        description.appendText("item in the middle");
    }

    @Override
    protected boolean matchesSafely(CustomAdapter.ViewHolder customHolder) {
        return customHolder.getIsInTheMiddle();
    }
}
