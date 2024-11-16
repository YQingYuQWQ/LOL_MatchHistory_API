package com.enums;

/*
大区映射
 */
public enum Regions {
    UNKNOWN("未知大区", "HN0_NEW"),
    IONIA("艾欧尼亚", "HN1"),
    BILGEWATER("比尔吉沃特", "TJ100"),
    ZUAN("祖安", "NJ100"),
    NOXUS("诺克萨斯", "GZ100"),
    BANDLE_CITY("班德尔城", "CQ100"),
    DEMACIA("德玛西亚", "TJ101"),
    PILTOVER("皮尔特沃夫", "NJ100"),
    WAR_ACADEMY("战争学院", "GZ100"),
    FRELJORD("弗雷尔卓德", "WT3_NEW"),
    MT_TARGON("巨神峰", "NJ100"),
    LACERATE("雷瑟守备", "GZ100"),
    UNYIELDING("无畏先锋", "TJ101"),
    JUDGMENT("裁决之地", "CQ100"),
    BLACK_ROSE("黑色玫瑰", "HN10"),
    SHADOW_ISLES("暗影岛", "GZ100"),
    SHURIMA("恕瑞玛", "TJ101"),
    IRON_SOLARI("钢铁烈阳", "CQ100"),
    CRYSTAL_SCAR("水晶之痕", "CQ100"),
    KINKOU("均衡教派", "NJ100"),
    TWISTED_TREELINE("扭曲丛林", "WT6"),
    EDUCATION("教育网专区", "NJ100"),
    YING_LIU("影流", "NJ100"),
    WATCH_SEA("守望之海", "NJ100"),
    SEA_OF_CONQUEST("征服之海", "GZ100"),
    KARAMANDA("卡拉曼达", "GZ100"),
    DRAGON_NEST("巨龙之巢", "TJ101"),
    PILTOVER_SECURITY("皮城警备", "CQ100"),
    BARON("男爵领域", "NJ100"),
    TOP_OF_CANYON("峡谷之巅", "BGP2"),
    REGION_1("联盟一区", "NJ100"),
    REGION_2("联盟二区", "GZ100"),
    REGION_3("联盟三区", "CQ100"),
    REGION_4("联盟四区", "TJ100"),
    REGION_5("联盟五区", "TJ101");

    private final String name;
    private final String code;

    Regions(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static String getCodeByName(String name) {
        for (Regions region : Regions.values()) {
            if (region.getName().equals(name)) {
                return region.getCode();
            }
        }
        return null;
    }

    public static String getNameByCode(String code) {
        for (Regions region : Regions.values()) {
            if (region.getCode().equals(code)) {
                return region.getName();
            }
        }
        return null;
    }
}
