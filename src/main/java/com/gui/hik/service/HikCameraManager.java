package com.gui.hik.service;

import com.alibaba.fastjson.JSONObject;
import com.gui.hik.bean.HikCamera;
import com.gui.hik.dao.HikCameraRepository;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class HikCameraManager {
	@Autowired
	HikCameraRepository hikCameraRepository;
	/**
	 * 用于密钥过期重新获取
	 * @return
	 * @throws Exception
	 */
	public HikCamera getCameraKey() throws Exception {
		
		
		return null;
	}

    public String cameraPreviewURL(String api,String paramsBody) {
	
		HikCamera hc = hikCameraRepository.findAll().get(0);
		//过期则重新获取 getCameraKey();
	    /**
	     * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
	     */
	    ArtemisConfig.host = hc.getGateway(); // artemis网关服务器ip端口
	    ArtemisConfig.appKey = hc.getAppKey();  // 秘钥appkey
	    ArtemisConfig.appSecret = hc.getAppSecret();// 秘钥appSecret
	
	    /**
	     * STEP2：设置OpenAPI接口的上下文
	     */
	    final String ARTEMIS_PATH = "/artemis";
	
	    /**
	     * STEP3：设置接口的URI地址
	     */
	    final String previewURLsApi = ARTEMIS_PATH + api;
	    Map<String, String> path = new HashMap<String, String>();
	    path.put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
	
	    /**
	     * STEP4：设置参数提交方式
	     */
	    String contentType = "application/json";
	
	    /**
	     * STEP6：调用接口
	     */
	    String result = ArtemisHttpUtil.doPostStringArtemis(path, paramsBody, null, null, contentType , null);// post请求application/json类型参数
	    return result;
	}

	public String GetCameraPreviewURL() {

        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        ArtemisConfig.host = "127.0.0.1:443"; // artemis网关服务器ip端口
        ArtemisConfig.appKey = "29180881";  // 秘钥appkey
        ArtemisConfig.appSecret = "XO0wCAYGi4KV70ybjznx";// 秘钥appSecret

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String previewURLsApi = ARTEMIS_PATH + "/api/video/v1/cameras/previewURLs";
        Map<String, String> path = new HashMap<String, String>();
        path.put("https://", previewURLsApi);//根据现场环境部署确认是http还是https

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP5：组装请求参数
         */
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("cameraIndexCode", "748d84750e3a4a5bbad3cd4af9ed5101");
        jsonBody.put("streamType", 0);
        jsonBody.put("protocol", "rtsp");
        jsonBody.put("transmode", 1);
        jsonBody.put("expand", "streamform=ps");
        String body = jsonBody.toJSONString();
        /**
         * STEP6：调用接口
         */
        String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType , null);// post请求application/json类型参数
        return result;
    }
    
    public static void main(String[] args) {
        String result = new HikCameraManager().GetCameraPreviewURL();
        System.out.println("result结果示例: " + result);
    }
}