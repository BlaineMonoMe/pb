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
    String baseDataPath = "res" + separator + "data" + separator;
    String baseImagesPath = "res" + separator + "images" + separator;

    public static final String CARDS_DIFFERENCES = baseDataPath + "CardsDifferences.txt";
    public static final String STACK_DIFFERENCES = baseDataPath + "stackDigitsDifferences.txt";
    public static final String CHAT = baseImagesPath + "CHAT.PNG";
    public static final String MY_NAME = baseImagesPath + "MY_NAME.PNG";
    public static final String ICON = baseImagesPath + "ICON.PNG";
    public static final String LITTLE_ICON = baseImagesPath + "LITTLE_ICON.PNG";
    public static final String CANCEL_EMPTY_TABLE = baseImagesPath + "CANCEL_EMPTY_TABLE.PNG";
    public static final String EMPTY_TABLE_CANCEL_BTN = baseImagesPath + "empty_table_cancel_btn.png";
    public static final String EMPTY_TABLE_MESSAGE = baseImagesPath + "empty_table_message.png";
    public static final String EMPTY_TABLE_SIT_BTN = baseImagesPath + "sit_at_empty_table_btn.png";
    public static final String BUY_IN_DISCONNECT_PROTECTION_MSG = baseImagesPath + "buy_in_disconnect_protection_msg.png";
    public static final String BUY_IN_STACK_SIZE_MSG = baseImagesPath + "buy_in_stack_size_msg.png";
    public static final String POKER_TABLE_ID = baseImagesPath + "main_poker_table_id.png";
}
