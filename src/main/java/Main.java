import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static final String LINK = "https://api.nasa.gov/planetary/apod?api_key=Qx7HyndbtGxm3EzcD9hRuNy4f0PPHXHbRb55PMtt";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(LINK);
        CloseableHttpResponse response = httpClient.execute(request);

        NasaResponse nasaResponse = mapper.readValue(
                response.getEntity().getContent(),
                new TypeReference<>() {
                }
        );

        String linkToFile = nasaResponse.getHdurl();
        request = new HttpGet(linkToFile);
        response = httpClient.execute(request);

        InputStream in = response.getEntity().getContent();

        String fileName = linkToFile.substring(linkToFile.lastIndexOf('/') + 1);
        FileOutputStream fos = new FileOutputStream(fileName);

        byte[] bytes = new byte[4096];
        int length;
        while ((length = in.read(bytes)) > 0) {
            fos.write(bytes, 0, length);
        }

        response.close();
        httpClient.close();
    }
}