package com.lht.utils.lhtutils.Activity;

import java.util.List;

/**
 * Created by lht on 2017/3/9.
 */

public class TestGaon {


    /**
     * result : 200
     * user_classid : 1
     * tabImage : [{"userid":"9331803","newsid":"49315","imgurl":"http://app.680.com/images/app/gz_index_ad1.jpg"},{"userid":"10393411","newsid":"49623","imgurl":"http://app.680.com/images/app/gz_index_ad2.jpg"}]
     * fuwushang_list : [{"imgurl":"http://fj.p.680.com/home/2016-5/16/s/2016051620204094124_8550078.jpg","goodpjv":"5","cjnum":"126","chengxindu":"5","goodval":"99.8%","shopname":"广州境亜视觉","goodpjper":"99.8","fanwei":"LOGO设计/VI设计/包装设计","userid":"8550078"},{"imgurl":"http://p9.680.com/home/2015-3/27/s/2015032714283649232_3011094.jpg","goodpjv":"5","cjnum":"256","chengxindu":"4","goodval":"100%","shopname":"台州诚世网络技术有限公司","goodpjper":"100","fanwei":"UI设计/整站建设/网页设计","userid":"3011094"},{"imgurl":"http://fj.p.680.com/home/2017-2/6/s/2017020616355603119_106883.jpg","goodpjv":"5","cjnum":"427","chengxindu":"4","goodval":"100%","shopname":"琳视觉","goodpjper":"100","fanwei":"LOGO设计/包装设计/字体设计","userid":"106883"}]
     * fuwu_list : [{"imgurl":"http://sp2.680.com/myfile/2016-4/19/22016041912524353843_9331803.jpg","fuwu_id":"45402","unit":"个","price":"￥800","cityname":"大连","deal_count":"1439","field_id":"1","fuwu_name":"LOGO设计/品牌logo/企业标志/资深设计师/助跑企业腾飞","rank":"黄金服务商"},{"imgurl":"http://sp2.680.com/myfile/2016-4/27/32016042722034216866_1312478.jpg","fuwu_id":"47742","unit":"个","price":"￥1500","cityname":"无锡","deal_count":"9","field_id":"1","fuwu_name":"精品服务企业画册/餐饮地产酒店精品画册/欢迎咨询","rank":"黄金服务商"},{"imgurl":"http://sp2.680.com/myfile/2016-4/27/32016042714252428987_3005669.jpg","fuwu_id":"47716","unit":"套起","price":"￥79","cityname":"深圳","deal_count":"26","field_id":"1","fuwu_name":"公司精品宣传册设计高端格调画册设计形象推广宣传册设计","rank":"黄金服务商"},{"imgurl":"http://sp2.680.com/myfile/2015-8/24/2015082420583925508_1312478.jpg","fuwu_id":"44725","unit":"套","price":"￥498","cityname":"无锡","deal_count":"7","field_id":"1","fuwu_name":"资深设计师设计/画册设计/封面设计特价","rank":"黄金服务商"},{"imgurl":"http://sp2.680.com/myfile/2016-4/27/32016042720464030730_1312478.jpg","fuwu_id":"47741","unit":"个","price":"￥500","cityname":"无锡","deal_count":"4","field_id":"1","fuwu_name":"特惠专场企业商业网站Logo设计/标志设计/商标设计","rank":"黄金服务商"},{"imgurl":"http://fj.p.680.com/myfile/2017-2/16/32017021616272792533_10411039.jpg","fuwu_id":"52382","unit":"套","price":"￥600","cityname":"广州","deal_count":"9","field_id":"1","fuwu_name":"卡通形象设计/品牌人物卡通形象设计/整体卡通形象设计企业","rank":"黄金服务商"},{"imgurl":"http://fj.p.680.com/myfile/2016-10/21/32016102116473345344_3231694.png","fuwu_id":"43510","unit":"起","price":"￥3800","cityname":"成都","deal_count":"24","field_id":"1","fuwu_name":"产品包装设计,茶业,食品包装设计","rank":"黄金服务商"},{"imgurl":"http://sp2.680.com/myfile/2015-8/24/2015082422341315904_1312478.jpg","fuwu_id":"44728","unit":"个","price":"￥99","cityname":"无锡","deal_count":"17","field_id":"1","fuwu_name":"定制名片设计/创意名片/个性名片","rank":"黄金服务商"}]
     * alipay_config : {"alipay_flag":"1","partner":"2088101950325505","seller":"680@680.com","public_key":"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB","private_key":"MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAO237yuEWhkhaSYLThF3SeXN19HB5GqEJzUZVJyyh8ucogwpkseCULOu+AbQ/ZLhUgJx9VmMOCRdHFF5RDzE6Sqvvxsa4x6ze2tFhEhgn/pN6co1obmHH8HxxQcU5jALzWX3KDuQAgTAVeCsButhe3f65DfWp9YAVC5UOevQX9/pAgMBAAECgYAPUkuYAm/MLtsYGKJvfcDFjv7aBiUyuFHOmlNZxif6d536KrPLKBsLebuCtPWjAeLHRZEKtH7p2RZ6Kda8E/TzK457URtuVeBpfETxFhaWaIM/T3UoSdZPWFzJBL0+TbRQBwtnBAE/GW4WrEwAz0gsRVJA7nWQoYUbU+8fqnEoAQJBAP/Wdgpoft5leMac3R0fXUNNDlUZftCeCjwWpChQvrp81ylWTCzDt39t1iyvge5Zhu2rC6RwNxgwxFtOilgXMlUCQQDt3of/lc9ErUI2RaPOrHZO4CN4gPjVnzjChZNRDYArVoNy8g179Gqqxhhp2I6Vf01TIYVddbyJuuCyd8TE1xNFAkEAxlEGe5b5EYhzEPKz7ElN0EfBHnJ+/VQk9uNOKPo+fu0bFK4Sqnikm6EW7ti1zX+UulNm2PcrnhLfgy/kydhhaQJBAJ+LM6KtI7FHmHJ4cS9tI4kx8vifYMc/nT1zhGTH7mCjDVWqnnq2bFkQt00/MBt7oCyS/jW6g3PJwW5SwMraCxUCQQDJXQANPqkqAOlaU57qkiU4nbUwvm5mIO23fHLLo7SSiCecFvEXN/1rgMr3osZs4sEN6D6S/7eW1XbqbMU4urnQ"}
     * weixinpay_config : {"appsecret":"51c56b886b5be869567dd389b3e5d1d6","appid":"wxfc34fb9efbdedeae","weixinpay_flag":"0","mchid":"1289720501","key":"d4624c36b6795d1d99dcf0547af5443d"}
     */

