package com.ke.serivce.impl;

import com.ke.serivce.IDogService;

public class BlackDogService implements IDogService {
    @Override
    public void sleep() {
        System.err.println("Black Dog did not sleep");
    }
}
