package com.project4398.michael.austinfoodtrucks;

import android.util.Log;

import java.io.IOException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;


/**
 * Class to delete objects from online.
 */
public class DeleteAnObject {
    /**
     * Deletes an object from the given s3 bucket
     * @param bucketName Is the name of the current bucket that we will be erasing from.
     * @param keyName    This is going to be name of the truck that you want to delete.
     * @param s3Client   This is a pre-certified credentials to be passsed.
     */
    DeleteAnObject(String bucketName, String keyName, AmazonS3Client s3Client){
        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
        } catch (AmazonServiceException ase) {
            Log.d("userDebug", "Caught an AmazonServiceException.");
            Log.d("userDebug", "Error Message:    " + ase.getMessage());
            Log.d("userDebug", "HTTP Status Code: " + ase.getStatusCode());
            Log.d("userDebug", "AWS Error Code:   " + ase.getErrorCode());
            Log.d("userDebug","Error Type:       " + ase.getErrorType());
            Log.d("userDebug", "Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            Log.d("userDebug", "Caught an AmazonClientException.");
            Log.d("userDebug", "Error Message: " + ace.getMessage());
        }
    }
}
