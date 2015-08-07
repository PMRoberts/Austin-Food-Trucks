package com.project4398.michael.austinfoodtrucks;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.test.AndroidTestCase;
import android.test.MoreAsserts;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.Display;

import com.project4398.michael.austinfoodtrucks.AWSInterface;
import com.project4398.michael.austinfoodtrucks.DeleteAnObject;
import com.project4398.michael.austinfoodtrucks.TruckInfo;

import junit.framework.Assert;
import org.junit.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import junit.framework.Assert;
import android.test.ActivityTestCase;

import junit.framework.Assert;
import junit.framework.TestCase

/**
 *
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class s3Tests extends TestCase {

    /**
     * This test checks that upload item is actually uplading the item into s3. It gets the number
     * of items online before the upload and then the number after the upload. Then the numbers are
     * compared. If the numbers are the same; if they are that means that the item was not uploaded
     * theere for the test has failed.
     */
    @Test
    public void testUploadOneItem(){
        AWSInterface aws = new AWSInterface(mContext);
        boolean has_changed = false;

        //An arraylast to hold the amount of trucks before the upload.
        ArrayList<String> total = aws.AllItemsInBucket();
        int total_number_online_before_update = total.size();

        //Dummy item to be uploaded to s3
        TruckInfo truckDummy = new TruckInfo();
        truckDummy.name = "TestDummy";
        aws.uploadItem(truckDummy);

        //An arrayList after the upload
        ArrayList<String> total_after_upload = aws.AllItemsInBucket();
        int total_number_online = total_after_upload.size();

        //if size is still the same it means that the item was not uploaded to s3.
        Assert.assertEquals(total_number_online, total_number_online_before_update);

        DeleteAnObject del = new DeleteAnObject(aws.Bucket_Curr,truckDummy.name,
                aws.getAmazonS3Client());
    }
}
