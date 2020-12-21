package pl.put.poznan.transformer.logic;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Class converts floating point numbers to fixed 2 decimal points numbers
 */

public class FloatToFixedTransformer implements TextTransformerInterface {
    private final TextTransformerInterface decorator;

    private static DecimalFormat df = new DecimalFormat("0.00");

    private static final Map<Integer, String> numbersMap = new HashMap<>() {{
        put(0, "zero");
        put(1, "jeden");
        put(2, "dwa");
        put(3, "trzy");
        put(4, "cztery");
        put(5, "pięć");
        put(6, "sześć");
        put(7, "siedem");
        put(8, "osiem");
        put(9, "dziewięć");
        put(10, "dziesięć");
        put(11, "jedenaście");
        put(12, "dwanaście");
        put(13, "trzynaście");
        put(14, "czternaście");
        put(15, "piętnaście");
        put(16, "szesnaście");
        put(17, "siedemnaście");
        put(18, "osiemnaście");
        put(19, "dziewiętnaście");
        put(20, "dwadzieścia");
        put(30, "trzydzieści");
        put(40, "czterdzieści");
        put(50, "pięćdziesiąt");
        put(60, "sześćdziesiąt");
        put(70, "siedemdziesiąt");
        put(80, "osiemdziesiąt");
        put(90, "dziewięćdziesiąt");
        put(100, "sto");
        put(200, "dwieście");
        put(300, "trzysta");
        put(400, "czterysta");
        put(500, "pięćset");
        put(600, "sześćset");
        put(700, "siedemset");
        put(800, "osiemset");
        put(900, "dziewięćset");
        put(1000, "tysiąc");
    }};

    private String fitNumber(int value) {

        int units, tens, hundreds, thousands, help;
        thousands = (value / 1000) * 1000;
        help = value % 1000;
        hundreds = (help / 100) * 100;
        help = help % 100;
        tens = (help / 10) * 10;
        units = (help % 10);
        String result = "";
        if(thousands >= 1000) {
            result += numbersMap.get(thousands) + " ";
        }
        if (hundreds > 0) {
            result += numbersMap.get(hundreds) + " ";
        }
        if (tens > 10) {
            result += numbersMap.get(tens);
            if(units > 0) {
                result += " " + numbersMap.get(units);
            }
        } else if (tens == 10){
            result += numbersMap.get(tens+units);
        } else if (units > 0 || (units == 0 && result.length() == 0)) {
            result += numbersMap.get(units);
        }
        return result;
    }

    public FloatToFixedTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    /***
     *
     * @param text string to be transformed
     * @return string containing floating point numbers rounded(HALF-EVEN) to 2 decimal points
     * and then converts them to text
     */

    @Override
    public String transform(String text) {
        text = decorator.transform(text);
        String str = text.replaceAll("[^\\d[.]\\d]", ":").trim();

        for (String num : str.split(":")) {
            if (num.length() > 0) {
                String[] buf = df.format(Double.parseDouble(num)).split("\\.");
                String buffer = fitNumber(Integer.parseInt(buf[0])) + " i " + fitNumber(Integer.parseInt(buf[1]));
                text = text.replaceFirst(num, buffer + " setnych");
            }
        }

        return text;
    }
}
