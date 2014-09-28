package org.pb.util;

/**
 * Created with IntelliJ IDEA.
 * User: AROS
 * Date: 9/28/14
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ExpectedCondition<T> {

    public abstract <T> T until();

}
