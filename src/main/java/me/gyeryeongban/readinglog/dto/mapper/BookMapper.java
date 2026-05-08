package me.gyeryeongban.readinglog.dto.mapper;

import me.gyeryeongban.readinglog.domain.Book;
import me.gyeryeongban.readinglog.dto.BookRequestDto;
import me.gyeryeongban.readinglog.dto.BookResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookResponseDto toDto(Book book);
    Book toEntity(BookRequestDto bookRequestDto);
}
