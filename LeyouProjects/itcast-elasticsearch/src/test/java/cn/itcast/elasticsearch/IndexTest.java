package cn.itcast.elasticsearch;

import cn.itcast.elasticsearch.pojo.Item;
import cn.itcast.elasticsearch.repository.ItemRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author shkstart
 * @create 2020-03-03 15:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItcastElasticsearchApplication.class)
public class IndexTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepository itemRepository;
    @Test
    public void testCreate(){
        // 创建索引，会根据Item类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(Item.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(Item.class);
    }
    @Test
    public void testQuery(){
        Iterable<Item> items = this.itemRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        items.forEach(item-> System.out.println(item));
    }
    @Test
    public void index() {
        Item item = new Item(1L, "小米手机2", " 手机",
                "小米", 3999.00, "http://image.leyou.com/13123.jpg");
        itemRepository.save(item);
    }
    @Test
    public void indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }
    @Test
    public void testFind(){
        Optional<Item> optional = this.itemRepository.findById(1l);
        System.out.println(optional.get());
    }
    @Test
    public void finByPrice(){
        List<Item> list = this.itemRepository.findByPriceBetween(2, 3000);
        System.out.println(list);
    }

//    -------------高级查询--------------
    @Test
    public void testMach(){
        //工具类：通过工具类可以构建高级查询
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "手机");
        Iterable<Item> items = this.itemRepository.search(queryBuilder);
        items.forEach(System.out::println);
    }
    @Test
    public void testNative(){
        //构建自定义查询构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //参数是查询条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("category","手机"));
        //分页
        queryBuilder.withPageable(PageRequest.of(1,2));
        //添加排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));


        //分页结果集
        Page<Item> itemPage = this.itemRepository.search(queryBuilder.build());
        System.out.println(itemPage.getTotalPages());//总页数
        System.out.println(itemPage.getTotalElements());//总记录条数
        //当前页记录
        itemPage.forEach(System.out::println);
    }
    @Test
    public void testNative1(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //添加聚合查询。 使用工具类AggregationBuilders 构建聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand").subAggregation(AggregationBuilders.avg("price_avg").field("price")));

        //添加结果集过滤，不包含任何字段，不需要普通结果集
        FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(new String[]{}, null);
        queryBuilder.withSourceFilter(fetchSourceFilter);

        //执行聚合查询，需要强转成子类，子类中才有getAggregation()方法
        AggregatedPage<Item> items = (AggregatedPage)this.itemRepository.search(queryBuilder.build());

        //根据聚合名称获取聚合结果集中的聚合对象，强转成子类，子类中有getBuckets()方法
        StringTerms brands = (StringTerms)items.getAggregation("brands");

        //获取聚合中的桶
        brands.getBuckets().forEach(bucket -> {
            //输出桶中的key
            System.out.print(bucket.getDocCount());
            System.out.print(bucket.getKeyAsString());
            //map:解析子聚合,子聚合结果集转成map结构，key：聚合名称。value：聚合内容
            Map<String, Aggregation> map = bucket.getAggregations().asMap();
            InternalAvg priceAvg = (InternalAvg)map.get("price_avg");
            double value = priceAvg.getValue();
            System.out.println(value);
            System.out.println("----");
        });
    }
}
