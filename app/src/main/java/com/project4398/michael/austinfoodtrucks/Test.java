package com.project4398.michael.austinfoodtrucks;
import android.content.Context;
import android.test.AndroidTestCase;

import junit.framework.Assert;
import java.util.ArrayList;


/**
 *
 * </p>List of tests
 * </p>testUploadOneItem
 * </p>testUpload10Items
 * </p>List of tests
 * </p>List of tests
 *
 * @author Paul M. Roberts
 * @author Luis M. Rocha
 */
public class Test {
    String pass = "Test test passed";
    String fail = "Test has failed";
    /**
     * This test checks that upload item is actually uplading the item into s3. It gets the number
     * of items online before the upload and then the number after the upload. Then the numbers are
     * compared. If the numbers are the same; if they are that means that the item was not uploaded
     * there for the test has failed.
     */
    public String testUploadOneItem(Context mContext){
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
        if(total_number_online == total_number_online_before_update){
            return fail;
        }

        //If item was uploaded now it will be erased.
        DeleteAnObject del = new DeleteAnObject(aws.Bucket_Curr,truckDummy.name,
                aws.getAmazonS3Client());

        return pass;
    }
    /**
     * This test checks that upload item is actually uplading the item into s3. It gets the number
     * of items online before the upload and then the number after the upload. Then the numbers are
     * compared. If the numbers are the same; if they are that means that the item was not uploaded
     * there for the test has failed.
     */
    public String testUploadOneItem(Context mContext){
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
        if(total_number_online == total_number_online_before_update){
            return fail;
        }

        //If item was uploaded now it will be erased.
        DeleteAnObject del = new DeleteAnObject(aws.Bucket_Curr,truckDummy.name,
                aws.getAmazonS3Client());

        return pass;
    }
}
