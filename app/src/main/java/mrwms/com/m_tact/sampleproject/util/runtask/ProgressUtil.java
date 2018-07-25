package mrwms.com.m_tact.sampleproject.util.runtask;

import android.content.Context;

import dmax.dialog.SpotsDialog;
import mrwms.com.m_tact.sampleproject.R;

/**
 * Created by NeverMore on 2018/04/03.
 */
public class ProgressUtil {

    public static SpotsDialog newProgress(Context context) {
        SpotsDialog dialog = new SpotsDialog(context, R.style.CustomSpots);
        dialog.show();
        return dialog;
    }

}
