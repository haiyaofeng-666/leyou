package com.leyou.client;

import com.leyou.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author shkstart
 * @create 2020-04-13 13:46
 */
@Component
@FeignClient("user-service")
public interface UserClient extends UserApi {
}
