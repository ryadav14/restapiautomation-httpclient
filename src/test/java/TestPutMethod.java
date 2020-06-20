import helper.RestApiHelper;
import model.RestResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestPutMethod {


    @Test
    public void testPut(){
        String jsonBody2 = " {\n" +
                "        \"title\": \"video game list\",\n" +
                "        \"products\": [\n" +
                "            {\n" +
                "                \"likes\": 0,\n" +
                "                \"_id\": \"5ed68b54bded75652828b134\",\n" +
                "                \"title\": \"todya\",\n" +
                "                \"price\": 203,\n" +
                "                \"__v\": 0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"_id\": \"5ed68ee360baff63dc721350\",\n" +
                "        \"__v\": 0\n" +
                "    }";
        Map<String, String> headers = new LinkedHashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        RestResponse response = RestApiHelper.performPutRequest("http://localhost:3001/wishlist/product/add", jsonBody2, ContentType.APPLICATION_JSON, headers);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }
}
