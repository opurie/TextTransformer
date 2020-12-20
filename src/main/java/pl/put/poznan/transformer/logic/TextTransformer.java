package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.TextTransformerInterface;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * A transformation class that returns unnchanged text
 */
public class TextTransformer implements TextTransformerInterface{
    /**
     *
     * @param text String of text to transform
     * @return an original text
     */
    public String transform(String text){
        return text;
    }
}
