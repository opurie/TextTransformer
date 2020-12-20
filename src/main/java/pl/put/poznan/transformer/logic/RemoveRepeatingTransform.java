package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RemoveRepeatingTransform implements TextTransformerInterface {
    private final TextTransformerInterface decorator;

    public RemoveRepeatingTransform(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }


    @Override
    public String transform(String text) {
        text = decorator.transform(text);
        String[] split = text.split("\\s");

        for (int i = 1, splitLength = split.length; i < splitLength; i++) {
            String lastWord = split[i];
            String word = split[i - 1];
            if (lastWord.equals(word)) {
                split[i - 1] = null;
            }
        }

        return Arrays.stream(split)
                .filter(Predicate.not(Objects::isNull))
                .collect(Collectors.joining(" "));
    }
}
