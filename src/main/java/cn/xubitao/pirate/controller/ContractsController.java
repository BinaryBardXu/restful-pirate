package cn.xubitao.pirate.controller;

import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.dolphin.foundation.response.Response;
import cn.xubitao.pirate.assmbler.contract.ContractResourceAssembler;
import cn.xubitao.pirate.assmbler.contract.ContractsResourceAssembler;
import cn.xubitao.pirate.domain.contract.Contract;
import cn.xubitao.pirate.domain.contract.Contracts;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

    @RequestMapping(value = "/providers/{providerId}/contracts/{id}", method = RequestMethod.PUT)
    public HttpEntity<ResourceSupport> update(@RequestBody Contract contract, @PathVariable Integer providerId, @PathVariable Integer id) throws Exception {
        Contract updatedContract = contracts.update(contract, id);
        return Response.build(updatedContract, new ContractResourceAssembler(), providerId);
    }

    @RequestMapping(value = "/providers/{providerId}/contracts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable Integer providerId, @PathVariable Integer id) throws Exception {
        contracts.deleteById(id);
        Link link = linkTo(methodOn(ContractsController.class).loadAll(providerId)).withRel("contracts");
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
