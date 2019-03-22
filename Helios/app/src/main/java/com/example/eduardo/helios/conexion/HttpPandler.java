package com.example.eduardo.helios.conexion;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.util.ArrayList;


public class HttpPandler {

    public Boolean enviarInformacion(ArrayList<NameValuePair> params, String IP)
    {
        Boolean respuesta = true;
        HttpClient httpClient=new DefaultHttpClient();
        HttpContext httpContext=new BasicHttpContext();
        HttpPost httpPost=new HttpPost("http://"+IP+"/android_insert.php");
        HttpResponse httpResponse=null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            httpResponse=httpClient.execute(httpPost,httpContext);
            respuesta =  true;
        } catch (Exception e) {
            e.printStackTrace();
            respuesta  = false;
        }
        return  respuesta;
    }
}
