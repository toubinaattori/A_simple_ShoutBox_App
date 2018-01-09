package com.topias.shoutbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topias.shoutbox.dao.ShoutDAO;
import com.topias.shoutbox.model.Shout;

@Service
public class ShoutService {


    @Autowired
    private ShoutDAO shoutDAO;

    public Shout addShout( String nickname, String content ) {
        Shout newShout = new Shout( nickname, content );
        shoutDAO.save( newShout );
        return newShout;
    }

    public List<Shout> loadShouts() {
        List<Shout> oldShout = shoutDAO.load();
        return oldShout;
    }
}
