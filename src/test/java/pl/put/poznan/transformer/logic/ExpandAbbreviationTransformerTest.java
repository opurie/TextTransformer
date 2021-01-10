package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExpandAbbreviationTransformerTest {
    private ExpandAbbreviationTransformer expander;

    @BeforeEach
    void setUp() {
        this.expander = new ExpandAbbreviationTransformer(new TextTransformerInterface() {
            @Override
            public String transform(String text) {
                return text;
            }
        });
    }

    @Test
    void transformSimple1() {
        Assertions.assertTrue(this.expander.transform("np").equals("np"), "Zwykle np ---> " + this.expander.transform("np"));
        Assertions.assertTrue(this.expander.transform("gen").equals("gen"), "Zwykle gen ---> " + this.expander.transform("gen"));
        Assertions.assertTrue(this.expander.transform("gen.").equals("generał"), "Zwykle gen. ---> " + this.expander.transform("gen."));
        Assertions.assertTrue(this.expander.transform("Np.").equals("Na przykład"), "Zwykle Np. ---> " + this.expander.transform("Np."));
        Assertions.assertTrue(this.expander.transform("Cm?").equals("Centymetrów?"), "Zwykle Cm? ---> " + this.expander.transform("Cm?"));
        Assertions.assertTrue(this.expander.transform("zw,").equals("zaraz wracam,"), "Zwykle zw ---> " + this.expander.transform("zw,"));
        Assertions.assertTrue(this.expander.transform("Sz.P.").equals("Szanowny Pan/Szanowna Pani"), "Zwykle Sz.P. ---> " + this.expander.transform("Sz.P."));
        Assertions.assertTrue(this.expander.transform("C.D.N.").equals("CIĄG DALSZY NASTĄPI."), "C.D.N. problem ---> " + this.expander.transform("C.D.N."));
        Assertions.assertTrue(this.expander.transform("Wsm").equals("W sumie"), "Zwykle Wsm ---> " + this.expander.transform("Wsm"));
    }

    @Test
    void transformMid1() {
        Assertions.assertTrue(this.expander.transform("prowodyr").equals("prowodyr"),
                "Zwykle prowoDYR ---> " + this.expander.transform("prowodyr"));
        Assertions.assertTrue(this.expander.transform("oni zwali").equals("oni zwali"),
                "oni zwali ---> " + this.expander.transform("oni zwali"));
        Assertions.assertTrue(this.expander.transform("Cmok").equals("Cmok"),
                "Zwykle Cmok ---> " + this.expander.transform("Cmok"));
        Assertions.assertTrue(this.expander.transform("dr lubi drzem").equals("doktor lubi drzem"),
                "dr lubi drzem ---> " + this.expander.transform("dr lubi drzem"));
        Assertions.assertTrue(this.expander.transform("droga").equals("droga"),
                "Zwykla DRoga ---> " + this.expander.transform("droga"));
        Assertions.assertTrue(this.expander.transform("Gen. Dyrektor czyta książki itp.").equals("Generał Dyrektor czyta książki i tym podobne"),
                "Gen. Dyrektor czyta książki itp. ---> " + this.expander.transform("Gen. Dyrektor czyta książki itp."));
    }

    @Test
    void transformMid2() {
        Assertions.assertTrue(this.expander.transform("Taki generalnie miły, gen. jak się patrzy itpatrzec jak sobie pojdzie").equals("Taki generalnie miły, generał jak się patrzy itpatrzec jak sobie pojdzie"),
                "Pierwszy średni2 -->" + this.expander.transform("Taki generalnie miły, gen. jak się patrzy itp.atrzec jak sobie pojdzie"));
        Assertions.assertTrue(this.expander.transform("Taki dyr. to dopiero mjr, az dziw bierze, że nie potrzebował takich tytułów jak mgr czy inż.").equals("Taki dyrektor. to dopiero major, az dziw bierze, że nie potrzebował takich tytułów jak magister czy inżynier"),
                "Drugi średni2  ---> " + this.expander.transform("Taki dyr. to dopiero mjr, az dziw bierze, że nie potrzebował takich tytułów jak mgr czy inż."));
    }

    @Test
    void transformHard1() {
        Assertions.assertTrue(this.expander.transform("GenGen. to dyr.ektor").equals("GenGen. to dyrektor.ektor"),
                "Pierwszy trudny1 ---> " + this.expander.transform("GenGen. to dyr.ektor"));
        Assertions.assertTrue(this.expander.transform("zw:;zwariowałgen. gen.ek").equals("zaraz wracam:;zwariowałgen. generałek"),
                "Drugi trudny1 ---> " + this.expander.transform("zw:;zwariowałgen. gen.ek"));
    }

    @Test
    void testMock1() {
        TextTransformerInterface txtTransfMock = mock(TextTransformerInterface.class);
        when(txtTransfMock.transform("dyr lubi drzem")).thenReturn("dyr lubi drzem");
        ExpandAbbreviationTransformer testedExpander = new ExpandAbbreviationTransformer(txtTransfMock);
        Assertions.assertTrue(testedExpander.transform("dyr lubi drzem").equals("dyrektor lubi drzem"),
                "Mock1 nie działa" + testedExpander.transform("dyr lubi drzem"));
    }

    @Test
    void testMock2() {
        TextTransformerInterface txtTransfMock = mock(TextTransformerInterface.class);
        when(txtTransfMock.transform(anyString())).thenReturn("GenGen. to dyr.ektor");
        ExpandAbbreviationTransformer testedExpander = new ExpandAbbreviationTransformer(txtTransfMock);
        Assertions.assertTrue(testedExpander.transform("test").equals("GenGen. to dyrektor.ektor"),
                "Mock2 nie działa" + testedExpander.transform("GenGen. to dyr.ektor"));
    }

    @Test
    void testMock3() {
        TextTransformerInterface txtTransfMock = mock(TextTransformerInterface.class);
        when(txtTransfMock.transform(anyString())).thenReturn("GenGen. to dyr.ektor");
        ExpandAbbreviationTransformer testedExpander = new ExpandAbbreviationTransformer(txtTransfMock);
        Assertions.assertTrue(testedExpander.transform("test").equals("GenGen. to dyrektor.ektor"),
                "Mock2 nie działa" + testedExpander.transform("GenGen. to dyr.ektor"));
    }
}