package com.mavericsystems.likeservice.feign;

import com.mavericsystems.likeservice.config.CustomerRetryClientConfig;
import com.mavericsystems.likeservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",configuration = CustomerRetryClientConfig.class,fallbackFactory = HystrixFallBackFactory.class)
//@FeignClient(name = "user-service")
public interface UserFeign {
    @GetMapping("/users/{userId}")
    UserDto getUserById(@PathVariable("userId") String userId);
}
