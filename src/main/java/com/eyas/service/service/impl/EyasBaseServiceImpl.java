package com.eyas.service.service.impl;

import com.eyas.parent.util.EmptyUtil;
import com.eyas.parent.util.ListUtil;
import com.eyas.service.constant.ErrCodeEnum;
import com.eyas.service.exception.EyasServiceException;
import com.eyas.service.middle.EyasBaseMiddle;
import com.eyas.service.service.EyasBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by yixuan on 2019/1/17.
 */
@Service
public class EyasBaseServiceImpl<Dto,D,Q> implements EyasBaseService<Dto,Q> {

    @Autowired
    private EyasBaseMiddle<D,Q> eyasBaseMiddle;


    private D dtoToD(Dto dto){
        Class<D> entityClass = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        D d;
        try {
            d = entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new EyasServiceException(ErrCodeEnum.NEW_INSTANCE_ERROR, "泛型创建对象有误!");
        }
        BeanUtils.copyProperties(dto, d);
        return d;
    }

    private Dto dToDto(D d){
        Class<Dto> entityClass = (Class<Dto>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Dto dto;
        try {
            dto = entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new EyasServiceException(ErrCodeEnum.NEW_INSTANCE_ERROR, "泛型创建对象有误!");
        }
        BeanUtils.copyProperties(d, dto);
        return dto;
    }

    @Override
    public List<Dto> queryByDifferentConditions(Q q){
        List<D> dList = this.eyasBaseMiddle.queryByDifferentConditions(q);
        List<Dto> dtoList = new ArrayList<>();
        dList.forEach(d->{
            Dto dto = this.dToDto(d);
            // dto转换
            if (EmptyUtil.isNotEmpty(dto)) {
                BeanUtils.copyProperties(d, dto);
            }
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insert(Dto dto){
        D d = this.dtoToD(dto);
        return this.eyasBaseMiddle.insert(d);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer batchInsert(List<Dto> dtoList){
        ListUtil.splitList(dtoList, 10).stream().forEach(dtoList1 -> {
            List<D> dList = new ArrayList<>();
            dtoList1.stream().forEach(dto -> {
                D d = this.dtoToD(dto);
                dList.add(d);
            });
            this.eyasBaseMiddle.batchInsert(dList);
        });
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer update(Dto dto){
        D d = this.dtoToD(dto);
        return this.eyasBaseMiddle.update(d);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateById(Dto dto){
        D d = this.dtoToD(dto);
        D d1 = this.eyasBaseMiddle.getInfoById(d);
        BeanUtils.copyProperties(dto, d1);
        return this.eyasBaseMiddle.update(d1);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteById(Long id) {
        return this.eyasBaseMiddle.deleteById(id);
    }

    @Override
    public Integer delete(Dto dto){
        D d = this.dtoToD(dto);
        return this.eyasBaseMiddle.delete(d);
    }

    @Override
    public List<Dto> query(Q q){
        List<D> dList = this.eyasBaseMiddle.query(q);
        List<Dto> dtoList = new ArrayList<>();
        for (D d:dList) {
            Dto dto = this.dToDto(d);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public Integer queryCount(Q q) {
        return this.eyasBaseMiddle.queryCount(q);
    }

    @Override
    public Dto getInfoById(String id){
        D d = this.eyasBaseMiddle.getInfoById(id);
        Dto dto = this.dToDto(d);
        if (EmptyUtil.isNotEmpty(d)){
            BeanUtils.copyProperties(d, dto);
        }
        return dto;
    }

    @Override
    public Dto getInfoById(Dto dto) {
        D d = this.dtoToD(dto);
        D d1 = this.eyasBaseMiddle.getInfoById(d);
        Dto dto1 = this.dToDto(d1);
        if (EmptyUtil.isNotEmpty(d)){
            BeanUtils.copyProperties(d, dto);
        }
        return dto1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer batchUpdate(Dto dto){
        D d = this.dtoToD(dto);
        return this.eyasBaseMiddle.batchUpdate(d);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer batchDelete(Dto dto){
        D d = this.dtoToD(dto);
        return this.eyasBaseMiddle.batchDelete(d);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateByDelete(Dto dto){
        D d = this.dtoToD(dto);
        // 先执行删除
        this.eyasBaseMiddle.delete(d);
        // 再执行新增
        return this.insert(dto);
    }
}
