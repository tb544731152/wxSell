package com.weixin.sell.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

@Component
public class WechatPayConfig {
	
	@Autowired
	WechatAccountConfig wechatConfig;
	
	@Autowired
	WxPayH5Config wxPayH5Config;
	
	@Bean
	public BestPayServiceImpl bestPayServiceImpl() {
		BestPayServiceImpl bestPayServiceImpl = new BestPayServiceImpl();
		bestPayServiceImpl.setWxPayH5Config(wxPayH5Config);
		return bestPayServiceImpl;
	}
	
	@Bean
	public WxPayH5Config wxPayH5Config() {
		WxPayH5Config wxPayH5Config = new WxPayH5Config();
		wxPayH5Config.setAppId(wechatConfig.getMpAppId());
		wxPayH5Config.setAppSecret(wechatConfig.getMpAppSecret());
		wxPayH5Config.setKeyPath(wechatConfig.getKeyPath());
		wxPayH5Config.setMchId(wechatConfig.getMchId());
		wxPayH5Config.setMchKey(wechatConfig.getMchKey());
		wxPayH5Config.setNotifyUrl(wechatConfig.getNotifyUrl());
		return wxPayH5Config;
	}
}
