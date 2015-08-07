package com.project4398.michael.austinfoodtrucks;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
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
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This java class will handle or the uploading and downloading of the S3 storate unit it will also
 * </p> deal with updating items and deleting items as they are no longer needed.
 *
 * @version 1.0 - most features are there but some are not yet finished being implemented. The
 * TruckInfo profile when downloaded from S3 does not contain the menuItems array yet. The changes
 * to menu are however uploaded.
 *
 *@author Paul M. Roberts
 *@author Luis M. Rocha
 */
public class AWSInterface
{
    /* Singleton */
    private static AWSInterface mAWSInterface;

    public static void setPlayer(AWSInterface aWSInterface){
        mAWSInterface = aWSInterface;}

    public static AWSInterface getPlayer(){return mAWSInterface;}
    /* Singleton End */

    public String Bucket_Curr = "aft.version.1.2.0"; //Current Version of Data
    private String bucket_name;
    private CognitoCachingCredentialsProvider credentialsProvider;
    private AmazonS3Client s3Client;
    private TransferUtility transferUtility;
    public Context mContext;
    public static ArrayList<TruckInfo> mTruckList;
    public int ownersTruckID = -1;

    private String  truck_to_be_deleted = "";

    public ArrayList<String> AllItemsInBucket(){
        ArrayList<String> list = new ArrayList<String>(0);
        /*
            First we create an s3Client to communicate and download the object;
         */
        AmazonS3Client s3client = getAmazonS3Client();
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(Bucket_Curr)
                .withPrefix("");
        ObjectListing objectListing;
        do {
            objectListing = s3client.listObjects(listObjectsRequest);
            for (S3ObjectSummary objectSummary :
                    objectListing.getObjectSummaries()) {
                list.add(objectSummary.getKey());
            }
            listObjectsRequest.setMarker(objectListing.getNextMarker());
        } while (objectListing.isTruncated());
        return list;
    }


    /**
     * This is used to be able to access s3. It automally creates a key for the developer/user
     * @return an amazons3 clients that allow upload and download
     */
    public AmazonS3Client getAmazonS3Client() {
        return new AmazonS3Client(new BasicAWSCredentials(
                "AKIAJL2EY65OZGTME4SA",
                "XkFG0M28GpE7/x2h0w5nE8rME/v0LsTr1O8s3SRc"));
    }

    /**
     * This used to access the S3 database.
     * @param context This gives the whole method the context that wil be used to invoke other
     *                methods
     */
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

    public void createImageForTruck(int current_key) {
        mTruckList.get(current_key).imageURL = "https://s3.amazonaws.com/aft.photos.500.500/Truck+("+ (current_key + 1) +").jpg";
        mTruckList.get(current_key).image = loadImage(mTruckList.get(current_key).imageURL);
    }

    /**
     * Display the text from the downloaded file for debugging purposes.
     * @param input we input the file. to read it and make sure that it will done.
     * @throws IOException
     */
    private String displayTextInputStream(InputStream input)
            throws IOException {
        // Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = "";
        int i = 0;
        line = line + reader.readLine();
        return line;
    }

