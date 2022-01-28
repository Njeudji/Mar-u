package com.example.ma_reu.UI.Activity;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.example.ma_reu.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMeetingTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.ma_reu", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addMeetingTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab_list_meetings_fragment),
                        childAtPosition(
                                allOf(withId(R.id.list_meetings_fragment),
                                        childAtPosition(
                                                withId(R.id.fragment_container_view),
                                                0)),
                                4),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.text_input_object_meeting)
                        ));
        textInputEditText.perform(scrollTo(), replaceText("reunion"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_layout_room_meeting),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.text_input_date),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.text_layout_date),
                                        0),
                                1)));
        textInputEditText2.perform(scrollTo(), click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.text_layout_hour_start), withText("Start"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.text_layout_hour_end), withText("End"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction chip = onView(
                allOf(withText("amanda@lamzone"),
                        childAtPosition(
                                allOf(withId(R.id.chip_group_participant),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        chip.perform(scrollTo(), click());

        ViewInteraction chip2 = onView(
                allOf(withText("nkwame@lamzone"),
                        childAtPosition(
                                allOf(withId(R.id.chip_group_participant),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        chip2.perform(scrollTo(), click());

        ViewInteraction chip3 = onView(
                allOf(withText("frantz@lamzone"),
                        childAtPosition(
                                allOf(withId(R.id.chip_group_participant),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                5)));
        chip3.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.ok_button), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1)));
        materialButton.perform(scrollTo(), click());

        // Test effectué, ajout d'une réunion et vérification que la réunion a été ajouter.
        onView(AllOf.allOf(withId(R.id.recycler_view_list_meetings)))
                .check(withItemCount(2));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
