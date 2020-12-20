package pl.put.poznan.transformer.logic;


import pl.put.poznan.transformer.logic.TextTransformerInterface;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Class that does nothing
 */

public class TextTransformer implements TextTransformerInterface{

    /**
     * @param text string to be transformed
     * @return unchanged string
     */

    public String transform(String text){
        return text;
    }
}
