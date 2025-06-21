package org.myproject.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.myproject.dto.*;
import org.myproject.entity.*;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configure ModelMapper
        modelMapper.getConfiguration()
            .setSkipNullEnabled(true)
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setPreferNestedProperties(false)  // This is important for handling nested properties
            .setFieldMatchingEnabled(true);

        // Create type maps with full control over the mapping
        modelMapper.typeMap(TempleDanaAssignmentEntity.class, TempleDanaAssignmentDTO.class)
            .addMappings(mapper -> {
                mapper.map(src -> src.getId(), TempleDanaAssignmentDTO::setId);
                mapper.map(src -> src.getDate(), TempleDanaAssignmentDTO::setDate);
                mapper.map(src -> src.getIsConfirmed(), TempleDanaAssignmentDTO::setIsConfirmed);
                mapper.map(src -> src.getConfirmationDate(), TempleDanaAssignmentDTO::setConfirmationDate);
                mapper.map(src -> src.getFamily(), TempleDanaAssignmentDTO::setFamily);
                mapper.map(src -> src.getTempleDana(), TempleDanaAssignmentDTO::setTempleDana);
            });

        modelMapper.typeMap(TempleDanaEntity.class, TempleDanaDTO.class)
            .addMappings(mapper -> {
                mapper.map(src -> src.getId(), TempleDanaDTO::setTempleId);
                mapper.map(src -> src.getMinNumberOfFamilies(), TempleDanaDTO::setMinNumberOfFamilies);
                mapper.map(src -> src.getDana(), TempleDanaDTO::setDana);
                mapper.map(src -> src.getTemple(), TempleDanaDTO::setTempleId);
            });

        modelMapper.typeMap(TempleEntity.class, TempleDTO.class)
            .addMappings(mapper -> {
                mapper.map(src -> src.getId(), TempleDTO::setId);
                mapper.map(src -> src.getName(), TempleDTO::setName);
                mapper.map(src -> src.getAddress(), TempleDTO::setAddress);
                mapper.map(src -> src.getContactNumber(), TempleDTO::setContactNumber);
                mapper.map(src -> src.getEmail(), TempleDTO::setEmail);
                mapper.map(src -> src.getWebsite(), TempleDTO::setWebsite);
            });

        return modelMapper;
    }
}
