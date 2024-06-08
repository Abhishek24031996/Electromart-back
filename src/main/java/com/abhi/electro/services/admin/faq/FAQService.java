package com.abhi.electro.services.admin.faq;

import com.abhi.electro.dto.FAQDto;

public interface FAQService {
	
	FAQDto postFAQ(Long productId, FAQDto faqDto);

}
