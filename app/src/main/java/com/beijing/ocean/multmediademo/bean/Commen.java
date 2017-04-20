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

  public static final   String htmltext2= "<p>\r\n\t\"英美剧鲜肉系列\"之《少狼众美男》第三弹！本期要为大家介绍的，" +
            "就是第四季加入的角色Liam Dunbar的扮演者Dylan Sprayberry！\r\n</p>\r\n<p>\r\n\t<img src=\"" +
            "http://img.princeblog.com/img/20150807/20150807145715_74483.jpg\" alt=\"\" width=\"600\" height=\"" +
            "600\" title=\"\" align=\"\" /> \r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n<p>\r\n\t<s" +
            "pan>Dylan Sprayberry</span>于1998年7月7日出生于美国德州休斯敦，今年也就17岁，够鲜嫩吧！目前身高165厘米，本部落真心希望他" +
            "二十三猛一窜，好歹窜到175。\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n<p>\r\n\t<span style=\"line-height:1.5;\">看看他的颜值，" +
            "眼眸清澈如水：</span> \r\n</p>\r\n<p>\r\n\t<img src=\"http://img.princeblog.com/img/20150807/20150807143135_52152.gif\" a" +
            "" +
            "lt=\"\" width=\"200\" height=\"206\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/2015080" +
            "7143136_46795.gif\" alt=\"\" width=\"200\" height=\"206\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20" +
            "150807/20150807143139_77323.gif\" alt=\"\" width=\"200\" height=\"206\" title=\"\" align=\"\" /> \r\n</p>\r\n<p>\r\n\t<img src=\"http://img.princeblog.com/img/20150807/20150807143141_47716.gif\" alt=\"\" width=\"200\" height=\"206\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143143_26661.gif\" alt=\"\" width=\"200\" height=\"206\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143405_96855.gif\" alt=\"\" width=\"200\" height=\"206\" title=\"\" align=\"\" /> \r\n</p>\r\n<p>\r\n\t<img src=\"http://img.princeblog.com/img/20150807/20150807143436_97604.gif\" alt=\"\" width=\"300\" height=\"282\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143439_56819.gif\" alt=\"\" width=\"300\" height=\"282\" title=\"\" align=\"\" /> \r\n</p>\r\n<p>\r\n\t<img src=\"http://img.princeblog.com/img/20150807/20150807143524_47482.png\" alt=\"\" width=\"600\" height=\"814\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143526_42860.png\" alt=\"\" width=\"600\" height=\"911\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143528_10491.png\" alt=\"\" width=\"600\" height=\"401\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143530_31425.png\" alt=\"\" width=\"600\" height=\"896\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143532_21796.png\" alt=\"\" width=\"600\" height=\"398\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143533_62276.png\" alt=\"\" width=\"600\" height=\"901\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143535_14151.png\" alt=\"\" width=\"600\" height=\"399\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143536_61739.png\" alt=\"\" width=\"600\" height=\"898\" title=\"\" align=\"\" /> \r\n</p>\r\n<br />\r\n<p>\r\n\t2013年，Dylan在《超人：钢铁英雄》中饰演13岁时的超人克拉克·肯特，并在2014年的土星奖中获得\"年轻演员最佳表演奖\"提名。随后加入MTV热播青春科幻剧《少狼 第四季》，饰演被男主Scott救下咬伤转变成的狼人Liam Dunbar，并在第五季中成为常驻角色。最初Dylan试镜的角色是第三季中的小德里克，但因为当时年纪太小，并未成功。\r\n</p>\r\n<p>\r\n\t<span style=\"line-height:1.5;\">Dylan Sprayberry在<span>《超人：钢铁英雄》中的剧照：</span></span> \r\n</p>\r\n<p>\r\n\t<span style=\"line-height:1.5;\"><img src=\"http://img.princeblog.com/img/20150807/20150807143749_98220.jpg\" alt=\"\" width=\"600\" height=\"399\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143750_38194.jpg\" alt=\"\" width=\"600\" height=\"399\" title=\"\" align=\"\" /><br />\r\n</span> \r\n</p>\r\n<p>\r\n\t<span style=\"line-height:1.5;\"></span> \r\n</p>\r\n<p>\r\n\t<span><br />\r\n</span> \r\n</p>\r\n<p>\r\n\t<span>Dylan Sprayberry在《少狼》中的剧照：</span> \r\n</p>\r\n<p>\r\n\t<img src=\"http://img.princeblog.com/img/20150807/20150807144819_33623.gif\" alt=\"\" width=\"200\" height=\"200\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807144821_35194.gif\" alt=\"\" width=\"200\" height=\"200\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807144823_66929.gif\" alt=\"\" width=\"200\" height=\"200\" title=\"\" align=\"\" /> \r\n</p>\r\n<p>\r\n\t<img src=\"http://img.princeblog.com/img/20150807/20150807144825_78794.gif\" alt=\"\" width=\"200\" height=\"200\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807144826_60907.gif\" alt=\"\" width=\"200\" height=\"200\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807144828_90984.gif\" alt=\"\" width=\"200\" height=\"200\" title=\"\" align=\"\" /> \r\n</p>\r\n<p>\r\n\t<img src=\"http://img.princeblog.com/img/20150807/20150807144829_92538.gif\" alt=\"\" width=\"200\" height=\"200\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807144831_54918.gif\" alt=\"\" width=\"200\" height=\"200\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807145251_78725.gif\" alt=\"\" width=\"200\" height=\"200\" title=\"\" align=\"\" /> \r\n</p>\r\n<p>\r\n\t<img src=\"http://img.princeblog.com/img/20150807/20150807145335_49115.gif\" alt=\"\" width=\"300\" height=\"268\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807145346_98384.gif\" alt=\"\" width=\"300\" height=\"300\" title=\"\" align=\"\" /> \r\n</p>\r\n<p>\r\n\t<img src=\"http://img.princeblog.com/img/20150807/20150807145005_12372.gif\" alt=\"\" width=\"300\" height=\"300\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807145007_63172.gif\" alt=\"\" width=\"300\" height=\"300\" title=\"\" align=\"\" /> \r\n</p>\r\n<p>\r\n\t没错，Dylan所饰演的Liam在剧里全面负责卖萌、搞笑、打斗等等。看看男主对Dlyan的喜爱之情，恨不得Dylan是自己亲弟弟：\r\n</p>\r\n<p>\r\n\t<img src=\"http://img.princeblog.com/img/20150807/20150807145643_43314.png\" alt=\"\" width=\"600\" height=\"493\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807145644_81822.png\" alt=\"\" width=\"600\" height=\"904\" title=\"\" align=\"\" /> \r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n<p>\r\n\tDylan有个妹妹，名叫Ellery Sprayberry，也是名演员。看看兄妹二人的合照，果然家里的基因不错。\r\n</p>\r\n<p>\r\n\t<img src=\"http://img.princeblog.com/img/20150807/20150807143854_66207.png\" alt=\"\" width=\"600\" height=\"900\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143856_29568.png\" alt=\"\" width=\"600\" height=\"902\" title=\"\" align=\"\" /><img src=\"http://img.princeblog.com/img/20150807/20150807143858_11117.png\" alt=\"\" width=\"600\" height=\"404\" title=\"\" align=\"\"" +
            " /> \r\n</p>\r\n<p>\r\n\tPRINCEBLOG王子部落 原创文章\r\n</p>";



    public static final String htmltext1="<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<script>\n" +
            "function copyText()\n" +
            "{\n" +
            "document.getElementById(\"field2\").value=document.getElementById(\"field1\").value;\n" +
            "}\n" +
            "</script>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "Field1: <input type=\"text\" id=\"field1\" value=\"Hello World!\"><br>\n" +
            "Field2: <input type=\"text\" id=\"field2\"><br><br>\n" +
            "\n" +
            "<button onclick=\"copyText()\">复制文本</button>\n" +
            "\n" +
            "<p>当按钮被单击时触发函数。此函数把文本从 Field1 复制到 Field2 中。</p>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
}