package pl.put.poznan.transformer.logic;

public class UpperCaseTransformer implements TextTransformerInterface{
    @Override
    public String transform(String text) {
        return text.toUpperCase();
    }
}