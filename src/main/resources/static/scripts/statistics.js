/**
 * Created by xubitao on 1/9/16.
 */

var Statistics = {
    init: function (_link) {
        Statistics.bindStatisticsButton(_link);
    },
    bindStatisticsButton: function (_link) {
        $("#stats-button").unbind();
        $("#stats-button").click(function () {
            $('#statisticsModel').modal();
            Statistics.loadData(_link);
        });
    },
    loadData: function (_link) {
        Racoon.restful({
            url: _link,
            success: function (_data) {
                Statistics.displayItems(_data);
                Statistics.displayCharts(_data);
            }
        })
    },
    displayItems: function (_data) {
        $("#statistics-providers-count").html(_data.providersCount);
        $("#statistics-contracts-count").html(_data.contractsCount);
        $("#statistics-records-count").html(_data.recordsCount);
        $("#statistics-hit-records-count").html(_data.hitRecordsCount);
        $("#statistics-missed-records-count").html(_data.missedRecordsCount);
    },
    displayCharts: function (_data) {
        $('#container').highcharts({
            chart: {
                type: 'spline',
                reflow: true,
                width: $("#statistics-chart").width() - 20,
                height: $("#statistics-chart").height() - 60

            },
            title: {
                text: '最近一个月记录情况'
            },
            xAxis: {
                categories: Statistics.buildCategoriesData(_data)
            },
            yAxis: {
                title: {
                    text: '次数'
                },
                min: 0,
                minorGridLineWidth: 0,
                gridLineWidth: 0,
                alternateGridColor: null,

            },
            tooltip: {
                valueSuffix: ' 次数'
            },
            series: [{
                name: '总数',
                data: Statistics.buildSeriesData(_data),
                color: '#563D7C'

            }, {
                name: '命中',
                data: Statistics.buildHitSeriesData(_data),
                color: 'green'
            }, {
                name: '未命中',
                data: Statistics.buildMissedSeriesData(_data),
                color: 'red'
            }]
        });
    },
    buildSeriesData: function (_data) {
        var seriesRecordsData = [];
        for (var index in _data.records) {
            seriesRecordsData.push(_data.records[index].count);
        }
        return seriesRecordsData;
    },
    buildHitSeriesData: function (_data) {
        var seriesRecordsData = [];
        for (var index in _data.hitRecords) {
            seriesRecordsData.push(_data.hitRecords[index].count);
        }
        return seriesRecordsData;
    },
    buildMissedSeriesData: function (_data) {
        var seriesRecordsData = [];
        for (var index in _data.missedRecords) {
            seriesRecordsData.push(_data.missedRecords[index].count);
        }
        return seriesRecordsData;
    },
    buildCategoriesData: function (_data) {
        var categoriesData = [];
        for (var index in _data.records) {
            categoriesData.push(_data.records[index].date);
        }
        return categoriesData;
    }
};