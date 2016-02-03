package cathywu.rapidpro.webclient.common;

/**
 * @author lzwu
 * @since 2/3/16
 */
public class Response {
    private int responseCode;
    private String message;

    public Response(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
