package com.aiden.trading.controller;

import com.aiden.trading.constant.TradingViewConstant;
import com.aiden.trading.dto.tradingview.req.SaveChartReq;
import com.aiden.trading.dto.tradingview.resp.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tradingview")
@Tag(name = "tradingview接口")
@CrossOrigin
public class TradingViewController {
    @GetMapping("/config")
    public ConfigurationResp config() {
        ConfigurationResp configurationResp = new ConfigurationResp();
        configurationResp.setSupportsSearch(true);
        configurationResp.setSupportsMarks(true);
        configurationResp.setSupportsTime(true);
        return configurationResp;
    }

    @GetMapping("/time")
    public long time() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * GET /charts_storage_api_version/study_templates?client=client_id&user=user_id
     * status	ok or error
     * data	Array of objects where each object has a name property representing the template name (example: Test)
     *
     * @return
     */
    @GetMapping("/1.0/study_templates")
    public Object studyTemplatesV1(@RequestParam("client") String client, @RequestParam("user") String user, @RequestParam(value = "template",required = false) String template) {
        if (Objects.nonNull(template)) {
            GetStudyTemplateResp ret = new GetStudyTemplateResp();
            ret.setStatus(TradingViewConstant.Ok);
            GetStudyTemplateResp.DataDTO data = new GetStudyTemplateResp.DataDTO();
            data.setName("he;");
            ret.setData(data);
            return ret;
        }
        GetStudyTemplatesResp ret = new GetStudyTemplatesResp();
        ret.setStatus(TradingViewConstant.Ok);
        GetStudyTemplatesResp.DataDTO data = new GetStudyTemplatesResp.DataDTO();
        data.setName("he;");
        ret.setData(List.of(data));
        return ret;
    }


    /**
     * post /charts_storage_api_version/charts?client=client_id&user=user_id
     * status	ok or error
     * data	Array of objects where each object has a name property representing the template name (example: Test)
     *
     * @return
     */
    @PostMapping("/1.0/study_templates")
    public SaveOrUpdateChartsResp postStudyTemplatesV1(@RequestParam("client") String client, @RequestParam("user") String user,@RequestParam("name") String name,@RequestParam("content") String content) {
        SaveOrUpdateChartsResp ret = new SaveOrUpdateChartsResp();
        ret.setStatus(TradingViewConstant.Ok);
        return ret;
    }
    /**
     * delete /charts_storage_api_version/charts?client=client_id&user=user_id
     * status	ok or error
     * data	Array of objects where each object has a name property representing the template name (example: Test)
     *
     * @return
     */
    @DeleteMapping("/1.0/study_templates")
    public DeleteChartsResp deleteStudyTemplatesV1(@RequestParam("client") String client, @RequestParam("user") String user, @RequestParam("chart") Integer chart) {
        DeleteChartsResp ret = new DeleteChartsResp();
        ret.setStatus(TradingViewConstant.Ok);
        return ret;
    }

