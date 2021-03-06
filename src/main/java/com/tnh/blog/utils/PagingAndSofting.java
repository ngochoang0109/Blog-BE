package com.tnh.blog.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagingAndSofting {
	public static Pageable getPageable(int pageNo, int pageSize, String field) {
		Pageable paging=PageRequest.of(pageNo, pageSize, Sort.by(field));
		return paging;
	}
}
