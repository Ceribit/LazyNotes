package com.ceribit.android.lazynotes;

import android.support.annotation.Nullable;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;


import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.ceribit.android.lazynotes.RecyclerViewTestUtils.TestUtils.withRecyclerView;
import static junit.framework.TestCase.fail;

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

    @Test
    public void clickGridItem_HasCorrectDetails(){

        String text = getText(withRecyclerView(R.id.note_recycler_view)
                .atPositionOnView(0, R.id.grid_item_title));

        onView(withId(R.id.note_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.fragment_note_title)).check(matches(withText(text)));

    }


    public static String getText(final Matcher<View> matcher){

        try{
            final String[] stringHolder = new String[1];
            onView(matcher).perform(new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return isAssignableFrom(TextView.class);
                }

                @Override
                public String getDescription() { return "Gets text from a view"; }

                @Override
                public void perform(UiController uiController, View view) {
                    TextView tv = (TextView) view;
                    Log.e("NANI", tv.getClass().getSimpleName());
                    stringHolder[0] = tv.getText().toString();
                }
            });
            if(stringHolder[0].isEmpty()){
                fail("No text found for the given view");
            }
            return stringHolder[0];
        } catch (Exception e){
            fail("Null value found");
            return null;
        }
    }
}
