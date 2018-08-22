package com.samfdl.demo.math;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.samfdl.demo.R;

import java.util.ArrayList;
import java.util.List;

//        据说有五个不同颜色的房间排成一排，每个房间里分别住着一个不同国籍的人，每个人都喝一种特定品牌的饮料，抽一种特定品牌的烟，养一种宠物，没有任意两个人抽相同品牌的香烟，或喝相同品牌的饮料，或养相同的宠物。
//
//        问题是谁在养鱼作为宠物？为了寻找答案，爱因斯坦给出了以下 15 条线索。

//    1.    挪威人住在第一个房子里面；
//    2.    挪威人和住在蓝房子的人相邻；
//    3.    住在中间那个房子里的人喝牛奶；
//    4.    绿房子紧挨着白房子，在白房子的左边；
//    5.    英国人住在红色的房子里；
//    6.    黄色房子里的人抽 Dunhill 牌香烟；
//    7.    瑞典人养狗作为宠物；
//    8.    丹麦人喝茶；
//    9.    绿房子的主人喝咖啡；
//    10    抽 Pall Mall 牌香烟的人养鸟；
//    11    抽 Blends 牌香烟的人和养猫的人相邻；
//    12    养马的人和抽 Dunhill 牌香烟的人相邻；
//    13    抽 BlueMaster 牌香烟的人喝啤酒；
//    14    德国人抽 Prince 牌香烟；
//    15    抽 Blends 牌香烟的人和喝矿泉水的人相邻。

//        1	黄	挪威人	猫 			水		Dunhill 烟
//        2	蓝	丹		马			茶		Blends 牌香烟
//        3	红	英国人	鸟 			牛奶		Pall Mall 牌香烟
//        4	绿	德		鱼			咖啡		Prince 牌香烟
//        5	白	瑞		狗			啤		BlueMaster 牌香烟

//    4.    绿房子紧挨着白房子，在白房子的左边；
//    5.    英国人住在红色的房子里；
//    6.    黄色房子里的人抽 Dunhill 牌香烟；
//    7.    瑞典人养狗作为宠物；
//    8.    丹麦人喝茶；
//    9.    绿房子的主人喝咖啡；
//    10    抽 Pall Mall 牌香烟的人养鸟；
//    11    抽 Blends 牌香烟的人和养猫的人相邻；
//    12    养马的人和抽 Dunhill 牌香烟的人相邻；
//    13    抽 BlueMaster 牌香烟的人喝啤酒；
//    14    德国人抽 Prince 牌香烟；
//    15    抽 Blends 牌香烟的人和喝矿泉水的人相邻。
public class MathActivity extends AppCompatActivity {
    List<Person> personList = new ArrayList<>();

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        text = findViewById(R.id.text);

        for (int i = 0; i < 5; i++) {
            Person person = new Person();
            person.position = i + 1;
            personList.add(person);
        }

        // 挪威人住在第一个房子里面；
        personList.get(0).setNationalityList("挪威人");
        for (int i = 1; i < 5; i++) {
            personList.get(i).nationalityList.remove("挪威人");
        }

        // 住在中间那个房子里的人喝牛奶；
        personList.get(2).setDrinkList("牛奶");
        for (int i = 0; i < 5; i++) {
            if (i != 2) {
                personList.get(i).drinkList.remove("牛奶");
            }
        }

        // 挪威人和住在蓝房子的人相邻；
        personList.get(1).setHouseList("蓝房子");
        for (int i = 0; i < 5; i++) {
            if (i != 1) {
                personList.get(i).houseList.remove("蓝房子");
            }
        }


        //    5.    英国人住在红色的房子里；

        out();
    }

    //  约束4.    绿房子紧挨着白房子，在白房子的左边；
    void func4() {
        for (int i = 0; i < 4; i++) {
            if (personList.get(i).houseList.contains("绿房子")
                    && !personList.get(i + 1).houseList.contains("白房子")) {
                personList.get(i).houseList.remove("绿房子");
                personList.get(i).houseList.remove("白房子");
                personList.get(i + 1).houseList.remove("绿房子");
                personList.get(i + 1).houseList.remove("白房子");
            }
        }
    }

    void out() {
        for (int i = 0; i < 5; i++) {
            System.out.println("***********************");
            Person person = personList.get(i);
            for (int j = 0; j < person.nationalityList.size(); j++) {
                System.out.print(person.nationalityList.get(j) + "  ");
            }
            System.out.println();
            for (int j = 0; j < person.houseList.size(); j++) {
                System.out.print(person.houseList.get(j) + "  ");
            }
            System.out.println();
            for (int j = 0; j < person.animalList.size(); j++) {
                System.out.print(person.animalList.get(j) + "  ");
            }
            System.out.println();
            for (int j = 0; j < person.drinkList.size(); j++) {
                System.out.print(person.drinkList.get(j) + "  ");
            }
            System.out.println();
            for (int j = 0; j < person.tobaccoList.size(); j++) {
                System.out.print(person.tobaccoList.get(j) + "  ");
            }
            System.out.println();
        }
    }
}