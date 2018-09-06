package com.banking.mapper.user;

import com.banking.domain.user.RecUser;

public interface RecUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecUser record);

    int insertSelective(RecUser record);

    RecUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecUser record);

    int updateByPrimaryKey(RecUser record);
}