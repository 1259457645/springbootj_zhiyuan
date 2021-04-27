package com.springbootj_zhiyuan.service;


import com.springbootj_zhiyuan.entity.UrAndGp;
import com.springbootj_zhiyuan.mapper.UrAndGpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UrAndGpService")
public class UrAndGpService {
    @Autowired
    UrAndGpMapper urAndGpMapper;

    public UrAndGp findByAll(String account, String groupid) {return urAndGpMapper.findByAll(account,groupid);}
    public int deleteByGp(String groupid){return urAndGpMapper.deleteByGp(groupid);}
    public int deleteByAc(String account){return urAndGpMapper.deleteByAc(account);}
    public int addUrAndGp(String account,String groupid) {return urAndGpMapper.addUrAndGp(account,groupid);
    }
    public int deleteUrAndGp(String account,String groupid){
        return urAndGpMapper.deleteUrAndGp(account,groupid);
    }
}
