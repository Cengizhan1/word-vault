package com.cengizhanyavuz.wordvault.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PointConstants {

    @Value("${wordvault.user.point}")
    public static int USER_POINT;

    @Value("${wordvault.word.point}")
    public static int WORD_POINT;

    @Value("${wordvault.user.points.increased}")
    public static int USER_POINTS_TO_INCREASED;

    @Value("${wordvault.word.points.decreased}")
    public static int WORD_POINTS_TO_DECREASED;

    @Value("${wordvault.word.points.increased}")
    public static int WORD_POINTS_TO_INCREASED;

    @Value("${wordvault.max.day.count.for.update.word}")
    public static int MAX_DAY_COUNT_FOR_UPDATE_WORD;

    @Value("${wordvault.test.word.count}")
    public static int TEST_WORD_COUNT;
}
