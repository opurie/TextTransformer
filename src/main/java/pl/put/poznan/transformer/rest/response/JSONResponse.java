package pl.put.poznan.transformer.rest.response;

public class JSONResponse {
    private String result;

    public JSONResponse(String resultStr){
        this.result = resultStr;
    }

    public void setResult(String resultStr){
        this.result = resultStr;
    }

    public String getResult() {
        return this.result;
    }
}
