package cn.xubitao.pirate.assmbler;

import cn.xubitao.pirate.controller.ProvidersController;
import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.domain.Providers;
import cn.xubitao.pirate.resource.ProviderResource;
import cn.xubitao.pirate.resource.ProvidersResource;
import cn.xubitao.pirate.resource.RestResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 1/1/16.
 */
public class ProvidersResourceAssembler extends ResourceAssemblerSupport<Providers, RestResource> {
    public ProvidersResourceAssembler() {
        super(ProvidersController.class, RestResource.class);
    }

    public RestResource toResource(Providers providers) {
        ProvidersResource providersResource = new ProvidersResource();
        try {
            Link providersLink = linkTo(ProvidersController.class).withRel("providers");
            if (providers == null) {
                return RestResource.link(providersLink);
            }
            List<RestResource> providerResources = buildProviderResources(providers);
            providersResource.setProviders(providerResources);
            providersResource.add(linkTo(methodOn(ProvidersController.class).loadAll()).withSelfRel());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providersResource;
    }

    private List<RestResource> buildProviderResources(Providers providers) {
        List<RestResource> providerResources = new ArrayList<RestResource>();
        ProviderResourceAssembler providerResourceAssembler = new ProviderResourceAssembler();
        for (Provider provider : providers.getProviders()) {
            RestResource providerResource = providerResourceAssembler.toResource(provider);
            providerResources.add(providerResource);
        }
        return providerResources;
    }
}
