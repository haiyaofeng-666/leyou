package cn.itcast.elasticsearch.repository;

import cn.itcast.elasticsearch.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-03-03 15:35
 */
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    List<Item> findByPriceBetween(int a, int b);
}
