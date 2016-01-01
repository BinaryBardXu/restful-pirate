package cn.xubitao.pirate.controller;

import cn.xubitao.pirate.assmbler.ProviderResourceAssembler;
import cn.xubitao.pirate.domain.ProviderEntities;
import cn.xubitao.pirate.domain.ProviderEntity;
import cn.xubitao.pirate.resource.ProviderResource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import scala.Int;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by xubitao on 12/27/15.
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Resource
    ProviderEntities providerEntities;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<ProviderResource> findById(@PathVariable Integer id) throws SQLException {
        ProviderEntity providerEntity = providerEntities.findById(id);
        ProviderResourceAssembler providerResourceAssembler=new ProviderResourceAssembler();
        ProviderResource providerResource=providerResourceAssembler.toResource(providerEntity);
        return new ResponseEntity<ProviderResource>(providerResource, HttpStatus.OK);
    }
}
