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
import java.util.Calendar;
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
            st = MySqlConnection.getConnection().prepareStatement(" insert into bank_event (actual_sum,sum_spent, event_date, insert_date)"
                    + "value (?,?,?,?)");

            st.setFloat(1, bankEvent.getActualSum() == null ? 0 : bankEvent.getActualSum());
            st.setDouble(2, bankEvent.getSumSpent());
            st.setDate(3, new java.sql.Date(bankEvent.getEventDate().getTime().getTime()));
            st.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));

            st.execute();

            if (st != null) {
                st.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(BankEventDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<BankEvent> getUnCheckedSums() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<BankEvent> bankEvents = new ArrayList<>();
        try {
            st = MySqlConnection.getConnection().prepareStatement(""
                    + " select \n"
                    + "    `actual_sum`,\n"
                    + "    `prev_sum`,\n"
                    + "    `event_date`,\n"
                    + "    `sum_spent`, \n"
                    + "     bank_event_id "
                    + " from home.bank_event"
                    + " where is_checked = 0"
                    + " order by insert_date desc ");
            rs = st.executeQuery();
            Calendar c = null;
//            c.setTime(rs.getDate(3));

            while (rs.next()) {
                bankEvents.add(new BankEvent(rs.getFloat(1), rs.getFloat(2), c, rs.getFloat(4), rs.getInt(5)));
            }
            return bankEvents;

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
            System.out.println(rs.next() == false ? true : false);

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

    public void setChecked(Integer bankEventId) {
        PreparedStatement st = null;
        try {
            st = MySqlConnection.getConnection().prepareStatement(" "
                    + " update bank_event set is_checked = 1 where bank_event_id = ?");

            st.setInt(1, bankEventId);
            st.execute();

            if (st != null) {
                st.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(BankEventDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
