package com.springbootj_zhiyuan.service;

import com.github.pagehelper.PageHelper;
import com.springbootj_zhiyuan.entity.Active;
import com.springbootj_zhiyuan.mapper.ActiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ActiveService")
public class ActiveService {
    @Autowired
    ActiveMapper activeMapper;

    public List<Active> findByTitle(String title){return  activeMapper.findByTitle(title);}
    public Active findById(String activityid) {return activeMapper.findByid(activityid);}
    public int addActive(Active active) {return activeMapper.addActive(active.getTitle(),active.getDesc(),active.getContent()
    ,active.getEntrystart(),active.getEntryend(),active.getStart(),active.getEnd(),active.getRange(),active.getPoster(),
            active.getApplicantsalimit(),active.getMaterial(),active.getVtime());
    }
    public int updateActive(Active active){
        return  activeMapper.updateActive(active.getTitle(),active.getDesc(),active.getContent()
                ,active.getEntrystart(),active.getEntryend(),active.getStart(),active.getEnd(),active.getRange(),active.getPoster(),
                active.getApplicantsalimit(),active.getMaterial(),active.getVtime(),active.getActivityid());
    }
    public int deleteActive(String groupid){
        return activeMapper.deleteActive(groupid);
    }
    public List<Active> findByPage(int pageSize, int pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List<Active> page = activeMapper.findByPage();
        return page;
    }
}
