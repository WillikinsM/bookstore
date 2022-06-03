package com.will.bookstoreapi.dtos;

import com.will.bookstoreapi.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class BookDTO {


    private Long id;
    @NotEmpty(message = "The field Title is mandatory")
    @Length(min = 3, max = 50, message = "This filed must contain between 3 to 50 characters")
    private String title;
    private String imgLink;


    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.imgLink = book.getImgLink();
    }

}

