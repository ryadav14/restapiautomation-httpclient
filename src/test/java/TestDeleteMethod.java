import helper.RestApiHelper;
import model.RestResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestDeleteMethod {

    /**
     * Step1: Call post method to post the data, Validate Status Code
     * Step2: Call delete end point to delete posted data, Validate Status Code
     * Step3: Call get end point , Validate Status should be 404 not found
     */

    @Test
    public void testDelete(){

        String id =(int) (1000*(Math.random())) + "";
        String jsonBody = "  {\n" +
                "        \"likes\": 0, \n" +
                "        \"title\": \"Product"+id+"\",\n" +
                "        \"price\": 120,\n" +
                "        \"__v\": 0\n" +
                "    }";


        Map<String, String> headers = new LinkedHashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        RestResponse response = RestApiHelper.performPostRequest("http://localhost:3001/product", jsonBody, ContentType.APPLICATION_JSON, headers);
        System.out.println(response);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        response = RestApiHelper.performDeleteRequest("http://localhost:3001/product/1",null);
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());

        response = RestApiHelper.performGetRequest("http://localhost:3001/product",headers);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());


    }
}
