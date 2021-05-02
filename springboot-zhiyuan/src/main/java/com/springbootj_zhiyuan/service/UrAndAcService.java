package com.springbootj_zhiyuan.service;

import com.springbootj_zhiyuan.entity.UrAndAc;
import com.springbootj_zhiyuan.mapper.UrAndAcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UrAndAcService")
public class UrAndAcService {
    @Autowired
    UrAndAcMapper urAndAcMapper;
    public List<UrAndAc> findBoolean(String account,String activityid){
        return urAndAcMapper.findboolean(account, activityid);
    }
    public List<UrAndAc> findByAccount(String account){return urAndAcMapper.findByAccount(account);}
    public List<UrAndAc> findByActivityId(String activityid){return urAndAcMapper.findByActivityId(activityid);}
    public int addUrAndAc(String account,String activityid){
        return urAndAcMapper.addUrAndAc(account,activityid);
    }
    public int deleteUrAndAcAll(String account,String activityid){
        return urAndAcMapper.deleteUrAndAc(account,activityid);
    }
    public int deleteUrAndAcById(String activityid){ return urAndAcMapper.deleteByActivity(activityid);}
    public int deleteUrAndAcByAc(String account){return urAndAcMapper.deleteByAccount(account);}
}
