/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.utils.parse;

import java.util.List;
import ua.anza.ukrsib.model.bank.UkrSibBankEvent;

/**
 *
 * @author andrey_zatvornitskiy
 */
public interface IBankeEventParser {

    public List<UkrSibBankEvent> getParsedBankEvents(String tableText);
    public Float getParsedString(String value);

}
