package ru.skypro.lessons.auction.exeption;


public class LotNotFoundException extends RuntimeException{
    private final int id;
    public LotNotFoundException(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
