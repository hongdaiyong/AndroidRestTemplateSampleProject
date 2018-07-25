package mrwms.com.m_tact.sampleproject.remote;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import mrwms.com.m_tact.sampleproject.remote.bean.SampleObject;

/**
 * Created by NeverMore on 2018/04/11.
 */
public class MainRequester extends BaseRequester{

    public static MainRequester newInstance(){
        return new MainRequester();
    }

    static final String url=MAIN+"";

    public SampleObject getSampleProject(int id){
        //set it as a request
        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        map.add("id",String.valueOf(id));

        //request
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /* all you should do is to put parameters to the map*/
        //SampleObject obj=post(url,map,SampleObject.class);
        //return obj;
        return null;
    }

}
