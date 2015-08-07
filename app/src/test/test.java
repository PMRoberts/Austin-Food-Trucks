package com.project4398.michael.austinfoodtrucks;

import android.app.Application;
import android.test.ApplicationTestCase;
import junit.framework.Assert;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    void check(){
        boolean a = true;
        boolean b = true;
        Assert.assertEquals(a,b);
    }
}