package pl.put.poznan.transformer.logic;

/**
 * Class that inverts text order
 */

public class InverseTransformer implements TextTransformerInterface{
    private final TextTransformerInterface decorator;

    public InverseTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    /**
     * @param text string to be transformed
     * @return reversed string
     */

    @Override
    public String transform(String text) {
        text = decorator.transform(text);

        StringBuilder sb = new StringBuilder();
        int len = text.length();
        for(int i = 0; i < len; i++) {
            char c = Character.toLowerCase(text.charAt(len - i - 1));
            char original = text.charAt(i);
            if(Character.isUpperCase(original)) c = Character.toUpperCase(c);
            sb.append(c);
        }
        String r = sb.toString();
        return r.trim();
    }
}
