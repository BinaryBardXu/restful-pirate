package cn.xubitao.pirate.controller;

import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.dolphin.foundation.response.Response;
import cn.xubitao.pirate.assmbler.ProviderResourceAssembler;
import cn.xubitao.pirate.assmbler.ProvidersResourceAssembler;
import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.domain.Providers;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by xubitao on 12/27/15.
 */
@RestController
@RequestMapping("/providers")
public class ProvidersController {
    @Resource
    private Providers providers;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> loadAll() throws Exception {
        Providers providerList = providers.loadAll();
        return Response.build(providerList, new ProvidersResourceAssembler());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> findById(@PathVariable Integer id) throws Exception {
        Provider provider = providers.findById(id);
        return Response.build(provider, new ProviderResourceAssembler());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Provider provider, @PathVariable Integer id) throws Exception {
        providers.update(provider, id);
        return Response.ok();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable Integer id) throws Exception {
        providers.deleteById(id);
        Link link = linkTo(ProvidersController.class).withRel("providers");
        return Response.ok(RestResource.link(link));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Provider provider) throws Exception {
        Provider savedProvider = providers.create(provider);
        ProviderResourceAssembler providerResourceAssembler = new ProviderResourceAssembler();
        RestResource providerResource = providerResourceAssembler.toResource(savedProvider);
        return Response.created(providerResource);
    }
}
