package cn.xubitao.pirate.assmbler.contract;

import cn.xubitao.dolphin.foundation.assmbler.DolphinAssembler;
import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.pirate.controller.ContractsController;
import cn.xubitao.pirate.controller.RecordsController;
import cn.xubitao.pirate.domain.contract.Contract;
import cn.xubitao.pirate.resource.contract.ContractResource;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 12/30/15.
 */
public class ContractResourceAssembler extends DolphinAssembler {

    public ContractResourceAssembler() {
        super(ContractsController.class, RestResource.class);
    }

    @Override
    public RestResource toRestResource(Object domain, Object... pathVariables) throws Exception {
        this.pathVariables = pathVariables;
        Contract contract = (Contract) domain;
        ContractResource contractResource = new ContractResource();
        Link contractsLink = linkTo(methodOn(ContractsController.class).loadAll((Integer) pathVariables[0])).withRel("contracts");
        if (domain == null) {
            return RestResource.link(contractsLink);
        }
        contractResource.setName(contract.getName());
        contractResource.setRequest(contract.getRequest());
        contractResource.setResponse(contract.getResponse());
        contractResource.setDesc(contract.getDesc());
        contractResource.setServer(contract.getServer());
        contractResource.setExcludeFields(contract.getExcludeFields());
        contractResource.setRecordsCount(contract.getRecordsCount());
        contractResource.add(linkTo(methodOn(ContractsController.class).findById(contract.getProvider().getId(), contract.getId())).withSelfRel());
        contractResource.add(linkTo(methodOn(RecordsController.class).loadAll(contract.getId(), null, null)).withRel("records"));

        contractResource.add(contractsLink);
        return contractResource;
    }
}
