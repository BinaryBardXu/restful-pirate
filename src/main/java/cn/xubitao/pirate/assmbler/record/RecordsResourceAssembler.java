package cn.xubitao.pirate.assmbler.record;

import cn.xubitao.dolphin.foundation.assmbler.DolphinAssembler;
import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.pirate.controller.EntranceController;
import cn.xubitao.pirate.controller.RecordsController;
import cn.xubitao.pirate.domain.record.Records;
import cn.xubitao.pirate.resource.record.RecordsResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 1/1/16.
 */
public class RecordsResourceAssembler extends DolphinAssembler {
    public RecordsResourceAssembler() {
        super(RecordsController.class, RestResource.class);
    }

    @Override
    public RestResource toRestResource(Object domain, Object... pathVariables) throws Exception {
        this.pathVariables = pathVariables;
        Records records = (Records) domain;
        RecordsResource recordsResource = new RecordsResource();

        Link recordsLink = linkTo(methodOn(RecordsController.class).loadAll(null, null, null)).withRel("records");
        Link entranceLink = linkTo(EntranceController.class).withRel("entrance");

        if (records == null) {
            return RestResource.link(recordsLink);
        }
        List<ResourceSupport> contractResources = buildResources(records.getRecords(), new RecordResourceAssembler(), pathVariables);
        recordsResource.setRecords(contractResources);
        recordsResource.add(entranceLink);
        recordsResource.add(recordsLink);
        recordsResource.add(linkTo(methodOn(RecordsController.class).loadAll((Integer) pathVariables[0], (Integer) pathVariables[1], (String) pathVariables[2])).withSelfRel());
        return recordsResource;
    }
}
