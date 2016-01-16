package cn.xubitao.pirate.resource.contract;

import cn.xubitao.dolphin.foundation.resource.RestResource;

/**
 * Created by xubitao on 1/6/16.
 */
public class ContractResource extends RestResource {

    private String request;
    private String response;
    private String name;
    private String desc;
    private String server;
    private Integer providerId;
    private String excludeFields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getExcludeFields() {
        return excludeFields;
    }

    public void setExcludeFields(String excludeFields) {
        this.excludeFields = excludeFields;
    }
}
