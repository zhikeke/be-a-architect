package com.ke.aboutproxy.jdk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HMan implements Person {

    private String name;

    private String age;

    private String sex;

    @Override
    public void findLove() {
        System.err.println("大家好,我的要求是:胸大肤白屁股翘");
    }
}
