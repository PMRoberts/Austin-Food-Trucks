package com.project4398.michael.austinfoodtrucks;

import android.content.Context;
import android.test.AndroidTestCase;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by PaleToys on 8/7/2015.
 */
public class DeleteAnObjectTest extends AndroidTestCase {


    public void setUp() throws Exception {
        super.setUp();
        AWSInterface.




    }
        AWSInterface aws = new AWSInterface(mContext );
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

    public void tearDown() throws Exception {

    }
}