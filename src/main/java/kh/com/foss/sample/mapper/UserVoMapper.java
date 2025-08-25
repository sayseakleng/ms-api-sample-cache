package kh.com.foss.sample.mapper;

import kh.com.foss.sample.dto.UserCreationInputDto;
import kh.com.foss.sample.dto.UserResultDto;
import kh.com.foss.sample.vo.request.UserCreationRequestVo;
import kh.com.foss.sample.vo.response.UserCreationResponseVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserVoMapper {
    UserCreationInputDto to(UserCreationRequestVo userCreationRequestVo);

    UserCreationResponseVo from(UserResultDto userResultDto);
}
