package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapperToBoardTest() {

        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1","First",trelloListDtoList);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        Assertions.assertEquals(trelloBoard.size(),1);
        Assertions.assertEquals(trelloBoard.get(0).getName(),"First");

    }

    @Test
    public void mapperToBoardsDtoTest() {

        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("1","First",trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assertions.assertEquals(trelloBoardDtoList.size(),1);
        Assertions.assertEquals(trelloBoardDtoList.get(0).getName(),"First");

    }
    @Test
    public void mapperToListTest() {

        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1","First",true);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        //Then
        Assertions.assertEquals(trelloLists.size(),1);
        Assertions.assertEquals(trelloLists.get(0).isClosed(),true);

    }
    @Test
    public void mapperToListDto() {

        //Given
        TrelloList trelloList = new TrelloList("1","First",true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //Then
        Assertions.assertEquals(trelloListDtos.size(),1);
        Assertions.assertEquals(trelloListDtos.get(0).getName(),"First");

    }
    @Test
    public void mapperToCardDto() {

        //Given
        TrelloCard trelloCard = new TrelloCard("First","ABC","XYZ","1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assertions.assertEquals(trelloCardDto.getName(),"First");
        Assertions.assertEquals(trelloCardDto.getDescription(),"ABC");
    }
    @Test
    public void mapperToCart() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("First","ABC","XYZ","1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assertions.assertEquals(trelloCard.getName(),"First");
        Assertions.assertEquals(trelloCard.getDescription(),"ABC");
    }
}
