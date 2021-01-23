package pl.put.poznan.transformer.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;
import pl.put.poznan.transformer.logic.TextTransformerInterface;
import pl.put.poznan.transformer.rest.response.JSONResponse;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class TextTransformerControllerTest {
    private TextTransformerInterface mockedInterface;
    private TextTransformerController controller;

    @BeforeEach
    void setUp() {
        this.mockedInterface = mock(TextTransformerInterface.class);
        this.controller = new TextTransformerController();
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void postMock1() {
        when(this.mockedInterface.transform(anyString())).thenReturn("Lorem Ipsum");
        this.controller.overrideTransformation("testTransformation", this.mockedInterface);
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("text", "lorem ipsum sralalala");
        requestBody.put("transformation", "testTransformation");
//        this.controller.post(requestBody);
        assertEquals(new JSONResponse("Lorem Ipsum").getResult(),this.controller.post(requestBody).getResult());
    }

    @Test
    void postMock2() {
        when(this.mockedInterface.transform(anyString())).thenReturn("");
        when(this.mockedInterface.transform("tekst tekst")).thenReturn("tekst");
        this.controller.overrideTransformation("removeRepetition", this.mockedInterface);
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("text", "tekst tekst");
        requestBody.put("transformation", "removeRepetition");
        assertEquals(new JSONResponse("tekst").getResult(),this.controller.post(requestBody).getResult());

        requestBody = new HashMap<String, String>();
        requestBody.put("text", "tekst tekst tekst");
        requestBody.put("transformation", "removeRepetition");
        assertNotEquals(new JSONResponse("tekst").getResult(),this.controller.post(requestBody).getResult());
    }
}