package pl.put.poznan.transformer.logic.transforms;

import pl.put.poznan.transformer.logic.TextTransformerInterface;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;
public class exampleTransformer extends TextTransformerDecorator {
    public exampleTransformer(TextTransformerInterface TTinterface){
        super(TTinterface);
    }

    public String transform(String text){
        return text;
    }
}
