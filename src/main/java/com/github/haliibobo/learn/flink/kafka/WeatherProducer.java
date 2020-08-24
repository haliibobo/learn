package com.github.haliibobo.learn.flink.kafka;

import com.github.haliibobo.learn.flink.http.HttpFetcher;
import com.github.haliibobo.learn.flink.kafka.pojo.Weather;
import com.github.haliibobo.learn.tools.XmlUtil;
import okhttp3.Call;
import okhttp3.Response;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * http://flash.weather.com.cn/wmaps/xml/china.xml
 * http://www.weather.com.cn/data/cityinfo/101190408.html
 * http://www.weather.com.cn/data/sk/101010100.html
 */
public class WeatherProducer extends Thread implements okhttp3.Callback {
    private final KafkaProducer<String, String> producer;
    private final String topic;

    public WeatherProducer(final String topic, final boolean enableIdempotency) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "weather-producer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotency);

        producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    @Override
    public void run() {
        HttpFetcher.fetcher(this);
    }


    @Override
    public void onFailure(@NotNull Call call, IOException e) {

    }

    @Override
    public void onResponse(@NotNull Call call, Response response) throws IOException {
        assert response.body() != null;
        String xml = response.body().string();
        try {
            Element root = XmlUtil.getInstance().getRoot(xml);
            List<Weather> v = XmlUtil.getInstance().xml2List(root).stream().map(m -> {
                try {
                    return XmlUtil.getInstance().mapToBean(m, Weather.class);
                } catch (Exception ignored) { }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());
            v.forEach(weather -> producer.send(
                    new ProducerRecord<>(topic,
                    root.getName()+ ":" +weather.getPyName(),
                    weather.toString()),(recordMetadata, e) -> {
                    if(e != null){
                        e.printStackTrace();
                    }else{
                       System.out.println("offset:"+recordMetadata.offset());
                    }
            }));

        } catch (DocumentException e) {
            System.out.println(call.request().url());
        }
    }
}


