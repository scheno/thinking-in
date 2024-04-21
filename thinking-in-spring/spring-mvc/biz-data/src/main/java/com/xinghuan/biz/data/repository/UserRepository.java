package com.xinghuan.biz.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Commons 会帮助接口生成动态代理对象 实现 Repository 透明化（SQL、NoSQL）
 * Spring Data Commons 支持自定义存储引擎
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see CrudRepository
 * @since
 */
@Repository
public interface UserRepository extends CrudRepository {

    // CRUD
}
