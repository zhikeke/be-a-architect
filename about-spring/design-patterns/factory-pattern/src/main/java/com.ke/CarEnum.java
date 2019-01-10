package com.ke;

/**
 * 汽车类型枚举类
 */
public enum CarEnum {
    AUDI("Audi", "奥迪"),
    BENZ("BenZ", "奔驰"),
    BMW("BMW", "宝马");

    private String carType;
    private String brandName;

    CarEnum(String carType, String brandName) {
        this.carType = carType;
        this.brandName = brandName;
    }

    public String getCarType() {
        return carType;
    }

    public String getBrandName() {
        return brandName;
    }

}
