package cathywu.rapidpro.webclient.common;

import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author lzwu
 * @since 2/3/16
 */
public class HttpClient {

    private static HttpClient instance = null;

    private HttpClient() {
    }

    public static HttpClient getInstance() {
        if (instance == null) {
            instance = new HttpClient();
        }
        return instance;
    }

    public Response send(String link, Map<String, Object> params, RequestMethod method) throws IOException {
        String queryString = buildParam(params);
        URL url = new URL(link + queryString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(method.name());
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder responseMsg = new StringBuilder();
        String strTemp;
        while (null != (strTemp = br.readLine())) {
            responseMsg.append(strTemp);
        }
        return new Response(urlConnection.getResponseCode(), responseMsg.toString());
    }

    private String buildParam(Map<String, Object> params) throws UnsupportedEncodingException {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringBuilder queryString = new StringBuilder();
        for (String key : params.keySet()) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), "UTF-8"));
        }
        queryString.insert(0, "?");
        return queryString.toString();
    }
}
