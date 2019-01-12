package com.zoo.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.hadoop.security.ssl.SSLHostnameVerifier;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	
	
	public static String get(String url){
		String result=null;
	    try {
	    	CloseableHttpClient client = createSSLClientDefault();
	    	HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(),"UTF-8");
                return result;  
            } 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result==null?"":result;
	}
	
	public static String post(String url, Map<String, Object> map){
		String result=null;
		try {
			CloseableHttpClient client = createSSLClientDefault();
			HttpPost post=new HttpPost(url);
			post.setHeader("accept", "*/*");
			post.setHeader("connection", "Keep-Alive");
			post.setHeader("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();   
            for (String key:map.keySet()) {
                nvps.add(new BasicNameValuePair(key, String.valueOf(map.get(key))));  
            }  
            post.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));  
			CloseableHttpResponse response = client.execute(post);
			StatusLine status = response.getStatusLine();  
            int state = status.getStatusCode();  
            if (state == HttpStatus.SC_OK) {  
                HttpEntity responseEntity = response.getEntity();  
                result = EntityUtils.toString(responseEntity);  
            }  
            else{  
                 System.out.println("请求返回:"+state+"("+url+")");  
            }
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return result==null?"":result;
	}
	
	/**
	 * 用来绕过证书检查
	 * @return
	 */
	public static CloseableHttpClient createSSLClientDefault(){  
        try {  
             SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {  
                 //信任所有证书  
                 public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
                     return true;  
                 }  
             }).build();  
             SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(sslContext,SSLHostnameVerifier.ALLOW_ALL);
             return HttpClients.custom().setSSLSocketFactory(sslFactory).build();  
         } catch (Exception e) {  
             e.printStackTrace();
         }  
         return  HttpClients.createDefault();  
    }
	
	/**
     * 将map编成http类型的参数返回(key1=value1&key2=value2)
     * @param map
     * @return
     */
    public static String param(Map<String, Object> map){
    	return param(map, null);
    }
    
    /**
     * 将map编成http类型的参数返回(key1=value1&key2=value2),有charset则编对应的url编码字符串
     * @param map
     * @param charset
     * @return
     */
    public static String param(Map<String, Object> map,String charset){
    	StringBuffer sb=new StringBuffer();
    	if(map!=null&&!map.isEmpty()){
    		for(String key:map.keySet()){
    			sb.append(key).append("=").append(map.get(key)).append("&");
    		}
    		sb.deleteCharAt(sb.length()-1);
    		if(charset!=null){
    			try {
    				return URLEncoder.encode(sb.toString(), charset);
    			} catch (UnsupportedEncodingException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return sb.toString();
    }
}
