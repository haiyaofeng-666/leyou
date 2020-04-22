package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @author shkstart
 * @create 2020-02-11 17:58
 */
@Component
public interface CategoryMapper extends Mapper<Category>,SelectByIdListMapper<Category,Long> {
    @Select("select * from tb_category  where id in (select category_id from tb_category_brand where brand_id = #{id}) ")
    List<Category> queryBrandByid(Long id);

    /**     * 根据品牌id查询商品分类     * @param bid     * @return     */
    @Select("SELECT * FROM tb_category WHERE id IN (SELECT category_id FROM tb_category_brand WHERE brand_id = #{bid})")
    List<Category> queryByBrandId(Long bid);
}
