package com.will.bookstoreapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.will.bookstoreapi.domain.Category;

public class CategoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "The field Name is mandatory")
	@Length(min = 3, max = 30, message = "This filed must contain between 3 to 30 characters")
	private String name;

	@NotEmpty(message = "The field Description is mandatory")
	@Length(min = 8, max = 1000, message = "This filed must contain between 8 to 250 characters")
	private String description;

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(Category cat) {
		super();
		this.id = cat.getId();
		this.name = cat.getName();
		this.description = cat.getDescription();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
