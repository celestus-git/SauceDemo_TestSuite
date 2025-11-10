package Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogManager {

    private LogManager(){

    }

    public static Logger getLogger(Class<?> clazz){
        return LoggerFactory.getLogger(clazz);
    }
}
