package cathywu.rapidpro.webclient.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lzwu
 * @since 2/3/16
 */
@Component
public class Configurations {

    private static Configurations instance = null;

    @Value("${RAPID_PRO_URL}")
    private String rapidProUrl;

    @Value("${RAPID_PRO_RECEIVED_URL}")
    private String rapidProReceivedUrl;

    @Value("${RAPID_PRO_SENT_URL}")
    private String rapidProDeliveryFlagUrl;

    private Channel channel;

    private Configurations() {
    }

    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }

    public void setChannel(int id, String uuid, String name) {
        this.channel = new Channel();
        this.channel.setId(id);
        this.channel.setUuid(uuid);
        this.channel.setName(name);
    }

    public String getRapidProUrl() {
        return rapidProUrl;
    }

    public String getRapidProReceivedUrl() {
        if (this.channel == null) {
            return "";
        }
        return rapidProUrl + rapidProReceivedUrl + this.channel.getUuid() + "/";
    }

    public String getRapidProDeliveryFlagUrl() {
        if (this.channel == null) {
            return "";
        }
        return rapidProUrl + rapidProDeliveryFlagUrl + this.channel.getUuid() + "/";
    }

    public void setRapidProUrl(String rapidProUrl) {
        this.rapidProUrl = rapidProUrl;
    }

    public Channel getChannel() {
        return channel;
    }

    public class Channel {
        private int id;
        private String name;
        private String uuid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
