package com.springbootj_zhiyuan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Active {
    String activityid;
    String title;
    String desc;
    String content;
    DateFormat entrystart = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
    DateFormat entryend = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
    DateFormat start = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
    DateFormat end = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
    String range;
    String poster;
    int applicantsalimit;
    String material;
    int vtime;
}
