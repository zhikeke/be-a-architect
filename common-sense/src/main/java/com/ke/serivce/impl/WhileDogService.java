package com.ke.serivce.impl;

import com.ke.serivce.IDogService;

public class WhileDogService implements IDogService {
    @Override
    public void sleep() {
        System.err.println("While Dog Sleep");
    }
}
