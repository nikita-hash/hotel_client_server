package com.repository;

import com.config.DateBaseConfig;

public class BanListRepository extends DateBaseConfig {
    private static BanListRepository BanListRepository = new BanListRepository();

    public  static BanListRepository getBanListRepository(){
        return BanListRepository;
    }
}
