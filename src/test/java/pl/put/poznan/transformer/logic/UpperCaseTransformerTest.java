package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpperCaseTransformerTest {
    private TextTransformerInterface decorator = new TextTransformer();
    private UpperCaseTransformer transformer;

    @BeforeEach
    void setUp() {
        transformer = new UpperCaseTransformer(decorator);
    }

    @Test
    public void textInLowerCaseTest() {
        assertEquals(transformer.transform("ababagalamaga"), "ABABAGALAMAGA");
        assertEquals(transformer.transform("lorem ipsum"), "LOREM IPSUM");
        assertEquals(transformer.transform(""), "");
    }

    @Test
    public void textInUpperCaseTest() {
        assertEquals(transformer.transform("VENI VIDI VICI"), "VENI VIDI VICI");
        assertEquals(transformer.transform("FOOBAR"), "FOOBAR");
    }

    @Test
    public void textInMixedCaseTest() {
        assertEquals(transformer.transform("CoGiTo ErGo SuM"), "COGITO ERGO SUM");
        assertEquals(transformer.transform("AbRaCaDaBrA"), "ABRACADABRA");
    }

    @Test
    public void textWithNonLettersTest() {
        assertEquals(transformer.transform("21 pilots"), "21 PILOTS");
        assertEquals(transformer.transform("#include <stdio.h>"), "#INCLUDE <STDIO.H>");
        assertEquals(transformer.transform("eat;sleep;repeat"), "EAT;SLEEP;REPEAT");
    }
}