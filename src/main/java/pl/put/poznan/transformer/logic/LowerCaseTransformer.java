package pl.put.poznan.transformer.logic;

/**
 * Class that converts text to lower case
 */

public class LowerCaseTransformer implements TextTransformerInterface {

    private final TextTransformerInterface decorator;

    public LowerCaseTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    /**
     * @param text string to be transformed
     * @return string in lower case
     */

    @Override
    public String transform(String text) {
        text = decorator.transform(text);
        return text.toLowerCase();
    }
}
