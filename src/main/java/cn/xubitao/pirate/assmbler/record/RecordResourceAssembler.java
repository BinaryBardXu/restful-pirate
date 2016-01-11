package cn.xubitao.pirate.assmbler.record;

import cn.xubitao.dolphin.foundation.assmbler.DolphinAssembler;
import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.pirate.controller.RecordsController;
import cn.xubitao.pirate.domain.record.Record;
import cn.xubitao.pirate.resource.record.RecordResource;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 12/30/15.
 */
public class RecordResourceAssembler extends DolphinAssembler {

    public RecordResourceAssembler() {
        super(RecordsController.class, RestResource.class);
    }

    @Override
    public RestResource toRestResource(Object domain, Object... pathVariables) throws Exception {
        this.pathVariables = pathVariables;
        Record record = (Record) domain;
        RecordResource recordResource = new RecordResource();
        Link recordsLink = linkTo(methodOn(RecordsController.class).loadAll(null)).withRel("records");
        if (domain == null) {
            return RestResource.link(recordsLink);
        }
        recordResource.setUrl(record.getUrl());
        recordResource.setRequest(record.getRequest());
        recordResource.setResponse(record.getResponse());
        recordResource.setConsumerKey(record.getConsumerKey());
        recordResource.setIsHit(record.getIsHit());
        recordResource.setContractId(record.getContractId());
        recordResource.setCreateTime(record.getCreateTime());
        recordResource.add(recordsLink);
        return recordResource;
    }
}
