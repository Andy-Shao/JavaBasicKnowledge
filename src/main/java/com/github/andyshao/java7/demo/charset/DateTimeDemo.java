package com.github.andyshao.java7.demo.charset;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

import org.junit.Test;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 6, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class DateTimeDemo {

    @SuppressWarnings("unused")
    @Test
    public void parseWithPosition() {
        DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);
        Date date = new Date();
        String dateStr = format.format(date);
        String prefix = "== START ==";
        String toParse = prefix + dateStr + "== END ==";
        ParsePosition position = new ParsePosition(prefix.length());
        Date d = format.parse(toParse , position);
        int index = position.getIndex();
    }

    @Test
    public void trackFormatPosition() {
        DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);
        Date date = new Date();
        StringBuffer result = new StringBuffer();
        FieldPosition dayField = new FieldPosition(DateFormat.DAY_OF_WEEK_FIELD);
        format.format(date , result , dayField);
        result.substring(dayField.getBeginIndex() , dayField.getEndIndex());
    }
}
