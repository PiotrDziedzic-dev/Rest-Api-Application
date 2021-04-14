package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoardsTest() {

        //Given
        TrelloCard trelloCard = new TrelloCard("Random name","Random desc","Random pos","Random id");
        List<TrelloList> list = List.of(new TrelloList("2","TEST NAME",false));
        TrelloBoard trelloBoard = new TrelloBoard("1","FIRST NAME",list);
        List<TrelloBoard> trelloBoardList = List.of(trelloBoard);

        //When
        trelloValidator.validateCard(trelloCard);
        List<TrelloBoard> abc = trelloValidator.validateTrelloBoards(trelloBoardList);

        //Them
        Assertions.assertEquals(abc.size(),1);


    }

}
