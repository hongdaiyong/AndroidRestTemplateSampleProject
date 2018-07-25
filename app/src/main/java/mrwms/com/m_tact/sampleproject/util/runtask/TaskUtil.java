package mrwms.com.m_tact.sampleproject.util.runtask;

import android.os.AsyncTask;
import android.os.Handler;


/**
 * Created by NeverMore on 2018/03/10.
 *
 * a util of request task
 *
 * example:new TaskUtil(()->Requester.getString(),Requester.getInt()){
 *     void onPostExecute() {
         Object[] results=getResults();
         String a= (String) results[0];
         int b= (int) results[1];
         }
 * }
 */

public abstract class TaskUtil {

    final static int TIMEOUT=10000;

    public TaskUtil(CustomSupplier<?> ... suppliers){
        this.suppliers=suppliers;
        results=new Object[suppliers.length];

        //it's not bad to request at this time...i think
//        execute();
    }

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
                                //UIThread onPostExecute()
                                TaskUtil.this.onPostExecute();
                            }
                        }

                    }

                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            Handler handler = new Handler();
            handler.postDelayed(()->{
                if(task.getStatus()== AsyncTask.Status.RUNNING)
                    task.cancel(true);
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
