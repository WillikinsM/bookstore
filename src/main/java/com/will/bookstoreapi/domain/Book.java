package com.will.bookstoreapi.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.ForeignKey;

@Entity
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "The field Title is mandatory")
	@Length(min = 3, max = 50, message = "This filed must contain between 3 to 50 characters")
	private String title;

	@NotEmpty(message = "The field Author Name is mandatory")
	@Length(min = 3, max = 30, message = "This filed must contain between 3 to 30 characters")
	private String authorName;

	@NotEmpty(message = "The field Name is mandatory")
	@Length(min = 4, max = 3000, message = "This filed must contain between 10 to 300 characters")
	private String text;
	
	
	private String imgLink;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_categoria"))
	private Category category;

	public Book() {
		super();
	}

	public Book(Integer id, String title, String authorName, String text, String imgLink, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.authorName = authorName;
		this.text = text;
		this.imgLink = imgLink;
		this.category = category;
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

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(id, other.id);
	}

}
