package com.home.service;

import org.springframework.stereotype.Service;

/**
 * Created by liyang on 27/1/2017.
 * liyang27@le.com;
 * only in letv.
 */
@Service
public class BillService {

    public String getBill(String name) {
        return name+".bill anniversary";
    }
}
