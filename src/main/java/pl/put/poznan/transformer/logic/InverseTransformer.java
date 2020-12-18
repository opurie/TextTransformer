package pl.put.poznan.transformer.logic;

public class InverseTransformer implements TextTransformerInterface{
    @Override
    public String transform(String text) {
        StringBuilder sb = new StringBuilder();
        int len = text.length();
        for(int i = 0; i < len; i++) {
            char c = Character.toLowerCase(text.charAt(len - i - 1));
            char original = text.charAt(i);
            if(Character.isUpperCase(original)) c = Character.toUpperCase(c);
            sb.append(c);
        }
        return sb.toString();
    }
}
