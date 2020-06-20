package helper;

import model.RequestStatus;
import model.RestResponse;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HttpAsyncClientHelper {

    public static Header[] getCustomHeaders(Map<String, String> headers) {
        Header[] customHeaders = new Header[headers.size()];
        int i = 0;
        for (String key : headers.keySet()) {
            customHeaders[i++] = new BasicHeader(key, headers.get(key));
        }
        return customHeaders;
    }

    private static HttpEntity getHttpEntity(Object content, ContentType type) {
        if (content instanceof String) return new StringEntity((String) content, type);
        else if (content instanceof File) return new FileEntity((File) content, type);
        else
            throw new RuntimeException("Entity Type not found");
    }

    public static SSLContext getSSLContext() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        TrustStrategy trust = new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        };
        return SSLContextBuilder.create().loadTrustMaterial(trust).build();
    }

    public static CloseableHttpAsyncClient getHttpAsyncClient(SSLContext context) {
        if (context == null)
            return HttpAsyncClientBuilder.create().build();
        return HttpAsyncClientBuilder.create().setSSLContext(context).build();

    }

    public static RestResponse performRequest(HttpUriRequest method, SSLContext context) throws InterruptedException, ExecutionException {
        Future<HttpResponse> response = null;
        try (CloseableHttpAsyncClient client = getHttpAsyncClient(context)) {
            client.start();
            response = client.execute(method, new RequestStatus());
            ResponseHandler<String> handler = new BasicResponseHandler();
            return new RestResponse(response.get().getStatusLine().getStatusCode(),handler.handleResponse(response.get()));
        } catch (Exception e) {
            if (e instanceof HttpResponseException) {
                return new RestResponse(response.get().getStatusLine().getStatusCode(), e.getMessage());
            }
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static RestResponse performGetRequestAsync(String uri,Map<String,String> headers){
        try {
            return performGetRequestAsync(new URI(uri), headers);
        }
        catch (URISyntaxException e){
                throw new RuntimeException(e.getMessage(),e);
            }
    }


    public static RestResponse performGetRequestAsync(URI uri,Map<String,String> headers){

        HttpGet get = new HttpGet(uri);
        if(headers!=null) get.setHeaders(getCustomHeaders(headers));
        try {
            return performRequest(get, null);
        }
        catch (InterruptedException | ExecutionException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
