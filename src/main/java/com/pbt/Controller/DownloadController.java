package com.pbt.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pbt")
public class DownloadController {

	@GetMapping("/downloads/{filename}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("filename") String filename) throws IOException {

		String filepathname = "/static/Downloadsfile/" + filename;

		Path filePath = Paths.get(filepathname);

		if (Files.exists(filePath)) {
			byte[] fileContent = Files.readAllBytes(filePath);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", filename);

			return ResponseEntity.ok().headers(headers).body(fileContent);
		} else {
			// Handle the case where the file is not found
			return ResponseEntity.notFound().build();
		}
	}

}
