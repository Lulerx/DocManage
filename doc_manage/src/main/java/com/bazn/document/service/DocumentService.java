package com.bazn.document.service;

import com.bazn.document.bean.Document;

import java.util.List;

public interface DocumentService {

    int saveDoc(Document document);

    List<Document> findAllDocs();

    List<Document> findAllDocsByState(int state);

    Document findDocById(Integer id);
}
