package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;

public class DiacriticToUniversalTransformer implements TextTransformerInterface {

    private final TextTransformerInterface decorator;

    public DiacriticToUniversalTransformer(TextTransformerInterface decorator) {
        this.decorator = decorator;
    }

    private static final Map<Character, Character> diacriticalsMap = new HashMap<>() {{
        put('ą', 'a');
        put('ć', 'c');
        put('ę', 'e');
        put('ł', 'l');
        put('ń', 'n');
        put('ó', 'o');
        put('ś', 's');
        put('ź', 'z');
        put('ż', 'z');
    }};

    @Override
    public String transform(String text) {
        text = decorator.transform(text);

        for (Character character : diacriticalsMap.keySet())
            text = text.replaceAll(character.toString(), diacriticalsMap.get(character).toString());

        return text;
    }
}