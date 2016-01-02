package cn.xubitao.pirate.assmbler;

import cn.xubitao.dolphin.foundation.assmbler.DolphinAssembler;
import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.pirate.controller.ProvidersController;
import cn.xubitao.pirate.domain.Providers;
import cn.xubitao.pirate.resource.ProvidersResource;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 1/1/16.
 */
public class ProvidersResourceAssembler extends DolphinAssembler {
    public ProvidersResourceAssembler() {
        super(ProvidersController.class, RestResource.class);
    }

    public RestResource toRestResource(Object domain) throws Exception {
        Providers providers = (Providers) domain;
        ProvidersResource providersResource = new ProvidersResource();

        Link providersLink = linkTo(ProvidersController.class).withRel("providers");
        if (providers == null) {
            return RestResource.link(providersLink);
        }
        List<RestResource> providerResources = buildResources(providers.getProviders(), new ProviderResourceAssembler());
        providersResource.setProviders(providerResources);
        providersResource.add(linkTo(methodOn(ProvidersController.class).loadAll()).withSelfRel());
        return providersResource;
    }
}
