package com.slipkprojects.sockshttp.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.app.ProgressDialog;

public class JsonRequestConfig {

    private Context Context;
    private JsonRequestConfig.Listener listener;
    private ProgressDialog pDialog;
    private boolean IsDialog;
    //String Updater = "LINK HERE";
    String Updater = "LINK NG UPDATER RAW MO";
    
    
    public interface Listener {
        void onResponse(String result) throws Exception;
        void onErrorResponse(String error);
        void onRequestFinished();
    }

    public JsonRequestConfig(Context context, boolean isDialog, JsonRequestConfig.Listener listener) {
        this.Context = context;
        this.listener = listener;
        this.IsDialog = isDialog;
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (!IsDialog) {
                pDialog = new ProgressDialog(Context);
				pDialog.setMessage("Please wait while loading");
				pDialog.setTitle("Checking Update");
				pDialog.setCancelable(false);
                pDialog.show();
            }
            checkUpdate();
        }, 300);
    }

    private void checkUpdate() {
        RequestQueue queue = Volley.newRequestQueue(Context);
        queue.getCache().clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Updater, new Response.Listener<String>() {

                @Override
                public void onResponse(String Response) {
                    try {
                        listener.onResponse(Response);
                    } catch (Exception e) {}
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onErrorResponse(error.toString());
                }
            });
        queue.add(stringRequest);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
                @Override
                public void onRequestFinished(Request<String> request) {
                    if (!IsDialog && pDialog != null) {
                        pDialog.dismiss();
                    }
                    if (listener != null) {
                        listener.onRequestFinished();
                    } 
                }
            });
    }
}

