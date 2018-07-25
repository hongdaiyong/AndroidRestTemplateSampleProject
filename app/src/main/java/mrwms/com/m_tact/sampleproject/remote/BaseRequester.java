package mrwms.com.m_tact.sampleproject.remote;

import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * Created by NeverMore on 2018/04/11.
 */
public class BaseRequester {

    protected final static String MAIN="";

    /**
     * return RestTemplate
     * as Requester is a singleton ,it can't be a field in constructor
     * @return
     */
    private final RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        converters.add(new StringHttpMessageConverter());
        converters.add(new FormHttpMessageConverter());
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());

        return restTemplate;
    }

    /**
     * post for array
     */
    protected  <T> T[] postForList(String url, MultiValueMap<String, String> parameters, Class<T[]> clazz) {
        RestTemplate restTemplate = getRestTemplate();
        // Add the String message converter
        try {
            T[] result = restTemplate.postForObject(url, parameters, clazz);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    protected <T> T post(String url, MultiValueMap<String,? super String> parameters, Class<T> clazz){
        RestTemplate restTemplate=getRestTemplate();
        try {
            T result = restTemplate.postForObject(url, parameters, clazz);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
