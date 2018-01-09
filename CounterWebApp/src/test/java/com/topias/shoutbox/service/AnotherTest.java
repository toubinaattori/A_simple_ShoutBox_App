package com.topias.shoutbox.service;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.topias.shoutbox.model.Shout;
import com.topias.shoutbox.rest.ShoutBoxRestService;


public class AnotherTest {



    @Test
    public void TestShoutConstruction() {
        Shout t = new Shout();
        t.setContent( "testi" );
        t.setNickname( "topias" );
        Assert.assertEquals( t.getNickname(), "topias" );
        Assert.assertEquals( t.getContent(), "testi" );
    }
    
    @Test
    public void TestEscaping() {
        ShoutBoxRestService t = new ShoutBoxRestService();
        Assert.assertNotEquals( t.escape( "<h1>moi</h1>" ), "<h1>moi</h1>" );
    }
    



}
