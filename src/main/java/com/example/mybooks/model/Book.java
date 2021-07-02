package com.example.mybooks.model;

import javax.persistence.*;

@Entity
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String author;
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String description)
    {
        this.notes = description;
    }

    public void setUser(User user)
    {
        this.user = user;
    }




}
