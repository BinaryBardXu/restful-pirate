package cn.xubitao.pirate.assmbler.contract;

import cn.xubitao.dolphin.foundation.assmbler.DolphinAssembler;
import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.pirate.controller.ContractsController;
import cn.xubitao.pirate.domain.contract.Contracts;
import cn.xubitao.pirate.resource.ContractsResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 1/1/16.
 */
public class ContractsResourceAssembler extends DolphinAssembler {
    public ContractsResourceAssembler() {
        super(ContractsController.class, RestResource.class);
    }

    @Override
    public RestResource toRestResource(Object domain, Integer... pathVariables) throws Exception {
        this.pathVariables = pathVariables;
        Contracts contracts = (Contracts) domain;
        ContractsResource contractsResource = new ContractsResource();

        Link contractsLink = linkTo(methodOn(ContractsController.class).loadAll(pathVariables[0])).withRel("contracts");
        if (contracts == null) {
            return RestResource.link(contractsLink);
        }
        List<ResourceSupport> contractResources = buildResources(contracts.getContracts(), new ContractResourceAssembler(), pathVariables);
        contractsResource.setContracts(contractResources);
        contractsResource.add(linkTo(methodOn(ContractsController.class).loadAll(pathVariables[0])).withSelfRel());
        return contractsResource;
    }
}
