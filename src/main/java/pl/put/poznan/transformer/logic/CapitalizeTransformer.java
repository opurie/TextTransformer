package pl.put.poznan.transformer.logic;

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

        String[] textArray = text.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < textArray.length; i++) {
            String s = textArray[i];
            sb.append(s.substring(0, 1).toUpperCase());
            sb.append(s.substring(1));
            if(i != textArray.length - 1) sb.append(" ");
        }
        return sb.toString();
    }
}
