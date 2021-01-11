package com.bazn.document.mapper;

import com.bazn.document.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("SELECT r1.name " +
            "FROM roles r1, roles_user r2 " +
            "WHERE r2.uid=#{id} AND r1.id=r2.rid")
    List<Role> getRolesByUid(@Param("id")Long id);

    int addRoles(@Param("roles") String[] roles, @Param("uid") Long uid);

}
