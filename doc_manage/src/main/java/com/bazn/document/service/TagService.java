package com.bazn.document.service;

import com.bazn.document.bean.Tags;
import com.bazn.document.dto.Result;

import java.util.List;

public interface TagService {
    Result<List> findAllTags();

    int createTag(Tags tags);

    int updateTag(Tags tags);

    int deleteTag(Long id);
}
