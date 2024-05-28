package Util;

import javafx.scene.control.Button;

public class FXUtil
{
    private FXUtil()
    {
    }
    public static boolean isEmpty(String str)
    {
        return str == null || str.trim().isEmpty();
    }
}
