package com.example.sony.ecommerce.Message;

/**
 * Created by SONY on 7/5/2016.
 */
public class TriggerGrid {
    public TriggerGrid(boolean isTrigger) {
        this.isTrigger = isTrigger;
    }

    public boolean isTrigger() {
        return isTrigger;
    }

    public void setTrigger(boolean trigger) {
        isTrigger = trigger;
    }

    boolean isTrigger;
}
