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
        setTimeout(function () {
            Racoon.restful({
                url: _link,
                success: function (_data) {
                    Statistics.displayItems(_data);
                    Statistics.displayCharts(_data);
                }
            })
        }, 1000);
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
                text: '最近一周记录情况'
            },
            xAxis: {
                type: 'datetime',
                labels: {
                    overflow: 'justify'
                }
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
            plotOptions: {
                spline: {
                    lineWidth: 4,
                    states: {
                        hover: {
                            lineWidth: 5
                        }
                    },
                    marker: {
                        enabled: false
                    },
                    pointInterval: 3600000, // one hour
                    pointStart: Date.UTC(2009, 9, 6, 0, 0, 0)
                }
            },
            series: [{
                name: '总数',
                data: [4.3, 5.1, 4.3, 5.2, 5.4, 4.7, 3.5, 4.1, 5.6, 7.4, 6.9, 7.1,
                    7.9, 7.9, 7.5, 6.7, 7.7, 7.7, 7.4, 7.0, 7.1, 5.8, 5.9, 7.4,
                    8.2, 8.5, 9.4, 8.1, 10.9, 10.4, 10.9, 12.4, 12.1, 9.5, 7.5,
                    7.1, 7.5, 8.1, 6.8, 3.4, 2.1, 1.9, 2.8, 2.9, 1.3, 4.4, 4.2,
                    3.0, 3.0],
                color: '#563D7C'

            }, {
                name: '命中',
                data: [0.1, 0.5, 0.9, 0.3, 0.0, 0.0, 9.2, 0.9, 0.1, 0.0, 0.3, 0.0,
                    0.0, 12.4, 0.0, 0.1, 0.8, 0.0, 0.9, 10.0, 0.8, 0.3, 0.2, 0.9,
                    0.0, 0.6, 1.2, 1.7, 0.6, 5.9, 4.1, 8.6, 3.7, 3.9, 1.7, 8.3,
                    3.0, 3.3, 4.8, 5.4, 4.8, 5.0, 3.2, 2.5, 0.9, 5.4, 0.3, 0.5, 0.4],
                color: 'green'
            }, {
                name: '未命中',
                data: [0.0, 0.0, 0.0, 0.0, 9.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.3, 0.0,
                    0.0, 0.4, 0.0, 0.1, 0.0, 3.0, 0.0, 0.0, 0.0, 0.0, 0.0, 5.0,
                    0.0, 0.6, 1.2, 1.7, 0.7, 2.9, 4.1, 2.6, 3.7, 3.9, 1.7, 2.3,
                    3.0, 3.3, 4.8, 5.0, 4.8, 5.0, 3.2, 2.0, 0.9, 0.4, 0.3, 0.5, 0.4]
                ,
                color: 'red'
            }]
        });
    }
};