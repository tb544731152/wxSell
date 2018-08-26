package com.weixin.sell.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "projectUrl")
@Configuration
public class ProjectUrlConfig {

    /**
     * 微信公众平台授权url
     */
    public String wechatMpAuthorize;

    /**
     * 微信开放平台授权url
     */
    public String wechatOpenAuthorize;

    /**
     * 点餐系统
     */
    public String sell;

	public String getWechatMpAuthorize() {
		return wechatMpAuthorize;
	}

	public void setWechatMpAuthorize(String wechatMpAuthorize) {
		this.wechatMpAuthorize = wechatMpAuthorize;
	}

	public String getWechatOpenAuthorize() {
		return wechatOpenAuthorize;
	}

	public void setWechatOpenAuthorize(String wechatOpenAuthorize) {
		this.wechatOpenAuthorize = wechatOpenAuthorize;
	}

	public String getSell() {
		return sell;
	}

	public void setSell(String sell) {
		this.sell = sell;
	}
    
    
}
