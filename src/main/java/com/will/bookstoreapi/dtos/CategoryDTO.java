package com.will.bookstoreapi.dtos;

import com.will.bookstoreapi.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Long id;

    @NotEmpty(message = "The field Name is mandatory")
    @Length(min = 3, max = 30, message = "This filed must contain between 3 to 30 characters")
    private String name;

    @NotEmpty(message = "The field Description is mandatory")
    @Length(min = 8, max = 1000, message = "This filed must contain between 8 to 250 characters")
    private String description;

    private String imgLink;


    public CategoryDTO(Category cat) {
        this.id = cat.getId();
        this.name = cat.getName();
        this.description = cat.getDescription();
        this.imgLink = cat.getImgLink();
    }


}
