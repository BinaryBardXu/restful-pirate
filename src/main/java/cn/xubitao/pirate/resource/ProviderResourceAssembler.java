package cn.xubitao.pirate.resource;

import cn.xubitao.pirate.domain.ProviderEntities;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by xubitao on 12/30/15.
 */
public class ProviderResourceAssembler extends ResourceAssemblerSupport<ProviderEntities, ProviderResource> {
    public ProviderResourceAssembler(Class<?> controllerClass, Class<ProviderResource> resourceType) {
        super(controllerClass, resourceType);
    }

    public ProviderResource toResource(ProviderEntities providerEntities) {
        ProviderResource providerResource=new ProviderResource();

        return null;
    }
}
