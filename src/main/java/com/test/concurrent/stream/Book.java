package com.test.concurrent.stream;

public class Book {
	private String name;
	private String author;
	private String publication;
	private Double price;
	
	
	
	public Book(String name, String author, String publication, Double price) {
		this.name = name;
		this.author = author;
		this.publication = publication;
		this.price = price;
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
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", publication=" + publication + ", price=" + price + "]";
	}

}
