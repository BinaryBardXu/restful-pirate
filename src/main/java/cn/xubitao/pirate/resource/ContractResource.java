package cn.xubitao.pirate.resource;

import cn.xubitao.dolphin.foundation.resource.RestResource;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by xubitao on 1/6/16.
 */
public class ContractResource  extends RestResource {

    private String request;
    private String response;
    private String name;
    private String desc;
    private String url;
    private Integer providerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
