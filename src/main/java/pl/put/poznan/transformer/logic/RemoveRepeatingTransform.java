package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class that removes repetitions
 */

public class RemoveRepeatingTransform implements TextTransformerInterface {
    private final TextTransformerInterface decorator;
    private String[] symbols = {".", ",", ":", ";", "?", "!"};

    public String[] generateWords(String word){
        String[] words = new String[this.symbols.length];
        for(int i = 0; i < this.symbols.length; i++){
            words[i] = word + this.symbols[i];
        }
        return words;
    }

    public RemoveRepeatingTransform(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    /**
     * @param text string to be transformed
     * @return string without repetitions
     */

    @Override
    public String transform(String text) {
        text = decorator.transform(text);
        String[] split = text.split("\\s+");

        for (int i = 1, splitLength = split.length; i < splitLength; i++) {
            String lastWord = split[i];
            String word = split[i - 1];
            if (lastWord.equals(word) || Arrays.asList(generateWords(word)).contains(lastWord)) {
                split[i - 1] = null;
            }
        }

        return Arrays.stream(split)
                .filter(Predicate.not(Objects::isNull))
                .collect(Collectors.joining(" "));
    }
}
