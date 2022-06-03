package com.will.bookstoreapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The field Name is mandatory")
    @Length(min = 3, max = 30, message = "This filed must contain between 3 to 30 characters")
    private String name;

    @NotEmpty(message = "The field Description is mandatory")
    @Length(min = 8, max = 1000, message = "This filed must contain between 8 to 250 characters")
    private String description;

    private String imgLink;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Book> book = new ArrayList<>();

    public Category(Long id, String name, String description, String imgLink) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgLink = imgLink;
    }

    public Category() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
