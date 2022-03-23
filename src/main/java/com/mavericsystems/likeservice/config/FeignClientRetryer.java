package com.mavericsystems.likeservice.config;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class FeignClientRetryer implements Retryer {
    private static Logger logger = LoggerFactory.getLogger(FeignClientRetryer.class);
    @Override
    public void continueOrPropagate(RetryableException e) {
        throw e;
    }

    @Override
    public Retryer clone() {
        logger.info("Feign retry attempt");
        return new Default(1000000, 1000000, 5);
    }



}