    /**
     *
     * status：ok 或 error
     * data：对象数组
     * timestamp：保存图表的 UNIX 时间（例如，1449084321）
     * symbol：图表的商品ID（例如，AA）
     * resolution：图表的分辨率（例如，1D）
     * id：图表的唯一整数标识符（例如，9163）
     * name：图表名称（例如，Test
     * @return
     */
    @GetMapping("/1.0/charts")
    public Object charts(@RequestParam("client") String client, @RequestParam("user") String user, @RequestParam(value = "chart",required = false) Integer chart) {
        if (Objects.nonNull(chart)) {
            GetChartsResp reta = new GetChartsResp();
            reta.setStatus(TradingViewConstant.Ok);
            GetChartsResp.DataDTO data = new GetChartsResp.DataDTO();
            data.setId(761245);
            data.setName("he;");
            data.setContent("{\"resolution\":\"1W\",\"symbol_type\":\"spot\",\"exchange\":\"BITSTAMP\",\"listed_exchange\":\"BITSTAMP\",\"symbol\":\"BITSTAMP:BTCUSD\",\"short_name\":\"BTCUSD\",\"legs\":\"[{\\\"symbol\\\":\\\"BITSTAMP:BTCUSD\\\",\\\"pro_symbol\\\":[\\\"BITSTAMP:BTCUSD\\\"]}]\",\"name\":\"1\",\"description\":\"\",\"charts_symbols\":\"{\\\"1\\\":{\\\"symbol\\\":\\\"BITSTAMP:BTCUSD\\\"}}\",\"is_realtime\":\"1\",\"content\":\"{\\\"name\\\":\\\"1\\\",\\\"layout\\\":\\\"s\\\",\\\"charts\\\":[{\\\"panes\\\":[{\\\"sources\\\":[{\\\"type\\\":\\\"MainSeries\\\",\\\"id\\\":\\\"_seriesId\\\",\\\"zorder\\\":0,\\\"haStyle\\\":{\\\"studyId\\\":\\\"BarSetHeikenAshi@tv-basicstudies-60\\\"},\\\"renkoStyle\\\":{\\\"studyId\\\":\\\"BarSetRenko@tv-prostudies-64\\\"},\\\"pbStyle\\\":{\\\"studyId\\\":\\\"BarSetPriceBreak@tv-prostudies-34\\\"},\\\"kagiStyle\\\":{\\\"studyId\\\":\\\"BarSetKagi@tv-prostudies-34\\\"},\\\"pnfStyle\\\":{\\\"studyId\\\":\\\"BarSetPnF@tv-prostudies-34\\\"},\\\"rangeStyle\\\":{\\\"studyId\\\":\\\"BarSetRange@tv-basicstudies-72\\\"},\\\"volFootprintStyle\\\":{\\\"studyId\\\":\\\"Footprint@tv-volumebyprice-104\\\"},\\\"formattingDeps\\\":{\\\"format\\\":\\\"price\\\",\\\"pricescale\\\":1,\\\"minmov\\\":1,\\\"fractional\\\":false,\\\"minmove2\\\":0,\\\"variable_tick_size\\\":\\\"\\\"},\\\"state\\\":{\\\"style\\\":1,\\\"esdShowDividends\\\":true,\\\"esdShowSplits\\\":true,\\\"esdShowEarnings\\\":true,\\\"esdShowBreaks\\\":false,\\\"esdFlagSize\\\":2,\\\"showContinuousContractSwitches\\\":true,\\\"showContinuousContractSwitchesBreaks\\\":false,\\\"showFuturesContractExpiration\\\":true,\\\"showLastNews\\\":true,\\\"showCountdown\\\":false,\\\"bidAsk\\\":{\\\"visible\\\":false,\\\"lineStyle\\\":1,\\\"lineWidth\\\":1,\\\"bidLineColor\\\":\\\"#2962FF\\\",\\\"askLineColor\\\":\\\"#F7525F\\\"},\\\"prePostMarket\\\":{\\\"visible\\\":true,\\\"lineStyle\\\":1,\\\"lineWidth\\\":1,\\\"preMarketColor\\\":\\\"#FB8C00\\\",\\\"postMarketColor\\\":\\\"#2962FF\\\"},\\\"highLowAvgPrice\\\":{\\\"highLowPriceLinesVisible\\\":false,\\\"highLowPriceLabelsVisible\\\":false,\\\"averageClosePriceLineVisible\\\":false,\\\"averageClosePriceLabelVisible\\\":false,\\\"highLowPriceLinesColor\\\":\\\"\\\",\\\"highLowPriceLinesWidth\\\":1,\\\"averagePriceLineColor\\\":\\\"\\\",\\\"averagePriceLineWidth\\\":1},\\\"visible\\\":true,\\\"showPriceLine\\\":true,\\\"priceLineWidth\\\":1,\\\"priceLineColor\\\":\\\"\\\",\\\"baseLineColor\\\":\\\"#5d606b\\\",\\\"showPrevClosePriceLine\\\":false,\\\"prevClosePriceLineWidth\\\":1,\\\"prevClosePriceLineColor\\\":\\\"#555555\\\",\\\"minTick\\\":\\\"default\\\",\\\"dividendsAdjustment\\\":{},\\\"backAdjustment\\\":false,\\\"settlementAsClose\\\":true,\\\"sessionId\\\":\\\"regular\\\",\\\"sessVis\\\":false,\\\"useVolumeWeightBars\\\":false,\\\"statusViewStyle\\\":{\\\"fontSize\\\":16,\\\"showExchange\\\":true,\\\"showInterval\\\":true,\\\"symbolTextSource\\\":\\\"description\\\"},\\\"candleStyle\\\":{\\\"upColor\\\":\\\"#089981\\\",\\\"downColor\\\":\\\"#F23645\\\",\\\"drawWick\\\":true,\\\"drawBorder\\\":true,\\\"borderColor\\\":\\\"#378658\\\",\\\"borderUpColor\\\":\\\"#089981\\\",\\\"borderDownColor\\\":\\\"#F23645\\\",\\\"wickColor\\\":\\\"#B5B5B8\\\",\\\"wickUpColor\\\":\\\"#089981\\\",\\\"wickDownColor\\\":\\\"#F23645\\\",\\\"barColorsOnPrevClose\\\":false,\\\"drawBody\\\":true},\\\"hollowCandleStyle\\\":{\\\"upColor\\\":\\\"#089981\\\",\\\"downColor\\\":\\\"#F23645\\\",\\\"drawWick\\\":true,\\\"drawBorder\\\":true,\\\"borderColor\\\":\\\"#378658\\\",\\\"borderUpColor\\\":\\\"#089981\\\",\\\"borderDownColor\\\":\\\"#F23645\\\",\\\"wickColor\\\":\\\"#B5B5B8\\\",\\\"wickUpColor\\\":\\\"#089981\\\",\\\"wickDownColor\\\":\\\"#F23645\\\",\\\"drawBody\\\":true},\\\"haStyle\\\":{\\\"upColor\\\":\\\"#089981\\\",\\\"downColor\\\":\\\"#F23645\\\",\\\"drawWick\\\":true,\\\"drawBorder\\\":true,\\\"borderColor\\\":\\\"#378658\\\",\\\"borderUpColor\\\":\\\"#089981\\\",\\\"borderDownColor\\\":\\\"#F23645\\\",\\\"wickColor\\\":\\\"#B5B5B8\\\",\\\"wickUpColor\\\":\\\"#089981\\\",\\\"wickDownColor\\\":\\\"#F23645\\\",\\\"showRealLastPrice\\\":false,\\\"barColorsOnPrevClose\\\":false,\\\"inputs\\\":{},\\\"inputInfo\\\":{},\\\"drawBody\\\":true},\\\"barStyle\\\":{\\\"upColor\\\":\\\"#089981\\\",\\\"downColor\\\":\\\"#F23645\\\",\\\"barColorsOnPrevClose\\\":false,\\\"dontDrawOpen\\\":false,\\\"thinBars\\\":true},\\\"hiloStyle\\\":{\\\"color\\\":\\\"#2962FF\\\",\\\"showBorders\\\":true,\\\"borderColor\\\":\\\"#2962FF\\\",\\\"showLabels\\\":true,\\\"labelColor\\\":\\\"#2962FF\\\",\\\"drawBody\\\":true},\\\"columnStyle\\\":{\\\"upColor\\\":\\\"rgba(8, 153, 129, 0.5)\\\",\\\"downColor\\\":\\\"rgba(242, 54, 69, 0.5)\\\",\\\"barColorsOnPrevClose\\\":true,\\\"priceSource\\\":\\\"close\\\"},\\\"lineStyle\\\":{\\\"color\\\":\\\"#2962FF\\\",\\\"linestyle\\\":0,\\\"linewidth\\\":2,\\\"priceSource\\\":\\\"close\\\"},\\\"lineWithMarkersStyle\\\":{\\\"color\\\":\\\"#2962FF\\\",\\\"linestyle\\\":0,\\\"linewidth\\\":2,\\\"priceSource\\\":\\\"close\\\"},\\\"steplineStyle\\\":{\\\"color\\\":\\\"#2962FF\\\",\\\"linestyle\\\":0,\\\"linewidth\\\":2,\\\"priceSource\\\":\\\"close\\\"},\\\"areaStyle\\\":{\\\"color1\\\":\\\"rgba(41, 98, 255, 0.28)\\\",\\\"color2\\\":\\\"#2962FF\\\",\\\"linecolor\\\":\\\"#2962FF\\\",\\\"linestyle\\\":0,\\\"linewidth\\\":2,\\\"priceSource\\\":\\\"close\\\",\\\"transparency\\\":100},\\\"hlcAreaStyle\\\":{\\\"highLineColor\\\":\\\"#089981\\\",\\\"highLineStyle\\\":0,\\\"highLineWidth\\\":2,\\\"lowLineColor\\\":\\\"#F23645\\\",\\\"lowLineStyle\\\":0,\\\"lowLineWidth\\\":2,\\\"closeLineColor\\\":\\\"#868993\\\",\\\"closeLineStyle\\\":0,\\\"closeLineWidth\\\":2,\\\"highCloseFillColor\\\":\\\"rgba(8, 153, 129, 0.2)\\\",\\\"closeLowFillColor\\\":\\\"rgba(242, 54, 69, 0.2)\\\"},\\\"renkoStyle\\\":{\\\"upColor\\\":\\\"#089981\\\",\\\"downColor\\\":\\\"#F23645\\\",\\\"borderUpColor\\\":\\\"#089981\\\",\\\"borderDownColor\\\":\\\"#F23645\\\",\\\"upColorProjection\\\":\\\"#336854\\\",\\\"downColorProjection\\\":\\\"#7f323f\\\",\\\"borderUpColorProjection\\\":\\\"#336854\\\",\\\"borderDownColorProjection\\\":\\\"#7f323f\\\",\\\"wickUpColor\\\":\\\"#089981\\\",\\\"wickDownColor\\\":\\\"#F23645\\\",\\\"inputs\\\":{\\\"source\\\":\\\"close\\\",\\\"sources\\\":\\\"Close\\\",\\\"boxSize\\\":3,\\\"style\\\":\\\"ATR\\\",\\\"atrLength\\\":14,\\\"wicks\\\":true},\\\"inputInfo\\\":{\\\"source\\\":{\\\"name\\\":\\\"Source\\\"},\\\"sources\\\":{\\\"name\\\":\\\"Source\\\"},\\\"boxSize\\\":{\\\"name\\\":\\\"Box size\\\"},\\\"style\\\":{\\\"name\\\":\\\"Style\\\"},\\\"atrLength\\\":{\\\"name\\\":\\\"ATR length\\\"},\\\"wicks\\\":{\\\"name\\\":\\\"Wicks\\\"}}},\\\"pbStyle\\\":{\\\"upColor\\\":\\\"#089981\\\",\\\"downColor\\\":\\\"#F23645\\\",\\\"borderUpColor\\\":\\\"#089981\\\",\\\"borderDownColor\\\":\\\"#F23645\\\",\\\"upColorProjection\\\":\\\"#336854\\\",\\\"downColorProjection\\\":\\\"#7f323f\\\",\\\"borderUpColorProjection\\\":\\\"#336854\\\",\\\"borderDownColorProjection\\\":\\\"#7f323f\\\",\\\"inputs\\\":{\\\"source\\\":\\\"close\\\",\\\"lb\\\":3},\\\"inputInfo\\\":{\\\"source\\\":{\\\"name\\\":\\\"Source\\\"},\\\"lb\\\":{\\\"name\\\":\\\"Number of line\\\"}}},\\\"kagiStyle\\\":{\\\"upColor\\\":\\\"#089981\\\",\\\"downColor\\\":\\\"#F23645\\\",\\\"upColorProjection\\\":\\\"#336854\\\",\\\"downColorProjection\\\":\\\"#7f323f\\\",\\\"inputs\\\":{\\\"source\\\":\\\"close\\\",\\\"style\\\":\\\"ATR\\\",\\\"atrLength\\\":14,\\\"reversalAmount\\\":1},\\\"inputInfo\\\":{\\\"source\\\":{\\\"name\\\":\\\"Source\\\"},\\\"style\\\":{\\\"name\\\":\\\"Style\\\"},\\\"atrLength\\\":{\\\"name\\\":\\\"ATR length\\\"},\\\"reversalAmount\\\":{\\\"name\\\":\\\"Reversal amount\\\"}}},\\\"pnfStyle\\\":{\\\"upColor\\\":\\\"#089981\\\",\\\"downColor\\\":\\\"#F23645\\\",\\\"upColorProjection\\\":\\\"#336854\\\",\\\"downColorProjection\\\":\\\"#7f323f\\\",\\\"inputs\\\":{\\\"sources\\\":\\\"Close\\\",\\\"reversalAmount\\\":3,\\\"boxSize\\\":1,\\\"style\\\":\\\"ATR\\\",\\\"atrLength\\\":14,\\\"oneStepBackBuilding\\\":false},\\\"inputInfo\\\":{\\\"sources\\\":{\\\"name\\\":\\\"Source\\\"},\\\"boxSize\\\":{\\\"name\\\":\\\"Box size\\\"},\\\"reversalAmount\\\":{\\\"name\\\":\\\"Reversal amount\\\"},\\\"style\\\":{\\\"name\\\":\\\"Style\\\"},\\\"atrLength\\\":{\\\"name\\\":\\\"ATR length\\\"},\\\"oneStepBackBuilding\\\":{\\\"name\\\":\\\"One step back building\\\"}}},\\\"baselineStyle\\\":{\\\"baselineColor\\\":\\\"#758696\\\",\\\"topFillColor1\\\":\\\"rgba(8, 153, 129, 0.28)\\\",\\\"topFillColor2\\\":\\\"rgba(8, 153, 129, 0.05)\\\",\\\"bottomFillColor1\\\":\\\"rgba(242, 54, 69, 0.05)\\\",\\\"bottomFillColor2\\\":\\\"rgba(242, 54, 69, 0.28)\\\",\\\"topLineColor\\\":\\\"#089981\\\",\\\"bottomLineColor\\\":\\\"#F23645\\\",\\\"topLineWidth\\\":2,\\\"bottomLineWidth\\\":2,\\\"priceSource\\\":\\\"close\\\",\\\"transparency\\\":50,\\\"baseLevelPercentage\\\":50},\\\"rangeStyle\\\":{\\\"barStyle\\\":0,\\\"upColor\\\":\\\"#089981\\\",\\\"downColor\\\":\\\"#F23645\\\",\\\"upColorProjection\\\":\\\"#336854\\\",\\\"downColorProjection\\\":\\\"#7f323f\\\",\\\"thinBars\\\":true,\\\"candlesUpColor\\\":\\\"#089981\\\",\\\"candlesDownColor\\\":\\\"#F23645\\\",\\\"candlesBorderUpColor\\\":\\\"#089981\\\",\\\"candlesBorderDownColor\\\":\\\"#F23645\\\",\\\"candlesWickUpColor\\\":\\\"#089981\\\",\\\"candlesWickDownColor\\\":\\\"#F23645\\\",\\\"inputs\\\":{\\\"range\\\":10,\\\"phantomBars\\\":false},\\\"inputInfo\\\":{\\\"range\\\":{\\\"name\\\":\\\"Range\\\"},\\\"phantomBars\\\":{\\\"name\\\":\\\"Phantom bars\\\"}}},\\\"volFootprintStyle\\\":{\\\"upColor\\\":\\\"#089981\\\",\\\"downColor\\\":\\\"#F23645\\\",\\\"drawWick\\\":true,\\\"drawBorder\\\":true,\\\"borderColor\\\":\\\"#378658\\\",\\\"borderUpColor\\\":\\\"#089981\\\",\\\"borderDownColor\\\":\\\"#F23645\\\",\\\"wickColor\\\":\\\"#737375\\\",\\\"wickUpColor\\\":\\\"#089981\\\",\\\"wickDownColor\\\":\\\"#F23645\\\",\\\"barColorsOnPrevClose\\\":false,\\\"drawBody\\\":true,\\\"deltaAdjust\\\":true,\\\"showSummary\\\":true,\\\"type\\\":\\\"Buy and sell\\\",\\\"pointOfControl\\\":true,\\\"imbalanceHighlight\\\":{\\\"buyColor\\\":\\\"#089981\\\",\\\"sellColor\\\":\\\"#F23645\\\",\\\"visible\\\":true},\\\"stackedLevels\\\":{\\\"visible\\\":true,\\\"count\\\":3},\\\"inputs\\\":{\\\"rowSize\\\":\\\"Auto\\\",\\\"atrLength\\\":14,\\\"imbalancePercent\\\":300,\\\"ticksPerRow\\\":100,\\\"showVA\\\":true,\\\"vaPercent\\\":70},\\\"bgColors\\\":{}},\\\"symbol\\\":\\\"BITSTAMP:BTCUSD\\\",\\\"shortName\\\":\\\"BTCUSD\\\",\\\"timeframe\\\":\\\"\\\",\\\"onWidget\\\":false,\\\"interval\\\":\\\"1W\\\",\\\"unitId\\\":null,\\\"currencyId\\\":\\\"USD\\\",\\\"priceAxisProperties\\\":{\\\"autoScale\\\":true,\\\"autoScaleDisabled\\\":false,\\\"lockScale\\\":false,\\\"percentage\\\":false,\\\"percentageDisabled\\\":false,\\\"log\\\":false,\\\"logDisabled\\\":false,\\\"alignLabels\\\":true,\\\"isInverted\\\":false,\\\"indexedTo100\\\":false}}},{\\\"type\\\":\\\"study_Volume\\\",\\\"id\\\":\\\"tTokYu\\\",\\\"state\\\":{\\\"inputs\\\":{\\\"showMA\\\":false,\\\"length\\\":20,\\\"col_prev_close\\\":false,\\\"symbol\\\":\\\"\\\",\\\"smoothingLine\\\":\\\"SMA\\\",\\\"smoothingLength\\\":9},\\\"styles\\\":{\\\"vol\\\":{\\\"display\\\":15,\\\"color\\\":\\\"#000080\\\",\\\"linestyle\\\":0,\\\"linewidth\\\":1,\\\"plottype\\\":5,\\\"transparency\\\":50,\\\"trackPrice\\\":false,\\\"title\\\":\\\"vol\\\"},\\\"vol_ma\\\":{\\\"display\\\":0,\\\"color\\\":\\\"#2196f3\\\",\\\"linestyle\\\":0,\\\"linewidth\\\":1,\\\"plottype\\\":0,\\\"transparency\\\":0,\\\"trackPrice\\\":false,\\\"title\\\":\\\"vol_ma\\\"},\\\"smoothedMA\\\":{\\\"display\\\":0,\\\"color\\\":\\\"#2196f3\\\",\\\"linestyle\\\":0,\\\"linewidth\\\":1,\\\"plottype\\\":0,\\\"transparency\\\":0,\\\"trackPrice\\\":false,\\\"title\\\":\\\"smoothedMA\\\"}},\\\"bands\\\":{},\\\"graphics\\\":{},\\\"ohlcPlots\\\":{},\\\"palettes\\\":{\\\"volumePalette\\\":{\\\"colors\\\":{\\\"0\\\":{\\\"color\\\":\\\"#F7525F\\\",\\\"width\\\":1,\\\"style\\\":0},\\\"1\\\":{\\\"color\\\":\\\"#22AB94\\\",\\\"width\\\":1,\\\"style\\\":0}}}},\\\"filledAreasStyle\\\":{},\\\"filledAreas\\\":{},\\\"visible\\\":true,\\\"showLegendValues\\\":true,\\\"showLabelsOnPriceScale\\\":true,\\\"precision\\\":\\\"default\\\",\\\"parentSources\\\":{},\\\"intervalsVisibilities\\\":{\\\"ticks\\\":true,\\\"seconds\\\":true,\\\"secondsFrom\\\":1,\\\"secondsTo\\\":59,\\\"minutes\\\":true,\\\"minutesFrom\\\":1,\\\"minutesTo\\\":59,\\\"hours\\\":true,\\\"hoursFrom\\\":1,\\\"hoursTo\\\":24,\\\"days\\\":true,\\\"daysFrom\\\":1,\\\"daysTo\\\":366,\\\"weeks\\\":true,\\\"weeksFrom\\\":1,\\\"weeksTo\\\":52,\\\"months\\\":true,\\\"monthsFrom\\\":1,\\\"monthsTo\\\":12,\\\"ranges\\\":true}},\\\"zorder\\\":-10000,\\\"ownFirstValue\\\":null,\\\"metaInfo\\\":{\\\"palettes\\\":{\\\"volumePalette\\\":{\\\"colors\\\":{\\\"0\\\":{\\\"name\\\":\\\"Falling\\\"},\\\"1\\\":{\\\"name\\\":\\\"Growing\\\"}}}},\\\"inputs\\\":[{\\\"id\\\":\\\"symbol\\\",\\\"name\\\":\\\"Other Symbol\\\",\\\"defval\\\":\\\"\\\",\\\"type\\\":\\\"symbol\\\",\\\"optional\\\":true,\\\"isHidden\\\":false,\\\"display\\\":15},{\\\"id\\\":\\\"showMA\\\",\\\"name\\\":\\\"show MA\\\",\\\"defval\\\":false,\\\"type\\\":\\\"bool\\\",\\\"isHidden\\\":true,\\\"display\\\":0},{\\\"id\\\":\\\"length\\\",\\\"name\\\":\\\"MA Length\\\",\\\"defval\\\":20,\\\"type\\\":\\\"integer\\\",\\\"min\\\":1,\\\"max\\\":2000,\\\"hideWhenPlotsHidden\\\":[\\\"vol_ma\\\"],\\\"display\\\":15},{\\\"defval\\\":false,\\\"id\\\":\\\"col_prev_close\\\",\\\"name\\\":\\\"Color based on previous close\\\",\\\"type\\\":\\\"bool\\\",\\\"display\\\":0},{\\\"id\\\":\\\"smoothingLine\\\",\\\"name\\\":\\\"Smoothing Line\\\",\\\"defval\\\":\\\"SMA\\\",\\\"type\\\":\\\"text\\\",\\\"options\\\":[\\\"SMA\\\",\\\"EMA\\\",\\\"WMA\\\"],\\\"hideWhenPlotsHidden\\\":[\\\"smoothedMA\\\"],\\\"display\\\":15},{\\\"id\\\":\\\"smoothingLength\\\",\\\"name\\\":\\\"Smoothing Length\\\",\\\"defval\\\":9,\\\"type\\\":\\\"integer\\\",\\\"min\\\":1,\\\"max\\\":10000,\\\"hideWhenPlotsHidden\\\":[\\\"smoothedMA\\\"],\\\"display\\\":15}],\\\"plots\\\":[{\\\"id\\\":\\\"vol\\\",\\\"type\\\":\\\"line\\\"},{\\\"id\\\":\\\"volumePalette\\\",\\\"palette\\\":\\\"volumePalette\\\",\\\"target\\\":\\\"vol\\\",\\\"type\\\":\\\"colorer\\\"},{\\\"id\\\":\\\"vol_ma\\\",\\\"type\\\":\\\"line\\\"},{\\\"id\\\":\\\"smoothedMA\\\",\\\"type\\\":\\\"line\\\"}],\\\"graphics\\\":{},\\\"defaults\\\":{\\\"styles\\\":{\\\"vol\\\":{\\\"display\\\":15,\\\"linestyle\\\":0,\\\"linewidth\\\":1,\\\"plottype\\\":5,\\\"trackPrice\\\":false,\\\"transparency\\\":50,\\\"color\\\":\\\"#000080\\\"},\\\"vol_ma\\\":{\\\"display\\\":0,\\\"linestyle\\\":0,\\\"linewidth\\\":1,\\\"plottype\\\":0,\\\"trackPrice\\\":false,\\\"transparency\\\":0,\\\"color\\\":\\\"#2196F3\\\"},\\\"smoothedMA\\\":{\\\"display\\\":0,\\\"linestyle\\\":0,\\\"linewidth\\\":1,\\\"plottype\\\":0,\\\"trackPrice\\\":false,\\\"transparency\\\":0,\\\"color\\\":\\\"#2196F3\\\"}},\\\"palettes\\\":{\\\"volumePalette\\\":{\\\"colors\\\":{\\\"0\\\":{\\\"color\\\":\\\"#F7525F\\\",\\\"width\\\":1,\\\"style\\\":0},\\\"1\\\":{\\\"color\\\":\\\"#22AB94\\\",\\\"width\\\":1,\\\"style\\\":0}}}},\\\"inputs\\\":{\\\"showMA\\\":false,\\\"length\\\":20,\\\"col_prev_close\\\":false,\\\"symbol\\\":\\\"\\\",\\\"smoothingLine\\\":\\\"SMA\\\",\\\"smoothingLength\\\":9}},\\\"_metainfoVersion\\\":53,\\\"isTVScript\\\":false,\\\"isTVScriptStub\\\":false,\\\"is_hidden_study\\\":false,\\\"styles\\\":{\\\"vol\\\":{\\\"title\\\":\\\"Volume\\\",\\\"histogramBase\\\":0},\\\"vol_ma\\\":{\\\"title\\\":\\\"Volume MA\\\",\\\"histogramBase\\\":0},\\\"smoothedMA\\\":{\\\"title\\\":\\\"Smoothed MA\\\",\\\"histogramBase\\\":0}},\\\"description\\\":\\\"Volume\\\",\\\"shortDescription\\\":\\\"Volume\\\",\\\"is_price_study\\\":false,\\\"id\\\":\\\"Volume@tv-basicstudies-1\\\",\\\"format\\\":{\\\"type\\\":\\\"volume\\\"},\\\"description_localized\\\":\\\"Volume\\\",\\\"shortId\\\":\\\"Volume\\\",\\\"packageId\\\":\\\"tv-basicstudies\\\",\\\"version\\\":\\\"1\\\",\\\"fullId\\\":\\\"Volume@tv-basicstudies-1\\\",\\\"productId\\\":\\\"tv-basicstudies\\\",\\\"_serverMetaInfoVersion\\\":52}},{\\\"type\\\":\\\"LineToolTrendLine\\\",\\\"id\\\":\\\"EwYWtX\\\",\\\"state\\\":{\\\"linecolor\\\":\\\"#2962FF\\\",\\\"linewidth\\\":2,\\\"linestyle\\\":0,\\\"extendLeft\\\":false,\\\"extendRight\\\":false,\\\"leftEnd\\\":0,\\\"rightEnd\\\":0,\\\"showLabel\\\":false,\\\"horzLabelsAlign\\\":\\\"center\\\",\\\"vertLabelsAlign\\\":\\\"bottom\\\",\\\"textcolor\\\":\\\"#2962FF\\\",\\\"fontsize\\\":14,\\\"bold\\\":false,\\\"italic\\\":false,\\\"alwaysShowStats\\\":false,\\\"showMiddlePoint\\\":false,\\\"showPriceLabels\\\":false,\\\"showPriceRange\\\":false,\\\"showPercentPriceRange\\\":false,\\\"showPipsPriceRange\\\":false,\\\"showBarsRange\\\":false,\\\"showDateTimeRange\\\":false,\\\"showDistance\\\":false,\\\"showAngle\\\":false,\\\"statsPosition\\\":2,\\\"symbolStateVersion\\\":2,\\\"zOrderVersion\\\":2,\\\"visible\\\":true,\\\"frozen\\\":false,\\\"symbol\\\":\\\"BITSTAMP:BTCUSD\\\",\\\"currencyId\\\":null,\\\"unitId\\\":null,\\\"intervalsVisibilities\\\":{\\\"ticks\\\":true,\\\"seconds\\\":true,\\\"secondsFrom\\\":1,\\\"secondsTo\\\":59,\\\"minutes\\\":true,\\\"minutesFrom\\\":1,\\\"minutesTo\\\":59,\\\"hours\\\":true,\\\"hoursFrom\\\":1,\\\"hoursTo\\\":24,\\\"days\\\":true,\\\"daysFrom\\\":1,\\\"daysTo\\\":366,\\\"weeks\\\":true,\\\"weeksFrom\\\":1,\\\"weeksTo\\\":52,\\\"months\\\":true,\\\"monthsFrom\\\":1,\\\"monthsTo\\\":12,\\\"ranges\\\":true},\\\"title\\\":\\\"\\\",\\\"text\\\":\\\"\\\",\\\"interval\\\":\\\"1W\\\"},\\\"points\\\":[{\\\"time_t\\\":1670803200,\\\"offset\\\":0,\\\"price\\\":17761.133146967317},{\\\"time_t\\\":1711324800,\\\"offset\\\":10,\\\"price\\\":80507.56941287003}],\\\"zorder\\\":-15000,\\\"ownerSource\\\":\\\"_seriesId\\\",\\\"isSelectionEnabled\\\":true,\\\"userEditEnabled\\\":true,\\\"linkKey\\\":\\\"l9oSomAUivFG\\\"},{\\\"type\\\":\\\"LineToolFibRetracement\\\",\\\"id\\\":\\\"ppwypy\\\",\\\"state\\\":{\\\"showCoeffs\\\":true,\\\"showPrices\\\":true,\\\"fillBackground\\\":true,\\\"transparency\\\":80,\\\"extendLines\\\":false,\\\"extendLinesLeft\\\":false,\\\"horzLabelsAlign\\\":\\\"left\\\",\\\"vertLabelsAlign\\\":\\\"bottom\\\",\\\"reverse\\\":false,\\\"coeffsAsPercents\\\":false,\\\"fibLevelsBasedOnLogScale\\\":false,\\\"labelFontSize\\\":12,\\\"trendline\\\":{\\\"visible\\\":true,\\\"color\\\":\\\"#787B86\\\",\\\"linewidth\\\":2,\\\"linestyle\\\":2},\\\"levelsStyle\\\":{\\\"linewidth\\\":2,\\\"linestyle\\\":0},\\\"level1\\\":[0,\\\"#787B86\\\",true],\\\"level2\\\":[0.236,\\\"#F23645\\\",true],\\\"level3\\\":[0.382,\\\"#FF9800\\\",true],\\\"level4\\\":[0.5,\\\"#4caf50\\\",true],\\\"level5\\\":[0.618,\\\"#089981\\\",true],\\\"level6\\\":[0.786,\\\"#00bcd4\\\",true],\\\"level7\\\":[1,\\\"#787B86\\\",true],\\\"level8\\\":[1.618,\\\"#2962FF\\\",true],\\\"level9\\\":[2.618,\\\"#F23645\\\",true],\\\"level10\\\":[3.618,\\\"#9c27b0\\\",true],\\\"level11\\\":[4.236,\\\"#e91e63\\\",true],\\\"level12\\\":[1.272,\\\"#FF9800\\\",false],\\\"level13\\\":[1.414,\\\"#F23645\\\",false],\\\"level16\\\":[2,\\\"#089981\\\",false],\\\"level14\\\":[2.272,\\\"#FF9800\\\",false],\\\"level15\\\":[2.414,\\\"#4caf50\\\",false],\\\"level17\\\":[3,\\\"#00bcd4\\\",false],\\\"level18\\\":[3.272,\\\"#787B86\\\",false],\\\"level19\\\":[3.414,\\\"#2962FF\\\",false],\\\"level20\\\":[4,\\\"#F23645\\\",false],\\\"level21\\\":[4.272,\\\"#9c27b0\\\",false],\\\"level22\\\":[4.414,\\\"#e91e63\\\",false],\\\"level23\\\":[4.618,\\\"#FF9800\\\",false],\\\"level24\\\":[4.764,\\\"#089981\\\",false],\\\"symbolStateVersion\\\":2,\\\"zOrderVersion\\\":2,\\\"visible\\\":true,\\\"frozen\\\":false,\\\"symbol\\\":\\\"BITSTAMP:BTCUSD\\\",\\\"currencyId\\\":null,\\\"unitId\\\":null,\\\"intervalsVisibilities\\\":{\\\"ticks\\\":true,\\\"seconds\\\":true,\\\"secondsFrom\\\":1,\\\"secondsTo\\\":59,\\\"minutes\\\":true,\\\"minutesFrom\\\":1,\\\"minutesTo\\\":59,\\\"hours\\\":true,\\\"hoursFrom\\\":1,\\\"hoursTo\\\":24,\\\"days\\\":true,\\\"daysFrom\\\":1,\\\"daysTo\\\":366,\\\"weeks\\\":true,\\\"weeksFrom\\\":1,\\\"weeksTo\\\":52,\\\"months\\\":true,\\\"monthsFrom\\\":1,\\\"monthsTo\\\":12,\\\"ranges\\\":true},\\\"title\\\":\\\"\\\",\\\"interval\\\":\\\"1W\\\"},\\\"points\\\":[{\\\"time_t\\\":1711324800,\\\"offset\\\":2,\\\"price\\\":69621.59983827743},{\\\"time_t\\\":1711324800,\\\"offset\\\":78,\\\"price\\\":15090.230818162214}],\\\"zorder\\\":-12500,\\\"ownerSource\\\":\\\"_seriesId\\\",\\\"isSelectionEnabled\\\":true,\\\"userEditEnabled\\\":true,\\\"linkKey\\\":\\\"8DUaaFvoh4vq\\\",\\\"version\\\":2}],\\\"mainSourceId\\\":\\\"_seriesId\\\",\\\"stretchFactor\\\":2000,\\\"leftAxisesState\\\":[],\\\"rightAxisesState\\\":[{\\\"state\\\":{\\\"id\\\":\\\"JLSGhNKiFC3r\\\",\\\"m_priceRange\\\":null,\\\"m_isAutoScale\\\":true,\\\"m_isPercentage\\\":false,\\\"m_isIndexedTo100\\\":false,\\\"m_isLog\\\":false,\\\"m_isLockScale\\\":false,\\\"m_isInverted\\\":false,\\\"m_topMargin\\\":0.1,\\\"m_bottomMargin\\\":0.08,\\\"alignLabels\\\":true,\\\"logFormula\\\":{\\\"logicalOffset\\\":4,\\\"coordOffset\\\":0.0001}},\\\"sources\\\":[\\\"_seriesId\\\",\\\"EwYWtX\\\",\\\"ppwypy\\\"]}],\\\"overlayPriceScales\\\":{\\\"tTokYu\\\":{\\\"id\\\":\\\"yRsCNjoBlGbI\\\",\\\"m_priceRange\\\":null,\\\"m_isAutoScale\\\":true,\\\"m_isPercentage\\\":false,\\\"m_isIndexedTo100\\\":false,\\\"m_isLog\\\":false,\\\"m_isLockScale\\\":false,\\\"m_isInverted\\\":false,\\\"m_topMargin\\\":0.1,\\\"m_bottomMargin\\\":0.08,\\\"alignLabels\\\":true,\\\"logFormula\\\":{\\\"logicalOffset\\\":4,\\\"coordOffset\\\":0.0001}}},\\\"priceScaleRatio\\\":null,\\\"isCollapsed\\\":false}],\\\"timeScale\\\":{\\\"m_barSpacing\\\":5.3404032325222595,\\\"m_rightOffset\\\":10,\\\"rightOffsetPercentage\\\":5,\\\"usePercentageRightOffset\\\":false},\\\"chartProperties\\\":{\\\"paneProperties\\\":{\\\"backgroundType\\\":\\\"gradient\\\",\\\"background\\\":\\\"#131722\\\",\\\"backgroundGradientStartColor\\\":\\\"#181C27\\\",\\\"backgroundGradientEndColor\\\":\\\"#131722\\\",\\\"gridLinesMode\\\":\\\"both\\\",\\\"vertGridProperties\\\":{\\\"color\\\":\\\"rgba(240, 243, 250, 0.06)\\\"},\\\"horzGridProperties\\\":{\\\"color\\\":\\\"rgba(240, 243, 250, 0.06)\\\"},\\\"crossHairProperties\\\":{\\\"color\\\":\\\"#9598A1\\\",\\\"style\\\":2,\\\"transparency\\\":0,\\\"width\\\":1},\\\"topMargin\\\":10,\\\"bottomMargin\\\":8,\\\"axisProperties\\\":{\\\"autoScale\\\":true,\\\"autoScaleDisabled\\\":false,\\\"lockScale\\\":false,\\\"percentage\\\":false,\\\"percentageDisabled\\\":false,\\\"indexedTo100\\\":false,\\\"log\\\":false,\\\"logDisabled\\\":false,\\\"alignLabels\\\":true,\\\"isInverted\\\":false},\\\"legendProperties\\\":{\\\"showStudyArguments\\\":true,\\\"showStudyTitles\\\":true,\\\"showStudyValues\\\":true,\\\"showSeriesTitle\\\":true,\\\"showSeriesOHLC\\\":true,\\\"showLegend\\\":true,\\\"showLastDayChange\\\":false,\\\"showBarChange\\\":true,\\\"showVolume\\\":false,\\\"showBackground\\\":true,\\\"showPriceSource\\\":true,\\\"backgroundTransparency\\\":50,\\\"showLogo\\\":true},\\\"separatorColor\\\":\\\"#2A2E39\\\"},\\\"scalesProperties\\\":{\\\"backgroundColor\\\":\\\"#ffffff\\\",\\\"lineColor\\\":\\\"rgba(240, 243, 250, 0)\\\",\\\"textColor\\\":\\\"#B2B5BE\\\",\\\"fontSize\\\":12,\\\"scaleSeriesOnly\\\":false,\\\"showSeriesLastValue\\\":true,\\\"seriesLastValueMode\\\":1,\\\"showSeriesPrevCloseValue\\\":false,\\\"showStudyLastValue\\\":true,\\\"showSymbolLabels\\\":false,\\\"showStudyPlotLabels\\\":false,\\\"showBidAskLabels\\\":false,\\\"showPrePostMarketPriceLabel\\\":true,\\\"showFundamentalNameLabel\\\":false,\\\"showFundamentalLastValue\\\":true,\\\"barSpacing\\\":6,\\\"axisHighlightColor\\\":\\\"rgba(41, 98, 255, 0.25)\\\",\\\"axisLineToolLabelBackgroundColorCommon\\\":\\\"#2962FF\\\",\\\"axisLineToolLabelBackgroundColorActive\\\":\\\"#143EB3\\\",\\\"showPriceScaleCrosshairLabel\\\":true,\\\"showTimeScaleCrosshairLabel\\\":true,\\\"crosshairLabelBgColorLight\\\":\\\"#131722\\\",\\\"crosshairLabelBgColorDark\\\":\\\"#363A45\\\"},\\\"chartEventsSourceProperties\\\":{\\\"visible\\\":true,\\\"futureOnly\\\":true,\\\"breaks\\\":{\\\"color\\\":\\\"#555555\\\",\\\"visible\\\":false,\\\"style\\\":2,\\\"width\\\":1}},\\\"tradingProperties\\\":{\\\"showPositions\\\":true,\\\"positionPL\\\":{\\\"visibility\\\":true,\\\"display\\\":0},\\\"bracketsPL\\\":{\\\"visibility\\\":true,\\\"display\\\":0},\\\"showOrders\\\":true,\\\"showExecutions\\\":true,\\\"showExecutionsLabels\\\":false,\\\"showReverse\\\":true,\\\"horizontalAlignment\\\":2,\\\"extendLeft\\\":true,\\\"lineLength\\\":5,\\\"lineWidth\\\":1,\\\"lineStyle\\\":0},\\\"priceScaleSelectionStrategyName\\\":\\\"auto\\\"},\\\"sessions\\\":{\\\"properties\\\":{\\\"graphics\\\":{\\\"backgrounds\\\":{\\\"outOfSession\\\":{\\\"color\\\":\\\"#2962FF\\\",\\\"transparency\\\":92,\\\"visible\\\":false},\\\"preMarket\\\":{\\\"color\\\":\\\"#FF9800\\\",\\\"transparency\\\":92,\\\"visible\\\":false},\\\"postMarket\\\":{\\\"color\\\":\\\"#2962FF\\\",\\\"transparency\\\":92,\\\"visible\\\":false}},\\\"vertlines\\\":{\\\"sessBreaks\\\":{\\\"color\\\":\\\"#4985e7\\\",\\\"style\\\":2,\\\"visible\\\":false,\\\"width\\\":1}}}}},\\\"version\\\":3,\\\"timezone\\\":\\\"Etc/UTC\\\",\\\"shouldBeSavedEvenIfHidden\\\":true,\\\"linkingGroup\\\":null,\\\"lineToolsGroups\\\":{\\\"groups\\\":[]},\\\"chartId\\\":\\\"1\\\"}],\\\"symbolLock\\\":0,\\\"intervalLock\\\":0,\\\"trackTimeLock\\\":0,\\\"dateRangeLock\\\":0,\\\"crosshairLock\\\":1,\\\"layoutsSizes\\\":{\\\"s\\\":[{\\\"percent\\\":1}]}}\"}"
            );
            data.setTimestamp(1632846049L);
            reta.setData(data);
            return reta;
        }
        GetChartsListResp ret = new GetChartsListResp();
        ret.setStatus(TradingViewConstant.Ok);
        GetChartsListResp.DataDTO data = new GetChartsListResp.DataDTO();
        data.setId(761245);
        data.setName("he;");
        data.setResolution("resolution");
        data.setSymbol("AAPL");
        data.setTimestamp(1632846049L);
        ret.setData(List.of(data));
        return ret;
    }