    public void DownloadList()
    {
        /*
               Test Cases
         */
        Log.d("Test","Test Case Commence\n\n\n");

        String message;

        //Test to see if an item can be added correctly
        message  = new Test().testTruckInfo(mContext);
        Log.d("TEST","testTruckInfo : " + message);

        //Test to see if an item can be added correctly
        message  = new Test().testUploadOneItem(mContext);
        Log.d("TEST","TestUploadOneItem : " + message);

        //Test to see if an item can be added correctly
        message  = new Test().testUploadTenItem(mContext);
        Log.d("TEST","TestUploadTenItem : " + message);


        ArrayList <String> list_of_trucks_from_s3 = AllItemsInBucket();
        for(int current_key = 0; current_key < list_of_trucks_from_s3.size(); current_key++) {
            TruckInfo truckInfo = new TruckInfo();
            truckInfo = downloadItem(list_of_trucks_from_s3.get(current_key));
            mTruckList.add(truckInfo);
            createImageForTruck(current_key);
        }
        dummyDataForMenu();
    }
    /**
     * Download the given item.
     * @return returns the downloaed item.
     */
    public TruckInfo downloadItem(String key_of_item){
        TruckInfo item = new TruckInfo();
        AmazonS3Client s3Client = getAmazonS3Client();
        S3Object s3object = s3Client.getObject(new GetObjectRequest(
                Bucket_Curr, key_of_item));
        try {
            String hope = displayTextInputStream(s3object.getObjectContent());
            TruckInfo yassss = new TruckInfo();
            yassss = jsonToObject(hope);
            item = yassss;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    public void dummyDataForMenu() {
        for (int x = 0; x < mTruckList.size(); x++) {
            ArrayList<menuItem> menuTemp;
            menuTemp = new ArrayList<menuItem>();
    /*
        Quick array list to variate the items in the menus
     */
            ArrayList<String> dishes = new ArrayList<String>();
            dishes.add("Tacos");
            dishes.add("Quesadilla");
            dishes.add("Flautas");
            dishes.add("Pollo Loco");
            dishes.add("good fagita");
            dishes.add("Huevostaco");

            for (int i = 0; i < dishes.size(); i++) {

                menuTemp.add(new menuItem());
                menuTemp.get(i).name = dishes.get(i);
                menuTemp.get(i).description = "Really good!!!";
                menuTemp.get(i).price = "$" + (i * i + 1) + ".99";
                menuTemp.get(i).inStock = true;
                menuTemp.get(i).favorite = true;
                menuTemp.get(i).TruckId = mTruckList.get(x).id;
                menuTemp.get(i).id = i;
                menuTemp.get(i).imageUrl = "https://s3.amazonaws.com/aft.photos.250.250/" + (i + 1) + ".jpeg";
                menuTemp.get(i).image = loadImage(menuTemp.get(i).imageUrl);
            }
            mTruckList.get(x).menu = menuTemp;
        }
    }

    /**
     * This method will iterate throught the existing list of trucks and seeing if there is a match
     * </P>.If there is a match for the same id. The old Item will be removed from S3. This will
     * </p> the user to be able to upload a file without collision. Therefore 2 Trucks can't have
     * </p> the same name or .id
     * </P> The DeleteAnobject.java will be used.
     * @param newInfo The comparison file.
     */
    public void EditTruckByID(TruckInfo newInfo)
    {
        for (int x = 0; x < mTruckList.size(); x++){
        }
        Boolean tempNew = true;
        for (int x = 0; x < mTruckList.size(); x++)
        {
            if (mTruckList.get(x).id == newInfo.id)
            {

                truck_to_be_deleted = mTruckList.get(x).name;
                tempNew = false;
                new UploadItemTask().execute(newInfo);
                break;
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
     * Gets the matching TruckInfo file from the current list of Locally saved profiles
     * @param ID methods uses this function to call TruckInfo for that particular matching id.
     * @return returns the found TruckInfo profile or just null if nothing was found.
     */
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

    /**
     * Converts a given json object into a an object
     * @param string
     * @return
     */
    public TruckInfo jsonToObject(String string){
        TruckInfo truck = new TruckInfo();
        try {
            JSONObject jsonObj = new JSONObject(string);
            int i = 0;
            truck.name = (String) jsonObj.get("name");
            truck.name = truck.name.toString();
            truck.imageURL = (String) jsonObj.get("imageUrl");
            truck.about = (String)jsonObj.get("about");
            truck.phoneNumber = (String)jsonObj.get("phoneNumber");
            truck.distance =  -1;//@todo need to fix this show actual distanc.
            truck.id = (Integer)  jsonObj.get("id");
            truck.foodType = (String)jsonObj.get("foodtype");
            truck.latitude = (Double)  jsonObj.get("latitude");
            truck.longitude = (Double)  jsonObj.get("longitude");
            String us  = (String) jsonObj.get("UserID");
            truck.setUserID(us);
            String ps =  (String) jsonObj.get("Password");
            truck.setPassword(ps);
             } catch (JSONException e) {
            e.printStackTrace();
        }
        return truck;
    }

    /**
     * Function tries to find the ID by iterating throught the TruckInfo progiles saved locally
     * then if a match is found. it will check the password that it has saved for that particular
     * truck info progfile
     * @param id used to match with the truckinfo array that is saved locally.
     * @param password the password that user has entered.
     * @return True or False if the credentials are actually matching up.
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
        AmazonS3Client s3Client = getAmazonS3Client();
        File file = new File(mContext.getFilesDir(),"fileName.txt");
        FileOutputStream fos = null;
        JSONObject TruckItemJson = toJsonAndBeyond(itemToUpload);
        for (int x = 0; x < mTruckList.size(); x++)
        {
            if (mTruckList.get(x).id == itemToUpload.id)
            {
                String name_to_erase = mTruckList.get(x).name;
                mTruckList.remove(x);
                mTruckList.add(itemToUpload);
                DeleteAnObject deleteAnObject =
                        new DeleteAnObject(Bucket_Curr,name_to_erase,s3Client);
            }
        }
        try {
            file = createSampleFile(TruckItemJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PutObjectRequest por = new PutObjectRequest( Bucket_Curr,
                itemToUpload.name,
                file);
        s3Client.putObject(por);
    }
    /**
     * Convets given item into a json item and returns the JSON object
     * </P> To later be use to uploaded online
     * @param item The item that needs to be converted into a JSONobject.
     * @return converted json objet.
     */
    public JSONObject toJsonAndBeyond(TruckInfo item){
        //Create an object to use for puting the trucks attributes.
        JSONObject obj = new JSONObject();

        try {
            //All public items for the current item.
            obj.put("name", item.name);
            obj.put("foodtype", item.foodType);
            obj.put("imageUrl",item.imageURL);
            obj.put("about", item.about);
            obj.put("phoneNumber",item.phoneNumber);
            obj.put("distance", Float.valueOf(item.distance));
            obj.put("id", Integer.valueOf(item.id));
            //@todo comment it out for now might come back later;
            //Lat and Long for the current item.
            obj.put("latitude", Double.valueOf(item.latitude));
            obj.put("longitude", Double.valueOf(item.longitude));
            obj.put("end",null);
            int current_menu_item = 0; //keeps track of the index
            int number_of_menu_items = 0;//initialize to zero.
            number_of_menu_items = item.menu.size();//creates a new
            //iterates through the list
            ArrayList menuList = new ArrayList();
            //obj.put(obj.put())
            for(current_menu_item = 0;
                current_menu_item<number_of_menu_items;
                current_menu_item++) {
                ArrayList list = new ArrayList();
                list.add(item.menu.get(current_menu_item).name + "new!!yay");
                list.add(item.menu.get(current_menu_item).description);
                list.add(item.menu.get(current_menu_item).price);
                list.add(item.menu.get(current_menu_item).id);
                list.add(item.menu.get(current_menu_item).TruckId);
                JSONArray jsonArray = new JSONArray(list);
                list.add(item.menu.get(current_menu_item).imageUrl);
                obj.put("Menu_item" + current_menu_item, jsonArray);
            }
            //Private Strings from current item.
            obj.put("UserID", item.getUserID());
            obj.put("Password", item.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
    /**
     *transfors a url image into  a drawable object.
     * @param url Take the given URL to be created as drawable object
     * @return Returns either null or a drawable object.
     */
    public static Drawable loadImage(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * An Async task that we run to do uploads. This is used because you cant to network operations
     * outside of the main task. This method makes it so it can access the internet.
     */
    class UploadItemTask extends AsyncTask<TruckInfo, Void, Void>
    {
        protected Void doInBackground(TruckInfo... newInfo)
        {
            try
            {
                uploadItem(newInfo[0]);
                return null;
            } catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
        protected void onPostExecute(Void feed){}
    }
}
