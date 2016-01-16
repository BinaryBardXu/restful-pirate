package cn.xubitao.pirate.controller;

import cn.xubitao.dolphin.foundation.response.Response;
import cn.xubitao.pirate.assmbler.statistic.StatisticResourceAssembler;
import cn.xubitao.pirate.domain.statistic.Statistic;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by xubitao on 1/15/16.
 */
@RestController
@RequestMapping(value = "/statistic")
public class StatisticController {
    @Resource
    private Statistic statistic;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> load() throws Exception {
        Statistic statisticResult = statistic.load();
        return Response.build(statisticResult, new StatisticResourceAssembler());
    }
}