    /**
     * post /charts_storage_api_version/charts?client=client_id&user=user_id
     * status	ok or error
     * data	Array of objects where each object has a name property representing the template name (example: Test)
     *
     */
    @PostMapping("/1.0/charts")
    public SaveOrUpdateChartsResp postCharts( SaveChartReq saveChartReq) {
        SaveOrUpdateChartsResp ret = new SaveOrUpdateChartsResp();
        ret.setStatus(TradingViewConstant.Ok);
        ret.setId(1);
        return ret;
    }
    /**
     * delete /charts_storage_api_version/charts?client=client_id&user=user_id
     * status	ok or error
     * data	Array of objects where each object has a name property representing the template name (example: Test)
     *
     */
    @DeleteMapping("/1.0/charts")
    public DeleteChartsResp deleteCharts(@RequestParam("client") String client, @RequestParam("user") String user, @RequestParam("chart") Integer chart) {
        DeleteChartsResp ret = new DeleteChartsResp();
        ret.setStatus(TradingViewConstant.Ok);
        return ret;
    }

    /**
     * GET /quotes?symbols=<ticker_name_1>,<ticker_name_2>,...,<ticker_name_n>
     *
     * @return
     */
    @GetMapping("quotes")
    public QuotesResp quotes(@RequestParam("symbols") List<String> symbols) {
        QuotesResp quotesResp = new QuotesResp();
        quotesResp.setS(TradingViewConstant.Ok);
        List<QuotesResp.Datas> datas = new ArrayList<>();
        quotesResp.setD(datas);
        QuotesResp.Datas datas1 = new QuotesResp.Datas();
        datas1.setS(TradingViewConstant.Ok);
        datas1.setN("AAPL");
        datas.add(datas1);
        QuotesResp.Datas.DataV dataV = new QuotesResp.Datas.DataV();
        datas1.setV(dataV);
        dataV.setCh(new BigDecimal("0.1"));
        dataV.setChp(new BigDecimal("0.1"));
        dataV.setShortName("AAPL");
        dataV.setExchange("AAPL");
        dataV.setOriginalName("AAPL");
        dataV.setDescription("AAPL");
        dataV.setLp(new BigDecimal("0.1"));
        dataV.setAsk(new BigDecimal("0.1"));
        dataV.setBid(new BigDecimal("0.1"));
        dataV.setOpenPrice(new BigDecimal("0.1"));
        dataV.setHighPrice(new BigDecimal("0.1"));
        dataV.setLowPrice(new BigDecimal("0.1"));
        dataV.setPrevClosePrice(new BigDecimal("0.1"));
        dataV.setVolume(new BigDecimal("0.1"));
        return quotesResp;
    }

