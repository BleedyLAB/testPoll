package com.test.service.mapper;

import com.test.domain.dto.PollDTO;
import com.test.entities.poll.Poll;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PollMapper {

    PollMapper INSTANCE = Mappers.getMapper( PollMapper.class);

    Poll pollDtoToPoll(PollDTO pollDTO);
    PollDTO pollToPollDto(Poll poll);
}
