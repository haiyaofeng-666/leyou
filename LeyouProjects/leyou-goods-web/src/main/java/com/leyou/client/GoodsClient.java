package com.leyou.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author shkstart
 * @create 2020-03-05 16:02
 */
@Component
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {

}