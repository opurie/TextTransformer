package pl.put.poznan.transformer.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Web controller for serving the GUI
 */
@Controller
public class WebController {
    /**
     * Method for handling incoming get requests to /gui
     * @return name of file containg the GUI
     */
    @RequestMapping("/gui")
    public String web(){
        return "index.html";
    }
}
