package com.fadelJmartPK.jmart_android.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Model yang sesuai dengan backend payment
 * @author Muhammad Fadel Akbar Putra
 */
public class Payment extends Invoice{
    public Shipment shipment;
    public int productCount;
    public ArrayList<Record> history = new ArrayList<Record>();

    public  static class Record {
        public final Date date;
        public String massage;
        public Status status;


        public Record( Status status, String massage) {
            this.date = java.util.Calendar.getInstance().getTime();
            this.status = status;
            this.massage = massage;
        }

    }

    @Override
    public String toString() {
        return String.valueOf(this.productId);
    }
}