    private String result;
    private int user_classid;
    private AlipayConfigBean alipay_config;
    private WeixinpayConfigBean weixinpay_config;
    private List<TabImageBean> tabImage;
    private List<FuwushangListBean> fuwushang_list;
    private List<FuwuListBean> fuwu_list;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getUser_classid() {
        return user_classid;
    }

    public void setUser_classid(int user_classid) {
        this.user_classid = user_classid;
    }

    public AlipayConfigBean getAlipay_config() {
        return alipay_config;
    }

    public void setAlipay_config(AlipayConfigBean alipay_config) {
        this.alipay_config = alipay_config;
    }

    public WeixinpayConfigBean getWeixinpay_config() {
        return weixinpay_config;
    }

    public void setWeixinpay_config(WeixinpayConfigBean weixinpay_config) {
        this.weixinpay_config = weixinpay_config;
    }

    public List<TabImageBean> getTabImage() {
        return tabImage;
    }

    public void setTabImage(List<TabImageBean> tabImage) {
        this.tabImage = tabImage;
    }

    public List<FuwushangListBean> getFuwushang_list() {
        return fuwushang_list;
    }

    public void setFuwushang_list(List<FuwushangListBean> fuwushang_list) {
        this.fuwushang_list = fuwushang_list;
    }

    public List<FuwuListBean> getFuwu_list() {
        return fuwu_list;
    }

    public void setFuwu_list(List<FuwuListBean> fuwu_list) {
        this.fuwu_list = fuwu_list;
    }

    public static class AlipayConfigBean {
        /**
         * alipay_flag : 1
         * partner : 2088101950325505
         * seller : 680@680.com
         * public_key : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB
         * private_key : MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAO237yuEWhkhaSYLThF3SeXN19HB5GqEJzUZVJyyh8ucogwpkseCULOu+AbQ/ZLhUgJx9VmMOCRdHFF5RDzE6Sqvvxsa4x6ze2tFhEhgn/pN6co1obmHH8HxxQcU5jALzWX3KDuQAgTAVeCsButhe3f65DfWp9YAVC5UOevQX9/pAgMBAAECgYAPUkuYAm/MLtsYGKJvfcDFjv7aBiUyuFHOmlNZxif6d536KrPLKBsLebuCtPWjAeLHRZEKtH7p2RZ6Kda8E/TzK457URtuVeBpfETxFhaWaIM/T3UoSdZPWFzJBL0+TbRQBwtnBAE/GW4WrEwAz0gsRVJA7nWQoYUbU+8fqnEoAQJBAP/Wdgpoft5leMac3R0fXUNNDlUZftCeCjwWpChQvrp81ylWTCzDt39t1iyvge5Zhu2rC6RwNxgwxFtOilgXMlUCQQDt3of/lc9ErUI2RaPOrHZO4CN4gPjVnzjChZNRDYArVoNy8g179Gqqxhhp2I6Vf01TIYVddbyJuuCyd8TE1xNFAkEAxlEGe5b5EYhzEPKz7ElN0EfBHnJ+/VQk9uNOKPo+fu0bFK4Sqnikm6EW7ti1zX+UulNm2PcrnhLfgy/kydhhaQJBAJ+LM6KtI7FHmHJ4cS9tI4kx8vifYMc/nT1zhGTH7mCjDVWqnnq2bFkQt00/MBt7oCyS/jW6g3PJwW5SwMraCxUCQQDJXQANPqkqAOlaU57qkiU4nbUwvm5mIO23fHLLo7SSiCecFvEXN/1rgMr3osZs4sEN6D6S/7eW1XbqbMU4urnQ
         */

