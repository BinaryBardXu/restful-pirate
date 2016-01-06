package cn.xubitao.pirate.controller;

import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.dolphin.foundation.response.Response;
import cn.xubitao.pirate.assmbler.ContractResourceAssembler;
import cn.xubitao.pirate.assmbler.ContractsResourceAssembler;
import cn.xubitao.pirate.domain.Contract;
import cn.xubitao.pirate.domain.Contracts;
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
public class ContractsController {
    @Resource
    private Contracts contracts;

    @RequestMapping(value = "/providers/{providerId}/contracts", method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> loadAll(@PathVariable Integer providerId) throws Exception {
        Contracts contractList = contracts.loadAll(providerId);
        return Response.build(contractList, new ContractsResourceAssembler(), providerId);
    }

    @RequestMapping(value = "/providers/{providerId}/contracts/{id}", method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> findById(@PathVariable Integer providerId, @PathVariable Integer id) throws Exception {
        Contract contract = contracts.findById(id);
        return Response.build(contract, new ContractResourceAssembler(), providerId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Contract contract, @PathVariable Integer id) throws Exception {
        contracts.update(contract, id);
        return Response.ok();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable Integer id) throws Exception {
        contracts.deleteById(id);
        Link link = linkTo(ContractsController.class).withRel("contracts");
        return Response.ok(RestResource.link(link));
    }

    @RequestMapping(value = "/providers/{providerId}/contracts", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable Integer providerId, @RequestBody Contract contract) throws Exception {
        contract.setProviderId(providerId);
        Contract savedContract = contracts.create(contract);
        ContractResourceAssembler contractResourceAssembler = new ContractResourceAssembler();
        RestResource contractResource = contractResourceAssembler.toResource(savedContract);
        return Response.created(contractResource);
    }
}
