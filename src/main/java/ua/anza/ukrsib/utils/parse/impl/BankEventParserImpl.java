/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.utils.parse.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ua.anza.ukrsib.model.bank.UkrSibBankEvent;
import ua.anza.ukrsib.utils.parse.IBankeEventParser;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class BankEventParserImpl implements IBankeEventParser {

    @Override
    public List<UkrSibBankEvent> getParsedBankEvents(String tableText) {
        List<UkrSibBankEvent> bankEvents = new ArrayList<>();

        tableText = tableText.replaceAll("ДАТА ОПЕРАЦІЇ ОПИС ОПЕРАЦІЇСУМА ОПЕРАЦІЇ", "");

        Pattern p;// = Pattern.compile("(\\d{2}.\\d{2}.\\d{4}).*?(\\d{1,},\\d{2})");
        List<String> its = Arrays.asList(tableText.split("грн"));
        for (String res : its) {
            if (!res.matches("(.*)Зарахування заробітної плати та виплат прирівняних до неї(.*)")) {
                p = Pattern.compile("(\\d{2}.\\d{2}.\\d{4}).*?(\\d{1,},\\d{2})");
                Matcher m = p.matcher(res);
                while (m.find()) {
                    bankEvents.add(this.initBankeEvent("-" + m.group(2), m.group(1)));
                    break;

                }
            } else {
                p = Pattern.compile("(\\d{2}.\\d{2}.\\d{4}).*?(\\d{1,2}([\\s-])?\\d{1,}).*?(\\d{1,}([\\s-])\\d{3},\\d{1,})");
                Matcher m = p.matcher(res);
                while (m.find()) {
                    System.out.println(m.group(4));

                    System.out.println(m.group(3));
                    System.out.println(m.group(2));
                    System.out.println(m.group(1));
                    bankEvents.add(this.initBankeEvent(m.group(4).replaceAll(" ", ""), m.group(1)));
                    break;
                }
            }
        }
        return bankEvents;
    }

    private UkrSibBankEvent initBankeEvent(String eventSum, String eventDate) {

        UkrSibBankEvent event = new UkrSibBankEvent();
        event.setSumSpent(Float.parseFloat(eventSum.replaceAll(",", ".")));
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(df.parse(eventDate));
        } catch (ParseException ex) {
            Logger.getLogger(BankEventParserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        event.setEventDate(cal);
        event.setInsertDate(Calendar.getInstance());

        return event;
    }

    @Override
    public Float getParsedString(String value) {
        Pattern p = Pattern.compile("(\\d{1,}([\\s])\\d{1,},\\d{1,})|(\\d{1,},\\d{1,})");
        Matcher m = p.matcher(value);
        while (m.find()) {
            if (m.group(0) == null) {
                return Float.parseFloat(m.group(1).replaceAll(",", ".").replaceAll(" ", ""));
            } else {
                return Float.parseFloat(m.group(0).replaceAll(",", ".").replaceAll(" ", ""));
            }
        }
        return null;
    }

}
