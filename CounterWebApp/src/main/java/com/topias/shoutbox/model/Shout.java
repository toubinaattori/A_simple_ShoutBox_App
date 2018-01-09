package com.topias.shoutbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "shout" )
public class Shout implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String nickname;

    @Column
    private String content;

    public Shout() {
        // TODO Auto-generated constructor stub
    }


    public Shout( String nickname, String content ) {
        this.nickname = nickname;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public void setNickname( String nickname ) {
        this.nickname = nickname;
    }

    public void setContent( String content ) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }

}
