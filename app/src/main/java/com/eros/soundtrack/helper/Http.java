package com.eros.soundtrack.helper;

import android.content.Context;
import android.util.Log;

import com.eros.soundtrack.interfaces.Parameters;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
/**
 * Created by eroschen on 2017/7/13.
 */

public class Http {

    private String DEFAULT_ENCODING = "UTF-8";
    private int TIME_OUT = 30 * 1000;
    private String METHOD_POST = "POST";
    private String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

    protected String get(String targetURL, Map<String, String> header){

        Log.d(Parameters.TAG, "url: " + targetURL.toString());

        HttpURLConnection httpConn = null;
        BufferedReader reader = null;
        String result = "";

        try {
            URL url = new URL(targetURL);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setReadTimeout(TIME_OUT);
            if(header != null){
                Set<String> keys = header.keySet();
                Iterator<String> it = keys.iterator();
                while(it.hasNext()){
                    String sb = it.next();
                    httpConn.setRequestProperty(sb,header.get(sb));
                }

            }

            httpConn.connect();

            int responseCode = httpConn.getResponseCode();
            Log.d(Parameters.TAG, "responseCode: "+httpConn.getResponseCode());
            Log.v(Parameters.TAG, "responseMessage: "+httpConn.getResponseMessage());
            if(responseCode == HttpURLConnection.HTTP_OK){
                reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),DEFAULT_ENCODING));
            }
            else {
                reader = new BufferedReader(new InputStreamReader(httpConn.getErrorStream(),DEFAULT_ENCODING));
            }

            StringBuffer sb = new StringBuffer("");
            String line = "";
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
            reader.close();
            result = sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if(httpConn != null)
                httpConn.disconnect();
        }

        Log.d(Parameters.TAG, "result: " + result);
        return result;
    }

    protected String post(Context context, String targetURL, Map<String, String> header){

        Log.d(Parameters.TAG, "url: "+targetURL.toString());

        HttpURLConnection httpConn = null;
        BufferedReader  reader = null;
        DataOutputStream output = null;

        String result = null;

        List<NameValuePair> formData = new ArrayList<NameValuePair>();

        try{
            URL url = new URL(targetURL);
            httpConn = (HttpURLConnection) url.openConnection();

            if (httpConn instanceof HttpsURLConnection) {

                HttpsURLConnection httpsConn = (HttpsURLConnection) httpConn;

                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, trustAllCerts, null);
                SSLSocketFactory factory = sc.getSocketFactory();
                httpsConn.setSSLSocketFactory(factory);
                httpsConn.setHostnameVerifier(trustAllHosts);

                httpsConn.setReadTimeout(TIME_OUT);
                httpsConn.setDoInput(true);
                httpsConn.setDoOutput(true);
                httpsConn.setUseCaches(false);

                httpsConn.setRequestMethod(METHOD_POST);
                httpsConn.setRequestProperty("Content-Type", CONTENT_TYPE_FORM);

                Set<String> keys = header.keySet();
                Iterator<String> it = keys.iterator();
                while(it.hasNext()){
                    String sb = it.next();
                    httpsConn.setRequestProperty(sb,header.get(sb));
                    formData.add(new BasicNameValuePair(sb,header.get(sb)));
                }

                httpsConn.connect();

                output = new DataOutputStream(httpsConn.getOutputStream());

            } else {

                httpConn.setReadTimeout(TIME_OUT);
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);
                httpConn.setUseCaches(false);


                httpConn.setRequestMethod(METHOD_POST);
                httpConn.setRequestProperty("Content-Type", CONTENT_TYPE_FORM);

                Set<String> keys = header.keySet();
                Iterator<String> it = keys.iterator();
                while(it.hasNext()){
                    String sb = it.next();
                    httpConn.setRequestProperty(sb,header.get(sb));
                    formData.add(new BasicNameValuePair(sb,header.get(sb)));
                }

                httpConn.connect();

                output = new DataOutputStream(httpConn.getOutputStream());

            }

//			output.writeBytes(getQuery(formData));
            setPostData(formData, output);
            output.flush();

            int responseCode = httpConn.getResponseCode();
            Log.d(Parameters.TAG, "responseCode: "+httpConn.getResponseCode());
            Log.d(Parameters.TAG, "responseMessage: "+httpConn.getResponseMessage());
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),DEFAULT_ENCODING));
            }
            else {
                reader = new BufferedReader(new InputStreamReader(httpConn.getErrorStream(),DEFAULT_ENCODING));
            }

            StringBuffer sb = new StringBuffer();
            String line = "";
            while(( line = reader.readLine()) != null){
                sb.append(line);
            }
            reader.close();
            result = sb.toString();

            if(output != null)
                output.close();

        }catch(SocketTimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(MalformedURLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
                httpConn = null;
            }
        }

        Log.d(Parameters.TAG, "result: "+result);
        return result;
    }

    private void setPostData(List<NameValuePair> formData,
                             DataOutputStream output) throws UnsupportedEncodingException, IOException {

        if (formData != null) {
            StringBuilder value = new StringBuilder();

            for (int i = 0; i < formData.size(); i++) {
                value.append(URLEncoder.encode(formData.get(i).getValue(),DEFAULT_ENCODING));

                if (i!=0)
                    output.writeBytes("&");

                output.writeBytes(formData.get(i).getName() +"="+ value.toString());

                value.delete(0, value.length());
            }
        }
    }

    private final TrustManager[] trustAllCerts  = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType)
                        throws CertificateException {
                    return;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType)
                        throws CertificateException {
                    return;
                }
            }
    };

    private final HostnameVerifier trustAllHosts = new HostnameVerifier(){

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

}
