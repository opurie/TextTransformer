package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveRepeatingTransformTest {
    private TextTransformerInterface decorator = new TextTransformer();
    private RemoveRepeatingTransform transformer;

    private String text;

    @BeforeEach
    void setUp() {
        this.transformer = new RemoveRepeatingTransform(decorator);
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void testRepeatWithinWord(){
        this.text = "aabcdd";
        assertEquals(this.text,this.transformer.transform(this.text));
    }

    @Test
    void testNoRepeats(){
        this.text = "abcdefg";
        assertEquals(this.text,this.transformer.transform(this.text));
    }

    @Test
    void testProperRepeats(){
        this.text = "a a aa";
        assertEquals("a aa", this.transformer.transform(this.text));

        this.text = "a a aa";
        assertEquals("a aa", this.transformer.transform(this.text));
    }

    @Test
    void testRepetitionsWithMoreWhitespaces(){
        this.text = "text text  text   text";
        assertEquals("text", this.transformer.transform(this.text));
    }
}