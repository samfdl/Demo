package com.samfdl.demo.math;

import java.util.ArrayList;

public class Person {
    String[] nationality = {"英国人", "瑞典人", "丹麦人", "挪威人", "德国人"};
    String[] house = {"红房子", "白房子", "绿房子", "黄房子", "蓝房子"};
    String[] animal = {"狗", "鸟", "猫", "马", "鱼"};
    String[] drink = {"茶", "咖啡", "啤酒", "矿泉水", "牛奶"};
    String[] tobacco = {"Pall Mall 牌香烟", "Dunhill 牌香烟", "Blends 牌香烟",
            "Prince 牌香烟", "BlueMaster 牌香烟"};
    ArrayList<String> nationalityList = new ArrayList<>();
    ArrayList<String> houseList = new ArrayList<>();
    ArrayList<String> animalList = new ArrayList<>();
    ArrayList<String> drinkList = new ArrayList<>();
    ArrayList<String> tobaccoList = new ArrayList<>();

    int position;

    public Person() {
        for (int i = 0; i < 5; i++) {
            nationalityList.add(nationality[i]);
            houseList.add(house[i]);
            animalList.add(animal[i]);
            drinkList.add(drink[i]);
            tobaccoList.add(tobacco[i]);
        }
    }

    public void setNationalityList(String nationality) {
        nationalityList = new ArrayList<>();
        nationalityList.add(nationality);
    }

    public void setHouseList(String house) {
        houseList = new ArrayList<>();
        houseList.add(house);
    }

    public void setAnimalList(String animal) {
        animalList = new ArrayList<>();
        animalList.add(animal);
    }

    public void setDrinkList(String drink) {
        drinkList = new ArrayList<>();
        drinkList.add(drink);
    }

    public void setTobaccoList(String tobacco) {
        tobaccoList = new ArrayList<>();
        tobaccoList.add(tobacco);
    }
}