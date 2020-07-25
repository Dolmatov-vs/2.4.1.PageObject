package ru.netology.web.data;

import lombok.Value;

public class CardInfo {
    private CardInfo() {

    }

    @Value
    public static class DataCards {
        private String cards;
        private String value;
    }

    public static DataCards getFirstCard() {
        return new DataCards("5559000000000001", "**** **** **** 0001");
    }

    public static DataCards getSecondCard() {
        return new DataCards("5559000000000002", "**** **** **** 0002");
    }

    @Value
    public static class NonExistentCard {
        private String thirdCard;
    }
    public static NonExistentCard getNonExistentCard (DataCards thirdCard){
        return new NonExistentCard("5559000000000003");
    }


}
