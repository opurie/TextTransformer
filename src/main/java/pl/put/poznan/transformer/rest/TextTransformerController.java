package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;
import pl.put.poznan.transformer.rest.response.JSONResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Main controller of an application
 * It is responsible for API communication and text transformations
 */
@RestController
@RequestMapping("/transform")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    private Map<String, TextTransformerInterface> transformationMap;

    public TextTransformerController(){
        TextTransformerInterface transform = new TextTransformer();
        this.transformationMap = new HashMap<String, TextTransformerInterface>();
        this.transformationMap.put("removeRepetition",new RemoveRepeatingTransform(transform));
        this.transformationMap.put("upper", new UpperCaseTransformer(transform));
        this.transformationMap.put("invert", new InverseTransformer(transform));
        this.transformationMap.put("expandAbbreviation" ,  new ExpandAbbreviationTransformer(transform));
        this.transformationMap.put("lower", new LowerCaseTransformer(transform));
        this.transformationMap.put("capitalize",  new CapitalizeTransformer(transform));
        this.transformationMap.put("makeAbbreviation",  new WrapExpressionTransformer(transform));
        this.transformationMap.put("numbersToText",  new NumberToTextTransformer(transform, false));
        this.transformationMap.put("floatToInt",  new NumberToTextTransformer(transform, true));
        this.transformationMap.put("latex",  new LatexTransformer(transform));
        this.transformationMap.put("removeDiacritical" ,  new DiacriticToUniversalTransformer(transform));
    }

    public void overrideTransformation(String name, TextTransformerInterface transformation){
        this.transformationMap.put(name, transformation);
    }

    /**
     * Method for handling incoming POST requests
     * @param payload request body (application/json), must contain fields: transformation (name of transformation to be performed), text (text to be transformed)
     * @return response with transformed text (field result) as JSONResponse
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public JSONResponse post(@RequestBody Map<String, String> payload) {

        String text = payload.get("text");
        String transform = payload.get("transformation");

        // temporary solution for testing, needs to be fixed
        String[] transforms = new String[1];
        transforms[0] = transform;

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));
        /* perform the transformation, you should run your logic here, below is just a silly example */
        String res = createTransformChain(transforms).transform(text);
        return new JSONResponse(res);
    }

    /**
     * This method is used to creating a proper transformation class
     *
     * @param transforms is a name of transformation to choose
     * @return an interface representing transform chain
     */
    private TextTransformerInterface createTransformChain(String[] transforms){
        TextTransformerInterface chain = new TextTransformer();
        for(String transform : transforms) chain = decorator(transform, chain);
        return chain;
    }

    /**
     * Decorator method is used to find the correct class for the transformation
     *
     * @param name is a name of transformation requested by user
     * @param transform is an interface
     * @return a proper transformer class served as an interface
     */
    private TextTransformerInterface decorator(String name, TextTransformerInterface transform){
//        switch(name){
//            case "removeRepetition" : return new RemoveRepeatingTransform(transform);
//            case "upper" : return new UpperCaseTransformer(transform);
//            case "invert" : return new InverseTransformer(transform);
//            case "expandAbbreviation" : return new ExpandAbbreviationTransformer(transform);
//            case "lower":return new LowerCaseTransformer(transform);
//            case "capitalize": return new CapitalizeTransformer(transform);
//            case "makeAbbreviation": return new WrapExpressionTransformer(transform);
//            case "numbersToText": return new NumberToTextTransformer(transform, false);
//            case "floatToInt": return new NumberToTextTransformer(transform, true);
//            case "latex": return new LatexTransformer(transform);
//            case "removeDiacritical" : return new DiacriticToUniversalTransformer(transform);
//            default: return transform;
//        }
        if(this.transformationMap.containsKey(name)) return this.transformationMap.get(name);
        else return new TextTransformer();
    }
//    private static TextTransformerInterface decorator(String name, TextTransformerInterface transform){
//        return transformationMap.get(name);
//    }

}


