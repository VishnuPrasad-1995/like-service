package com.mavericsystems.likeservice.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;


@Component
public class HystrixFallBackFactory implements FallbackFactory<UserFeign> {
    private static Logger logger = LoggerFactory.getLogger(HystrixFallBackFactory.class);

    @Override
    public UserFeign create(Throwable cause) {
        logger.info("fallback reason was: {}" + cause.getMessage());
        return null;
    }


}
