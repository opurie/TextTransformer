package pl.put.poznan.transformer.logic;

import java.text.DecimalFormat;

/**
 * Class converts floating point numbers to fixed 2 decimal points numbers
 */

public class FloatToFixedTransformer implements TextTransformerInterface {
    private final TextTransformerInterface decorator;

    private static DecimalFormat df = new DecimalFormat("0.00");

    public FloatToFixedTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    /***
     *
     * @param text string to be transformed
     * @return string containing floating point numbers rounded(HALF-EVEN) to 2 decimal points
     */

    @Override
    public String transform(String text) {
        text = decorator.transform(text);
        String str = text.replaceAll("[^\\d[.]\\d]", ":").trim();

        for (String num : str.split(":")) {
            if (num.length() > 0)
                text = text.replaceFirst(num, df.format(Double.parseDouble(num)));
        }

        return text;
    }
}
