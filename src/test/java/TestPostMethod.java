import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ResponseBody;
import helper.RestApiHelper;
import model.RestResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestPostMethod {


    @Test
    public void testPost() {
        String jsonBody = "  {\n" +
                "        \"likes\": 0, \n" +
                "        \"title\": \"Product2\",\n" +
                "        \"price\": 120,\n" +
                "        \"__v\": 0\n" +
                "    }";


        Map<String, String> headers = new LinkedHashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        RestResponse response = RestApiHelper.performPostRequest("http://localhost:3001/product", jsonBody, ContentType.APPLICATION_JSON, headers);
        System.out.println(response);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
       // RestResponse  response2 = RestApiHelper.performGetRequest("http://localhost:3001/product", headers);
      //  System.out.println(response2);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.serializeNulls().setPrettyPrinting().create();
        ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);

        XmlMapper xml = new XmlMapper();
        xml.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
        try {
            System.out.println("I am in try");
           ResponseBody body2= xml.readValue(response.getResponseBody(), ResponseBody.class);
            System.out.println("I am failing here"+body2);
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }


}