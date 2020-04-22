package com.leyou.item.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-03-05 16:51
 */
@RequestMapping("category")
public interface CategoryApi {

    @GetMapping("names")
   List<String> queryNamesByIds(@RequestParam("ids") List<Long> ids);
}
