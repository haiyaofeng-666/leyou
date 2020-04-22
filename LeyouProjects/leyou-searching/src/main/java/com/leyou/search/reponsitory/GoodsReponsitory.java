package com.leyou.search.reponsitory;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author shkstart
 * @create 2020-03-06 13:50
 */
@Component
public interface GoodsReponsitory extends ElasticsearchRepository<Goods,Long>{
}
