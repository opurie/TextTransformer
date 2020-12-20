package pl.put.poznan.transformer.logic;

/**
 * Class that escapes LateX special characters
 */

public class LatexTransformer implements TextTransformerInterface{
    private final TextTransformerInterface decorator;

    public LatexTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    private static final char[] latexSymbols = {'\\', '&', '%', '$', '#', '_', '{', '}', '~', '^'};

    /**
     * @param text string to be transformed
     * @return LaTeX compatible string
     */

    @Override
    public String transform(String text) {
        text = decorator.transform(text);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            for(char l : latexSymbols) {
                if(l == c) {
                    sb.append("\\");
                    break;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
