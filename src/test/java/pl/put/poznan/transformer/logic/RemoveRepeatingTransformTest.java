package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    }

    @Test
    void testRepetitionsWithMoreWhitespaces(){
        this.text = "text text  text   text";
        assertEquals("text", this.transformer.transform(this.text));
    }

    @Test
    void testRepetitionsWithComma(){
        this.text = "a a aa aa, abc ab abc abc";
        assertEquals("a aa, abc ab abc", this.transformer.transform(this.text));
    }

    @Test
    void testWordGeneration(){
        String[] expectedArray = {"dupa.", "dupa,", "dupa:", "dupa;", "dupa?", "dupa!"};
        assertArrayEquals(expectedArray, this.transformer.generateWords("dupa"));
    }

    @Test
    public void mockIt() {
        TextTransformer mock = mock(TextTransformer.class);
        when(mock.transform(anyString())).thenAnswer(i -> i.getArguments()[0]);
        RemoveRepeatingTransform removeRepeatingTransform = new RemoveRepeatingTransform(mock);
        assertEquals(removeRepeatingTransform.transform("mee mee"), "mee");
    }
}