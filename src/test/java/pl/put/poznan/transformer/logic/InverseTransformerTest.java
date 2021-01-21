package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InverseTransformerTest {

    private TextTransformerInterface decorator = new TextTransformer();
    private InverseTransformer transformer;

    @BeforeEach
    void setUp() {
        transformer = new InverseTransformer(decorator);
    }


    @Test
    public void inverseLowerCaseTest() {
        assertEquals(transformer.transform(""), "");
        assertEquals(transformer.transform("i am a human"), "namuh a ma i");
        assertEquals(transformer.transform("iamarobot"), "toboramai");
    }

    @Test
    public void inverseUpperCaseText() {
        assertEquals(transformer.transform("HEISAPIRATE"), "ETARIPASIEH");
        assertEquals(transformer.transform("CRAB RAVE"), "EVAR BARC");
        assertEquals(transformer.transform("I AM A DINO"), "OnID A MA I");
    }

    @Test
    public void inverseMixedCaseTest() {
        assertEquals(transformer.transform("Is That You John Wayne"), "EnyAw nhOj uOy taHt si");
        assertEquals(transformer.transform("WhO sAiD tHaT?"), "?tAht dIas oHw");
        assertEquals(transformer.transform("hOMER pILE"), "eLIP reMOH");
    }
}