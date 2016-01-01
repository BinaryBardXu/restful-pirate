package cn.xubitao.pirate.assmbler;

import cn.xubitao.pirate.controller.ProvidersController;
import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.resource.ProviderResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.sql.SQLException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 12/30/15.
 */
public class ProviderResourceAssembler extends ResourceAssemblerSupport<Provider, ProviderResource> {
    public ProviderResourceAssembler() {
        super(ProvidersController.class, ProviderResource.class);
    }

    public ProviderResource toResource(Provider provider) {
        ProviderResource providerResource = new ProviderResource();
        providerResource.setName(provider.getName());
        providerResource.setVersion(provider.getVersion());
        try {
            providerResource.add(linkTo(methodOn(ProvidersController.class).findById(provider.getId())).withSelfRel());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providerResource;
    }
}
