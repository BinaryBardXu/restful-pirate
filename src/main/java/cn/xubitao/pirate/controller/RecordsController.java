package cn.xubitao.pirate.controller;

import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.dolphin.foundation.response.Response;
import cn.xubitao.pirate.assmbler.record.RecordResourceAssembler;
import cn.xubitao.pirate.assmbler.record.RecordsResourceAssembler;
import cn.xubitao.pirate.domain.record.Record;
import cn.xubitao.pirate.domain.record.Records;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by xubitao on 12/27/15.
 */
@RestController
public class RecordsController {
    @Resource
    private Records records;

    @RequestMapping(value = "/records", method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> loadAll(@RequestParam(required = false) Integer contractId) throws Exception {
        Records recordList = records.loadAll(contractId);
        return Response.build(recordList, new RecordsResourceAssembler(), contractId);
    }

    @RequestMapping(value = "/records", method = RequestMethod.POST)
    public HttpEntity<ResourceSupport> create(@RequestBody Record record) throws Exception {
        Record savedRecord = records.create(record);
        RecordResourceAssembler recordResourceAssembler = new RecordResourceAssembler();
        RestResource recordResource = recordResourceAssembler.toRestResource(savedRecord);
        return Response.created(recordResource);
    }
}