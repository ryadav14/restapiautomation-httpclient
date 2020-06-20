package model;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;

public class RequestStatus implements FutureCallback<HttpResponse> {

    @Override
    public void completed(HttpResponse httpResponse) {
        System.out.println("------ Request Completed------"+ httpResponse.getProtocolVersion());
    }

    @Override
    public void failed(Exception e) {
        System.out.println("------ Request Completed------"+ e.getMessage());
    }

    @Override
    public void cancelled() {
        System.out.println("------ Request Cancelled-----------------");
    }
}
