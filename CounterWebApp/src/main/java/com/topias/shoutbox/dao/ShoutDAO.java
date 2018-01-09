package com.topias.shoutbox.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topias.shoutbox.model.Shout;

@Repository
public class ShoutDAO {

    @Autowired
    private SessionFactory bean;

    public void save( Shout shout ) {
        Session session = bean.openSession();
        session.beginTransaction();
        System.out.println(shout.getContent());
        session.save( shout );
        session.getTransaction().commit();
    }

    public List<Shout> load() {
        Session session = bean.openSession();
        Transaction t = session.beginTransaction();
        Query q = session.createQuery( "select shout From Shout shout order by id DESC" );
        q.setMaxResults( 3 );
        @SuppressWarnings( "unchecked" )
        List<Shout> resultList = q.list();
        t.commit();
        return resultList;
    }

}
