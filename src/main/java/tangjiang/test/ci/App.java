package tangjiang.test.ci;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0
 * @author 唐江
 *
 */
public class App 
{
	static org.slf4j.Logger logger  = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) throws Exception
    {
//        System.out.println( "Hello World!" );
//        System.out.println(System.getProperty("p1"));
        HttpClientConnectionManager hcm = new BasicHttpClientConnectionManager();
        SSLContext sslContext = SSLContext.getDefault();
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,new NoopHostnameVerifier() );
        
        CloseableHttpClient  chc = HttpClientBuilder.create().setConnectionManager(hcm).setSSLSocketFactory(sslSocketFactory).build();
        HttpUriRequest request = new HttpGet(args[0]);
        HttpResponse res = chc.execute(request);
        logger.info(EntityUtils.toString(res.getEntity()));
    }
    
}
