// package com.crackit.crackit.util;

// import com.google.api.client.auth.oauth2.Credential;
// import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
// import com.google.api.client.http.FileContent;
// import com.google.api.client.http.javanet.NetHttpTransport;
// import com.google.api.client.json.JsonFactory;
// import com.google.api.client.json.JsonFactory;
// //import com.google.api.client.json.JsonParseException;
// import com.google.api.client.json.JsonFactory;
// import com.google.api.services.drive.Drive;
// import com.google.api.services.drive.model.File;
// import org.springframework.stereotype.Component;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.FileInputStream;
// import java.io.IOException;
// import java.util.Collections;

// @Component
// public class GoogleDriveService {

//     private Drive driveService;

//     public GoogleDriveService() throws IOException {
//         // Load the credentials from a file (e.g., service account key)
//         FileInputStream credentialsStream = new FileInputStream("path/to/service-account-key.json");

//         // Set up the Drive API client
//         Credential credential = GoogleCredential.fromStream(credentialsStream)
//                 .createScoped(Collections.singleton("https://www.googleapis.com/auth/drive.file"));

//         this.driveService = new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
//                 .setApplicationName("CrackItApp")
//                 .build();
//     }

//     public String uploadFileToGoogleDrive(MultipartFile file) throws IOException {
//         // Prepare the file metadata
//         File fileMetadata = new File();
//         fileMetadata.setName(file.getOriginalFilename());

//         // Convert MultipartFile to FileContent for uploading
//         FileContent mediaContent = new FileContent(file.getContentType(), file.getInputStream());

//         // Upload the file to Google Drive
//         File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
//                 .setFields("id")
//                 .execute();

//         // Return the uploaded file ID (or URL if needed)
//         return uploadedFile.getId();
//     }
// }
