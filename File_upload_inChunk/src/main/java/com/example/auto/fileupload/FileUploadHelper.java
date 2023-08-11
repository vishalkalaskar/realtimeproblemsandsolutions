package com.example.auto.fileupload;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	//public final String UPLOAD_DIR = "D:\\Gopinath\\New folder";
	//public final String UPLOAD_DIR = "D:\\fotafile";
	 public final String UPLOAD_DIR = "/data/home/kpp/ividms/FotaFirmwareUpload/";
	//public final String UPLOAD_DIR="/data/home/kpp/ividms/FotaFirmwareUpload";
	//System.out.println(UPLOAD_DIR);
	public boolean uploadFile(MultipartFile multipartFile) {
		boolean f = false;
		try {
			Files.copy(multipartFile.getInputStream(),
					Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			
			//StandardCopyOption.COPY_ATTRIBUTES: for COPY_ATTRIBUTES (Copy attributes to the new file.)			
			//StandardCopyOption.REPLACE_EXISTING: for Replace an existing file if it exists.
			
          f= true;
          //return true;
		} catch (Exception e) {
			e.printStackTrace();
			//return false;
		}
         return true;
	}
}