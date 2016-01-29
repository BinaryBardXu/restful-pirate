package cn.xubitao.pirate.assmbler.statistic;

import cn.xubitao.dolphin.foundation.assmbler.DolphinAssembler;
import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.pirate.controller.EntranceController;
import cn.xubitao.pirate.controller.StatisticController;
import cn.xubitao.pirate.domain.statistic.Statistic;
import cn.xubitao.pirate.resource.statistic.StatisticResource;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by xubitao on 12/30/15.
 */
public class StatisticResourceAssembler extends DolphinAssembler {

    public StatisticResourceAssembler() {
        super(StatisticController.class, RestResource.class);
    }

    @Override
    public RestResource toRestResource(Object domain, Object... pathVariables) throws Exception {
        this.pathVariables = pathVariables;
        Statistic statistic = (Statistic) domain;
        StatisticResource statisticResource = new StatisticResource();
        Link entranceLink = linkTo(EntranceController.class).withRel("entrance");
        Link selfLink = linkTo(StatisticController.class).withSelfRel();
        if (domain == null) {
            return RestResource.link(entranceLink);
        }
        statisticResource.setProvidersCount(statistic.getProviderCount());
        statisticResource.setContractsCount(statistic.getContractsCount());
        statisticResource.setRecordsCount(statistic.getRecordsCount());
        statisticResource.setHitRecordsCount(statistic.getHitRecordsCount());
        statisticResource.setMissedRecordsCount(statistic.getMissedRecordsCount());
        statisticResource.setRecords(statistic.getRecords());
        statisticResource.setHitRecords(statistic.getHitRecords());
        statisticResource.setMissedRecords(statistic.getMissedRecords());

        statisticResource.add(entranceLink);
        statisticResource.add(selfLink);
        return statisticResource;
    }
}
