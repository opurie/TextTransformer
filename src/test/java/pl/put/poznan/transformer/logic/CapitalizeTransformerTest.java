package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CapitalizeTransformerTest {
    private TextTransformerInterface decorator = new TextTransformer();
    private CapitalizeTransformer transformer;

    @BeforeEach
    void setUp() {
        transformer = new CapitalizeTransformer(decorator);
    }

    @Test
    public void textInLowerCaseTest() {
        assertEquals(transformer.transform("ababagalamaga"), "Ababagalamaga");
        assertEquals(transformer.transform("lorem ipsum"), "Lorem Ipsum");
        assertEquals(transformer.transform(""), "");
    }

    @Test
    public void textInUpperCaseTest() {
        assertEquals(transformer.transform("VENI VIDI VICI"), "VENI VIDI VICI");
        assertEquals(transformer.transform("FOOBAR"), "FOOBAR");
    }

    @Test
    public void textInMixedCaseTest() {
        assertEquals(transformer.transform("coGiTo erGo SuM"), "CoGiTo ErGo SuM");
        assertEquals(transformer.transform("AbRaCaDaBrA"), "AbRaCaDaBrA");
    }

    @Test
    public void textWithNonLettersTest() {
        assertEquals(transformer.transform("21 pilots"), "21 Pilots");
        assertEquals(transformer.transform("#include stdio.h>"), "#include Stdio.h>");
        assertEquals(transformer.transform("eat;sleep;repeat"), "Eat;sleep;repeat");
    }

    @Test
    public void textWithSpaceTabTest() {
        assertEquals(transformer.transform("obama's\nlast\nname"), "Obama's\nLast\nName");
        assertEquals(transformer.transform("sponge\tbob\tsquare pants"), "Sponge\tBob\tSquare Pants");
    }

    @Test
    public void mockIt() {
        TextTransformer mock = mock(TextTransformer.class);
        when(mock.transform(anyString())).thenAnswer(i -> i.getArguments()[0]);
        CapitalizeTransformer capitalizeTransformer = new CapitalizeTransformer(mock);
        assertEquals(capitalizeTransformer.transform("po spacji powinna być duża litera"), "Po Spacji Powinna Być Duża Litera");
    }
}