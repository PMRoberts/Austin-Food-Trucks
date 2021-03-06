package com.project4398.michael.austinfoodtrucks;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.test.AndroidTestCase;
import android.util.Log;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    public String testUploadTenItem(Context mContext){
        AWSInterface aws = new AWSInterface(mContext);
        boolean has_changed = false;

        //An arraylast to hold the amount of trucks before the upload.
        ArrayList<String> total = aws.AllItemsInBucket();
        int total_number_online_before_update = total.size();

        String dummy_name = "TestDummy";
        for(int i = 0; i < 10; i++ ) {
            //Dummy item to be uploaded to s3
            TruckInfo truckDummy = new TruckInfo();
            truckDummy.name = dummy_name + i;
            aws.uploadItem(truckDummy);
        }
        //An arrayList after the upload
        ArrayList<String> total_after_upload = aws.AllItemsInBucket();
        int total_number_online = total_after_upload.size();

        //if size is still the same it means that the item was not uploaded to s3.
        if (total_number_online == total_number_online_before_update) {
            return fail;
        }

        //If item was uploaded now it will be erased.
        for(int i = 0; i < 10; i++ ) {
            DeleteAnObject del = new DeleteAnObject(aws.Bucket_Curr,dummy_name + i,
                    aws.getAmazonS3Client());
        }
        return pass;

    }

    /**
     * This test checks that the methods in the TruckInfo object work correctly.
     */
    public String testTruckInfo(Context mContext){

        TruckInfo TI = new TruckInfo();
// test that menu was initilized
        if(TI.menu == null)
        {
            return fail;
        }
//test set/get password
        String S1 = "Test";
        TI.setPassword(S1);
        if(!S1.equals(TI.getPassword()))
        {
            return fail;
        }
//test set/get userID
        String S2 = "TestID";
        TI.setUserID(S2);
        if(!S2.equals(TI.getUserID()))
        {
            return fail;
        }
//test check credentials
        if(!TI.CheckCredentials(S2,S1))
        {
            return fail;
        }

//test copy constructor
        TI.name = "testing";
        TruckInfo TI2 = new TruckInfo(TI);

        if (!TI2.name.equals(TI.name))
        {
            return fail;
        }
        TI.name = "New Name";

        if (TI2.name.equals(TI.name))
        {
            return fail;
        }

        return pass;
    }

    public String downloadItemsName(Context mContext){
        AWSInterface aws = new AWSInterface(mContext);

        //An arraylast to hold the amount of trucks before the upload.
        ArrayList<String> names_of_items = aws.AllItemsInBucket();

        for(int x = 0; x < names_of_items.size(); x++) {
            Log.d("TEST", "Items imported from S3 " + names_of_items.get(x));
        }
        //if size is still the same it means that the item was not uploaded to s3.
        if (names_of_items.isEmpty()) {
            return fail;
        }

        else {return pass;}
    }


    public String jsonToObjectTest(Context mContext){
        AWSInterface aws = new AWSInterface(mContext);

        TruckInfo tr = new TruckInfo();
        tr.name = "This is a test";
        tr.id = 999;
        tr.about = "This is a test decription";

        aws.uploadItem(tr);
        aws.downloadItem(tr.name);

        TruckInfo trN = new TruckInfo();


        String hope = "";

        AmazonS3Client s3Client = aws.getAmazonS3Client();
        S3Object s3object = s3Client.getObject(new GetObjectRequest(
                aws.Bucket_Curr, tr.name));
        try {
            hope = aws.displayTextInputStream(s3object.getObjectContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        trN = aws.jsonToObject(hope);

        Log.d("TEST", tr.name);
        Log.d("TEST", trN.name);

        if(tr.name != trN.name){
            return pass;
        }
        else{
            return fail;
        }
    }

    /**
     * This test checks that a truck info object can successfully be converted to a JSON object
     */
    public String testToJsonAndBeyond(Context mContext){
        AWSInterface aws = new AWSInterface(mContext);

        TruckInfo TI = new TruckInfo();
        TI.name = "TestName";
        TI.id = 1234;
        TI.about = "TestAbout";
        TI.imageURL = "";
        TI.phoneNumber = "44353452";
        TI.foodType = "TestFood";
        TI.distance = 1.0f;
        TI.favorite = false;
        TI.latitude = 0.0f;
        TI.longitude = 0.0f;
        TI.setUserID("TestUserID");
        TI.setPassword("TestPassword");

        JSONObject JO = aws.toJsonAndBeyond(TI);
        try
        {
            if(!JO.getString("name").equals(TI.name))
            {
                return fail;
            }
            if(JO.getInt("id") != (TI.id))
            {
                return fail;
            }
            if(!JO.getString("foodtype").equals(TI.foodType))
            {
                return fail;
            }
            if(!JO.getString("imageUrl").equals(TI.imageURL))
            {
                return fail;
            }
            if(!JO.getString("about").equals(TI.about))
            {
                return fail;
            }
            if(!JO.getString("phoneNumber").equals(TI.phoneNumber))
            {
                return fail;
            }
            if(JO.getDouble("distance") != TI.distance)
            {
                return fail;
            }
            if(JO.getDouble("latitude") != TI.latitude)
            {
                return fail;
            }
            if(JO.getDouble("longitude") != TI.longitude)
            {
                return fail;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return pass;

    }
    /**
     * This test checks that a iimage can be downloaded
     */
    public String testLoadImage(Context mContext)
    {
        AWSInterface aws = new AWSInterface(mContext);

        Drawable D = aws.loadImage("https://s3.amazonaws.com/aft.photos.250.250/1.jpeg");
        if(D == null)
        {
            return fail;
        }
        return pass;
    }
}

