package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;

import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("/transform")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

//    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
//    public String post(@RequestParam(value="text", defaultValue="") String text,
//                              @RequestParam(value="transforms", defaultValue="upper") String[] transforms) {
//
//        // log the parameters
//        logger.debug(text);
//        logger.debug(Arrays.toString(transforms));
//        /* perform the transformation, you should run your logic here, below is just a silly example */
//        return createTransformChain(transforms).transform(text);
//    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@RequestBody Map<String, String> payload) {

        String text = payload.get("text");
        String transform = payload.get("transformation");

        // temporary solution for testing, needs to be fixed
        String[] transforms = new String[1];
        transforms[0] = transform;

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));
        /* perform the transformation, you should run your logic here, below is just a silly example */
        return createTransformChain(transforms).transform(text);
    }

    /*@RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        return null;
    }*/

    private static TextTransformerInterface createTransformChain(String[] transforms){
        TextTransformerInterface chain = new TextTransformer();
        for(String transform : transforms) chain = decorator(transform, chain);
        return chain;
    }

    private static TextTransformerInterface decorator(String name, TextTransformerInterface transform){
        switch(name){
            case "removeRepetition" : return new RemoveRepeatingTransform(transform);
            case "upper" : return new UpperCaseTransformer(transform);
            case "invert" : return new InverseTransformer(transform);
            case "expandShortcuts" : return new ExpandShourtcutsTransformer(transform);

            default: return transform;
        }
    }

}


