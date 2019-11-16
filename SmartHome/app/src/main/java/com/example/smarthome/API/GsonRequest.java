package com.example.smarthome.API;

//https://developer.android.com/training/volley/request-custom.html#java


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;

public class GsonRequest<T, E> extends Request<E> {
    private final Gson gson = new Gson();
    private final T data;
    private final String token;
    private final TypeToken<E> typeToken;
    private final Map<String, String> headers;
    private final Response.Listener<E> listener;

    public GsonRequest(int method, String url, T data, String token, TypeToken<E> typeToken, Map<String, String> headers,
                       Response.Listener<E> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.data = data;
        this.token = token;
        this.typeToken = typeToken;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(E response) {
        listener.onResponse(response);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return data != null ? gson.toJson(data).getBytes() : super.getBody();
    }

    @Override
    protected Response<E> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            if (token != null) {
                JSONObject jsonObject = new JSONObject(json);

                if (typeToken.getType() == Boolean.class) {
                    json = (new Boolean(jsonObject.getBoolean(token))).toString();
                } else if (typeToken.getType() == Integer.class) {
                    json = (new Integer(jsonObject.getInt(token))).toString();
                } else if (typeToken.getType() == Double.class) {
                    json = (new Double(jsonObject.getDouble(token))).toString();
                } else if (typeToken.getType() == Long.class) {
                    json = (new Long(jsonObject.getLong(token))).toString();
                } else if (Collection.class.isAssignableFrom(typeToken.getRawType())) {
                    json = jsonObject.getJSONArray(token).toString();
                } else {
                    json = jsonObject.getJSONObject(token).toString();
                }
            }

            return Response.success(
                    (E)gson.fromJson(json, typeToken.getType()),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (JSONException | UnsupportedEncodingException | JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}