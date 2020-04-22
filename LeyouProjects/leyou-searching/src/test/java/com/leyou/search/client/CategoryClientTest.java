package com.leyou.search.client;

import com.leyou.search.LeyouSearchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-03-05 17:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LeyouSearchApplication.class)
public class CategoryClientTest {

    @Autowired
    private CategoryClient categoryClient;

    @Test
    public void testQueryCategories() {
//        List<String> names = this.categoryClient.queryNamesByIds(Arrays.asList(1L, 2L, 3L));
       List<String> byIds = this.categoryClient.queryNamesByIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(byIds.toString());
        while (true) {
        break;
        }
    }
}