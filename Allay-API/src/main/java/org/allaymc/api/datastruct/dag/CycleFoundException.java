package org.allaymc.api.datastruct.dag;

/**
 * @author KocproZ
 * Created 2018-08-14 at 11:57
 */
public class CycleFoundException extends RuntimeException {

    public CycleFoundException(String message) {
        super(message);
    }

}
