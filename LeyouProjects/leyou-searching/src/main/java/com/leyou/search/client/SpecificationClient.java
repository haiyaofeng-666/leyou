package com.leyou.search.client;

import com.leyou.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author shkstart
 * @create 2020-03-05 16:57
 */
@Component
@FeignClient("item-service")
public interface SpecificationClient extends SpecificationApi {
}