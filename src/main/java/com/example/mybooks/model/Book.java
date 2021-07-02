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

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", user=" + user.getUsername() +
                '}';
    }
}
