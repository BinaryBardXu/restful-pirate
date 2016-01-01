package cn.xubitao.pirate.assmbler;

import cn.xubitao.pirate.controller.ProvidersController;
import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.domain.Providers;
import cn.xubitao.pirate.resource.ProviderResource;
import cn.xubitao.pirate.resource.ProvidersResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 1/1/16.
 */
public class ProvidersResourceAssembler extends ResourceAssemblerSupport<Providers, ProvidersResource> {
    public ProvidersResourceAssembler() {
        super(ProvidersController.class, ProvidersResource.class);
    }

    public ProvidersResource toResource(Providers providers) {
        ProvidersResource providersResource = new ProvidersResource();
        providersResource.setProviders(providers.getProviders());
        try {
            providersResource.add(linkTo(methodOn(ProvidersController.class).loadAll()).withSelfRel());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return providersResource;
    }
}
