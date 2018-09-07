/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.model.bank;

import java.util.Calendar;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class BankEvent {

    private Float actualSum;
    private Float prevSum;
    private Calendar eventDate;
    private Float sumSpent;
    private Calendar insertDate;

    public BankEvent() {
    }

    public BankEvent(Float actualSum, Float prevSum, Calendar eventDate) {
        this.actualSum = actualSum;
        this.prevSum = prevSum;
        this.eventDate = eventDate;
    }

    public BankEvent(Float actualSum, Float prevSum, Calendar eventDate, Float sumSpent, Calendar insertDate) {
        this.actualSum = actualSum;
        this.prevSum = prevSum;
        this.eventDate = eventDate;
        this.sumSpent = sumSpent;
        this.insertDate = insertDate;
    }

    public Float getActualSum() {
        return actualSum;
    }

    public void setActualSum(Float actualSum) {
        this.actualSum = actualSum;
    }

    public Float getPrevSum() {
        return prevSum;
    }

    public void setPrevSum(Float prevSum) {
        this.prevSum = prevSum;
    }

    public Calendar getEventDate() {
        return eventDate;
    }

    public void setEventDate(Calendar savedDate) {
        this.eventDate = savedDate;
    }

    public Float getSumSpent() {
        return sumSpent;
    }

    public void setSumSpent(Float sumSpent) {
        this.sumSpent = sumSpent;
    }

    public Calendar getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Calendar insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public String toString() {
        return "BankEvent{" + "actualSum=" + actualSum + ", prevSum=" + prevSum + ", savedDate=" + eventDate + '}';
    }

}
