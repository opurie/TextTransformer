package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExpandAbbreviationTransformerTest {
    private TextTransformerInterface decorator = new TextTransformer();
    private ExpandAbbreviationTransformer expander;
    private TextTransformerInterface decoratorMock;

    @BeforeEach
    void setUp() {
        expander = new ExpandAbbreviationTransformer(decorator);
        decoratorMock = mock(TextTransformerInterface.class);
    }

    @Test
    void simpleAbbreviations() {
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
    void abbsWithinTxt() {
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
    void abbsWithinTxtPunctuationMess() {
        Assertions.assertTrue(this.expander.transform("Taki gen.eralnie miły, gen. jak się patrzy itpatrzec jak sobie pojdzie").equals("Taki generałeralnie miły, generał jak się patrzy itpatrzec jak sobie pojdzie"),
                "Pierwszy średni2 -->" + this.expander.transform("Taki generalnie miły, gen. jak się patrzy itp.atrzec jak sobie pojdzie"));
        Assertions.assertTrue(this.expander.transform("Taki dyr. to dopiero mjr, az dziw bierze, że nie potrzebował takich tytułów jak mgr czy inż.").equals("Taki dyrektor. to dopiero major, az dziw bierze, że nie potrzebował takich tytułów jak magister czy inżynier"),
                "Drugi średni2  ---> " + this.expander.transform("Taki dyr. to dopiero mjr, az dziw bierze, że nie potrzebował takich tytułów jak mgr czy inż."));
    }

    @Test
    void WordsMessPlusDifferentSymbols() {
        Assertions.assertTrue(this.expander.transform("GenGen. to dyr.ektor").equals("GenGen. to dyrektor.ektor"),
                "Pierwszy trudny1 ---> " + this.expander.transform("GenGen. to dyr.ektor"));
        Assertions.assertTrue(this.expander.transform("zw:;zwariowałgen. gen.ek").equals("zaraz wracam:;zwariowałgen. generałek"),
                "Drugi trudny1 ---> " + this.expander.transform("zw:;zwariowałgen. gen.ek"));
    }

    @Test
    void withManyWhitespacesMess() {
        when(decoratorMock.transform(anyString())).thenReturn("dyr     lubi   drzem, gen.  mjr    prof.prof.esor");
        ExpandAbbreviationTransformer testedExpander = new ExpandAbbreviationTransformer(decoratorMock);
        Assertions.assertTrue(testedExpander.transform("dyr     lubi   drzem, gen.  mjr    prof.prof.esor").equals("dyrektor     lubi   drzem, generał  major    profesorprofesoresor"),
                "Mock1 nie działa ---> " + testedExpander.transform("dyr     lubi   drzem, gen.  mjr    prof.prof.esor"));
    }

    @Test
    void withManyWhitespaces() {
        when(decoratorMock.transform(anyString())).thenReturn("C.D.N.");
        ExpandAbbreviationTransformer testedExpander = new ExpandAbbreviationTransformer(decoratorMock);
        Assertions.assertTrue(testedExpander.transform(anyString()).equals("CIĄG DALSZY NASTĄPI."),
                "Mock2 nie działa ---> " + testedExpander.transform("C.D.N."));
    }

    @Test
    void withUnnecessaryPunctuation() {
        when(decoratorMock.transform(anyString())).thenReturn("Gengen. itp.bieżnie wyświetlają ile przebiegłeś cm. Itd.?");
        ExpandAbbreviationTransformer testedExpander = new ExpandAbbreviationTransformer(decoratorMock);
        Assertions.assertTrue(testedExpander.transform(anyString()).equals("Gengen. i tym podobnebieżnie wyświetlają ile przebiegłeś centymetrów. I tak dalej?"),
                "Mock3 nie działa ---> " + testedExpander.transform("Gengen. itp.bieżnie wyświetlają ile przebiegłeś cm. Itd.?"));
    }

    @Test
    public void mockIt() {
        TextTransformer mock = mock(TextTransformer.class);
        when(mock.transform(anyString())).thenAnswer(i -> i.getArguments()[0]);
        ExpandAbbreviationTransformer expandAbbreviationTransformer = new ExpandAbbreviationTransformer(mock);
        assertEquals(expandAbbreviationTransformer.transform("dr leczy covid"), "doktor leczy covid");
    }
}