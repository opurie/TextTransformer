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
        put("np\\.", "na przykład");
        put("dr\\b", "doktor");
        put("mgr", "magister");
        put("prof\\.", "profesor");
        put("inż\\.", "inżynier");
        put("dyr\\b", "dyrektor");
        put("płk", "pułkownik");
        put("mjr", "major");
        put("gen\\.", "generał");
        put("itd\\.", "i tak dalej");
        put("itp\\.", "i tym podobne");
        put("Sz\\.P\\.", "Szanowny Pan/Szanowna Pani");
        put("cm\\b", "centymetrów");
        put("c\\.d\\.n\\.", "ciąg dalszy nastąpi.");
        put("zw\\b", "zaraz wracam");
        put("wsm\\b", "w sumie");

        put("Np\\.", "Na przykład");
        put("Dr\\b", "Doktor ");
        put("Mgr", "Magister");
        put("Prof\\.", "Profesor");
        put("Inż\\.", "Inżynier");
        put("Dyr\\b", "Dyrektor");
        put("Płk", "Pułkownik");
        put("Mjr", "Major");
        put("Gen\\.", "Generał");
        put("Itd\\.", "I tak dalej");
        put("Itp\\.", "I tym podobne");
        put("Cm\\b", "Centymetrów");
        put("C\\.D\\.N\\.", "CIĄG DALSZY NASTĄPI.");
        put("Zw\\b", "Zaraz wracam");
        put("Wsm\\b", "W sumie");
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
}
