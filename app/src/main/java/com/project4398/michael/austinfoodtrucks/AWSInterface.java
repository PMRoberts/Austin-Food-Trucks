package com.project4398.michael.austinfoodtrucks;

import android.content.ClipData;
import android.content.Context;
import android.widget.Button;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by PRoberts on 7/29/15.
 */
public class AWSInterface
{
    /* Singleton */
    private static AWSInterface mAWSInterface;

    public static void setPlayer(AWSInterface aWSInterface){
        mAWSInterface = aWSInterface;}

    public static AWSInterface getPlayer(){return mAWSInterface;}
    /* Singleton End */

    private String bucket_name;
    private CognitoCachingCredentialsProvider credentialsProvider;
    private AmazonS3Client s3Client;
    private TransferUtility transferUtility;
    private Context mContext;
    public static ArrayList<TruckInfo> mTruckList;
    public int ownersTruckID = -1;




    public AWSInterface(Context context)
    {
        mContext = context;
        bucket_name = "grp3.txstate.edu.test";//@todo make sure ths works....
        // Initialize the Amazon Cognito credentials provider
        credentialsProvider = new CognitoCachingCredentialsProvider(
                context, //
                "us-east-1:e40796ae-0851-48a4-88a2-ec40aca83c4e", // Identity Pool ID
                Regions.US_EAST_1 // Region
        );

        s3Client = new AmazonS3Client(credentialsProvider);
        transferUtility = new TransferUtility(s3Client, context);
        mTruckList = new ArrayList<TruckInfo>();
    }


