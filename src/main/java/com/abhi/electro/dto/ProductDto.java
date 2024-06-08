package com.abhi.electro.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class ProductDto {
	
private Long id;
	
	private String name;
	
	private Long price;
	
	
	private String description;
	
	private String categoryName;
	
	private Long quantity;
	
	
	

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	private byte[] byteimg;
	
	private Long categoryId;
	
	private MultipartFile img;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getByteimg() {
		return byteimg;
	}

	public void setByteimg(byte[] byteimg) {
		this.byteimg = byteimg;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public MultipartFile getImg() {
		return img;
	}

	public void setImg(MultipartFile img) {
		this.img = img;
	}
}
