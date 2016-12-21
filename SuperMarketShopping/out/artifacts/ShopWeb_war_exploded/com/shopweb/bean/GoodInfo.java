package com.shopweb.bean;

/**
 * Created by Administrator on 2016/12/12.
 */
public class GoodInfo {
    private long product_id;
    private long bar_code;
    private String type;
    private String product_name;
    private String retail_price;
    private String product_standard;
    private String unit;
    private String purchase_price;
    private String expiration_date;
    private String repertory;

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public long getBar_code() {
        return bar_code;
    }

    public void setBar_code(long bar_code) {
        this.bar_code = bar_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(String retail_price) {
        this.retail_price = retail_price;
    }

    public String getProduct_standard() {
        return product_standard;
    }

    public void setProduct_standard(String product_standard) {
        this.product_standard = product_standard;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(String purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getRepertory() {
        return repertory;
    }

    public void setRepertory(String repertory) {
        this.repertory = repertory;
    }

    @Override
    public String toString() {
        return "{" +
                "product_id:" + product_id +
                ", bar_code:" + bar_code +
                ", type:'" + type + '\'' +
                ", product_name:'" + product_name + '\'' +
                ", retail_price:'" + retail_price + '\'' +
                ", product_standard:'" + product_standard + '\'' +
                ", unit:'" + unit + '\'' +
                ", purchase_price:'" + purchase_price + '\'' +
                ", expiration_date:'" + expiration_date + '\'' +
                ", repertory:'" + repertory + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return (int) this.getProduct_id() + this.product_name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GoodInfo) {
            GoodInfo gi = (GoodInfo) obj;
            if (this.getProduct_id() == gi.getProduct_id() && this.getProduct_name().equals(gi.getProduct_name())){
                return true;
            }else {
                return  false;
            }
        } else {
            return false;
        }
    }
}
