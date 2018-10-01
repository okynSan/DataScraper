/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.workflow.dbflow.DAO.bankevent.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import ua.anza.ukrsib.confige.jdbcconfig.MySqlConnection;
import ua.anza.ukrsib.model.bank.UkrSibBankEvent;
import ua.anza.ukrsib.workflow.dbflow.DAO.bankevent.IBankEventDao;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class UkrSibBankEventDaoImpl implements IBankEventDao {

    final static Logger daoLogger = Logger.getLogger("dblogger");

    @Override
    public void saveBankEvent(UkrSibBankEvent bankEvent) {
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
            try {
                MySqlConnection.getConnection().rollback();
            } catch (SQLException ex1) {
                daoLogger.error(ex1);
            }
            daoLogger.warn(ex);
        }
    }

    @Override
    public List<UkrSibBankEvent> getUnCheckedSums() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<UkrSibBankEvent> bankEvents = new ArrayList<>();
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
                    + " order by bank_event_id desc ");
            rs = st.executeQuery();
            Calendar c = null;

            while (rs.next()) {
                bankEvents.add(new UkrSibBankEvent(rs.getFloat(1), rs.getFloat(2), c, rs.getFloat(4), rs.getInt(5)));
            }
            return bankEvents;

        } catch (SQLException ex) {
            daoLogger.error(ex);
        } finally {

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    daoLogger.error(ex);
                }
            }
        }
        return null;

    }

    @Override
    public boolean isBankEventIfNotExists(UkrSibBankEvent bankEvent) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = MySqlConnection.getConnection().prepareStatement(""
                    + " SELECT distinct 1\n"
                    + " FROM `home`.bank_event\n"
                    + " where event_date = ? and sum_spent = ? ");

            DecimalFormat df = new DecimalFormat(".##");
            System.out.println();

            st.setDate(1, new java.sql.Date(bankEvent.getEventDate().getTime().getTime()));
            st.setString(2, df.format(bankEvent.getSumSpent()).replaceAll(",", "."));

            System.out.println(st);
            rs = st.executeQuery();

            if (rs.next()) {
                boolean f = rs.getInt(1) == 1 ? true : false;
                System.out.println("Flag is = " + f);
                System.out.println("Row has - " + rs.getInt(1));
                return f;
            }

        } catch (SQLException ex) {
            daoLogger.warn(ex);
            return false;
        } finally {

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    daoLogger.error(ex);
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
            MySqlConnection.getConnection().commit();

            if (st != null) {
                st.close();
            }

        } catch (SQLException ex) {

            try {
                MySqlConnection.getConnection().rollback();
            } catch (SQLException ex1) {
                daoLogger.error(ex1);
            }
            daoLogger.error(ex);
        }
    }
}
