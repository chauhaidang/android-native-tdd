package com.example.android.testing.espresso.BasicSample.matcher;

import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.espresso.Root;

import com.example.android.testing.espresso.BasicSample.R;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    protected boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            return windowToken == appToken;
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Match Toast");
    }
}
