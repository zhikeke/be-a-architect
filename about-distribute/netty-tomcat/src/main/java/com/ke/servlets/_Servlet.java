package com.ke.servlets;

import com.ke.http._HttpRequest;
import com.ke.http._HttpResponse;
import com.ke.http._HttpServelt;

public class _Servlet extends _HttpServelt {
    public void doGet(_HttpRequest req, _HttpResponse resp) {
         resp.write(req.getParam("name"));
    }

    public void doPost(_HttpRequest req, _HttpResponse resp) {
          doGet(req, resp);
    }
}
