package com.github.bassaer.apptesting;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.github.bassaer.apptesting.ImageViewDrawableMatcher.withDrawable;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * UI test for MainActivity
 * Created by nakayama on 2017/06/25.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    private static final String TEXT_ITEM_ODD = "android";
    private static final String TEXT_ITEM_EVEN = "espresso";
    Context mContext;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testFirstRow() throws Exception {
        onRow(0).onChildView(withId(R.id.item_text)).check(
                matches(withText(mContext.getString(R.string.app_name)))
        );
    }

    @Test
    public void testShowingToast() throws Exception {
        //Tap first row
        onRow(0).onChildView(withId(R.id.item_text)).perform(click());
        //Checking toast message
        onView(withText(MainActivity.TOAST_MESSAGE))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testShowingIcon() throws Exception {
        onRow(0).onChildView(withId(R.id.item_icon)).check(matches(withDrawable(R.drawable.ic_android)));
    }

    @Test
    public void testAddingNewRowFromInput() throws Exception {
        //Type text length number is odd
        inputText(TEXT_ITEM_ODD);
        //Check text
        onRow(1).onChildView(withId(R.id.item_text)).check(matches(withText(TEXT_ITEM_ODD)));
        //Shows thumb down icon if input text length is odd.
        onRow(1).onChildView(withId(R.id.item_icon)).check(matches(withDrawable(R.drawable.ic_thumb_down)));
        inputText(TEXT_ITEM_EVEN);
        onRow(2).onChildView(withId(R.id.item_text)).check(matches(withText(TEXT_ITEM_EVEN)));
        onRow(2).onChildView(withId(R.id.item_icon)).check(matches(withDrawable(R.drawable.ic_thumb_up)));
    }

    private void inputText(String text) {
        //Reset input
        onView(withId(R.id.input)).perform(replaceText(""));
        //Type text
        onView(withId(R.id.input)).perform(typeText(text));
        //Close keyboard
        onView(withId(R.id.input)).perform(closeSoftKeyboard());
        //Tap button
        onView(withId(R.id.send_button)).perform(click());
    }

    private static DataInteraction onRow(int position) {
        return onData(anything()).inAdapterView(withId(R.id.main_list)).atPosition(position);
    }

}