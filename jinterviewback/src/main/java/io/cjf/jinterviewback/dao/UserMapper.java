package io.cjf.jinterviewback.dao;

import io.cjf.jinterviewback.po.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

//    custom
    User selectByMobile(@Param("mobile") String mobile);
}