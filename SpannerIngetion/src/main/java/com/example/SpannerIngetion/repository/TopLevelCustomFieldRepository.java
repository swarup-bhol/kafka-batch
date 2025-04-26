package com.example.SpannerIngetion.repository;

import com.example.SpannerIngetion.entity.TopLevelCustomField;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopLevelCustomFieldRepository extends SpannerRepository<TopLevelCustomField, String> {
}
