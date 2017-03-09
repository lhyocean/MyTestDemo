package com.beijing.ocean.multmediademo.bean;

import android.graphics.Color;

import com.beijing.ocean.multimediademo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 2016/11/12.
 */
public class Commen {
    public static final String[] NAMES={
            "张三","李四","汪汪","哈哈哈","Xman","薛之谦","大老毛","猫猫","嘎嘎","我的名字很长吧"
    };
    public static final String[] PHOTOS = {
            "http://f.hiphotos.baidu.com/image/pic/item/faf2b2119313b07e97f760d908d7912396dd8c9c.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/4b90f603738da977c76ab6fab451f8198718e39e.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/902397dda144ad343de8b756d4a20cf430ad858f.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/a6efce1b9d16fdfa0fbc1ebfb68f8c5495ee7b8b.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/a71ea8d3fd1f4134e61e0f90211f95cad1c85e36.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/7dd98d1001e939011b9c86d07fec54e737d19645.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fecc3e83ef586034a85edf723d.jpg",
            "http://cdn.duitang.com/uploads/item/201309/17/20130917111400_CNmTr.thumb.224_0.png",
            "http://pica.nipic.com/2007-10-17/20071017111345564_2.jpg",
            "http://pic4.nipic.com/20091101/3672704_160309066949_2.jpg",
            "http://pic4.nipic.com/20091203/1295091_123813163959_2.jpg",
            "http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg",
            "http://pic6.nipic.com/20100330/4592428_113348099353_2.jpg",
            "http://pic9.nipic.com/20100917/5653289_174356436608_2.jpg",
            "http://img10.3lian.com/sc6/show02/38/65/386515.jpg",
            "http://pic1.nipic.com/2008-12-09/200812910493588_2.jpg",
            "http://pic2.ooopic.com/11/79/98/31bOOOPICb1_1024.jpg"};
    public static final String[] HEADIMG = {
            "http://img.wzfzl.cn/uploads/allimg/140820/co140R00Q925-14.jpg",
            "http://www.feizl.com/upload2007/2014_06/1406272351394618.png",
            "http://v1.qzone.cc/avatar/201308/30/22/56/5220b2828a477072.jpg%21200x200.jpg",
            "http://v1.qzone.cc/avatar/201308/22/10/36/521579394f4bb419.jpg!200x200.jpg",
            "http://v1.qzone.cc/avatar/201408/20/17/23/53f468ff9c337550.jpg!200x200.jpg",
            "http://cdn.duitang.com/uploads/item/201408/13/20140813122725_8h8Yu.jpeg",
            "http://img.woyaogexing.com/touxiang/nv/20140212/9ac2117139f1ecd8%21200x200.jpg",
            "http://p1.qqyou.com/touxiang/uploadpic/2013-3/12/2013031212295986807.jpg"};

    public static final String[] VIDEOURLS ={
            "http://mdd-images.oss-cn-beijing.aliyuncs.com/2016/12/06/907daa6e-3063-4830-9601-160263dd042a_1480993177785.mp4",
            "http://wirrorcdn.jikexueyuan.com/jiuye%2Fvideo%2Fandroidshow.mp4"
    };


    public static final String[] CONTENTS ={

            "我曾怀疑我走在沙漠中",
            "从不结果无论种什么梦",
            "才张开翅膀",
            "风却变沉默",
            "习惯伤痛能不能算收获",
            "庆幸的是我一直没回头",
            "终于发现",
            "真的是有绿洲",
            "每把汗流了",
            "生命变的厚重",
            "走出沮丧才看见新宇宙" ,
            "海阔天空在勇敢以后",
            "要拿执着将命运的锁打破",
            "冷漠的人",
            "谢谢你们曾经看轻我",
            "让我不低头更精彩的活",
            "凌晨的窗口",
            "失眠整夜以后",
            "看着黎明从云里抬起了头",
            "日落是沉潜",
            "日出是成熟",
            "只要是光一定会灿烂的",
            "海阔天空狂风暴雨以后",
            "转过头对旧心酸一笑而过",
            "最懂我的人谢谢一路默默的陪我",
            "让我拥有好故事可以说",
            "海阔天空狂风暴雨以后",
            "转过头对旧心酸一笑而过",
            "最懂我的人",
            "谢谢一路默默的陪我",
            "让我拥有",
            "好故事可以说",
            "看未来",
            "一步步来了"
            ,"海阔天空"
    };

