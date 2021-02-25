package com.example.demo.File;


import com.example.demo.security.SecurityProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;

@Service
public class FileSystemStorageService {

	private final Path rootLocation;

	public FileSystemStorageService() throws FileNotFoundException {
		//File file = ResourceUtils.getFile("classpath:uploads");

		this.rootLocation = Paths.get(SecurityProperties.location);
	}

	public ResponseEntity<?> store(MultipartFile file) throws IOException {
		if(file != null) {
			init();
			String finalName = ZonedDateTime.now().toInstant().toEpochMilli() + file.getOriginalFilename();
			Files.copy(file.getInputStream(), this.rootLocation.resolve(finalName));
			//System.out.println(file.getOriginalFilename());
			//System.out.println(this.rootLocation.resolve(file.getOriginalFilename()).toRealPath().toString());
			return ResponseEntity.ok(finalName);
		}else {
			return ResponseEntity.ok("");
		}
	}


	public Path load(String file) throws URISyntaxException {
		/*URI uri = ClassLoader.getSystemResource(filename).toURI();
		return rootLocation.resolve(Paths.get(uri).toString());*/
		return rootLocation.resolve(file);
	}

	public Resource loadAsResource(String pathFile) throws Exception {
			Path file = load(pathFile);
			Resource resource = new UrlResource(file.toUri());
		if(resource.exists() || resource.isReadable()) {
			return resource;
		}
		else {
			throw new Exception("Could not read file: " + pathFile);

		}
	}


	private void init() throws IOException {
		if(!Files.exists(this.rootLocation)){
			Files.createDirectory(this.rootLocation);
		}
	}

}
