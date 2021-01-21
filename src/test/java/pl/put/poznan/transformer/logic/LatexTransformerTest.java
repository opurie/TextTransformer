package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LatexTransformerTest {
    private TextTransformerInterface decorator = new TextTransformer();
    private LatexTransformer transformer;

    @BeforeEach
    void setUp() {
        transformer = new LatexTransformer(decorator);
    }

    @Test
    public void latexTest() {
        assertEquals(transformer.transform("for$mula$"), "for\\$mula\\$");
        assertEquals(transformer.transform("sl\\as\\h"), "sl\\backslash as\\backslash h");
        assertEquals(transformer.transform("K&F&C"), "K\\&F\\&C");
        assertEquals(transformer.transform("146%"), "146\\%");
        assertEquals(transformer.transform("$#sign#$"), "\\$\\#sign\\#\\$");
        assertEquals(transformer.transform("__name__ $$ main"), "\\_\\_name\\_\\_ \\$\\$ main");
        assertEquals(transformer.transform("cla$$ {}"), "cla\\$\\$ \\{\\}");
        assertEquals(transformer.transform("1 ~ 2 ~ 3^"), "1 \\~ 2 \\~ 3\\^");
    }
}