    public static String getBaseUrl() {
        return BaseUrl;
    }
    public static void setBaseUrl(String baseUrl) {
        BaseUrl = baseUrl;
    }
    public static String BaseUrl="http:192.168.16.166";
    public static String AppUrl=BaseUrl+"好好学习";
    public static String myUrl=BaseUrl+"天天向上";

    public  static  final int[] Colors={
            R.color.color_select,R.color.beauty_divider_list,R.color.red,R.color.gray_normal,
            R.color.grey_light,R.color.btn_green_noraml,R.color.btn_blue_normal,R.color.yellow,
            R.color.colorPrimary,R.color.colorAccent,R.color.beauty_divider_list
    };

    public static List<String> creatURLs() {
        List<String> urls=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int position=new Random().nextInt(PHOTOS.length-1);
            urls.add(PHOTOS[position]);
        }
        return  urls;
    }

    public static int getRandomCor() {
        int cor= Color.BLUE;
        int random=new Random().nextInt(Colors.length-1);
        cor=Colors[random];
        return cor;
    }

    public static List<String> getTexts(int length) {
        List<String> urls=new ArrayList<>();

        if (length==0){
        for (int i = 0; i < CONTENTS.length-1; i++) {
            urls.add(CONTENTS[i]);
        }}else {
            for (int i = 0; i < length; i++) {
                urls.add(CONTENTS[i]);
            }}
        return urls;
    }

    public static List<UserInfo> getUsers() {
        int random=new Random().nextInt(6)+8;
        List<UserInfo> list=new ArrayList<>();
        for (int i = 0; i < random; i++) {

            UserInfo user=new UserInfo();
            user.setName(NAMES[new Random().nextInt(10)]);
            user.setDes(CONTENTS[new Random().nextInt(CONTENTS.length-2)]);
            user.setHeadurl(HEADIMG[new Random().nextInt(HEADIMG.length-2)]);
            list.add(user);
        }

        return list;
    }

    public static List<GoodBean> getGoods() {
        int random=new Random().nextInt(6)+8;
        List<GoodBean> list=new ArrayList<>();
        for (int i = 0; i < random; i++) {

            GoodBean user=new GoodBean();
            user.setGoodDes(CONTENTS[new Random().nextInt(CONTENTS.length-2)]);
            user.setGoodImg(HEADIMG[new Random().nextInt(HEADIMG.length-2)]);
            list.add(user);
        }

        return list;
    }

    public static List<GoodBean> getGoodsRandom(int ran) {
        int random=new Random().nextInt(ran)+1;
        List<GoodBean> list=new ArrayList<>();
        for (int i = 0; i < random; i++) {

            GoodBean user=new GoodBean();
            user.setGoodDes(CONTENTS[new Random().nextInt(CONTENTS.length-2)]);
            user.setGoodImg(HEADIMG[new Random().nextInt(HEADIMG.length-2)]);
            list.add(user);
        }

        return list;
    }
    public static String getRandomName() {

        String name="";
        name=CONTENTS[new Random().nextInt(CONTENTS.length-2)];
        return name;
    }

    public static List<GoodBean> getGoodRandom() {
        int random=new Random().nextInt(2);
        List<GoodBean> list=new ArrayList<>();
        for (int i = 0; i < random; i++) {

            GoodBean user=new GoodBean();
            user.setGoodDes(CONTENTS[new Random().nextInt(CONTENTS.length-2)]);
            user.setGoodImg(HEADIMG[new Random().nextInt(HEADIMG.length-2)]);
            list.add(user);
        }

        return list;
    }

}