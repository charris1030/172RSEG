package controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ImageController {

    @RequestMapping("/uploadImage")
    public String index() {
    	//TODO: Add logic to read in image passed as parameter and leverage 
    	// provider class to post to DB
        return "Image Uploaded";
    }	

}
