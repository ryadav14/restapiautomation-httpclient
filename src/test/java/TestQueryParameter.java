import helper.RestApiHelper;
import model.RestResponse;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class TestQueryParameter {

    @Test
    public void testQueryParam(){
        URI url=null;
        try {
              url = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost:3001/")
                    .setPath("product")
                      //.setParameter()
                      .build();
        }
        catch(URISyntaxException e)
        {
            e.printStackTrace();
        }
        RestResponse restResponse= RestApiHelper.performGetRequest(url,null);
        System.out.println(restResponse.toString());

    }
}
