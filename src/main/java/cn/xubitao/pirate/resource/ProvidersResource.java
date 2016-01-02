package cn.xubitao.pirate.resource;

import cn.xubitao.dolphin.foundation.resource.RestResource;

import java.util.List;

/**
 * Created by xubitao on 1/1/16.
 */
public class ProvidersResource extends RestResource {
    private List<RestResource> providers;

    public List<RestResource> getProviders() {
        return providers;
    }


    public void setProviders(List<RestResource> providers) {
        this.providers = providers;
    }
}
