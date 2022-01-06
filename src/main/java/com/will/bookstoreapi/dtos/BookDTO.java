package com.will.bookstoreapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.will.bookstoreapi.domain.Book;

public class BookDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "The field Title is mandatory")
	@Length(min = 3, max = 50, message = "This filed must contain between 3 to 50 characters")
	private String title;

	private String imgLink;

	public BookDTO() {
		super();
	}

	public BookDTO(Book book) {
		super();
		this.id = book.getId();
		this.title = book.getTitle();
		this.imgLink = book.getImgLink();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
