/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbctest;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import ua.anza.ukrsib.DAO.bankevent.impl.UkrSibBankEventDaoImpl;
import ua.anza.ukrsib.confige.jdbcconfig.MySqlConnection;
import ua.anza.ukrsib.model.bank.UkrSibBankEvent;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class ConnectionTest {

    Connection c = null;

    @Test
    public void connectionTest() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/home?serverTimezone=UTC",
                "root",
                "Okyn132465");

        DatabaseMetaData d = c.getMetaData();
        d.toString();

    }

    @Test
    public void testProjConnectionTest() {
        try {
            Connection co = MySqlConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void createANoteTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/home?serverTimezone=UTC",
                "root",
                "Okyn132465");

        PreparedStatement st = c.prepareStatement(" insert into bank_event (actual_sum, prev_sum, saved_date)"
                + "value (?,?,?)");

        UkrSibBankEvent bankEvent = new UkrSibBankEvent(1.2F, 0.5F, null);

        st.setFloat(1, bankEvent.getActualSum());
        st.setFloat(2, bankEvent.getPrevSum());
        st.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));

        st.execute();
    }

    @Test
    public void getUnCheckedStatementsTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/home?serverTimezone=UTC",
                "root",
                "Okyn132465");

        PreparedStatement st = null;
        ResultSet rs = null;
        List<UkrSibBankEvent> bankEvents = new ArrayList<>();
        try {
            st = c.prepareStatement(""
                    + " select \n"
                    + "    `actual_sum`,\n"
                    + "    `prev_sum`,\n"
                    + "    `event_date`,\n"
                    + "    `sum_spent` \n"
                    + " from home.bank_event"
                    + " where is_checked = 0"
                    + " ");

            rs = st.executeQuery();

            while (rs.next()) {
                Calendar cal = null;
//                cal.setTime(rs.getDate(3));

//                bankEvents.add(new UkrSibBankEvent(rs.getFloat(1), rs.getFloat(2), cal, rs.getFloat(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UkrSibBankEventDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        bankEvents.stream().forEach(el -> System.out.println(el.toString()));

    }

//    @Test
//    public void getUnCheckedSumsTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
//        PreparedStatement st = null;
//        ResultSet rs = null;
//        List<Float> sums = new ArrayList<>();
//        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/home?serverTimezone=UTC",
//                "root",
//                "Okyn132465");
//
//        try {
//            st = c.prepareStatement(""
//                    + " select sum_spent"
//                    + " from home.bank_event"
//                    + " where datediff(insert_date,now()) = -1");
//
//            rs = st.executeQuery();
//
//            while (rs.next()) {
//                sums.add(rs.getFloat(1));
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UkrSibBankEventDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        sums.stream().forEach(el -> System.out.println(el));
//    }
}
