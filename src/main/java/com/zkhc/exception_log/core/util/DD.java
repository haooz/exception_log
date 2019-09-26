package com.zkhc.exception_log.core.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Component
public class DD {

    // 钉钉消息
    private static final String LOCAL_TEST_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=";

    private static String TOKEN;
    private static String DEV_TOKEN;
    private static String PRO_TOKEN;
    private static String ALI_MSG_TOKEN;
    private static String TEST_TOKEN;
    private static String addUrl;
    private static String showUrl;
    @Autowired
    private Environment environment2;

    private static Environment environment;

    @PostConstruct
    public void init() {
        DD.environment = this.environment2;
    }

    @Value("${dd.access-token}")
    public void setTOKEN(String TOKEN) {
        DD.TOKEN = LOCAL_TEST_TOKEN+TOKEN;
    }
    @Value("${dd.dev-token}")
    public void setDevToken(String devToken) {
        DD.DEV_TOKEN = LOCAL_TEST_TOKEN+devToken;
    }
    @Value("${dd.test-token}")
    public void setTestToken(String testToken) {
        DD.TEST_TOKEN = LOCAL_TEST_TOKEN+testToken;
    }
    @Value("${dd.pro-token}")
    public void setProToken(String proToken) {
        DD.PRO_TOKEN = LOCAL_TEST_TOKEN+proToken;
    }
    @Value("${dd.ali-msg-token}")
    public void setAliMsgToken(String aliMsgToken) {
        DD.ALI_MSG_TOKEN = LOCAL_TEST_TOKEN+aliMsgToken;
    }


    @Value("${dd.add-url}")
    public void setAddUri(String addUrl) {
        DD.addUrl = addUrl;
    }

    @Value("${dd.show-url}")
    public void setShowUri(String showUrl) {
        DD.showUrl = showUrl;
    }


    private static void setMessage(String message_con, String token) {
        try {
            HttpClient httpclient = null;
            try {
                httpclient = HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(SSLContexts.custom().loadTrustMaterial(null,new TrustSelfSignedStrategy()).build())).build();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }

            HttpPost httppost = new HttpPost(token);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");

            String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"" +
                    message_con
                            .replaceAll("\"", "'")
                    + "\"}}";
            StringEntity se = new StringEntity(textMsg, "utf-8");
            httppost.setEntity(se);

            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setAliMessage(String message_con, String token) {
        try {
            HttpClient httpclient = null;
            try {
                httpclient = HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(SSLContexts.custom().loadTrustMaterial(null,new TrustSelfSignedStrategy()).build())).build();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
            HttpPost httppost = new HttpPost(token);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");
            String textMsg ="{ \"msgtype\": \"text\", \"text\": {\"content\": \"" + message_con + "\"},\"at\":{\"atMobiles\": [\"17610996162\"],\"isAtAll\": false}}";
            StringEntity se = new StringEntity(textMsg, "utf-8");
            httppost.setEntity(se);

            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertException(String id, String system, String runEnvironment, String host, int port, String exception) {
        HttpClient httpclient = HttpClients.createDefault();
        try {
            //HttpPost httppost = new HttpPost("http://"+host+":"+port+"/exceptionLog/addException");
            HttpPost httppost = new HttpPost(addUrl);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");
            String textMsg = "{ \"id\": \""
                    + id + "\",\"system\": \""
                    + system + "\", \"runEnvironment\": \""
                    + runEnvironment + "\",\"host\": \""
                    + host + "\",\"port\": \""
                    + port + "\",\"exceptionLog\": \""
                    + exception
                    .replaceAll("\n", "</br>")
                    .replaceAll("\"", "'") + "\"}";
            StringEntity se = new StringEntity(textMsg, "utf-8");
            httppost.setEntity(se);
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                String entity = EntityUtils.toString (response.getEntity(),"utf-8");
                System.out.println(entity);
                warn(entity.replaceAll("\"", "\\034"));
            }
        } catch (IOException e) {
            warn(e.getMessage());
            System.out.println(e.getMessage());
        } finally {

        }
    }

    /**
     * @param id
     * @param system         当前系统
     * @param runEnvironment 当前环境
     * @param host           ip地址
     * @param port           端口
     * @param exception      异常信息
     * @param token
     */
    private static void setExceptionMessage(String id, String system, String runEnvironment, String host, int port, String exception, String token) {
        try {
            HttpClient httpclient = null;
            try {
                httpclient = HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(SSLContexts.custom().loadTrustMaterial(null,new TrustSelfSignedStrategy()).build())).build();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
            HttpPost httppost = new HttpPost(token);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");
            String textMsg = "{ 'msgtype': 'markdown', " +
                    " 'markdown': {'title':'后台系统运行时异常', 'text':'后台系统运行时异常，错误信息如下: \n" +
                    "\n-当前系统:" + system + "    ip地址:" + host + "\n" +
                    "\n-当前环境:" + runEnvironment + "     端口:" + port + "\n" +
                    "\n-异常信息:" + exception.split("\n")[0] + "...\n" +
                    "\n[详情查看](" + showUrl + id + ")\n" +
                    " ' }," +
                    "'at': {" +
                    "         'atMobiles': [" +
                    "             '18612660303', '18538834689'" +
                    "         ]," +
                    "         'isAtAll': 'false'" +
                    "  }" +
                    "}";
            StringEntity se = new StringEntity(textMsg, "utf-8");
            httppost.setEntity(se);

            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *异常处理消息
     * @param id
     * @param message
     */
    private static void dealMessage(String id, String message,String token) {
        try {
            HttpClient httpclient = null;
            try {
                httpclient = HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(SSLContexts.custom().loadTrustMaterial(null,new TrustSelfSignedStrategy()).build())).build();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
            HttpPost httppost = new HttpPost(token);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");
            String textMsg = "{ 'msgtype': 'markdown', " +
                    " 'markdown': {'title':'异常处理', 'text':'异常处理信息: \n" +
                    "\n"+message+"\n"+
                    "\n[详情查看](" + showUrl + id + ")\n" +
                    " ' }," +
                    "}";
            StringEntity se = new StringEntity(textMsg, "utf-8");
            httppost.setEntity(se);

            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void warn(String message_con) {
        setMessage(message_con, TOKEN);
    }

    public static final void dev_send(String message_con) {
        setMessage(message_con, DEV_TOKEN);
    }

    public static final void test_send(String message_con) {
        setMessage(message_con, TEST_TOKEN);
    }

    public static final void send(String message_con) {
        setMessage(message_con, PRO_TOKEN);
    }

    public static final void sendAliMsg(String message_con) {
        setAliMessage(message_con, ALI_MSG_TOKEN);
    }

    public static final void sends(String message_con) {
        setMessage(message_con, TOKEN);
    }

    public static final void send_dealMsg(String id,String message_con) {
        dealMessage(id,message_con, TOKEN);
    }
}