    /**
     * GET /symbols?symbol=AAL, GET /symbols?symbol=NYSE:MSFT
     * symbol: string. Symbol name or ticker.
     *
     * @return
     */
    @GetMapping("/symbols")
    public SymbolInfoResp symbol(@RequestParam("symbol") String symbol) {
        SymbolInfoResp symbolInfoResp = new SymbolInfoResp();
        symbolInfoResp.setName("APPL");
        symbolInfoResp.setExchangetraded("NasdaqNM");
        symbolInfoResp.setExchangelisted("NasdaqNM");
        symbolInfoResp.setTimezone("America/New_York");
        symbolInfoResp.setMinmov(1);
        symbolInfoResp.setMinmov2(2);
        symbolInfoResp.setPointvalue(1);
        symbolInfoResp.setSession("0930-1630");
        symbolInfoResp.setHasIntraday(false);
        symbolInfoResp.setVisiblePlotsSet("ohlcv");
        symbolInfoResp.setDescription("Apple Inc.");
        symbolInfoResp.setType("stock");
        symbolInfoResp.setSupportedResolutions(Arrays.asList("D", "2D", "3D", "W", "3W", "M", "6M"));
        symbolInfoResp.setPricescale(100);
        symbolInfoResp.setTicker("APPL");
        symbolInfoResp.setLogoUrls(List.of("https://"));
        symbolInfoResp.setExchangeLogo("https://");
        return symbolInfoResp;
    }

