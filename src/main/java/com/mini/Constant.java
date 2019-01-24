package com.mini;

import org.junit.Test;
import sun.applet.Main;

/**
 * @author hht
 * @create 2019-01-17 20:30
 */
public enum Constant {

    /**
     * 	public static final String waitPay = "waitPay";
     * 	public static final String waitDelivery = "waitDelivery";
     * 	public static final String waitConfirm = "waitConfirm";
     * 	public static final String waitReview = "waitReview";
     * 	public static final String finish = "finish";
     * 	public static final String delete = "delete";
     */

    SINGLEIMAGE("single","单图"),
    DETAILIMAGE("detail","细节图"),
    ORDER_WAITPAY("waitPay","订单待支付"),
    ORDER_WAITDELIVERY("waitDelivery","待发货"),
    ORDER_WAITCONFIRM("waitConfirm","待收货"),
    ORDER_WAITREVIEW("waitReview","待评价"),
    ORDER_FINISH("finish","订单完成"),
    ORDER_DELETE("delete","订单删除");


    private String word;
    private String desc;


    Constant(String word,String desc) {
         this.word = word;
         this.desc = desc;
    }

    //getEnum 获取枚举
    public static Constant getConstant(String word){
          for (Constant c:values()){
                if(word.equals(c.word)){
                    return c;
                }

          }
          return null;

    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String toString() {
        return word;
    }}
