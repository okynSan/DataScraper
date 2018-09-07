/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.DAO.bankevent.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.anza.ukrsib.confige.jdbcconfig.MySqlConnection;
import ua.anza.ukrsib.model.bank.BankEvent;
import ua.anza.ukrsib.DAO.bankevent.IBankEventDao;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class BankEventDaoImpl implements IBankEventDao {

    @Override
    public void saveBankEvent(BankEvent bankEvent) {
        PreparedStatement st = null;
        try {
            st = MySqlConnection.getConnection().prepareStatement(" insert into bank_event (sum_spent, event_date, insert_date)"
                    + "value (?,?,?)");

            st.setDouble(1, bankEvent.getSumSpent());
            st.setDate(2, new java.sql.Date(bankEvent.getEventDate().getTime().getTime()));
            st.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));

            st.execute();

            if (st != null) {
                st.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(BankEventDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Float> getUnCheckedSums() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Float> sums = new ArrayList<>();
        try {
            st = MySqlConnection.getConnection().prepareStatement(""
                    + " select sum_spent"
                    + " from home.bank_event"
                    + " where datediff(insert_date,now()) = 0");

            rs = st.executeQuery();

            while (rs.next()) {
                sums.add(rs.getFloat(1));
            }
            return sums;

        } catch (SQLException ex) {
            Logger.getLogger(BankEventDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankEventDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankEventDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;

    }

    @Override
    public boolean isBankEventIfNotExists(BankEvent bankEvent) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = MySqlConnection.getConnection().prepareStatement(""
                    + " SELECT 1\n"
                    + " FROM home.bank_event\n"
                    + " where event_date = ? and sum_spent = ? ");

            DecimalFormat df = new DecimalFormat(".##");
            System.out.println();

            st.setDate(1, new java.sql.Date(bankEvent.getEventDate().getTime().getTime()));
            st.setString(2, df.format(bankEvent.getSumSpent()).replaceAll(",", "."));

            System.out.println(st);
            rs = st.executeQuery();
            System.out.println(rs.next());

            return rs.next() == false ? true : false;

        } catch (SQLException ex) {
            Logger.getLogger(BankEventDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankEventDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankEventDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
}
