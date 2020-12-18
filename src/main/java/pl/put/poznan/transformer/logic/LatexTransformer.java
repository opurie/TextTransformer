package pl.put.poznan.transformer.logic;

public class LatexTransformer implements TextTransformerInterface{

    private static final char[] latexSymbols = {'\\', '&', '%', '$', '#', '_', '{', '}', '~', '^'};

    @Override
    public String transform(String text) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            for(char l : latexSymbols) {
                if(l == c) {
                    sb.append("\\");
                    break;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
