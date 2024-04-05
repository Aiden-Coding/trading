package com.aiden.trading.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component("syncChinaStockJob")
public class SyncChinaStockJob implements JobService {

    private static final Logger log = LoggerFactory.getLogger(SyncChinaStockJob.class);

    @Override
    public void run(String params) {
        log.info("\n ======== \n syncChinaStockJob-job-params:{} \n ========",params);


    }
}
