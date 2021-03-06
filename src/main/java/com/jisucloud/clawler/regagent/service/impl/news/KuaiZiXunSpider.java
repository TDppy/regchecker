package com.jisucloud.clawler.regagent.service.impl.news;


import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.PapaSpiderConfig;
import com.jisucloud.clawler.regagent.util.StringUtil;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Map;


@Slf4j
@PapaSpiderConfig(
		home = "360kuai.com", 
		message = "快资讯 科技时报 娱乐八卦 热点新闻 军事历史 文化体育 政治经济 最新发布 搜一下 热门标签 政治 体育 科技 文化 经济 军事 娱乐 游戏 教育 艺术 热点。", 
		platform = "360kuai", 
		platformName = "快资讯", 
		tags = { "新闻", "资讯" }, 
		testTelephones = { "18720982607", "18212345678" },
		excludeMsg = "360旗下")
public class KuaiZiXunSpider extends PapaSpider {

	public boolean checkTelephone(String account) {
		try {
			String url = "http://login.360.cn/?callback=jQuery191010612164842010&src=pcw_so_news&from=pcw_so_news&charset=utf-8&requestScema=http&o=User&m=checkmobile&mobile="+account+"&type=&_=" + System.currentTimeMillis();
			Request request = new Request.Builder().url(url)
					.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0")
					.addHeader("Host", "login.360.cn")
					.addHeader("Referer", "http://www.360kuai.com/pc/home?sign=360_14600ced")
					.build();
			Response response = okHttpClient.newCall(request).execute();
			String res = StringUtil.unicodeToString(response.body().string());
			if (res.contains("已被使用")) {
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
