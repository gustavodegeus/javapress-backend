package br.com.javapress.infrastructure.s3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import br.com.javapress.domain.entity.recipe.Recipe;

@Component
public class ImageRepository {

	@Autowired
	private AmazonS3 s3;
	
	public Boolean uploadImage(Recipe recipe) throws IOException{
        String bucketName = recipe.getImagePath() + UUID.randomUUID(); 
        s3.createBucket(bucketName);
        s3.putObject(new PutObjectRequest(bucketName, "arenas", createSampleFile()));
        return true;
	}
	
	public void downloadImage(String bucketName, String key){
		System.out.println("Downloading an object");
        S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));
        System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
	}
	
	public void createBucketForRecipe(Recipe recipe){
        s3.createBucket(recipe.getImagePath());
	}
	
	private static File createSampleFile() throws IOException {
        File file = File.createTempFile("aws-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("01234567890112345678901234\n");
        writer.write("!@#$%^&*()-=[]{};':',.<>/?\n");
        writer.write("01234567890112345678901234\n");
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.close();

        return file;
    }
}