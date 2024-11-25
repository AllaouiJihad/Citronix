package com.example.citronix.web.VM.mapper;

import com.example.citronix.model.Farm;
import com.example.citronix.model.Field;
import com.example.citronix.web.VM.field.FieldRequestVm;
import com.example.citronix.web.VM.field.FieldResponseVm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    Field toEntity(FieldRequestVm fieldRequestVm);

    FieldResponseVm toResponseDTO(Field field);
}
