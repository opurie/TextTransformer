package pl.put.poznan.transformer.logic;

public class LowerCaseTransformer implements TextTransformerInterface {

    private final TextTransformerInterface decorator;

    public LowerCaseTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }


    @Override
    public String transform(String text) {
        text = decorator.transform(text);
        return text.toLowerCase();
    }
}
