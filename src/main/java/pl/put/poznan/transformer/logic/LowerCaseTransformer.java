package pl.put.poznan.transformer.logic;

public class LowerCaseTransformer implements TextTransformerInterface {
    @Override
    public String transform(String text) {
        return text.toLowerCase();
    }
}
