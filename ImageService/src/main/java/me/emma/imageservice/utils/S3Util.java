package me.emma.imageservice.utils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
@Data
@AllArgsConstructor
public class S3Util {

    private final AmazonS3 s3Client;
    private final String bucketName;

    /**
     * file upload
     *
     * @param
     * @param objectName
     * @return
     */
    public String upload(MultipartFile file, String objectName) {
        try {

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            // Creating a PutObject request and setting the ACL to public-read
            PutObjectRequest request = new PutObjectRequest(bucketName, objectName, file.getInputStream(), metadata);

            s3Client.putObject(request);

            // Construct the URL to access the uploaded object
            String url = s3Client.getUrl(bucketName, objectName).toString();
            log.info("File uploaded to: {}", url);
            return url;
        } catch (AmazonServiceException ase) {
            log.error("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            log.error("Error Message:    " + ase.getMessage());
            log.error("HTTP Status Code: " + ase.getStatusCode());
            log.error("AWS Error Code:   " + ase.getErrorCode());
            log.error("Error Type:       " + ase.getErrorType());
            log.error("Request ID:       " + ase.getRequestId());
            throw new RuntimeException("Error uploading file to S3");
        } catch (AmazonClientException ace) {
            log.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ace.getMessage());
            throw new RuntimeException("Error uploading file to S3");
        } catch (IOException ioe) {
            log.error("IOException: " + ioe.getMessage());
            throw new RuntimeException("Error uploading file to S3");
        }
    }



}
