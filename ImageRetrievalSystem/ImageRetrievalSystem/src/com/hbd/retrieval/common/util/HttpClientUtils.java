package com.hbd.retrieval.common.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.hbd.retrieval.common.domain.DataServerConfig;
/**
 * 访问图片数据库API工具类
 * @author Edward
 *
 */
public class HttpClientUtils {
	/**
	 * 访问图像数据库API返回json数据字符串
	 * @param apiStr	API字符串
	 * @return	json数据字符串
	 */
	
	public static String getJsonStr(String apiStr){
		String jsonStr = null;
		//穿件HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		//创建HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		
		DataServerConfig config = ConfigManager.getDataServerConfig();
		HttpGet httpGet = new HttpGet(config.getServerUrl() + apiStr + config.getFormat());
		httpGet.setHeader("Authorization", config.getAuthorization());
		
		try {
			//执行get请求
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			//获取相应消息实体
			HttpEntity entity = httpResponse.getEntity();
			//获取状态码
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			//判断相应实体是否为空
			if(statusCode == 200 && entity != null){
				jsonStr = EntityUtils.toString(entity);
			}else{
				throw new ApplicationException("connect dataServer error");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//关闭流并释放资源
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonStr;
	}
}
