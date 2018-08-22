package com.samfdl.demo.math;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.samfdl.demo.R;

import java.util.ArrayList;
import java.util.List;

public class MathActivity extends AppCompatActivity {
    private String[] nationality = {"英国人", "瑞典人", "丹麦人", "挪威人", "德国人"};
    private String[] house = {"红房子", "白房子", "绿房子", "黄房子", "蓝房子"};
    private String[] animal = {"狗", "鸟", "猫", "马", "鱼"};
    private String[] drink = {"茶", "咖啡", "啤酒", "矿泉水", "牛奶"};
    private String[] tobacco = {"Pall Mall 牌香烟", "Dunhill 牌香烟", "Blends 牌香烟",
            "Prince 牌香烟", "BlueMaster 牌香烟"};
    private int[] position = {1, 2, 3, 4, 5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.nationality = "英国人";
        person.house = "红房子";
        personList.add(person);

//        for (int i = 0; i < 5; i++) {
//            Person person1 = new Person();
//            person.nationality = nationality[i];
//            personList.add(person1);
//        }

//        1	黄	挪威人	猫 			水		Dunhill 烟
//        2	蓝	丹		马			茶		Blends 牌香烟
//        3	红	英国人	鸟 			牛奶		Pall Mall 牌香烟
//        4	绿	德		鱼			咖啡		Prince 牌香烟
//        5	白	瑞		狗			啤		BlueMaster 牌香烟

//        据说有五个不同颜色的房间排成一排，每个房间里分别住着一个不同国籍的人，每个人都喝一种特定品牌的饮料，抽一种特定品牌的烟，养一种宠物，没有任意两个人抽相同品牌的香烟，或喝相同品牌的饮料，或养相同的宠物。
//
//        问题是谁在养鱼作为宠物？为了寻找答案，爱因斯坦给出了以下 15 条线索。
//
//        英国人住在红色的房子里；
//
//        瑞典人养狗作为宠物；
//
//        丹麦人喝茶；
//
//        绿房子紧挨着白房子，在白房子的左边；
//
//        绿房子的主人喝咖啡；
//
//        抽 Pall Mall 牌香烟的人养鸟；
//
//        黄色房子里的人抽 Dunhill 牌香烟；
//
//        住在中间那个房子里的人喝牛奶；
//
//        挪威人住在第一个房子里面；
//
//        抽 Blends 牌香烟的人和养猫的人相邻；
//
//        养马的人和抽 Dunhill 牌香烟的人相邻；
//
//        抽 BlueMaster 牌香烟的人喝啤酒；
//
//        德国人抽 Prince 牌香烟；
//
//        挪威人和住在蓝房子的人相邻；
//
//        抽 Blends 牌香烟的人和喝矿泉水的人相邻。
    }
}