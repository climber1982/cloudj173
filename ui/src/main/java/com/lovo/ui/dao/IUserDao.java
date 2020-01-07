package com.lovo.ui.dao;

import com.lovo.ui.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<UserEntity,String> {


}
