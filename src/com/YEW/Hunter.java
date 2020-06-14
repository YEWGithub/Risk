package com.YEW;

/**
 * @description:
 * @author: 86177
 * @date: Created in 2020/6/14 11:46
 * @version:
 * @modified By:
 */
public class Hunter extends Creature{
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String weapon;


    int level;
    int exp;


    public Hunter(String name,String weapon){

    }

    public Hunter() {
        this.name = name;
        this.weapon = weapon;
        maxLife = 100;
        curLife = maxLife;
        isLive = true;
        attack = 25;
        defend = 8;
        level = 1;
        exp = 0;
        agile = 35;
        hideRate = 60;
    }

    public void fight(Monster monster){     //战斗
        if(monster.isLive){
            if(isLive){
                System.out.println("--------->"+name+"无情的拿起"+weapon+"杀向"+monster.type+"<----------------");
                monster.injured(this);
            }else{
                System.out.println("--------->"+"我们的主角"+name+"已经牺牲了"+"<----------------");
            }
        }else
        {
            System.out.println("拜托啊！这个怪物已经被你打死啦！");
        }
    }

    public void injured(Monster monster){   //掉血
        //增加躲避的判断
        if(monster.type == "吸血鬼"){
            if(GameUtil.hidden(this.agile,this.hideRate)){
                System.out.println("--------"+name+":躲避成功");
                show();
                fight(monster);
                return;
            }
            System.out.println("--------->"+name+":疼死了！"+"<---------");
            int lostLife = GameUtil.calLostLife(monster.attack, this.defend);
            curLife-=lostLife;
            if(curLife<0){
                curLife=0;
                died();
                return;
            }
            monster.curLife+=this.curLife/10;
            show();
            fight(monster);
        }else{
            if(GameUtil.hidden(this.agile,this.hideRate)){
                System.out.println("--------"+name+":躲避成功");
                show();
                fight(monster);
                return;
            }
            System.out.println("--------->"+name+":疼死了！"+"<---------");
            int lostLife = GameUtil.calLostLife(monster.attack, this.defend);
            curLife-=lostLife;
            if(curLife<0){
                curLife=0;
                died();
                return;
            }
            show();
            fight(monster);
        }
    }
    public void expAdd(Monster monster){
        this.exp+=monster.maxLife;
        int needExp = 0;
        for(int i=1;i<=level;i++){
            needExp+=i*50;
        }
        if(exp>=needExp){
            upgrade();
        }

    }
    public void upgrade(){
        attack+=4;
        defend+=3;
        maxLife+=20;
        curLife+=maxLife;
        level++;
        System.out.println("--------------------------分割线-------------------------");
        System.out.println("系统提示：升级啦，目前等级"+level+"血量"+curLife+"攻击力"+attack+"防御力"+defend);
    }
    public void died(){
        System.out.println("**********"+name+"被怪物咬死了"+"**********");
        isLive = false;
        show();
    }
    public void show(){
        System.out.println("---------->"+name+"  "+"生命值:"+curLife+"  "+
                "生命状态"+isLive+"   "+"等级"+level+"   "
                +"<-------------");
    }

}
