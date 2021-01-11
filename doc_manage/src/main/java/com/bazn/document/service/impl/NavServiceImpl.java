package com.bazn.document.service.impl;

import com.bazn.document.bean.Navigation;
import com.bazn.document.mapper.NavMapper;
import com.bazn.document.service.NavService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/26
 */
@Service
public class NavServiceImpl implements NavService {

    @Resource
    private NavMapper navMapper;

    @Override
    public List<Navigation> getAllNav() {
        List<Navigation> list = new ArrayList<>();

        List<Navigation> allNav = navMapper.findAllNav();
        for (Navigation nav : allNav) {
            List<Navigation> childList = navMapper.findChildByFid(nav.getId());
            if (childList.size() > 0) {
                nav.setChildren(childList);
            } else {
                nav.setChildren(null);
            }
            if (nav.getParent_id() == 0)
                list.add(nav);
        }

        return list;
    }
}
