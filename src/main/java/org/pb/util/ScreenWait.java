package org.pb.util;

/**
 * Created with IntelliJ IDEA.
 * User: AROS
 * Date: 9/28/14
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScreenWait {

    private long timeout;

    public ScreenWait(long timeout) {
        this.timeout = timeout;
    }

    public <T> void waitUntil(ExpectedCondition<T> expectedCondition) {
        long currentTime = System.currentTimeMillis();
        while (true) {
            if (expectedCondition.until()) {
                return;
            }
            if (System.currentTimeMillis() > currentTime + timeout) {
                throw new RuntimeException("The condition wasn't satisfied in time");
            }
        }
    }

}
