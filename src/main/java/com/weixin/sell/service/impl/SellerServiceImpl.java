package com.weixin.sell.service.impl;


import com.weixin.sell.dao.SellerInfoRepository;
import com.weixin.sell.pojo.SellerInfo;
import com.weixin.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
