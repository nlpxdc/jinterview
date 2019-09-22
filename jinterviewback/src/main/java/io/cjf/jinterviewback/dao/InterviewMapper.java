package io.cjf.jinterviewback.dao;

import io.cjf.jinterviewback.dto.InterviewGetDTO;
import io.cjf.jinterviewback.dto.InterviewListDTO;
import io.cjf.jinterviewback.po.Interview;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface InterviewMapper {
    int deleteByPrimaryKey(Integer interviewId);

    int insert(Interview record);

    int insertSelective(Interview record);

    Interview selectByPrimaryKey(Integer interviewId);

    int updateByPrimaryKeySelective(Interview record);

    int updateByPrimaryKey(Interview record);

//    custom
    List<InterviewListDTO> selectRecent(@Param("fromTime") Date fromTime,
                                        @Param("userId") Integer userId);

    InterviewGetDTO selectById(@Param("interviewId") Integer interviewId);
}