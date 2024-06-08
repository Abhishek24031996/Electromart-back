package com.abhi.electro.services.admin.faq;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abhi.electro.dto.FAQDto;
import com.abhi.electro.entity.FAQ;
import com.abhi.electro.entity.Product;
import com.abhi.electro.repository.FAQRepository;
import com.abhi.electro.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {
	
	private final FAQRepository faqRepository;
	
	private final ProductRepository productRepository;

	public FAQServiceImpl(FAQRepository faqRepository, ProductRepository productRepository) {
		super();
		this.faqRepository = faqRepository;
		this.productRepository = productRepository;
	}
	
	public FAQDto postFAQ(Long productId, FAQDto faqDto) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent()) {
			FAQ faq = new FAQ();
			
			faq.setQuestion(faqDto.getQuestion());
			faq.setAnswer(faqDto.getAnswer());
			faq.setProduct(optionalProduct.get());
			
			return faqRepository.save(faq).getFAQDto();
		}
		return null;
	}

}
