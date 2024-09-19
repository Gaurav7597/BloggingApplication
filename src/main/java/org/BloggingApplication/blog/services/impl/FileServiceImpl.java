package org.BloggingApplication.blog.services.impl;

import org.BloggingApplication.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService
{
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //file name
        String name = file.getOriginalFilename();
        //abc.png

        //random name generate file
        String randomId = UUID.randomUUID().toString();
        String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

        //full path
        String filePath = path + File.separator + fileName1 ;

        //create folder if not created
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream() , Paths.get(filePath));
        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
       String fullPath =  path+File.separator+fileName;
       InputStream is = new FileInputStream(fullPath);
        //db logic to return inputStream
        return is;

    }
}
