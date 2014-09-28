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
    String basePath = "res" + separator + "data" + separator;

    public static final String CARDS_DIFFERENCES = basePath + "CardsDifferences.txt";
    public static final String STACK_DIFFERENCES = basePath + "stackDigitsDifferences.txt";
    public static final String CHAT = basePath + "CHAT.PNG";
    public static final String MY_NAME = basePath + "MY_NAME.PNG";
    public static final String ICON = basePath + "ICON.PNG";
    public static final String LITTLE_ICON = basePath + "LITTLE_ICON.PNG";
    public static final String CANCEL_EMPTY_TABLE = basePath + "CANCEL_EMPTY_TABLE.PNG";
    public static final String EMPTY_TABLE_CANCEL_BTN = basePath + "empty_table_cancel_btn.png";
    public static final String EMPTY_TABLE_MASSAGE = basePath + "empty_table_message.png";
    public static final String EMPTY_TABLE_SIT_BTN = basePath + "sit_at_empty_table_btn.png";
    public static final String BUY_IN_DISCONNECT_PROTECTION_MSG = basePath + "buy_in_disconnect_protection_msg.png";
    public static final String BUY_IN_STACK_SIZE_MSG = basePath + "buy_in_stack_size_msg.png";
    public static final String POKER_TABLE_ID = basePath + "main_poker_table_id.png";
}
