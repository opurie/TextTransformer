package pl.put.poznan.transformer.logic;

/**
 * Interface to be implemented by transformation classes
 */

public interface TextTransformerInterface {

    /**
     * @param text string to be transformed
     * @return transformed string
     */

    String transform(String text);
}
