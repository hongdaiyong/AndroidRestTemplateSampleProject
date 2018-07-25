package mrwms.com.m_tact.sampleproject.util.runtask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import dmax.dialog.SpotsDialog;
import mrwms.com.m_tact.sampleproject.R;

/**
 * Created by NeverMore on 2018/04/03.
 *
 * Almost the same as TaskUtil,
 * It will show a dialog which preventing ui operation when connecting the server
 */
public abstract class WatingTaskUtil {

    static int TIMEOUT=10000;

    private Context context;

    public WatingTaskUtil(Context context, CustomSupplier<?> ... suppliers){
        this.context=context;
        progress = ProgressUtil.newProgress(context);

        this.suppliers=suppliers;
        results=new Object[suppliers.length];
    }

    private SpotsDialog progress;

    //request
    private final CustomSupplier<?>[] suppliers;
    //response
    private final Object[] results;
    //the count of completed request
    private int overTaskCount=0;
    private boolean flag=false;

    public void execute(){
        for(int i=0;i<suppliers.length;i++){
            //if there is no cast again,it causes final keyword execption
            final int c=i;
            AsyncTask<Void,Void,Void> task = new AsyncTask<Void,Void,Void>(){
                @Override
                protected Void doInBackground(Void... voids) {
                    results[c]=suppliers[c].get();

                    //count the response
                    overTaskCount++;
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {

                    if(overTaskCount==suppliers.length){
                        synchronized (this){
                            if(!flag) {
                                flag=true;
                                WatingTaskUtil.this.progress.dismiss();
                                //UIThread onPostExecute
                                WatingTaskUtil.this.onPostExecute();
                            }
                        }

                    }

                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            Handler handler = new Handler();
            handler.postDelayed(()->{
                if(task.getStatus()== AsyncTask.Status.RUNNING){
                    task.cancel(true);
                    progress.dismiss();
                    CusToastUtil.info(context, R.string.tip_connect_error);
                }
            },TIMEOUT);
        }
    }

    //the response of request
    public Object[] getResults(){
        return results;
    }

    /**
     * the process after request
     */
    public abstract void onPostExecute();

}
