package pl.put.poznan.transformer.logic;

public class UpperCaseTransformer implements TextTransformerInterface{
    private final TextTransformerInterface decorator;

    public UpperCaseTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    @Override
    public String transform(String text) {
        text = decorator.transform(text);
        return text.toUpperCase();
    }
}