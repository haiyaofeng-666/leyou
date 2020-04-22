package com.leyou.item.mapper;

import com.leyou.item.pojo.SpecParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author shkstart
 * @create 2020-02-24 12:21
 */
@Component
public interface SpecParamMapper extends Mapper<SpecParam>{
    @Delete("delete from tb_spec_param where id=#{id}")
    void deleteParam(Long id);
//    @Update("update tb_spec_param set numerical=#{specparam.numeric},unit=#{specparam.unit},generic=#{specparam.generic},searching=#{specparam.searching},segments=#{specparam.segments} where id=#{specpara.id}")
//    void updateParam(SpecParam specparam);
}
