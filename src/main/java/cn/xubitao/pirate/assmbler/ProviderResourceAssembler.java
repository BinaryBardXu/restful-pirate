package cn.xubitao.pirate.assmbler;

import cn.xubitao.pirate.controller.ProviderController;
import cn.xubitao.pirate.domain.ProviderEntity;
import cn.xubitao.pirate.resource.ProviderResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.sql.SQLException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 12/30/15.
 */
public class ProviderResourceAssembler extends ResourceAssemblerSupport<ProviderEntity, ProviderResource> {
    public ProviderResourceAssembler() {
        super(ProviderController.class, ProviderResource.class);
    }

    public ProviderResource toResource(ProviderEntity providerEntity) {
        ProviderResource providerResource = new ProviderResource();
        providerResource.setName(providerEntity.getName());
        providerResource.setVersion(providerEntity.getVersion());
        try {
            providerResource.add(linkTo(methodOn(ProviderController.class).findById(providerEntity.getId())).withSelfRel());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providerResource;
    }
}
