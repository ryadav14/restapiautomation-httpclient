public class GetRequest {
    /********
     Step 1 - Create HTTP Get method
     Step 2 - Create the HTTP Client
     Step 3 - Execute the HTTP method using the client
     Step 4 - Catch the response of the execution
     Step 5- Display the Response at the console
     */
    public static void main(String[] args) {
//       RestResponse response =  RestApiHelper.performGetRequest("http://localhost:3001/product",null);
//       System.out.println(response);


      /*  HttpPost post = new HttpPost("http://localhost:3001/product");
        try(CloseableHttpClient client = HttpClientBuilder.create().build();)
        {
            post.addHeader("Content-Type","application/json");
            post.addHeader("Accept","application/json");
            File file= new File("TestDataFile");
            FileEntity data = new FileEntity(file,ContentType.APPLICATION_JSON);
            post.setEntity(data);

            CloseableHttpResponse response= client.execute(post);
           ResponseHandler<String> handler= new BasicResponseHandler();
          RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(),handler.handleResponse(response));

        System.out.println(restResponse.toString());
        }catch (Exception e){
            e.printStackTrace();
        }*/
 /*       Map<String, String> headers = new LinkedHashMap<String,String>();
        headers.put("Content-Type","application/json");
        headers.put("Accept","application/json");

        File file= new File("TestDataFile");

        RestResponse response = RestApiHelper.performPostRequest("http://localhost:3001/product",file,ContentType.APPLICATION_JSON,headers);
        System.out.println(response.getResponseBody());
        System.out.println(response.getStatusCode());*/

/*
        HttpUriRequest delete = RequestBuilder.delete("http://localhost:3001/product/1").build();
        try(CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse response= client.execute(delete)){
            ResponseHandler<String> handler = new BasicResponseHandler();
            RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(),handler.handleResponse(response));
            System.out.println(restResponse.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
   */
 /*   RestResponse response = RestApiHelper.performDeleteRequest("http://localhost:3001/product/1",null);
    System.out.println(response.toString());*/
/*
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
                "    }";*/
/*
        RequestBuilder buildPut = RequestBuilder.put("http://localhost:3001/wishlist/product/add").
                setHeader("Content-Type", "application/json");
        HttpUriRequest put = buildPut.setEntity(new StringEntity(jsonBody2, ContentType.APPLICATION_JSON)).build();
        try (CloseableHttpClient client = HttpClientBuilder.create().build();
             CloseableHttpResponse response = client.execute(put)) {
            ResponseHandler<String> handler = new BasicResponseHandler();
            RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
            System.out.println(restResponse.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }*/
/*        Map<String, String> headers = new LinkedHashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        RestResponse response = RestApiHelper.performPutRequest("http://localhost:3004/wishlist/product/add", jsonBody2, ContentType.APPLICATION_JSON, headers);
        System.out.println(response.toString());*/

    }
}
