package com.project4398.michael.austinfoodtrucks;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.widget.Button;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.internal.Constants;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

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

    public String Bucket_Curr = "aft.version.1.1.1";
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
        bucket_name = "grp3.txstate.edu";
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


    public void DownloadList()
    {

        /*
            The next block comment should only be used to generate data for the
            truck list. Roughly 210 lines of code.
         */
        ArrayList<TruckInfo> TLITemp;
        ArrayList<menuItem> menuTemp;
        ArrayList<menuItem> menuTemp2;

        TLITemp = new ArrayList<TruckInfo>();
        menuTemp = new ArrayList<menuItem>();
        menuTemp2 = new ArrayList<menuItem>();

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food1";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = "$7.00";
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;
        menuTemp.get(menuTemp.size()-1).TruckId = 0;
        menuTemp.get(menuTemp.size()-1).id = 0;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food2";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = "$7.00";
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;
        menuTemp.get(menuTemp.size()-1).TruckId = 0;
        menuTemp.get(menuTemp.size()-1).id = 1;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food3";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = "$7.00";
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;
        menuTemp.get(menuTemp.size()-1).TruckId = 0;
        menuTemp.get(menuTemp.size()-1).id = 2;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food4";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = "$7.00";
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;
        menuTemp.get(menuTemp.size()-1).TruckId = 0;
        menuTemp.get(menuTemp.size()-1).id = 3;

        menuTemp.add(new menuItem());
        menuTemp.get(menuTemp.size()-1).name = "food5";
        menuTemp.get(menuTemp.size()-1).description = "it is edable";
        menuTemp.get(menuTemp.size()-1).price = "$7.00";
        menuTemp.get(menuTemp.size()-1).inStock = true;
        menuTemp.get(menuTemp.size()-1).favorite = true;
        menuTemp.get(menuTemp.size()-1).TruckId = 0;
        menuTemp.get(menuTemp.size()-1).id = 4;


        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "cow";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = "$7.00";
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;
        menuTemp2.get(menuTemp2.size()-1).TruckId = 1;
        menuTemp2.get(menuTemp2.size()-1).id = 0;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "chicken";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = "$7.00";
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;
        menuTemp2.get(menuTemp2.size()-1).TruckId = 1;
        menuTemp2.get(menuTemp2.size()-1).id = 1;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "lamb";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = "$7.00";
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;
        menuTemp2.get(menuTemp2.size()-1).TruckId = 1;
        menuTemp2.get(menuTemp2.size()-1).id = 2;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "pig";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = "$7.00";
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;
        menuTemp2.get(menuTemp2.size()-1).TruckId = 1;
        menuTemp2.get(menuTemp2.size()-1).id = 3;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "fish";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = "$7.00";
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;
        menuTemp2.get(menuTemp2.size()-1).TruckId = 1;
        menuTemp2.get(menuTemp2.size()-1).id = 4;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "rock";
        menuTemp2.get(menuTemp2.size()-1).description = "it is not edable";
        menuTemp2.get(menuTemp2.size()-1).price = "$7.00";
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;
        menuTemp2.get(menuTemp2.size()-1).TruckId = 1;
        menuTemp2.get(menuTemp2.size()-1).id = 5;

        menuTemp2.add(new menuItem());
        menuTemp2.get(menuTemp2.size()-1).name = "plant";
        menuTemp2.get(menuTemp2.size()-1).description = "it is edable";
        menuTemp2.get(menuTemp2.size()-1).price = "$7.00";
        menuTemp2.get(menuTemp2.size()-1).inStock = true;
        menuTemp2.get(menuTemp2.size()-1).favorite = true;
        menuTemp2.get(menuTemp2.size()-1).TruckId = 1;
        menuTemp2.get(menuTemp2.size()-1).id = 6;

        TLITemp.add(new TruckInfo());
        TLITemp.get(TLITemp.size()-1).name = "bob";
        //TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType="person";
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
//        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType="tacos";
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
//        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType="burgers";
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
//        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType="erererf";
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
//        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType="food";
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
//        TLITemp.get(TLITemp.size()-1).foodType = new ArrayList<String>();
        TLITemp.get(TLITemp.size()-1).foodType="thai";
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



        mTruckList = TLITemp;

//        TruckInfo truckInfo = new TruckInfo();
//        truckInfo = downloadItem();
//        Log.d("userDebug", "Trying out the download item eh." + truckInfo.name.toString());
//        Log.d("userDebug", "eh" + truckInfo.id);
//        mTruckList.add(truckInfo);
        //Iterates throught the array list to upload.

        /*
            The next block of code tries to upload all the trucks that
            needed to be uploaded by bulk if you wish to upload a single
            item you shoul call the function uploadItem(TruckInfo);
         */
//        for(int i = 0; i < mTruckList.size();i++){
//            //Assersts if there is no truck in the list
//            if(mTruckList.size() != 0) {
//                //uploads the current item to aws saving station
//                uploadItem(mTruckList.get(i));
//                break;
//            }
//            else{Log.d("userDebug","Just Letting you " +
//                    "know there isn't any trucks to upload");}
//        }
    }

    /**
     * Download the given item.
     * @return returns the downloaed item.
     */
    public TruckInfo downloadItem(){
        TruckInfo item = new TruckInfo();
        /*
            First we create an s3Cliente to communicate and download the object;
         */
        AmazonS3Client s3Client = new AmazonS3Client(new BasicAWSCredentials(
                "AKIAJL2EY65OZGTME4SA",
                "XkFG0M28GpE7/x2h0w5nE8rME/v0LsTr1O8s3SRc"));
        Log.d("userDebug", "Inside downloadItem();s3Client Created");

        S3Object s3object = s3Client.getObject(new GetObjectRequest(
                Bucket_Curr, "bob"));
        Log.d("userDebug", "Inside downloadItem();created an s3Object and" +
                " getobject reques");


       // String jsonObject = s3object.getObjectContent().toString();

        try {
            Log.d("userDebug", "Inside downloadItem();Output of currently downloaded file");
            String hope = displayTextInputStream(s3object.getObjectContent());
            Log.d("userDebug", "Inside downloadItem();End of downloaded file");

            TruckInfo yassss = new TruckInfo();
            yassss = jsonToObject(hope);
            item = yassss;
        } catch (IOException e) {
            e.printStackTrace();
        }




        //@todo parse through the file to get all attributes into a truck info item






        return item;
    }

    public TruckInfo jsonToObject(String string){

        Log.d("userDebug","jsonToOBjectStart");
        Log.d("userDebug",string);
        //Creates a new truck info.
        TruckInfo truck = new TruckInfo();
        try {
            JSONObject jsonObj = new JSONObject(string);
            int i = 0;
            Log.d("userDebug", "Current downloaded json string: " + string);
            Log.d("userDebug","jsonToOBject();These are the attributes" +
                    " from online");
            //Name,imageUrl,about,phonenumber,distance,id,lat,long,userid,password
            truck.name = (String) jsonObj.get("name");i++;
            truck.name = "New" + truck.name.toString();
            Log.d("userDebug","user::"+i);
            truck.imageURL = (String) jsonObj.get("imageUrl");i++;
            Log.d("userDebug","user::"+i);
            truck.about = (String)jsonObj.get("about");i++;
            Log.d("userDebug","user::"+i);
            truck.phoneNumber = (String)jsonObj.get("phoneNumber");i++;
            Log.d("userDebug","user::"+i);
            truck.distance = (Integer) jsonObj.get("distance");i++;
            Log.d("userDebug","user::"+i);
            truck.id = (Integer)  jsonObj.get("id");i++;
            Log.d("userDebug","user::"+i);

            //obj.put("favorite", item.favorite);

            truck.foodType ="Da fudging best!";
//            truck.foodType = (String) jsonObj.get("da best")
            //Lat and Long for the current item.
            truck.latitude = (Double)  jsonObj.get("latitude");
            truck.longitude = (Double)  jsonObj.get("longitude");


            String us  = (String) jsonObj.get("UserID");i++;
            truck.setUserID(us);
            String ps =  (String) jsonObj.get("Password");i++;
            truck.setPassword(ps);

            Log.d("userDebug","jsonToObject() asdfasdfasdfaf:" + truck.name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return truck;
    }



    /**
     * Display the text from the downloaded file for debugging purposes.
     * @param input we input the file. to read it and make sure that it will done.
     * @throws IOException
     */
    private String displayTextInputStream(InputStream input)
            throws IOException {
        // Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(input));

        String line = "";
        int i = 0;

        line = line + reader.readLine();
            Log.d("userDebug","loop");
        Log.d("userDebug",line);
        return line;
    }


    /**
     * Creates a temporary file with text data to demonstrate uploading a file
     * to Amazon S3
     *
     * @return A newly created temporary file with text data.
     *
     * @throws IOException
     */
    private static File createSampleFile(JSONObject jsonObject) throws IOException {
        File file = File.createTempFile("aws-java-sdk-", ".txt");
        file.deleteOnExit();
        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write(jsonObject.toString());
        writer.close();
        return file;
    }

    /**
     * uploadItem, Creates  a new file, with filename.txt name.
     * </p> Creates a file by the filename.txt, a fileoutpustream by the name of fos
     * </p> Create a jSON object that is then passed through a functions see
     * </p> toJsonAndBeyond for details. Tryes to create a file from the given json object
     * </p> Lastly credentials are loaded. and s3 object is created and the file is uploaded.
     * Uploads the given item.
     * @param itemToUpload - this item is converted to JSON file and then uploaded into s3.
     */
    public void uploadItem(TruckInfo itemToUpload){

        File file = new File(mContext.getFilesDir(),"fileName.txt");
        FileOutputStream fos = null;
        JSONObject TruckItemJson = toJsonAndBeyond(itemToUpload);

        try {
            file = createSampleFile(TruckItemJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
            Creates an amazon s3client;
         */
        Log.d("uploadItem","Creating an AmazonS3Client");
        AmazonS3Client s3Client = new AmazonS3Client( new BasicAWSCredentials(
                "AKIAJL2EY65OZGTME4SA",
                "XkFG0M28GpE7/x2h0w5nE8rME/v0LsTr1O8s3SRc") );


        /*
              Keep the following block for troubleshooting or mass upload!!!
         */
//        String Bucket_Curr = "group3.txstate.1250";
//        s3Client.createBucket(Bucket_Curr);
//        PutObjectRequest por = new PutObjectRequest( Bucket_Curr, itemToUpload.name, file );

        /*
            Creates an upload a file request to the amazon s3.
         */
        PutObjectRequest por = new PutObjectRequest( Bucket_Curr,
                itemToUpload.name,
                file);

        s3Client.putObject(por);
    }

    /**
     * Convets given item into a json item and returns the JSON object
     * </P> To later be use to uploaded online
     * @param item
     * @return converted json objet.
     */
    public JSONObject toJsonAndBeyond(TruckInfo item){

        //Create an object to use for puting the trucks attributes.
        JSONObject obj = new JSONObject();

        try {
            Log.d("userDebug", "Try: putting everything to objects");

            //All public items for the current item.
            obj.put("name", item.name);
            obj.put("imageUrl", "DummyURl");
            obj.put("about", item.about);
            obj.put("phoneNumber",item.phoneNumber);
            obj.put("distance", new Float(item.distance));
            obj.put("id", new Integer(item.id));

            //@todo comment it out for now might come back later;
            //obj.put("favorite", item.favorite);

            //Lat and Long for the current item.
            obj.put("latitude", new Double(item.latitude));
            obj.put("longitude", new Double(item.longitude));
            obj.put("end",null);

            /*
                Menu Arraylist for given item.
             */
            int current_menu_item = 0; //keeps track of the index
            int number_of_menu_items = 0;//initialize to zero.


            number_of_menu_items = item.menu.size();//creates a new

            //iterates through the list
            for(current_menu_item = 0;
                current_menu_item<number_of_menu_items;
                current_menu_item++) {

                ArrayList list = new ArrayList();
                list.add(item.menu.get(current_menu_item).name + "new!!yay");
                //Get Description
                list.add(item.menu.get(current_menu_item).description);
                //get price
                list.add(item.menu.get(current_menu_item).price);
                //Get ID
                list.add(item.menu.get(current_menu_item).id);
                //TruckId
                list.add(item.menu.get(current_menu_item).TruckId);
                JSONArray jsonArray = new JSONArray(list);

                obj.put("Menu_item" + current_menu_item, jsonArray);
            }

            //Private Strings from current item.
            obj.put("UserID", "UserID");
            obj.put("Password", "Password");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("userDebug",obj.toString());
        Log.d("userDebug","Done returning object");
        return obj;
    }

    /**
     * Converts the given file to a Json and returns it as an item.
     * @param file the file that will be converted in JSON
     * @return the item contained the transformed file
     */
    public TruckInfo fromJson(File file){
        TruckInfo item = new TruckInfo();
        JSONObject obj = new JSONObject();


        return item;
    }







    /***********************************************************************************************

        These functions will be to edit and modify items and check.

     **********************************************************************************************/


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

    /**
     * Method will check that the user name and password are legit.
     */
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
