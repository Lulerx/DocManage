package com.bazn.document.controller.admin;

import com.bazn.document.bean.Tags;
import com.bazn.document.dto.Result;
import com.bazn.document.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/20
 */
@RestController
@RequestMapping("/admin/tags")
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/getAllTags")
    public Result<List> getAllTags() {
        Result<List> result = tagService.findAllTags();
        if (result.getStatus() > 0) {
            return result;
        }
        result.setMsg("服务器出错啦~");
        return result;

    }

    @PostMapping("/createTag")
    public Result createTag(Tags tags) {
        Result result = new Result();
        int state = tagService.createTag(tags);
        if (state > 0) {
            result.setStatus(200);
        } else {
            result.setStatus(-1);
        }
        return result;
    }

    @PostMapping("/updateTag")
    public Result updateTag(Tags tags) {
        Result result = new Result();
        int state = tagService.updateTag(tags);
        if (state > 0) {
            result.setStatus(200);
        } else {
            result.setStatus(-1);
        }
        return result;
    }

    @PostMapping("/deleteTag")
    public Result deleteTag(@RequestParam("id") Long id) {
        Result result = new Result();
        int state = tagService.deleteTag(id);
        if (state > 0) {
            result.setStatus(200);
        } else {
            result.setStatus(-1);
        }
        return result;
    }
}