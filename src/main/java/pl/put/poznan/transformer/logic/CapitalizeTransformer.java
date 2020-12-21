package pl.put.poznan.transformer.logic;

import sun.util.resources.cldr.zh.CalendarData_zh_Hans_CN;

/**
 * Class that capitalizes words
 */

public class CapitalizeTransformer implements TextTransformerInterface{

    private final TextTransformerInterface decorator;

    public CapitalizeTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    /**
     * @param text string to be transformed
     * @return string with capitalized words
     */

    @Override
    public String transform(String text) {
        text = decorator.transform(text);

        StringBuilder sb = new StringBuilder(text);
        for(int i = 0; i < text.length(); i++) {
            if(i == 0 || Character.isWhitespace(sb.charAt(i - 1))) {
                char c = sb.charAt(i);
                c = Character.toUpperCase(c);
                sb.setCharAt(i, c);
            }
        }
        return sb.toString();
    }
}