        private String alipay_flag;
        private String partner;
        private String seller;
        private String public_key;
        private String private_key;

        public String getAlipay_flag() {
            return alipay_flag;
        }

        public void setAlipay_flag(String alipay_flag) {
            this.alipay_flag = alipay_flag;
        }

        public String getPartner() {
            return partner;
        }

        public void setPartner(String partner) {
            this.partner = partner;
        }

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public String getPublic_key() {
            return public_key;
        }

        public void setPublic_key(String public_key) {
            this.public_key = public_key;
        }

        public String getPrivate_key() {
            return private_key;
        }

        public void setPrivate_key(String private_key) {
            this.private_key = private_key;
        }
    }

    public static class WeixinpayConfigBean {
        /**
         * appsecret : 51c56b886b5be869567dd389b3e5d1d6
         * appid : wxfc34fb9efbdedeae
         * weixinpay_flag : 0
         * mchid : 1289720501
         * key : d4624c36b6795d1d99dcf0547af5443d
         */

        private String appsecret;
        private String appid;
        private String weixinpay_flag;
        private String mchid;
        private String key;

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getWeixinpay_flag() {
            return weixinpay_flag;
        }

        public void setWeixinpay_flag(String weixinpay_flag) {
            this.weixinpay_flag = weixinpay_flag;
        }

        public String getMchid() {
            return mchid;
        }

        public void setMchid(String mchid) {
            this.mchid = mchid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public static class TabImageBean {
        /**
         * userid : 9331803
         * newsid : 49315
         * imgurl : http://app.680.com/images/app/gz_index_ad1.jpg
         */

        private String userid;
        private String newsid;
        private String imgurl;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getNewsid() {
            return newsid;
        }

        public void setNewsid(String newsid) {
            this.newsid = newsid;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }

    public static class FuwushangListBean {
        /**
         * imgurl : http://fj.p.680.com/home/2016-5/16/s/2016051620204094124_8550078.jpg
         * goodpjv : 5
         * cjnum : 126
         * chengxindu : 5
         * goodval : 99.8%
         * shopname : 广州境亜视觉
         * goodpjper : 99.8
         * fanwei : LOGO设计/VI设计/包装设计
         * userid : 8550078
         */

        private String imgurl;
        private String goodpjv;
        private String cjnum;
        private String chengxindu;
        private String goodval;
        private String shopname;
        private String goodpjper;
        private String fanwei;
        private String userid;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getGoodpjv() {
            return goodpjv;
        }

        public void setGoodpjv(String goodpjv) {
            this.goodpjv = goodpjv;
        }

        public String getCjnum() {
            return cjnum;
        }

        public void setCjnum(String cjnum) {
            this.cjnum = cjnum;
        }

        public String getChengxindu() {
            return chengxindu;
        }

        public void setChengxindu(String chengxindu) {
            this.chengxindu = chengxindu;
        }

        public String getGoodval() {
            return goodval;
        }

        public void setGoodval(String goodval) {
            this.goodval = goodval;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getGoodpjper() {
            return goodpjper;
        }

        public void setGoodpjper(String goodpjper) {
            this.goodpjper = goodpjper;
        }

        public String getFanwei() {
            return fanwei;
        }

        public void setFanwei(String fanwei) {
            this.fanwei = fanwei;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }

    public static class FuwuListBean {
        /**
         * imgurl : http://sp2.680.com/myfile/2016-4/19/22016041912524353843_9331803.jpg
         * fuwu_id : 45402
         * unit : 个
         * price : ￥800
         * cityname : 大连
         * deal_count : 1439
         * field_id : 1
         * fuwu_name : LOGO设计/品牌logo/企业标志/资深设计师/助跑企业腾飞
         * rank : 黄金服务商
         */

        private String imgurl;
        private String fuwu_id;
        private String unit;
        private String price;
        private String cityname;
        private String deal_count;
        private String field_id;
        private String fuwu_name;
        private String rank;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getFuwu_id() {
            return fuwu_id;
        }

        public void setFuwu_id(String fuwu_id) {
            this.fuwu_id = fuwu_id;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getDeal_count() {
            return deal_count;
        }

        public void setDeal_count(String deal_count) {
            this.deal_count = deal_count;
        }

        public String getField_id() {
            return field_id;
        }

        public void setField_id(String field_id) {
            this.field_id = field_id;
        }

        public String getFuwu_name() {
            return fuwu_name;
        }

        public void setFuwu_name(String fuwu_name) {
            this.fuwu_name = fuwu_name;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }
}
