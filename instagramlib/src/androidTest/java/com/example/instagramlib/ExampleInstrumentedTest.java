package com.example.instagramlib;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.instagramlib.dataProvider.DataProvider;
import com.example.instagramlib.model.database.InstagramDatabaseUser;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "ExampleInstrumentedTest";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        final Context appContext = InstrumentationRegistry.getTargetContext();

       
        assertEquals("com.example.instagramlib.test", appContext.getPackageName());
    }


    @Test
    public void test() {
        final Context appContext = InstrumentationRegistry.getTargetContext();

        InstagramDatabaseUser user = DataProvider.getSingletone(appContext).getInstagramDataProvider().loadInstagramDatabaseUser("asd");
        String cookies = user.getCookies();
        Log.d(TAG, "test1: " + user);

        try {
            Thread.sleep(180000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
