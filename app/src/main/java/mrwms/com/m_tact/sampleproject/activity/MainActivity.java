package mrwms.com.m_tact.sampleproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mrwms.com.m_tact.sampleproject.R;
import mrwms.com.m_tact.sampleproject.remote.MainRequester;
import mrwms.com.m_tact.sampleproject.remote.bean.SampleObject;
import mrwms.com.m_tact.sampleproject.util.runtask.WatingTaskUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_request=findViewById(R.id.btn_request);
        btn_request.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_request:
                int id=10;
                MainRequester requester=MainRequester.newInstance();
                new WatingTaskUtil(this,()->requester.getSampleProject(id)){
                    @Override
                    public void onPostExecute() {

                        SampleObject sobj= (SampleObject) getResults()[0];
                        doWithSampleObj(sobj);
                    }
                }.execute();
                break;
        }
    }

    void doWithSampleObj(SampleObject sampleObject){
        //the things after request
    }
}
