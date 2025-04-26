package com.example.SpannerIngetion.entity;


import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.Data;

@Data
@Table(name = "users")
public class User {
    @PrimaryKey
    @Column(name = "user_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    // Add other fields as per your JSON structure
}
