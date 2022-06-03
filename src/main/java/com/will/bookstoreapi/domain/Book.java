package com.will.bookstoreapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The field Title is mandatory")
    @Length(min = 3, max = 50, message = "This filed must contain between 3 to 50 characters")
    private String title;

    @NotEmpty(message = "The field Author Name is mandatory")
    @Length(min = 3, max = 30, message = "This filed must contain between 3 to 30 characters")
    private String authorName;

    @NotEmpty(message = "The field Text is mandatory")
    @Length(min = 4, max = 3000, message = "This filed must contain between 10 to 300 characters")
    private String text;

    private String imgLink;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_category"))
    private Category category;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}