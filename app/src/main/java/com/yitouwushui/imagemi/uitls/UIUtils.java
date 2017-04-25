package com.yitouwushui.imagemi.uitls;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ding on 2017/4/24.
 */

public class UIUtils {

    private static Toast toast;

    /**
     * 弹出提示消息
     * @param context
     * @param content
     */
    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(),
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
