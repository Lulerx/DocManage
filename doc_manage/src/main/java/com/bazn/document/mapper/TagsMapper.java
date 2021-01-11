package com.bazn.document.mapper;

import com.bazn.document.bean.Tags;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TagsMapper {

    @Insert("insert into tags(tagName) values(#{tagName})")
    int insertTag(Tags tags) throws SQLException;

    @Select("select * from tags order by id DESC")
    List<Tags> findAllTags() throws SQLException;

    @Select("select * from tags where tagName=#{tagName}")
    Tags findTagsByTagName(@Param("tagName") String tagName) throws SQLException;


    @Update("update tags set tagName=#{tagName} where id=#{id}")
    int updateTagName(Tags tags) throws SQLException;

    @Delete("delete from tags where id=#{id}")
    int deleteTagById(@Param("id") Long id) throws SQLException;

}
