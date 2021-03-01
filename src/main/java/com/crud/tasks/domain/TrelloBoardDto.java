package com.crud.tasks.domain;

import lombok.Data;

@Data
public class TrelloBoardDto {
    private String name;
    private String id;

    public Boolean checkingWordInName(String word) {
        if(name.contains(word)) {
            return true;
        } else {
            return false;
        }

    }
}