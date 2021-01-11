package com.bazn.document.service.impl;

import com.bazn.document.bean.Tags;
import com.bazn.document.dto.Result;
import com.bazn.document.mapper.TagsMapper;
import com.bazn.document.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/20
 */
@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagsMapper tagsMapper;

    @Override
    public Result<List> findAllTags() {
        Result<List> result = new Result<>();
        try {
            List<Tags> list = tagsMapper.findAllTags();
            if (list.size() > 0){
                result.setStatus(200);
                result.setData(list);
                return result;
            }else {
                result.setStatus(-1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int createTag(Tags tags) {
        try {
            return tagsMapper.insertTag(tags);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int updateTag(Tags tags) {
        try {
            return tagsMapper.updateTagName(tags);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int deleteTag(Long id) {
        try {
            return tagsMapper.deleteTagById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
