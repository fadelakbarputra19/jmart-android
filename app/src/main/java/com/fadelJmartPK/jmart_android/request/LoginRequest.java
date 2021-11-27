package com.fadelJmartPK.jmart_android.request;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
public class LoginRequest extends StringRequest{
    static final String URL = "http://192.168.1.3:<6969>/account/login";
    private final Map<String, String> params;
    public LoginRequest(String email, String password, Response.Listener<String> listener,
                        Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }
}
