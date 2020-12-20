package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.TextTransformerInterface;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class TextTransformer implements TextTransformerInterface{

    public String transform(String text){
        return text;
    }
}
