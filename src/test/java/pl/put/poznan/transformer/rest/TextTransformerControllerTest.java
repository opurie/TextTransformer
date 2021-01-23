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
    private TextTransformerController controller;

    @BeforeEach
    void setUp() {
        this.controller = new TextTransformerController();
    }

    @Test
    void postMock1() {
        TextTransformerInterface mockedInterface = mock(TextTransformerInterface.class);

        when(mockedInterface.transform(anyString())).thenReturn("Lorem Ipsum");
        this.controller.overrideTransformation("testTransformation", mockedInterface);

        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("text", "lorem ipsum sralalala");
        requestBody.put("transformation", "testTransformation");
//        this.controller.post(requestBody);
        assertEquals(new JSONResponse("Lorem Ipsum").getResult(),this.controller.post(requestBody).getResult());
    }

    @Test
    void postMock2() {
        TextTransformerInterface mockedInterface = mock(TextTransformerInterface.class);

        when(mockedInterface.transform(anyString())).thenReturn("");
        when(mockedInterface.transform("tekst tekst")).thenReturn("tekst");
        this.controller.overrideTransformation("removeRepetition", mockedInterface);

        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("text", "tekst tekst");
        requestBody.put("transformation", "removeRepetition");
        assertEquals(new JSONResponse("tekst").getResult(),this.controller.post(requestBody).getResult());

        requestBody = new HashMap<String, String>();
        requestBody.put("text", "tekst tekst tekst");
        requestBody.put("transformation", "removeRepetition");
        assertNotEquals(new JSONResponse("tekst").getResult(),this.controller.post(requestBody).getResult());
    }

    @Test
    void postMock3() {
        TextTransformerInterface mockedInterface1 = mock(TextTransformerInterface.class);
        TextTransformerInterface mockedInterface2 = mock(TextTransformerInterface.class);

        when(mockedInterface1.transform("tekst tekst")).thenReturn("tekst");
        this.controller.overrideTransformation("removeRepetition", mockedInterface1);

        when(mockedInterface1.transform("lorem ipsum")).thenReturn("LOREM IPSUM");
        this.controller.overrideTransformation("capitalize", mockedInterface2);

        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("text", "tekst tekst");
        requestBody.put("transformation", "removeRepetition");
        assertEquals(new JSONResponse("tekst").getResult(),this.controller.post(requestBody).getResult());

        requestBody = new HashMap<String, String>();
        requestBody.put("text", "lorem ipsum");
        requestBody.put("transformation", "capitalize");
        assertNotEquals(new JSONResponse("LOREM IPSUM").getResult(),this.controller.post(requestBody).getResult());
    }
}