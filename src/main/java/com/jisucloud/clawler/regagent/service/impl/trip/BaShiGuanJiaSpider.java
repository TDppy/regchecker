package com.jisucloud.clawler.regagent.service.impl.trip;


import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.PapaSpiderConfig;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Map;



@Slf4j
@PapaSpiderConfig(
		home = "chebada.com", 
		message = "巴士管家官网为您提供全国汽车票查询,汽车时刻表查询,余票信息查询,汽车票网上订票等服务.中国道路运输协会官方互联网售票合作伙伴,安全出行,售后有保障!", 
		platform = "chebada", 
		platformName = "巴士管家", 
		tags = { "出行" , "巴士" , "班车" }, 
		testTelephones = { "15611695226", "18212345678" })
public class BaShiGuanJiaSpider extends PapaSpider {

	

	public boolean checkTelephone(String account) {
		try {
			String url = "https://m.chebada.com/cbdlogin/api/account/CheckMobile";
			Request request = new Request.Builder().url(url)
					.addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 7.0; PLUS Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.98 Mobile Safari/537.36")
					.addHeader("Host", "m.chebada.com")
					.addHeader("Referer", "https://m.chebada.com/cbdlogin/register")
					.post(FormBody.create(MediaType.parse("application/json"), "{\"MobileNo\":\""+account+"\"}"))
					.build();
			Response response = okHttpClient.newCall(request).execute();
			if (response.body().string().contains("无查询结果")) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkEmail(String account) {
		return false;
	}

	@Override
	public Map<String, String> getFields() {
		return null;
	}

}
