package com.bazn.document.service.impl;

import com.bazn.document.bean.Document;
import com.bazn.document.mapper.DocumentMapper;
import com.bazn.document.service.DocumentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/13
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Resource
    private DocumentMapper docMapper;

    @Override
    public int saveDoc(Document document) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (document.getState() == 1){
            document.setPublishDate(timestamp);
        }
        document.setEditTime(timestamp);
        try {
            return docMapper.insertDoc(document);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Document> findAllDocs() {
        try {
            return docMapper.findAllDocs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Document> findAllDocsByState(int state) {
        try {
            return docMapper.findAllDocsByState(state);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Document findDocById(Integer id) {
        return docMapper.findDocById(id);
    }
}
