package cn.xubitao.pirate.resource.provider;

import cn.xubitao.dolphin.foundation.resource.RestResource;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by xubitao on 1/1/16.
 */
public class ProvidersResource extends RestResource {
    private List<ResourceSupport> providers;

    public List<ResourceSupport> getProviders() {
        return providers;
    }

    public void setProviders(List<ResourceSupport> providers) {
        this.providers = providers;
    }
}