    /**
     * GET /history?symbol=<ticker_name>&from=<unix_timestamp>&to=<unix_timestamp>&resolution=<resolution>&countback=<countback>
     * symbol: 商品ID
     * from: unix timestamp (UTC) 最左侧所需K线的 unix 时间戳
     * to: unix timestamp (UTC) 最右边的所需K线（不包括在内）
     * resolution: string
     * countback: 以 to 开头的k线（优先级高于 from ）。 如果设置了 countback，则应该忽略 from
     * <p>
     * s: 状态码。 预期值:ok|error|no_data
     * errmsg: 错误消息。只在s = 'error'时出现
     * t: K线时间. unix时间戳 (UTC)
     * c: 收盘价
     * o: 开盘价 (可选)
     * h: 最高价 (可选)
     * l: 最低价(可选)
     * v: 成交量 (可选)
     *
     * @return
     */
    @GetMapping("/history")
    public HistoryResp history(@RequestParam("symbol") String symbol, @RequestParam("resolution") String resolution, @RequestParam("from") Long from, @RequestParam("to") Long to, @RequestParam("countback") Long countback) {
        HistoryResp ret = new HistoryResp();
        ret.setC(List.of(109.49));
        ret.setH(List.of(110.94));
        ret.setL(List.of(109.03));
        ret.setO(List.of(110.365));
        ret.setT(List.of(1480550400));
        ret.setV(List.of(37086862));
        if (from < 1672185600) {
            ret.setS("no_data");
            return ret;
        }
        ret.setS("ok");
        return ret;
    }


