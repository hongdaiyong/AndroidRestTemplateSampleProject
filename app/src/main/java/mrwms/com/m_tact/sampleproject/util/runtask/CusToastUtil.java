package mrwms.com.m_tact.sampleproject.util.runtask;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import mrwms.com.m_tact.sampleproject.R;

/**
 * Created by NeverMore on 2018/04/02.
 */
public final class CusToastUtil {

    private static Toast toast;

    public static void warn(Context context, int resId) {
        if(toast!=null)
            toast.cancel();
        toast = Toast.makeText(context, resId, Toast.LENGTH_LONG);
        TextView v =toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.RED);
        toast.show();
    }

    public static void info(Context context, int resId , int times) {
        toast = Toast.makeText(context, resId, times);
        TextView v = toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }

    public static void info(Context context, int resId ){
        info(context,resId,Toast.LENGTH_LONG);
    }

    public static void info(Context context, String mes) {
        if(toast!=null)
            toast.cancel();
        toast = Toast.makeText(context, mes, Toast.LENGTH_LONG);
        TextView v = toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        toast.show();
    }

    public static void showListDialog(Context context, String title, String[] contentList,
                                      DialogInterface.OnClickListener onClickListener) {
        // setup the alert builder

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context,R.style.AppTheme_Dialog_List));
        builder.setTitle(title);

        // add a list
        builder.setItems(contentList, onClickListener);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public static void showSelectDialog(Context context, String title, String buttonPositive, String buttonNegative,
                                        DialogInterface.OnClickListener positive, DialogInterface.OnClickListener negative){

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context,R.style.AppTheme_Dialog));

        TextView textView = new TextView(context);
        // background of title
        textView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark));
        // textcolor of title
        textView.setTextColor(Color.WHITE);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        int paddingLeftRight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics());
        textView.setPadding(paddingLeftRight,0,0,0);
        // text
        textView.setText("OK");

        builder.setCustomTitle(textView);
        builder.setMessage(title);
//        builder.setTitle(title);

        // Add the buttons
        builder.setPositiveButton(buttonPositive,positive);
        builder.setNegativeButton(buttonNegative,negative );
        builder.show();
    }

}
