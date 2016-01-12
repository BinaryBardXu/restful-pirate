package cn.xubitao.pirate.controller;

import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.dolphin.foundation.response.Response;
import cn.xubitao.pirate.assmbler.contract.ContractResourceAssembler;
import cn.xubitao.pirate.assmbler.contract.ContractsResourceAssembler;
import cn.xubitao.pirate.assmbler.record.RecordResourceAssembler;
import cn.xubitao.pirate.assmbler.record.RecordsResourceAssembler;
import cn.xubitao.pirate.domain.contract.Contract;
import cn.xubitao.pirate.domain.contract.Contracts;
import cn.xubitao.pirate.domain.record.Record;
import cn.xubitao.pirate.domain.record.Records;
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
public class RecordsController {
    @Resource
    private Records records;

    @RequestMapping(value = "/records", method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> loadAll(@RequestParam(required = false) Integer contractId, @RequestParam(required = false) Integer isHit, @RequestParam(required = false) String consumerKey) throws Exception {
        Records recordList = records.loadAll(contractId, isHit, consumerKey);
        return Response.build(recordList, new RecordsResourceAssembler(), contractId, isHit, consumerKey);
    }

    @RequestMapping(value = "/records", method = RequestMethod.POST)
    public HttpEntity<ResourceSupport> create(@RequestBody Record record) throws Exception {
        Record savedRecord = records.create(record);
        RecordResourceAssembler recordResourceAssembler = new RecordResourceAssembler();
        RestResource recordResource = recordResourceAssembler.toRestResource(savedRecord);
        return Response.created(recordResource);
    }
}
