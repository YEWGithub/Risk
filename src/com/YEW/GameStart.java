package com.YEW;

import java.util.Scanner;

/**
 * @description:
 * @author: 86177
 * @date: Created in 2020/6/14 11:45
 * @version:
 * @modified By:
 */
public class GameStart {
    Hunter hunter;
    Monster m1,m2,m3,m4,m5;

    public GameStart(){
        hunter = new Hunter();
        m1 = new Monster(3);
        m2 = new Monster(3);
        m3 = new Monster(3);
        m4 = new Monster(2);
        m5 = new Monster(4);
    }
    public void start(){
        System.out.println("------------------创建角色---------------------");
        Scanner input=new Scanner(System.in);
        Hunter hunter=new Hunter();
        System.out.println("请输入姓名");
        hunter.setName(input.next());
        hunter.show();

        while(hunter.isLive && (m1.isLive || m2.isLive || m3.isLive || m4.isLive || m5.isLive)){
            System.out.println("------------------对手寻找中---------------------");

            try{
                Thread.sleep(3000);
            }
            catch(Exception e)
            {}
            int ran = GameUtil.randomaRange(1,6);     // 产生随机数，随机寻找怪物进行战斗
            switch(ran){
                case 1: hunter.fight(m1);   break;
                case 2: hunter.fight(m2);   break;
                case 3: hunter.fight(m3);   break;
                case 4: hunter.fight(m4);   break;
                case 5: hunter.fight(m5);   break;
                default:System.out.println("选择错误，请重新选择");     break;   //rand到不存在的怪物;
            }
        }
        end();
    }
    public void end(){
        if(hunter.isLive == true){
            System.out.println("恭喜你！过关啦");
        }else{
            System.out.println("Game Over!");
        }
    }

}
