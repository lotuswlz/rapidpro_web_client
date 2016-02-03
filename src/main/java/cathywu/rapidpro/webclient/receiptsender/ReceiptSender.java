package cathywu.rapidpro.webclient.receiptsender;

import cathywu.rapidpro.webclient.common.Configurations;
import cathywu.rapidpro.webclient.common.HttpClient;
import cathywu.rapidpro.webclient.common.Response;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzwu
 * @since 2/4/16
 */
public class ReceiptSender implements Runnable {

    private String msgId;

    private int retryTimes = 0;

    private static final int MAX_RETRY_TIMES = 3;

    public ReceiptSender(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public void run() {
        try {
            sendReceipt();
        } catch (IOException e) {
            try {
                retryTimes++;
                if (retryTimes < MAX_RETRY_TIMES) {
                    Thread.sleep(3000);
                    run();
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void sendReceipt() throws IOException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", this.msgId);
        String rapidProDeliveryFlagUrl = Configurations.getInstance().getRapidProDeliveryFlagUrl();
        Response response = HttpClient.getInstance().send(rapidProDeliveryFlagUrl, params, RequestMethod.POST);
        System.out.println("[Response] code: " + response.getResponseCode() + ", message: " + response.getMessage());
    }
}
