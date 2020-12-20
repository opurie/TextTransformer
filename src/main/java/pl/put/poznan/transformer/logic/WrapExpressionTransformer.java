package pl.put.poznan.transformer.logic;


import java.util.HashMap;
import java.util.Map;

/**
 * Class that replaces expressions with abbreviations
 */

public class WrapExpressionTransformer implements TextTransformerInterface {

    private final TextTransformerInterface decorator;

    public WrapExpressionTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    private static final Map<String, String> expressionsMap = new HashMap<>() {{
        put("na przykład", "np.");
        put("doktor ", "dr ");
        put("magister", "mgr");
        put("profesor", "prof.");
        put("inżynier", "inż.");
        put("dyrektor", "dyr");
        put("pułkownik", "płk");
        put("major", "mjr");
        put("generał", "gen.");
        put("i tak dalej", "itd.");
        put("i tym podobne", "itp.");
        put("Szanowny/a Pan(i)", "sz. p.");
        put("centymetrów", "cm");
        put("ciąg dalszy nastąpi.", "c.d.n.");
        put("zaraz wracam", "zw");
        put("wsm", "w sumie");
    }};

    /**
     * @param text string to be transformed
     * @return string with abbreviations
     */

    @Override
    public String transform(String text) {
        text = decorator.transform(text);

        for (String exp : expressionsMap.keySet()) {
            while(text.contains(exp))
                text = text.replaceFirst(exp, expressionsMap.get(exp));
        }
        return text;
    }
}
