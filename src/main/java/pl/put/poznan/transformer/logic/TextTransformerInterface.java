package pl.put.poznan.transformer.logic;

/**
 *Interface that is used to be implemented by proper transformation class
 *
 */
public interface TextTransformerInterface {
    /**
     * Is used to trasformate user's text
     * @param text String of text to transform
     */
    String transform(String text);
}
