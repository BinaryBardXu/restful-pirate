package cn.xubitao.pirate.resource;

import cn.xubitao.pirate.domain.Provider;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by xubitao on 12/30/15.
 */
public class ProviderResourceAssembler extends ResourceAssemblerSupport<Provider, ProviderResource> {
    public ProviderResourceAssembler(Class<?> controllerClass, Class<ProviderResource> resourceType) {
        super(controllerClass, resourceType);
    }

    public ProviderResource toResource(Provider provider) {
        ProviderResource providerResource=new ProviderResource();

        return null;
    }
}
