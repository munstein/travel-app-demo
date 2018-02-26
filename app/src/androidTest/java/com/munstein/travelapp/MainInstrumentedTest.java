package com.munstein.travelapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.munstein.travelapp.views.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.munstein.bemobiviajabessa", appContext.getPackageName());
    }

    @Test
    public void navigateToDetailActivityAndClickDialog(){
        onView(withId(R.id.main_recyclerview)).perform(click());
        onView(withId(R.id.detail_btn_buy)).perform(click());
        onView(withText(mActivityRule.getActivity().getString(R.string.dialog_payment_title))).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfErrorViewsAreGone(){
        onView(withId(R.id.main_txt_error)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }


}
