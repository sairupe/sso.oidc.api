package com.syriana.sso.oidc.api.exception;
/**
 * @author syriana.zh
 * @date 2019/05/08
 */
public enum ErpSSOOidcExStatus {

    NULL_DATA("0001", "空数据"),
    ILLEGAL_PARAM("0002", "参数不符合要求"),
    OPERATION_FAIL("0003", "操作失败"),
    OPERATION_SUCCESS("0004", "操作成功"),
    RPC_RESULT_CLASS_DEFINE_ERROR("0005", "参数出错"),
    RPC_FAIL("0006", "调用出错"),
    NOT_PERMISSION("0007", "权限不足！"),
    BUSINESS_EX("0008", "业务异常！");



    ErpSSOOidcExStatus(String code, String desc){
        this.code = code;
        this.message = desc;
    }

    private String code;

    private String message;

    private String prefix = "203";

    public String getCode() {
        return prefix + code;
    }

    public String getMessage() {
        return message;
    }
}
