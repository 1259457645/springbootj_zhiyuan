package com.springbootj_zhiyuan.mapper;

import com.springbootj_zhiyuan.entity.Active;
import org.apache.ibatis.annotations.Param;

import java.text.DateFormat;
import java.util.List;

public interface ActiveMapper {
    List<Active> findByTitle(@Param("title") String title);
    Active findByid(@Param("activityid") String activityid);
    int addActive(@Param("title") String title, @Param("desc") String desc, @Param("content") String content,
                  @Param("entrystart") DateFormat entrystart, @Param("entryend") DateFormat entryend, @Param("start") DateFormat start,
                  @Param("end")DateFormat end,@Param("range") String range,@Param("poster") String poster,@Param("applicantsalimit") int applicantsalimit,
                  @Param("material") String material,@Param("vtime") int vtime);
    int updateActive(@Param("title") String title, @Param("desc") String desc, @Param("content") String content,
                  @Param("entrystart") DateFormat entrystart, @Param("entryend") DateFormat entryend, @Param("start") DateFormat start,
                  @Param("end")DateFormat end,@Param("range") String range,@Param("poster") String poster,@Param("applicantsalimit") int applicantsalimit,
                  @Param("material") String material,@Param("vtime") int vtime,@Param("activityid") String activityid);
    int deleteActive(@Param("activityid") String activityid);
    List<Active> findByPage();
}
