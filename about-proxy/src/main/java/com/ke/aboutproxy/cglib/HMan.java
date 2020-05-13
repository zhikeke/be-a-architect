package com.ke.aboutproxy.cglib;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HMan {

    private String name;

    private String age;

    private String sex;

    public void findLove() {
        System.err.println("大家好,我的要求是:胸大肤白屁股翘");
    }
}
