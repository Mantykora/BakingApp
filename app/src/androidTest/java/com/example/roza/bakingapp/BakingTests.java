package com.example.roza.bakingapp;


import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class
BakingTests {

    //Matcher from this site: https://stackoverflow.com/a/39756832/8131467
    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;


            @Override
            public void describeTo(Description description) {
                description.appendText("with text: ");
                description.appendValue(index);
                matcher.describeTo(description);

            }
            @Override
            protected boolean matchesSafely(View item) {
                return matcher.matches(item) && currentIndex ++ == index;
            }
        };
    }



    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
  //  onView(allOf(withId(R.id.recycler_id), hasFocus())).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    @Test
    public void bakingTests() {
        ViewInteraction appCompatTextView = onView(
                allOf((withIndex(withId(R.id.recipe_name_textView),0)), withText("Nutella Pie"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.constraint.ConstraintLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.steps_tv), withText("0. Recipe Introduction"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.steps_rv),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.ingredients_tv), withText("Ingredients"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_steps_list),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView3.perform(click());

        pressBack();

        pressBack();

        ViewInteraction textView = onView(
                allOf((withIndex(withId(R.id.recipe_name_textView),0)), withText("Nutella Pie"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf((withIndex(withId(R.id.recipe_name_textView),1)), withText("Brownies"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Brownies")));

        ViewInteraction appCompatTextView4 = onView(
                allOf((withIndex(withId(R.id.recipe_name_textView), 2)), withText("Yellow Cake"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.constraint.ConstraintLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.steps_tv), withText("0. Recipe Introduction"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.steps_rv),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.ingredients_tv), withText("Ingredients"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_steps_list),
                                        0),
                                0),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.ingredients_tv), withText("Ingredients"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_steps_list),
                                        0),
                                0),
                        isDisplayed()));
        textView5.check(matches(isDisplayed()));

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
