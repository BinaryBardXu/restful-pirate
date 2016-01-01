package cn.xubitao.pirate.controller;

import cn.xubitao.pirate.assmbler.ProviderResourceAssembler;
import cn.xubitao.pirate.assmbler.ProvidersResourceAssembler;
import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.domain.Providers;
import cn.xubitao.pirate.resource.BlankResource;
import cn.xubitao.pirate.resource.ProviderResource;
import cn.xubitao.pirate.resource.ProvidersResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 12/27/15.
 */
@RestController
@RequestMapping("/providers")
public class ProvidersController {
    @Resource
    Providers providers;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<ProvidersResource> loadAll() throws SQLException {
        Providers providerList = providers.loadAll();
        ProvidersResourceAssembler providersResourceAssembler = new ProvidersResourceAssembler();
        ProvidersResource providersResource = providersResourceAssembler.toResource(providerList);
        return new ResponseEntity<ProvidersResource>(providersResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<ProviderResource> findById(@PathVariable Integer id) throws SQLException {
        Provider provider = providers.findById(id);
        ProviderResourceAssembler providerResourceAssembler = new ProviderResourceAssembler();
        ProviderResource providerResource = providerResourceAssembler.toResource(provider);
        return new ResponseEntity<ProviderResource>(providerResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Provider provider, @PathVariable Integer id) throws SQLException {
        providers.update(provider, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable Integer id) throws SQLException {
        providers.deleteById(id);
        Link link = linkTo(ProvidersController.class).withRel("providers");
        return new ResponseEntity(BlankResource.link(link), HttpStatus.OK);
    }
}
