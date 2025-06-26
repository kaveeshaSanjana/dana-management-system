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

        modelMapper.getConfiguration()
            .setSkipNullEnabled(true)
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        // Create empty type maps first
        modelMapper.createTypeMap(TempleDanaAssignmentEntity.class, TempleDanaAssignmentDTO.class);
        modelMapper.createTypeMap(TempleDanaAssignmentDTO.class, TempleDanaAssignmentEntity.class);
        modelMapper.createTypeMap(FamilyDTO.class, FamilyEntity.class);
        modelMapper.createTypeMap(FamilyEntity.class, FamilyDTO.class);
        modelMapper.createTypeMap(MemberDTO.class, MemberEntity.class);
        modelMapper.createTypeMap(MemberEntity.class, MemberDTO.class);

        // Configure TempleDanaAssignment mapping
        modelMapper.getTypeMap(TempleDanaAssignmentDTO.class, TempleDanaAssignmentEntity.class)
            .addMappings(mapper -> {
                mapper.skip(TempleDanaAssignmentEntity::setId);  // Skip ID mapping
                mapper.map(TempleDanaAssignmentDTO::getDate, TempleDanaAssignmentEntity::setDate);
                mapper.map(TempleDanaAssignmentDTO::getIsConfirmed, TempleDanaAssignmentEntity::setIsConfirmed);
                mapper.map(TempleDanaAssignmentDTO::getConfirmationDate, TempleDanaAssignmentEntity::setConfirmationDate);
            });

        // Configure Family mapping
        modelMapper.getTypeMap(FamilyDTO.class, FamilyEntity.class)
            .addMappings(mapper -> {
                mapper.skip(FamilyEntity::setId);  // Skip ID mapping
                mapper.map(FamilyDTO::getFamilyName, FamilyEntity::setFamilyName);
                mapper.map(FamilyDTO::getAddress, FamilyEntity::setAddress);
                mapper.map(FamilyDTO::getTelephone, FamilyEntity::setTelephone);
            });

        // Member mapping
        modelMapper.getTypeMap(MemberDTO.class, MemberEntity.class)
            .addMappings(mapper -> {
                mapper.skip(MemberEntity::setId);  // Skip ID mapping
                mapper.map(MemberDTO::getName, MemberEntity::setName);
                mapper.map(MemberDTO::getNic, MemberEntity::setNic);
                mapper.map(MemberDTO::getPhoneNumber, MemberEntity::setPhoneNumber);
                mapper.map(MemberDTO::getAddress, MemberEntity::setAddress);
            });

        return modelMapper;
    }
}
