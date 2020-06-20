package helper;

import model.RestResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpsClientHelper {
    public static SSLContext getSSLContext() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        TrustStrategy trust = new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        };
        return SSLContextBuilder.create().loadTrustMaterial(trust).build();
    }

    public static CloseableHttpClient getHttpClient(SSLContext sslContext) {
        return HttpClientBuilder.create().setSSLContext(sslContext).build();
    }

    public static RestResponse performGetRequestWithSSL(String uri, Map<String, String> headers) {
        HttpGet get = new HttpGet(uri);
        if (headers != null) {
            for (String key : headers.keySet()) {
                get.addHeader(key, headers.get(key));
            }
        }
        CloseableHttpResponse response=null;
        try(CloseableHttpClient client = getHttpClient(getSSLContext());
            ) {
            response = client.execute(get);
            ResponseHandler<String> handler = new BasicResponseHandler();
            return new RestResponse(response.getStatusLine().getStatusCode(),handler.handleResponse(response));
        } catch (Exception e) {
            if (e instanceof HttpResponseException) {
                return new RestResponse(response.getStatusLine().getStatusCode(), e.getMessage());
            }
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
