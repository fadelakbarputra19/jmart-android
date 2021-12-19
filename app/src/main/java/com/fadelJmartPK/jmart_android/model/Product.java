package com.fadelJmartPK.jmart_android.model;
/**
 * Model yang sesuai dengan backend Product
 * @author Muhammad Fadel Akbar Putra
 */
public class Product extends Serializable{
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;

    @Override
    public String toString() {
        return  name ;
    }
}
