package cn.xubitao.pirate.resource.record;

import cn.xubitao.dolphin.foundation.resource.RestResource;

/**
 * Created by xubitao on 1/6/16.
 */
public class RecordResource extends RestResource {

    private String url;
    private String request;
    private String response;
    private String consumerKey;
    private Integer contractId;
    private Integer isHit;
    private String createTime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getIsHit() {
        return isHit;
    }

    public void setIsHit(Integer isHit) {
        this.isHit = isHit;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
