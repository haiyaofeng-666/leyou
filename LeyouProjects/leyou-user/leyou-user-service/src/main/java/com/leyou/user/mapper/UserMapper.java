package com.leyou.user.mapper;

import com.leyou.user.pojo.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author shkstart
 * @create 2020-04-09 11:07
 */
@Component
public interface UserMapper extends Mapper<User> {
}
