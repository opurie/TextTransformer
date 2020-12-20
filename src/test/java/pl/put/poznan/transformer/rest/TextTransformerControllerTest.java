package pl.put.poznan.transformer.rest;

import net.bytebuddy.implementation.auxiliary.MethodCallProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTransformerControllerTest {

    TextTransformerController textTransformerController = new TextTransformerController();

    @Test
    public void shouldReturnUpperAndRemovedRepetitions() {
        String[] transforms = {"upper", "removeRepetition"};
        String transformed = textTransformerController.get("Wyslij do do mnie mnie wiadomosc", transforms);
        Assertions.assertEquals("WYSLIJ DO MNIE WIADOMOSC", transformed);
    }

    @Test
    public void shouldInvertUpperAndRemoveRepetitions() {
        String[] transforms = {"invert" , "upper", "removeRepetition"};
        String transformed = textTransformerController.get("Wyslij do do mnie mnie wiadomosc", transforms);
        Assertions.assertEquals("CSOMODAIW EINM OD JILSYW", transformed);
    }
}