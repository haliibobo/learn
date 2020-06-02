package com.github.haliibobo.learn.quartz;

import java.text.ParseException;
import java.util.Date;
import org.apache.log4j.Logger;
import org.quartz.CronExpression;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-08-16 16:23
 * @description 定时器功能
 */
public class QuartzUtil {
    private final static Logger LOGGER = Logger.getLogger(QuartzUtil.class);

    public static boolean todayIsSatisfied(String cron){
        try {
            CronExpression cronExpression = new CronExpression(cron);
            return cronExpression.isSatisfiedBy(new Date());
        } catch (ParseException e) {
            LOGGER.warn(cron,e);
        }
        return false;
    }

    public static boolean isSatisfied(String cron, Date date){
        try {
            CronExpression cronExpression = new CronExpression(cron);
            return  cronExpression.isSatisfiedBy(date);
        } catch (ParseException e) {
            LOGGER.warn(cron,e);
        }
        return false;
    }

}
