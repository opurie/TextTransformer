package pl.put.poznan.transformer.logic;

public class CapitalizeTransformer implements TextTransformerInterface{
    @Override
    public String transform(String text) {
        String[] textArray = text.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < textArray.length; i++) {
            String s = textArray[i];
            sb.append(s.substring(0, 1).toUpperCase());
            sb.append(s.substring(1));
            if(i != textArray.length - 1) sb.append(" ");
        }
        return sb.toString();
    }
}
