package com.github.haliibobo.learn.finagle;


public class CatService {

    private CatService() {

    }

    private static final CatsDB DB = new CatsDB();

    public static Cat find(int id) {
        return DB.get(id);
    }

}
