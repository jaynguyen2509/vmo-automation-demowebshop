package vmo.demowebshop.helper;


import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static Logger logger = LogManager.getLogger(Log.class);

    public static void info(String message, String... value){
        if (value.length == 0){
            logger.info(message);
        }else {
            logger.info(String.format(message,(Object[]) value));
        }
    }
    public static void error(String message){
        logger.info(message);
    }

    public static void allure(String message, String... value){
        if (value.length==0){
            info(message);
            step(message);
        }else {
            info(String.format(message,(Object[])value));
            step(String.format(message,(Object[])value));
        }
    }
    @Step("{msg}")
    private static void step(String msg){}
}