    /**
     * Uploads one item to the S3 bucket declared by bucket_name
     * </p>
     * uses cognito to upload the file and sets the region
     * </p>
     * Creates a new credetials provider..
     * @param item which is a TruckListInfo
     */
    public void UploadItems(ArrayList<TruckInfo> item)
    {

        String filename = "ArrayList.ser";
        FileOutputStream outputStream;
        try{
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(item);
            TransferObserver observer = transferUtility.upload(bucket_name, "ArrayList.ser", file);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Creates a file to be used as a parameter on the transferUtility.Upload(par,par,FILE)
     * @param item
     * @return File containing the item that was passed as a parameter.
     */
    public File createFile(TruckInfo item)
    {
        File file = createFile(item);
        return file;
    }

    public void DownloadItems(){
        TruckInfo Temporary_trucklistinfo = new TruckInfo();
        String filename = "ArrayList.ser";
        FileOutputStream outputStream;
        try{
            File file = new File("downloadedArray");
            TransferObserver observer = transferUtility.download(bucket_name, "ArrayList.ser", file);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void DownloadList()
    {
        AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);

        String accessKey = "AKIAJAVNV4LQ3AUCEPAA";
        String secretKey = "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";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);

        AmazonS3 conn = new AmazonS3Client(credentials, clientConfig);
        conn.setEndpoint("endpoint.com");


        mTruckList = CreateRandomInput();
        String JsonToUpload = saveToJSON(mTruckList);


        Bucket bucket = conn.createBucket("my-new-bucket");

        ByteArrayInputStream input = new ByteArrayInputStream(JsonToUpload.getBytes());
        conn.putObject(bucket.getName(), "TruckList", input,new ObjectMetadata());

    }

    /**
     * Saves the to a JSON file the object that is the truck list.
     * @param inv
     */
    public String saveToJSON(ArrayList<TruckInfo> inv){
        Gson gson = new Gson();
        String json = gson.toJson(inv);
        return  json;
    }

    /**
     * Lets fill this thing up with some data.
     * @return
     */
    public ArrayList<TruckInfo> CreateRandomInput(){
        ArrayList<TruckInfo> TLITemp;
        ArrayList<menuItem> menuTemp;
        ArrayList<menuItem> menuTemp2;


        TLITemp = new ArrayList<TruckInfo>();
        menuTemp = new ArrayList<menuItem>();
        menuTemp2 = new ArrayList<menuItem>();

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food1";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = 7.00f;
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food2";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = 7.00f;
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food3";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = 7.00f;
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food4";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = 7.00f;
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food5";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = 7.00f;
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;


        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "cow";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "chicken";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "lamb";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "pig";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "fish";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "rock";
        menuTemp2.get(menuTemp2.size()-1).description = "it is not edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "plant";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = 7.00f;
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;

        TLITemp.add(new TruckInfo());
        TLITemp.get(TLITemp.size()-1).name = "bob";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("person");
        TLITemp.get(TLITemp.size()-1).distance = 5000.0f;
        TLITemp.get(TLITemp.size()-1).favorite = true;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = "this is where the about info would go if i had anything to say about this.";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp;
        TLITemp.get(TLITemp.size()-1).phoneNumber = "1-800-382-5968";
        TLITemp.get(TLITemp.size()-1).latitude = 30.24989235;
        TLITemp.get(TLITemp.size()-1).longitude = -97.74950266;
        TLITemp.get(TLITemp.size()-1).setUserID("a");
        TLITemp.get(TLITemp.size()-1).setPassword("1234");

        TLITemp.add(new TruckInfo());
        TLITemp.get(TLITemp.size()-1).name = "trucin";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("tacos");
        TLITemp.get(TLITemp.size()-1).foodType.add("tacos");
        TLITemp.get(TLITemp.size()-1).foodType.add("tacos");
        TLITemp.get(TLITemp.size()-1).distance = 1.2f;
        TLITemp.get(TLITemp.size()-1).favorite = true;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = "its so truckin good yo. itle blow yo miiiinnnnd!!!!!";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp2;
        TLITemp.get(TLITemp.size()-1).phoneNumber = "1-800-382-5968";
        TLITemp.get(TLITemp.size()-1).latitude = 30.2597715;
        TLITemp.get(TLITemp.size()-1).longitude = -97.75454521;
        TLITemp.get(TLITemp.size()-1).setUserID("b");
        TLITemp.get(TLITemp.size()-1).setPassword("1234");

        TLITemp.add(new TruckInfo());
        TLITemp.get(TLITemp.size()-1).name = "burgs";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("burgers");
        TLITemp.get(TLITemp.size()-1).distance = 1.0f;
        TLITemp.get(TLITemp.size()-1).favorite = false;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = "i sear dead cow flesh on a burning hot mettle slab";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp;
        TLITemp.get(TLITemp.size()-1).phoneNumber = "1-800-382-5968";
        TLITemp.get(TLITemp.size()-1).latitude = 30.25261709;
        TLITemp.get(TLITemp.size()-1).longitude = -97.75918007;
        TLITemp.get(TLITemp.size()-1).setUserID("c");
        TLITemp.get(TLITemp.size()-1).setPassword("1234");

        TLITemp.add(new TruckInfo());
        TLITemp.get(TLITemp.size()-1).name = "dfasf";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("erererf");
        TLITemp.get(TLITemp.size()-1).foodType.add("cvbcvbcvb");
        TLITemp.get(TLITemp.size()-1).distance = 5050.5f;
        TLITemp.get(TLITemp.size()-1).favorite = false;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = " hkdlfashdflk hs;dfkjsah  reiotwre cb,xmvbxzv euiwqurhysdfhaksl";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp;
        TLITemp.get(TLITemp.size()-1).phoneNumber = "1-800-382-5968";
        TLITemp.get(TLITemp.size()-1).latitude = 30.26416397;
        TLITemp.get(TLITemp.size()-1).longitude = -97.76353598;
        TLITemp.get(TLITemp.size()-1).setUserID("d");
        TLITemp.get(TLITemp.size()-1).setPassword("1234");

        TLITemp.add(new TruckInfo());
        TLITemp.get(TLITemp.size()-1).name = "food and stuff";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("food");
        TLITemp.get(TLITemp.size()-1).foodType.add("stuff");
        TLITemp.get(TLITemp.size()-1).distance = 2.0f;
        TLITemp.get(TLITemp.size()-1).favorite = true;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = "we have food and stuff. buy stuff. or dont. we dont care. ";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp2;
        TLITemp.get(TLITemp.size()-1).phoneNumber = "1-800-382-5968";
        TLITemp.get(TLITemp.size()-1).latitude = 30.24327481;
        TLITemp.get(TLITemp.size()-1).longitude = -97.78186083;
        TLITemp.get(TLITemp.size()-1).setUserID("e");
        TLITemp.get(TLITemp.size()-1).setPassword("1234");

        TLITemp.add(new TruckInfo());
        TLITemp.get(TLITemp.size()-1).name = "t";
        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType.add("thai");
        TLITemp.get(TLITemp.size()-1).distance = 5.0f;
        TLITemp.get(TLITemp.size()-1).favorite = false;
        TLITemp.get(TLITemp.size()-1).id = (TLITemp.size()-1);
        TLITemp.get(TLITemp.size()-1).about = "thai breastaurant. get it?";
        TLITemp.get(TLITemp.size()-1).menu = menuTemp;
        TLITemp.get(TLITemp.size()-1).phoneNumber = "1-800-382-5968";
        TLITemp.get(TLITemp.size()-1).latitude = 30.22625621;
        TLITemp.get(TLITemp.size()-1).longitude = -97.76237726;
        TLITemp.get(TLITemp.size()-1).setUserID("f");
        TLITemp.get(TLITemp.size()-1).setPassword("1234");
        return TLITemp;
}
    public TruckInfo getTruckByID(int ID)
    {
        for (int x = 0; x < mTruckList.size(); x++)
        {
            if (mTruckList.get(x).id == ID)
            {
                return mTruckList.get(x);
            }
        }
        return null;
    }

    public void EditTruckByID(TruckInfo newInfo)
    {
        Boolean tempNew = true;
        for (int x = 0; x < mTruckList.size(); x++)
        {
            if (mTruckList.get(x).id == newInfo.id)
            {
                tempNew = false;
                mTruckList.set(x, newInfo);
            }
        }
        if (tempNew)
        {
            newInfo.id = mTruckList.size();
            ownersTruckID = newInfo.id;
            mTruckList.add(newInfo);
        }
    }

    public Boolean CheckCredentials(String id, String password)
    {
        for (int x = 0; x < mTruckList.size(); x++)
        {
            if (mTruckList.get(x).CheckCredentials(id, password))
            {
                ownersTruckID = mTruckList.get(x).id;
                return true;
            }
        }
        return false;
    }
}