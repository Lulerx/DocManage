package com.bazn.document.mapper;

import com.bazn.document.bean.Navigation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NavMapper {

    @Select("select * from sys_menu")
    List<Navigation> findAllNav();

    @Select("select * from sys_menu where id=#{id}")
    Navigation findNavById(@Param("id")Long id);

    @Select("select * from sys_menu where parent_id=#{id}")
    List<Navigation> findChildByFid(@Param("id") Long id);

}
