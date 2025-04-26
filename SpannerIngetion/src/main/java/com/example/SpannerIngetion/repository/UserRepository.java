package com.example.SpannerIngetion.repository;


import com.example.SpannerIngetion.entity.User;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends SpannerRepository<User, String> {
}
