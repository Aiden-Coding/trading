package com.aiden.trading.controller;

import com.aiden.trading.constant.TradingViewConstant;
import com.aiden.trading.dto.TvResult;
import com.aiden.trading.dto.tradingview.ChartInfo;
import com.aiden.trading.dto.tradingview.StudyTemplateInfo;
import com.aiden.trading.dto.tradingview.req.SaveChartReq;
import com.aiden.trading.dto.tradingview.req.SaveStudyTemplateReq;
import com.aiden.trading.dto.tradingview.resp.*;
import com.aiden.trading.entity.TvChartInfo;
import com.aiden.trading.service.ITvChartInfoService;
import com.aiden.trading.service.ITvStudyTemplateInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/tradingview")
@Tag(name = "tradingview接口")
@CrossOrigin
public class TradingViewController {

    @Resource
    private ITvStudyTemplateInfoService tvStudyTemplateInfoService;

    @Resource
    private ITvChartInfoService tvChartInfoService;
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
     * GET /charts_storage_api_version/study_tempates?client=client_id&user=user_id&chart=chart_id&template=name
     * template：模板名称
     * status	ok or error
     * data	Array of objects where each object has a name property representing the template name (example: Test)
     *
     */
    @GetMapping("/1.0/study_templates")
    public TvResult<?> studyTemplatesV1(@RequestParam("client") String client, @RequestParam("user") String user, @RequestParam(value = "template",required = false) String template) {
        if (StringUtils.isNoneBlank(template)) {
            TvResult<StudyTemplateInfo> ret = new TvResult<>();
            StudyTemplateInfo studyTemplateInfo = tvStudyTemplateInfoService.getByName(template,user,client);
            ret.setStatus(TradingViewConstant.Ok);
            ret.setData(studyTemplateInfo);
            return ret;
        }
        TvResult<List<Map<String,Object>>> ret = new TvResult<>();
        ret.setStatus(TradingViewConstant.Ok);
        ret.setData(tvStudyTemplateInfoService.getStudyTemplateNames(user,client));
        return ret;
    }


    /**
     * post /charts_storage_api_version/charts?client=client_id&user=user_id
     * status	ok or error
     * data	Array of objects where each object has a name property representing the template name (example: Test)
     *
     */
    @PostMapping("/1.0/study_templates")
    public TvResult<?> postStudyTemplatesV1(SaveStudyTemplateReq saveStudyTemplateReq) {
        tvStudyTemplateInfoService.saveStudyTemplate(saveStudyTemplateReq);
        return TvResult.ok();
    }
    /**
     * delete /charts_storage_api_version/charts?client=client_id&user=user_id
     * status	ok or error
     * data	Array of objects where each object has a name property representing the template name (example: Test)
     *
     * @return
     */
    @DeleteMapping("/1.0/study_templates")
    public TvResult<?> deleteStudyTemplatesV1(@RequestParam("client") String client, @RequestParam("user") String user, @RequestParam("template") String template) {
        tvStudyTemplateInfoService.deleteStudyTemplateByName(client,user,template);
        return TvResult.ok();
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
    public TvResult<?> charts(@RequestParam("client") String client, @RequestParam("user") String user, @RequestParam(value = "chart",required = false) Integer chart) {
        if (Objects.nonNull(chart)) {
            TvResult<ChartInfo> reta = new TvResult<>();
            ChartInfo studyTemplateInfo = tvChartInfoService.getChartInfoById(chart,user,client);
            reta.setStatus(TradingViewConstant.Ok);
            reta.setData(studyTemplateInfo);
            return reta;
        }
        TvResult<List<Map<String,Object>>> ret = new TvResult<>();
        ret.setStatus(TradingViewConstant.Ok);
        ret.setData(tvChartInfoService.getChartInfos(user,client));
        return ret;
    }


    /**
     * post /charts_storage_api_version/charts?client=client_id&user=user_id
     * status	ok or error
     * data	Array of objects where each object has a name property representing the template name (example: Test)
     *
     */
    @PostMapping("/1.0/charts")
    public Map<String,Object> postCharts( SaveChartReq saveChartReq) {
        TvChartInfo tvChartInfo = tvChartInfoService.saveChart(saveChartReq);
        Map<String,Object> ret = new HashMap<>();
        ret.put("status",TradingViewConstant.Ok);
        if (Objects.isNull(saveChartReq.getChart())) {
            ret.put("id", tvChartInfo.getId());
        }
        return ret;
    }
    /**
     * delete /charts_storage_api_version/charts?client=client_id&user=user_id
     * status	ok or error
     * data	Array of objects where each object has a name property representing the template name (example: Test)
     *
     */
    @DeleteMapping("/1.0/charts")
    public TvResult<?> deleteCharts(@RequestParam("client") String client, @RequestParam("user") String user, @RequestParam("chart") Integer chart) {
        tvChartInfoService.deleteChart(client,user,chart);
        return TvResult.ok();
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
