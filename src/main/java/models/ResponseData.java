package models;

import java.util.Map;

public class ResponseData {
    private String url;
    private int statusCode;
    private Map<String, Object> headers;
    private String body;

    public ResponseData(String url, int statusCode, Map<String, Object> headers, String body) {
        this.url = url;
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
