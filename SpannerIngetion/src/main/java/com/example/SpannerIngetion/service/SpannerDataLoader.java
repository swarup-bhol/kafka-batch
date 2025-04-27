package com.example.SpannerIngetion.service;

import com.example.SpannerIngetion.entity.TopLevelCustomField;
import com.example.SpannerIngetion.repository.TopLevelCustomFieldRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class SpannerDataLoader implements CommandLineRunner {

    private final TopLevelCustomFieldRepository repository;

    public SpannerDataLoader(TopLevelCustomFieldRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Load JSON from resources
        ClassPathResource resource = new ClassPathResource("json/events.json");
        InputStream inputStream = resource.getInputStream();
        JsonNode jsonArray = mapper.readTree(inputStream);

        List<TopLevelCustomField> topLevelList = new ArrayList<>();

        for (JsonNode node : jsonArray) {
            TopLevelCustomField topLevel = mapper.treeToValue(node, TopLevelCustomField.class);
             topLevel.setEventId(UUID.randomUUID().toString());
//            JsonNode customFieldsNode = node.get("custom_fields");
//            if (customFieldsNode != null && !customFieldsNode.isNull()) {
//                topLevel.setCustomField(mapper.treeToValue(customFieldsNode, com.example.SpannerIngetion.entity.CustomField.class));
//            }

            topLevelList.add(topLevel);
        }

        // Save all to Spanner
        repository.saveAll(topLevelList);

        System.out.println("✅ Successfully inserted " + topLevelList.size() + " records into Spanner!");
    }
}

