package com.gongxm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.gongxm.domain.HttpPostResult;
import com.gongxm.domain.MyX509TrustManager;

/**************************************
 * @版本 1.0
 * @作者 gongxm
 * @时间 2016/8/3 22:23
 * @修正
 * @版权所有 gongxm
 * @描述
 ***************************************/

public class HttpUtils {

	private static int TIME_OUT = 30000;

	/**
	 * 发送https请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return 返回微信服务器响应的信息
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			MyX509TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 普通的GET请求
	 * 
	 * @param url
	 * @return 返回请求结果的内容
	 * @throws IOException
	 */
	public static String executGet(String url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setConnectTimeout(TIME_OUT);
		conn.setReadTimeout(TIME_OUT);
		conn.setRequestMethod("GET");
		InputStream is = conn.getInputStream();
		String result = StringUtils.readStream(is, MyConstants.DEFAULT_ENCODING);
		return result;
	}

	
	/**
	 * POST请求
	 * @param url
	 * @param data
	 * @return
	 */
	public static HttpPostResult executePost(String url,String data) {
		try {
			HttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type", "application/json");// 设置发送的内容为json
			
			if(data!=null){
				StringEntity entity = new StringEntity(data);
				post.setEntity(entity);
			}

			HttpResponse response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			HttpPostResult result = new HttpPostResult();
			result.setStatusCode(statusCode);
			if (statusCode >= 200 && statusCode < 400) {// 请求成功
				HttpEntity entity = response.getEntity();
				InputStream stream = entity.getContent();
				result.setStream(stream);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
