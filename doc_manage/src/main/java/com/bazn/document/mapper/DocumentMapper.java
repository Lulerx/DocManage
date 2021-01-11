package com.bazn.document.mapper;

import com.bazn.document.bean.Document;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface DocumentMapper {

    @Insert("insert into document(title, mdContent, htmlContent, summary,publishDate,editTime, state)" +
            "values(#{title}, #{mdContent},#{htmlContent}, #{summary},#{publishDate},#{editTime}, #{state})")
    int insertDoc(Document document) throws SQLException;

    @Select("select d.id, d.state, d.title, d.editTime, u.nickname, t.tagName " +
            "from document d, user u, tags t " +
            " where d.uid=u.id AND d.tid=t.id " +
            " order by d.editTime DESC ")
    List<Document> findAllDocs() throws SQLException;

    @Select("select d.id, d.state, d.title, d.editTime, u.nickname, t.tagName " +
            "from document d, user u, tags t " +
            " where d.state=#{state} AND d.uid=u.id AND d.tid=t.id " +
            " order by d.editTime DESC ")
    List<Document> findAllDocsByState(@Param("state") Integer state) throws SQLException;

    @Select("select * from document where id=#{id}")
    Document findDocById(Integer id);

    @Update({"<script>",
            "update document",
            "<set>",
            "<if test='title != null'>",
            "title=#{title},",
            "</if>",
            "<if test='mdContent != null'>",
            "mdContent=#{mdContent},",
            "</if>",
            "<if test='htmlContent != null'>",
            "htmlContent=#{htmlContent},",
            "</if>",
            "<if test='summary != null'>",
            "summary=#{summary},",
            "</if>",
            "<if test='state != null'>",
            "state=#{state},",
            "</if>",
            "</set>",
            "where id=#{id}",
    "</script>"})
    int updateDoc(Document document) throws SQLException;


    @Update("update document set state=2 where id=#{id}")
    int deleteDocById(@Param("id")int id) throws SQLException;

}
