package com.fadelJmartPK.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.fadelJmartPK.jmart_android.LoginActivity;

import java.util.HashMap;
import java.util.Map;
public class TopUpRequest extends StringRequest {

    /**
     * @description
     * List dari parameter yang ada di bawah akan di passing menuju backend
     * agar user dapat melakukan topUp ke balance akunnya
     */

    private static final String URL =  "http://192.168.1.7:6969/account/" + LoginActivity.getLoggedAccount().id + "/topUp";
    private final Map<String , String> params;

    public TopUpRequest
            (
                    int id,
                    double balance,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);

        params = new HashMap<>();
        params.put("id", Integer.toString(id));
        params.put("balance", Double.toString(balance));
    }

    public Map<String , String> getParams() {
        return params;
    }
}

