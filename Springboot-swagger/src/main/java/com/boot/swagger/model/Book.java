package com.boot.swagger.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Schema of Model Object Book")
@XmlRootElement(name = "Book")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class Book {

	public Book() {
		super();
	}

	public Book(int id, String name, String author, String publisher, String language, float price) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.language = language;
		this.price = price;
	}

	@ApiModelProperty(notes = "Generated id")
	private int id;
	@ApiModelProperty(notes = "Name of the Book")
	private String name;
	@ApiModelProperty(notes = "Author of the Book")
	private String author;
	@ApiModelProperty(notes = "Publisher of the Book")
	private String publisher;
	@ApiModelProperty(notes = "Language of the Book")
	private String language;
	@ApiModelProperty(notes = "Price of the Book")
	private float price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", publisher=" + publisher + ", language="
				+ language + ", price=" + price + "]";
	}

}
