package com.eureka.flame.main.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommonController {

    @GetMapping("/images/{url}")
    public void getImages(HttpServletResponse httpServletResponse, @PathVariable String url)  {
        String imagePath = System.getProperty("user.dir") + "/images/" + url;
        File inputFile =  new File(imagePath);
        try (InputStream is =  new FileInputStream(inputFile)){
            httpServletResponse.getOutputStream().write(IOUtils.toByteArray(is));
        }catch (Exception exp){
            exp.printStackTrace();
        }
    }
}
