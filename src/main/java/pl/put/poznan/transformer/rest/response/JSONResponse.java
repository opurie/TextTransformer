package pl.put.poznan.transformer.rest.response;

/**
 * Class models JSON response from API. Consists of single field: String result, which contains transformed text to be returned as a response
 */
public class JSONResponse {
    private String result;

    /**
     * Default constructor
     * @param resultStr text to be returned
     */
    public JSONResponse(String resultStr){
        this.result = resultStr;
    }

    /**
     * setter for result field
     * @param resultStr new content of result field
     */
    public void setResult(String resultStr){
        this.result = resultStr;
    }

    /**
     * getter for result field
     * @return content of result field
     */
    public String getResult() {
        return this.result;
    }
}
