package cn.xubitao.pirate.controller;

import cn.xubitao.pirate.assmbler.ProviderResourceAssembler;
import cn.xubitao.pirate.domain.Providers;
import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.resource.ProviderResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by xubitao on 12/27/15.
 */
@RestController
@RequestMapping("/provider")
public class ProvidersController {
    @Resource
    Providers providers;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<ProviderResource> findById(@PathVariable Integer id) throws SQLException {
        Provider provider = providers.findById(id);
        ProviderResourceAssembler providerResourceAssembler=new ProviderResourceAssembler();
        ProviderResource providerResource=providerResourceAssembler.toResource(provider);
        return new ResponseEntity<ProviderResource>(providerResource, HttpStatus.OK);
    }
}
