package com.fadelJmartPK.jmart_android.model;

import java.util.Date;

/**
 * Model yang sesuai dengan backend invoice
 * @author Muhamamd Fadel Akbar Putra
 */
public class Invoice extends Serializable {
    public static enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED;
    }

    public static enum Rating{
        NONE,BAD,NEUTRAL,GOOD;
    }

    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
}
