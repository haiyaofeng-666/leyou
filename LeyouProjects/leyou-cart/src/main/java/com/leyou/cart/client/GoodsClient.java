package com.leyou.cart.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2020-04-15 17:02
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
