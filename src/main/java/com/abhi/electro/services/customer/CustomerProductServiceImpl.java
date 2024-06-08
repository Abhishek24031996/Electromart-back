package com.abhi.electro.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abhi.electro.dto.ProductDetailDto;
import com.abhi.electro.dto.ProductDto;
import com.abhi.electro.entity.FAQ;
import com.abhi.electro.entity.Product;
import com.abhi.electro.entity.Review;
import com.abhi.electro.repository.FAQRepository;
import com.abhi.electro.repository.ProductRepository;
import com.abhi.electro.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {
	
	private final ProductRepository productRepository;

	private final FAQRepository faqRepository;
	
	private final ReviewRepository reviewRepository;


	public CustomerProductServiceImpl(ProductRepository productRepository, FAQRepository faqRepository, ReviewRepository reviewRepository) {
		super();
		this.productRepository = productRepository;
		this.faqRepository = faqRepository;
		this.reviewRepository = reviewRepository;
	}


	public List<ProductDto> getAllProducts(){
		List<Product> products = productRepository.findAll();
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	
	public List<ProductDto> searchProductByTitle(String name){
		List<Product> products = productRepository.findAllByNameContaining(name);
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	
	public ProductDetailDto getProductDetailById(Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent()) {
			List<FAQ> faqList = faqRepository.findAllByProductId(productId);
			List<Review> reviewList = reviewRepository.findAllByProductId(productId);
			
			ProductDetailDto productDetailDto = new ProductDetailDto();
			
			productDetailDto.setProductDto(optionalProduct.get().getDto());
			productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
			productDetailDto.setReviewDtoList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));
			
			return productDetailDto;
			
		}
		return null;
	}
}
