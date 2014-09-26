package org.pb.input.global;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: AROS
 * Date: 9/25/14
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FilePaths {

    String separator = File.separator;

    public static final String CARDS_DIFFERENCES = "res" + separator + "data" + separator + "CardsDifferences.txt";
    public static final String STACK_DIFFERENCES = "res" + separator + "data"  + separator + "stackDigitsDifferences.txt";
    public static final String CHAT = "res" + separator + "images" + separator + "CHAT.PNG";
    public static final String MY_NAME = "res" + separator + "images" + separator + "MY_NAME.PNG";
    public static final String ICON = "res" + separator + "images" + separator + "ICON.PNG";
    public static final String LITTLE_ICON = "res" + separator + "images"+ separator + "LITTLE_ICON.PNG";
    public static final String CANCEL_EMPTY_TABLE = "res" + separator + "images" + separator + "CANCEL_EMPTY_TABLE.PNG";
}
