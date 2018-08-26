package com.weixin.sell.handler;


import com.weixin.sell.config.ProjectUrlConfig;
import com.weixin.sell.exception.SellException;
import com.weixin.sell.exception.SellerAuthorizeException;
import com.weixin.sell.utils.ResultVOUtil;
import com.weixin.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    //http://lfysell.natapp1.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
        .concat(projectUrlConfig.getWechatOpenAuthorize())
        .concat("/sell/wechat/qrAuthorize")
        .concat("?returnUrl=")
        .concat(projectUrlConfig.getSell())
        .concat("/sell/seller/login"));
    }

    @ResponseBody
    @ExceptionHandler(value = SellException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO handleSellException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
