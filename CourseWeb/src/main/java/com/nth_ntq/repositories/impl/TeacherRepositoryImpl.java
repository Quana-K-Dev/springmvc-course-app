/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nth_ntq.repositories.impl;

import com.nth_ntq.pojo.Users;
import com.nth_ntq.repositories.TeacherRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TeacherRepositoryImpl implements TeacherRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Users> getTeachers() {
        Session s = factory.getObject().getCurrentSession();
        Query<Users> q = s.createQuery(
            "FROM Users WHERE userRole = 'ROLE_TEACHER'",
            Users.class
        );
        return q.getResultList();
    }

    @Override
    public Users getTeacherById(Long id) {
        Session s = factory.getObject().getCurrentSession();
        return s.get(Users.class, id);
    }

    @Override
    public void addOrUpdateTeacher(Users u) {
        Session s = factory.getObject().getCurrentSession();
        if (u.getId() == null)
            s.persist(u);
        else
            s.merge(u);
    }

    @Override
    public void deleteTeacher(Long id) {
        Session s = factory.getObject().getCurrentSession();
        Users u = s.get(Users.class, id);
        if (u != null) s.remove(u);
    }
}