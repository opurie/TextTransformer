package pl.put.poznan.transformer.logic;

/**
 * Class that converts text to upper case
 */

public class UpperCaseTransformer implements TextTransformerInterface{
    private final TextTransformerInterface decorator;

    public UpperCaseTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    /**
     * @param text string to be transformed
     * @return string in upper case
     */

    @Override
    public String transform(String text) {
        text = decorator.transform(text);
        return text.toUpperCase();
    }
}