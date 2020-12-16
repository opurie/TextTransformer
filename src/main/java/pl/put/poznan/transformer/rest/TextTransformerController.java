package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformerInterface;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.transforms.*;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper") String[] transforms) {

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

    private static TextTransformerInterface decorator(String name, TextTransformerInterface TInterface){
        switch(name){
            case "example": return new exampleTransformer(TInterface);
            default: return TInterface;
        }
    }

}


