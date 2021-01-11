package com.bazn.document.controller;

import com.bazn.document.bean.Document;
import com.bazn.document.dto.Result;
import com.bazn.document.service.DocumentService;
import com.bazn.document.service.TagService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/13
 */
@RestController
@RequestMapping("/doc")
public class DocumentController {

    @Autowired
    private DocumentService docService;

    @Autowired
    private TagService tagService;


    @RequestMapping(value = "/saveDoc",method = RequestMethod.POST)
    public Result saveDoc(Document doc){
        Result data = new Result();
        int res = docService.saveDoc(doc);
        if (res > 0){
            data.setStatus(200);
            data.setMsg("保存成功");
        }else {
            data.setStatus(-1);
            data.setMsg("保存出错");
        }
        return data;
    }

    @GetMapping("getAllDocs")
    public Result<Integer> getAllDocs(){
        Result<Integer> result = new Result<>();
        List<Document> list = docService.findAllDocs();
        result.setData(list.size());
        return result;
    }

    @PostMapping("getPageDocs")
    public Result<List<Document>> getAllDocs(@RequestParam("currentPage") int currentPage,
                                             @RequestParam("pageSize") int pageSize){
        Result<List<Document>> result = new Result<>();
        PageHelper.startPage(currentPage, pageSize);
        List<Document> list = docService.findAllDocs();
        result.setData(list);
        return result;
    }

    @GetMapping("/getAllDocsByState/{state}")
    public Result<Integer> getAllPublishedDocs(@PathVariable("state") Integer state){
        Result<Integer> result = new Result<>();
        if (state != null) {
            List<Document> list = docService.findAllDocsByState(state);
            result.setData(list.size());
        }else {
            result.setMsg("error");
        }
        return result;
    }


    @PostMapping("/getPageDocsByState")
    public Result<List<Document>> getAllPublishedDocs(Integer state,
                                                      Integer currentPage,
                                                      Integer pageSize){
        Result<List<Document>> result = new Result<>();
        if (state != null) {
            // mybatis 分页插件
            PageHelper.startPage(currentPage, pageSize);
            List<Document> list = docService.findAllDocsByState(state);
            result.setData(list);
        }else {
            result.setMsg("error");
        }

        return result;
    }

    @GetMapping("getDocById/{id}")
    public Result<Document> getDocById(@PathVariable("id")Integer id){
        Result<Document> result = new Result<>();
        Document document = docService.findDocById(id);
        result.setData(document);
        return result;
    }

    @GetMapping("/getAllTags")
    public Result<List> getAllTags() {
        Result<List> result = tagService.findAllTags();
        if (result.getStatus() > 0) {
            return result;
        }
        result.setMsg("服务器出错啦~");
        return result;

    }

}
