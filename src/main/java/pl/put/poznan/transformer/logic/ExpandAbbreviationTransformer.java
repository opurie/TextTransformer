package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class expanding all of abbreviations in the given text
 * to their long form - only those which are included in the predefined set
 *  { "np.", "dr", "mgr", "prof.", "inż.",
 *    "dyr", "płk", "mjr", "gen.", "itd.",
 *    "itp.", "Sz. P.", "cm", "c.d.n.", "zw", "wsm" }
 */

public class ExpandAbbreviationTransformer implements TextTransformerInterface {

    private final TextTransformerInterface decorator;

    public ExpandAbbreviationTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    private static final Map<String, String> expressionsMap = new HashMap<>() {{
        put("np.", "na przykład");
        put("dr", "doktor");
        put("mgr", "magister");
        put("prof.", "profesor");
        put("inż.", "inżynier");
        put("dyr", "dyrektor");
        put("płk", "pułkownik");
        put("mjr", "major");
        put("gen.", "generał");
        put("itd.", "i tak dalej");
        put("itp.", "i tym podobne");
        put("Sz.P.", "Szanowny Pan/Szanowna Pani");
        put("cm", "centymetrów");
        put("c.d.n.", "ciąg dalszy nastąpi.");
        put("zw", "zaraz wracam");
        put("wsm", "w sumie");

        put("Np.", "Na przykład");
        put("Dr", "Doktor");
        put("Mgr", "Magister");
        put("Prof.", "Profesor");
        put("Inż.", "Inżynier");
        put("Dyr", "Dyrektor");
        put("Płk", "Pułkownik");
        put("Mjr", "Major");
        put("Gen.", "Generał");
        put("Itd.", "I tak dalej");
        put("Itp.", "I tym podobne");
        put("Sz.P.", "Szanowny Pan/Szanowna Pani");
        put("Cm", "Centymetrów");
        put("c.d.n.", "Ciąg dalszy nastąpi.");
        put("Zw", "Zaraz wracam");
        put("Wsm", "W sumie");
    }};

    /**
     * @param text string to be transformed
     * @return string with expanded abbreviations
     */

    @Override
    public String transform(String text) {
        text = decorator.transform(text);

        for (String exp : expressionsMap.keySet()) {
            String pat = "\\b" + exp;
            Pattern expPattern = Pattern.compile(pat);
            Matcher matcher = expPattern.matcher(text);
            text = matcher.replaceAll(expressionsMap.get(exp));
        }
        return text;
    }
//    Tu Sz.P. robi takie coś, dr lubi zjeść. Mjr jest zdenerwowany a pROf. nie.
}