    /**
     * GET /history?symbol=<ticker_name>&from=<unix_timestamp>&to=<unix_timestamp>&resolution=<resolution>&countback=<countback>
     * symbol: 商品ID
     * from: unix timestamp (UTC) 最左侧所需K线的 unix 时间戳
     * to: unix timestamp (UTC) 最右边的所需K线（不包括在内）
     * resolution: string
     * countback: 以 to 开头的k线（优先级高于 from ）。 如果设置了 countback，则应该忽略 from
     * <p>
     * s: 状态码。 预期值:ok|error|no_data
     * errmsg: 错误消息。只在s = 'error'时出现
     * t: K线时间. unix时间戳 (UTC)
     * c: 收盘价
     * o: 开盘价 (可选)
     * h: 最高价 (可选)
     * l: 最低价(可选)
     * v: 成交量 (可选)
     *
     * @return
     */
    @GetMapping("/marks")
    public MarksResp marks(@RequestParam("symbol") String symbol, @RequestParam("resolution") String resolution, @RequestParam("from") Long from, @RequestParam("to") Long to) {
        MarksResp ret = new MarksResp();
        ret.setId(List.of(0));
        ret.setText(List.of("Red"));
        ret.setLabel(List.of("A"));
        ret.setLabelFontColor(List.of("red"));
        ret.setColor(List.of("red"));
        ret.setMinSize(List.of(12));
        ret.setTime(List.of(1522108800));
        return ret;
    }


}
