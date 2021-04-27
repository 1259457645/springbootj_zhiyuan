package com.springbootj_zhiyuan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Active {
    String activityid;
    String title;
    String desc;
    String content;
    DateFormat entrystart;
    DateFormat entryend;
    DateFormat start;
    DateFormat end;
    String range;
    String poster;
    int applicantsalimit;
    String material;
    int vtime;
}
