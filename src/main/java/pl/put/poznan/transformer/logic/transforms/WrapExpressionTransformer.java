package pl.put.poznan.transformer.logic.transforms;

import pl.put.poznan.transformer.logic.TextTransformerInterface;

import java.util.HashMap;
import java.util.Map;

public class WrapExpressionTransformer implements TextTransformerInterface {
    private static final Map<String, String> expressionsMap = new HashMap<>() {{
        put("np.", "na przykład");
        put(" dr ", " doktor ");
        put("mgr", "magister");
        put("prof.", "profesor");
        put("inż.", "inżynier");
        put("dyr", "dyrektor");
        put("płk", "pułkownik");
        put("mjr", "major");
        put("gen.", "generał");
        put("itd.", "i tak dalej");
        put("itp.", "i tym podobne");
        put("sz. p.", "Szanowny/a Pan(i)");
        put("cm", "centymetrów");
        put("c.d.n.", "ciąg dalszy nastąpi.");
        put("zw", "zaraz wracam");
        put("w sumie", "wsm");
    }};

    @Override
    public String transform(String text) {
        for (String exp : expressionsMap.keySet()) {
            while(text.contains(exp))
                text = text.replaceFirst(exp, expressionsMap.get(exp));
        }
        return text;
    }

}
