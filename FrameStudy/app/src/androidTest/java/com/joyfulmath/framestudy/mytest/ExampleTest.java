package com.joyfulmath.framestudy.mytest;

import android.test.InstrumentationTestCase;

/**
 * Created by Administrator on 2016/9/25 0025.
 */
public class ExampleTest extends InstrumentationTestCase {

    public void test() throws Exception {
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected, reality);
    }

    public void test_2() throws Exception{
        final int expected = 2;
        final int reality = 3;
        assertEquals(expected,reality);
    }
}
