package pl.put.poznan.transformer.rest;

import net.bytebuddy.implementation.auxiliary.MethodCallProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextTransformerControllerTest {

    TextTransformerController textTransformerController = new TextTransformerController();

    @Test
    public void shouldReturnUpperAndRemovedRepetitions() {
//        String[] transforms = {"upper", "removeRepetition"};
//        Map<String, String> requestBody = new HashMap<String, String>();
//        requestBody.put("text", "Wyslij do do mnie mnie wiadomosc");
//        requestBody.put("transformation", "upper");
//        String transformed = textTransformerController.post(requestBody);
//        requestBody.put("text", transformed);
//        requestBody.put("transformation", "removeRepetition");
//        transformed = textTransformerController.post(requestBody);
//        Assertions.assertEquals("WYSLIJ DO MNIE WIADOMOSC", transformed);

        String[] transforms = {"upper", "removeRepetition"};
        Map<String, String> requestBody = new HashMap<String, String>();
        String transformed = "Wyslij do do mnie mnie wiadomosc";
        for(String transformation: transforms){
            requestBody.put("transformation", transformation);
            requestBody.put("text", transformed);
            textTransformerController.post(requestBody);
        }
        Assertions.assertEquals("WYSLIJ DO MNIE WIADOMOSC", transformed);
    }

    @Test
    public void shouldInvertUpperAndRemoveRepetitions() {
//        String[] transforms = {"invert" , "upper", "removeRepetition"};
//        String transformed = textTransformerController.post("Wyslij do do mnie mnie wiadomosc", transforms);
//        Assertions.assertEquals("CSOMODAIW EINM OD JILSYW", transformed);
        String[] transforms = {"invert" , "upper", "removeRepetition"};
        Map<String, String> requestBody = new HashMap<String, String>();
        String transformed = "Wyslij do do mnie mnie wiadomosc";
        for(String transformation: transforms){
            requestBody.put("transformation", transformation);
            requestBody.put("text", transformed);
            textTransformerController.post(requestBody);
        }
        Assertions.assertEquals("CSOMODAIW EINM OD JILSYW", transformed);
    }
}