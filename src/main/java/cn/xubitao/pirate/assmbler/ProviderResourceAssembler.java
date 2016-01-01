package cn.xubitao.pirate.assmbler;

import cn.xubitao.pirate.controller.ProvidersController;
import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.resource.RestResource;
import cn.xubitao.pirate.resource.ProviderResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.sql.SQLException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 12/30/15.
 */
public class ProviderResourceAssembler extends ResourceAssemblerSupport<Provider, RestResource> {
    public ProviderResourceAssembler() {
        super(ProvidersController.class, RestResource.class);
    }

    public RestResource toResource(Provider provider) {
        ProviderResource providerResource = new ProviderResource();
        try {
            Link providersLink = linkTo(ProvidersController.class).withRel("providers");
            if (provider == null) {
                return RestResource.link(providersLink);
            }
            providerResource.setName(provider.getName());
            providerResource.setVersion(provider.getVersion());
            providerResource.add(linkTo(methodOn(ProvidersController.class).findById(provider.getId())).withSelfRel());

            providerResource.add(providersLink);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providerResource;
    }
}
