package com.syriana.sso.oidc.api.controller;

import com.syriana.sso.oidc.api.response.common.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sso.oidc.srv.controller.RpcTestControllerApi;
import sso.oidc.srv.model.RpcTestReqDto;
import sso.oidc.srv.model.RpcTestResDto;

/**
 * @author syriana.zh
 * @date 2020/09/30
 */
@RestController
@RequestMapping("/rpc")
public class RpcTestController {

    @Autowired
    RpcTestControllerApi rpcTestControllerApi;

    @PostMapping("/test")
    public RestResult<RpcTestResDto> rpc(@RequestBody RpcTestReqDto reqVo) {
        RpcTestReqDto rpcReq = new RpcTestReqDto();
        rpcReq.setUserName(reqVo.getUserName());
        rpcReq.setUserPwd(reqVo.getUserPwd());
        rpcReq.setEmpNo(reqVo.getEmpNo());
        RpcTestResDto result = rpcTestControllerApi.rpcUsingPOST(rpcReq);
        return RestResult.suc("查询成功", result);
    }

}
