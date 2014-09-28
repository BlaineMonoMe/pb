package org.pb.input.screens;

import org.pb.input.global.FilePaths;
import org.pb.inputOutputUtil.IOUtil;

/**
 * Created with IntelliJ IDEA.
 * User: AROS
 * Date: 9/28/14
 * Time: 1:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class PokerTableScreen {

    public static boolean exists() {
        return IOUtil.existPicture(FilePaths.POKER_TABLE_ID);
    }

}
