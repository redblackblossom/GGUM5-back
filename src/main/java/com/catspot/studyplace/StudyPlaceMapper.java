package com.catspot.studyplace;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudyPlaceMapper {

    StudyPlaceMapper INSTANCE = Mappers.getMapper(StudyPlaceMapper.class);

    StudyPlaceDto studyPlaceToStudyPlaceDto(StudyPlace studyPlace);
}
