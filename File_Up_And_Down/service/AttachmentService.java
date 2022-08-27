package com.example.demo.service;

import com.example.demo.entity.Attachment;
import com.example.demo.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository myRepo;

    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        try{
        Attachment attachment=new Attachment(fileName,
                                    file.getContentType(),
                                    file.getBytes());
        return myRepo.save(attachment);
       }catch (Exception e){
           throw new Exception("file " + fileName + "not stored in DB!");
       }
    }

    public Attachment getAttachment(String fileId) throws Exception {
        return myRepo.findById(fileId).orElseThrow(
                ()->new Exception("File with id : " + fileId +"not found"));
    }

}
