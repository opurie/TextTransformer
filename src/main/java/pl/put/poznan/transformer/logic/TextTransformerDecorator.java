package pl.put.poznan.transformer.logic;

public abstract class TextTransformerDecorator implements TextTransformerInterface{
    private TextTransformerInterface transformer;
    public TextTransformerDecorator(TextTransformerInterface transformer){
        this.transformer = transformer;
    }
    public String transform(String text){
        return transformer.transform(text);
    }


}
