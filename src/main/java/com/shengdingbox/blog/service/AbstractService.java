package com.shengdingbox.blog.service;

import java.util.List;

/**
 *
 * @author 周子斐 (17600004572@163.com)
 * @version 1.0
 * @website https://www.zhouzifei.com
 * @date 2019年7月16日
 * @since 1.0
 */
public interface AbstractService<T, PK> {

    T insert(T entity);

    default void insertList(List<T> entities) {

    }

    boolean removeByPrimaryKey(PK primaryKey);

    boolean updateSelective(T entity);

    T getByPrimaryKey(PK primaryKey);

    default List<T> listAll() {
        return null;
    }
}