package cn.xubitao.pirate.assmbler.provider;

import cn.xubitao.dolphin.foundation.assmbler.DolphinAssembler;
import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.pirate.controller.ProvidersController;
import cn.xubitao.pirate.controller.RecordsController;
import cn.xubitao.pirate.domain.provider.Providers;
import cn.xubitao.pirate.resource.provider.ProvidersResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 1/1/16.
 */
public class ProvidersResourceAssembler extends DolphinAssembler {

    public static final int MISSED = 0;

    public ProvidersResourceAssembler() {
        super(ProvidersController.class, RestResource.class);
    }

    @Override
    public RestResource toRestResource(Object domain, Object... pathVariables) throws Exception {
        Providers providers = (Providers) domain;
        ProvidersResource providersResource = new ProvidersResource();

        Link providersLink = linkTo(ProvidersController.class).withRel("providers");
        if (providers == null) {
            return RestResource.link(providersLink);
        }
        List<ResourceSupport> providerResources = buildResources(providers.getProviders(), new ProviderResourceAssembler(), pathVariables);
        providersResource.setProviders(providerResources);
        providersResource.add(linkTo(methodOn(ProvidersController.class).loadAll((String) pathVariables[0])).withSelfRel());
        providersResource.add(linkTo(methodOn(RecordsController.class).loadAll(null, MISSED, null)).withRel("missedRecords"));
        return providersResource;
    }
}
