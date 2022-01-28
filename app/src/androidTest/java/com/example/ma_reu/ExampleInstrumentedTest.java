package com.example.ma_reu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static com.RecyclerViewItemCountAssertion.withItemCount;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

import com.example.ma_reu.UI.Activity.MainActivity;
import com.example.ma_reu.data.model.Meeting;
import com.example.ma_reu.data.repository.MeetingRepository;

import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static int ITEMS_COUNT = 1;
    private List<MeetingRepository> meetingRepositoryList;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.ma_reu", appContext.getPackageName());
    }

    /**
     * When we delete an item, the item is no more shown
     */

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        //Given : We remove the element at position 1
        onView(allOf(withId(R.id.recycler_view_list_meetings),hasFocus()))
        .check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.recycler_view_list_meetings),hasFocus()))
                .perform(actionOnItemAtPosition(0, new DeleteViewAction()));
        //Then : the number of element is 11
        onView(allOf(withId(R.id.recycler_view_list_meetings),hasFocus()))
                .check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click on Fab TabLayout, we must see the page for add a meeting
     */

    @Test
    public void myMeetingList_clikListElement_shouldOpenMeetingCreate () {
        onView(allOf(withId(R.id.fab_list_meetings_fragment)))
        .perform(click());

        onView(withId(R.id.add_meeting_fragment)).check(matches(isDisplayed()));
    }





}