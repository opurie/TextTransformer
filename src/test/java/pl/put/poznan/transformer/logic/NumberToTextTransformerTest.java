package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class NumberToTextTransformerTest {
    private TextTransformerInterface decorator = new TextTransformer();
    private NumberToTextTransformer transformer;
    private String text;

    @BeforeEach
    void setUp() {
        transformer = new NumberToTextTransformer(decorator);
    }

    @Test
    void simpletest(){
        Assertions.assertTrue(this.transformer.transform("88").equals("osiemdziesiąt osiem"), "88 ---> " + this.transformer.transform("88"));
        Assertions.assertTrue(this.transformer.transform("213").equals("dwieście trzynaście"), "213 ---> " + this.transformer.transform("213"));
        Assertions.assertTrue(this.transformer.transform("7").equals("siedem"), "7 ---> " + this.transformer.transform("7"));
    }

    @Test
    void TextMixedWithNumbers(){
        Assertions.assertTrue(this.transformer.transform("g8 b8 m8").equals("gosiem bosiem mosiem"), "g8 b8 m8 ---> " + this.transformer.transform("g8 b8 m8"));
        Assertions.assertFalse(this.transformer.transform("0").equals("zero"), "0 ---> " + this.transformer.transform("0"));

    }

    @Test
    void Impossible(){
        Assertions.assertFalse(this.transformer.transform("8 8").equals("osiemdziesiąt osiem"), "8 8 ---> " + this.transformer.transform("8 8"));
        Assertions.assertFalse(this.transformer.transform(" 201").equals(" dwadzieścia jeden"), " 201 ---> " + this.transformer.transform(" 201"));

    }


}
