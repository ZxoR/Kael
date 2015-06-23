package kael.kael;

import android.content.Context;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by elkis on 21/06/15.
 */
public class Utils {
    public static void showToast(Context context, String text, int duration) {
        Toast.makeText(context, text, duration).show();
    }

    public static String DateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        return df.format(date);

    }

    public class RequestCodes {
        public static final int REQUEST_EDIT = 0;
        public static final int REQUEST_ADD = 1;
        public static final int REQUEST_RESULT = 2;
    }

    public class ResultCodes {
        public static final int EDITED_OK = 0;
        public static final int DELETED_OK = 1;
        public static final int RESULT_OK = 2;
    }
}
