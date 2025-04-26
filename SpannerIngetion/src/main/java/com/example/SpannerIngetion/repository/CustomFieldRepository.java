package com.example.SpannerIngetion.repository;

import com.example.SpannerIngetion.entity.CustomField;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomFieldRepository extends SpannerRepository<CustomField, String> {
}
