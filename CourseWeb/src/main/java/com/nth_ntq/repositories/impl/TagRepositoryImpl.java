/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nth_ntq.repositories.impl;

import com.nth_ntq.pojo.Tags;
import com.nth_ntq.repositories.TagRepository;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Repository
@Transactional
public class TagRepositoryImpl implements TagRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Tags> getTags() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Tags", Tags.class);
        return q.getResultList();
    }

    @Override
    public Tags findById(Long id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Tags.class, id); 
    }
}
