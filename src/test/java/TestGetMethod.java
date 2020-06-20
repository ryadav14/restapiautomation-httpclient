import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ResponseBody;
import helper.RestApiHelper;
import model.RestResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;


public class TestGetMethod {

    @Test
    public void testGetPingAlive() {
        String url = "http://localhost:3001/product";
        RestResponse response = RestApiHelper.performGetRequest(url, null);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        //Assert.assertEquals("[{\"likes\":0,\"_id\":\"5ed68b54bded75652828b134\",\"title\":\"todya\",\"price\":20,\"__v\":0},{\"likes\":0,\"_id\":\"5ed78db0a0e94c75ece65135\",\"title\":\"Link's Shield\",\"price\":129.5,\"__v\":0},{\"likes\":0,\"_id\":\"5ed78db4a0e94c75ece65136\",\"title\":\"Lancer\",\"price\":400,\"__v\":0},{\"likes\":0,\"_id\":\"5ed78dc7a0e94c75ece65137\",\"title\":\"Vault Boy Bobble Head\",\"price\":40.99,\"__v\":0}]", response.getResponseBody());
      //  System.out.println(response.toString());
        /***
         * Step 1 : Use the GSON builder Class to get the instance of GSON class
         * Step 2: Use the Gson object to deserialize JSON
         *
         **/
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.serializeNulls().setPrettyPrinting().create();
        System.out.println(response.getResponseBody());
        ResponseBody body =  gson.fromJson(response.getResponseBody(), ResponseBody.class);
        System.out.println(body);
    }
}
