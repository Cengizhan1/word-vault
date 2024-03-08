package com.cengizhanyavuz.wordvault.constants;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class PointConstants {

    //Initial points to be assigned to users
    public static final int USER_POINT = 100;
    //Initial points to be assigned to words+
    public static final int WORD_POINT = 100;

    public static final int USER_POINTS_TO_DECREASE = -1;
    public static final int USER_POINTS_TO_INCREASED = 1;
    public static final int WORD_POINTS_TO_DECREASED = -20;
    public static final int WORD_POINTS_TO_INCREASED = 20;
    public static final int GLOBAL_WORD_POINTS_TO_DECREASED = -1;
    public static final int GLOBAL_WORD_POINTS_TO_INCREASED = 1;
}
