package com.example.mybooks.model;

import javax.persistence.*;
import java.util.Objects;

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

    public Integer getId(){
        return id;
    }


    @Override
    public String toString()
    {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(notes, book.notes) && Objects.equals(user, book.user);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, title, author, notes, user);
    }
}
