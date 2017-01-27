package com.home.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyang on 27/1/2017.
 * liyang27@le.com;
 * only in letv.
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private BillService billService;

    public void regUser(String name) {
        log.info("save ok "+billService.getBill(name));
    }
}
