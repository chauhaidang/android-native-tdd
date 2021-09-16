package com.example.android.testing.espresso.BasicSample;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicBoolean;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ToastTest {
    private View decorView;
    @Rule
    public ActivityScenarioRule<ToastActivity> toastActivityActivityScenarioRule =
            new ActivityScenarioRule<>(ToastActivity.class);

    @Before
    public void setUp() {
        //this no longer work on api sdk 30
//        toastActivityActivityScenarioRule.getScenario().onActivity((activity) ->
//            decorView = activity.getWindow().getDecorView()
//        );
    }



    @Test()
    public void toastMessage_shouldDisplay() {
        onView(withId(R.id.buttonShowToast)).perform(click());

        //Test will be failed if we use custom toast matcher like this from api sdk 30 and above
        //https://github.com/android/android-test/issues/803
//        onView(withText(R.string.toast_text))
//                .inRoot(new ToastMatcher())
//                .check(matches(isDisplayed()));

        //this wont work neither
//        onView(withText(R.string.toast_text))
//                .inRoot(withDecorView(not(decorView))) // Match to the root that is not the view of the activity as toast component is in separate process
//                .check(matches(isDisplayed()));

        //The only easiest way to test toast
        AtomicBoolean toastShow = new AtomicBoolean(false);
        toastActivityActivityScenarioRule.getScenario().onActivity(
                activity -> toastShow.set(activity.toastShow)
        );

        assertThat(toastShow.get(), equalTo(true));

        //UiAutomator can not detect toast as well
//        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//        device.waitForIdle();
//        UiObject toastComponent = device.findObject(new UiSelector().text(
//                getApplicationContext().getString(R.string.toast_text)
//        ));
    }

}
