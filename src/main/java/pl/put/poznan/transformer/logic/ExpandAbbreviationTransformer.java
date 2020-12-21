package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that expands some abbreviations
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
        put("Sz. P.", "Szanowny Pan/Szanowna Pani");
        put("cm", "centymetrów");
        put("c.d.n.", "ciąg dalszy nastąpi.");
        put("zw", "zaraz wracam");
        put("wsm", "w sumie");
    }};

    /**
     * @param text string to be transformed
     * @return string with expanded abbreviations
     */

    @Override
    public String transform(String text) {
        text = decorator.transform(text);

        for (String exp : expressionsMap.keySet()) {
            String pat = "\\b" + exp + "\\b";
            Pattern expPattern = Pattern.compile(pat, Pattern.CASE_INSENSITIVE);
            Matcher matcher = expPattern.matcher(text);
            text = matcher.replaceAll(expressionsMap.get(exp));
        }
        return text;
    }

}
