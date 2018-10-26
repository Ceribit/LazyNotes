package com.ceribit.android.lazynotes;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;


import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.ceribit.android.lazynotes.RecyclerViewTestUtils.TestUtils.withRecyclerView;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewLayoutTest {
    private final String DEFAULT_TITLE_TEXT = "DEFAULT_TITLE";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void doNothing_CorrectInstantiation(){
        onView(withRecyclerView(R.id.note_recycler_view)
                .atPositionOnView(0, R.id.grid_item_title))
                .check(matches(withText("title")));
    }

    @Test
    public void clickGridItem_DetailFragmentDisplays(){
        onView(withId(R.id.note_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.fragment_note_title)).check(matches(isDisplayed()));
    }
}
