package cn.xubitao.pirate.controller;

import cn.xubitao.pirate.assmbler.ProviderResourceAssembler;
import cn.xubitao.pirate.assmbler.ProvidersResourceAssembler;
import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.domain.Providers;
import cn.xubitao.pirate.resource.RestResource;
import cn.xubitao.pirate.resource.ProvidersResource;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by xubitao on 12/27/15.
 */
@RestController
@RequestMapping("/providers")
public class ProvidersController {
    @javax.annotation.Resource
    Providers providers;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<RestResource> loadAll() throws SQLException {
        Providers providerList = providers.loadAll();
        ProvidersResourceAssembler providersResourceAssembler = new ProvidersResourceAssembler();
        RestResource providersResource = providersResourceAssembler.toResource(providerList);
        return new ResponseEntity<RestResource>(providersResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<RestResource> findById(@PathVariable Integer id) throws SQLException {
        Provider provider = providers.findById(id);
        ProviderResourceAssembler providerResourceAssembler = new ProviderResourceAssembler();
        RestResource providerResource = providerResourceAssembler.toResource(provider);
        return new ResponseEntity<RestResource>(providerResource, provider == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
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
        return new ResponseEntity(RestResource.link(link), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Provider provider) throws SQLException {
        Provider savedProvider = providers.create(provider);
        ProviderResourceAssembler providerResourceAssembler = new ProviderResourceAssembler();
        RestResource providerResource = providerResourceAssembler.toResource(savedProvider);
        return new ResponseEntity<RestResource>(providerResource, HttpStatus.OK);
    }
}
