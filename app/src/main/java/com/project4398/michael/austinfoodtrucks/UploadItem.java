package com.project4398.michael.austinfoodtrucks;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.File;


/**
 * Created by PRoberts on 7/28/15.
 */
public class UploadItem {
    /**
     * Uploads one item to the S3 bucket declared by bucket_name
     * </p>
     * uses cognito to upload the file and sets the region
     * </p>
     * Creates a new credetials provider..
     * @param item which is a TruckListInfo
     */
    public UploadItem(TruckInfo item, Context context) {
        String bucket_name = "grp3.tsstate.edu.test";
        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context, //
                "us-east-1:e40796ae-0851-48a4-88a2-ec40aca83c4e", // Identity Pool ID
                Regions.US_EAST_1 // Region
        );

        AmazonS3Client s3Client = new AmazonS3Client(credentialsProvider);
        TransferUtility transferUtility = new TransferUtility(s3Client, context);
        TransferObserver observer = transferUtility.upload(bucket_name, item.name, createFile(item));
    }

    /**
     * Creates a file to be used as a parameter on the transferUtility.Upload(par,par,FILE)
     * @param item
     * @return File containing the item that was passed as a parameter.
     */
    public File createFile(TruckInfo item){
        File file = createFile(item);
        return file;
    }
}
