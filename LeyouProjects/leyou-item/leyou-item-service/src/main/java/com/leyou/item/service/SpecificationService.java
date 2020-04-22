package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-02-24 11:51
 */
@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    /**
     * 根据分类id查询分组
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return this.groupMapper.select(specGroup);
    }
    @Autowired
    private SpecParamMapper paramMapper;
    @Transactional
    public void deleteParam(Long id) {

      this.paramMapper.deleteParam(id);
    }
    @Transactional
    public void updateParam(SpecParam specparam) {
        this.paramMapper.updateByPrimaryKeySelective(specparam);
    }

    /**
     * 根据gid查询规格参数
     * @param gid
     * @return
     */
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return this.specParamMapper.select(record);
    }

    public List<SpecGroup> querySpecsByCid(Long cid) {
        // 查询规格组
        List<SpecGroup> groups = this.queryGroupsByCid(cid);
        groups.forEach(g -> {
            // 查询组内参数
            g.setParams(this.queryParams(g.getId(), null, null, null));
        });
        return groups;
    }
    @Transactional
    public void saveParam(SpecParam specParam) {
        this.specParamMapper.insertSelective(specParam);
    }

    public void saveGroup(SpecGroup specGroup) {
        this.groupMapper.insertSelective(specGroup);
    }

    public void deleteGroup(Long gid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setId(gid);
        this.groupMapper.delete(specGroup);
    }

    public void updateGroup(SpecGroup specGroup) {
        this.groupMapper.updateByPrimaryKey(specGroup);
    }
}
