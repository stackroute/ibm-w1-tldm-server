package com.stackroute.tldm.controller;

import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.model.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class MessageControllerTest {

    private User sender;
    private User receiver;
    private List<User> userList;
    private Channel channel;
    private Message message;
    private ChannelMessage channelMessage;

    public MessageControllerTest() {
        super();
    }

    private static String USER_SENDER_TOPIC = "message_test";
    private static String CHANNEL_SENDER_TOPIC = "channel_test";

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, ChannelMessage> channelKafkaTemplate;


    private KafkaMessageListenerContainer<String, Message> container;
    private KafkaMessageListenerContainer<String, ChannelMessage> channelContainer;

    private BlockingQueue<ConsumerRecord<String, Message>> records;
    private BlockingQueue<ConsumerRecord<String, ChannelMessage>> channelRecords;

    @ClassRule
    public static KafkaEmbedded userEmbeddedKafka = new KafkaEmbedded(1, true, USER_SENDER_TOPIC);

    @ClassRule
    public static KafkaEmbedded channelEmbeddedKafka = new KafkaEmbedded(1, true, CHANNEL_SENDER_TOPIC);

    @Before
    public void setUp() throws Exception {

        sender = new User();
        sender.setUserId("kartikeymishr");
        sender.setUserName("Kartikey Mishr");
        sender.setUserMail("kartikeymishr@hotmail.com");
        sender.setPhoneNum("9555706914");

        receiver = new User();
        receiver.setUserId("mggmanik");
        receiver.setUserName("Manik Gupta");
        receiver.setUserMail("mggmanik@gmail.com");
        receiver.setPhoneNum("94738543938");

        userList = new ArrayList<>();
        userList.add(sender);
        userList.add(receiver);

        channel = new Channel();
        channel.setChannelId("channel123");
        channel.setChannelName("general");
        channel.setChannelCreatedBy("Manik Gupta");
        channel.setChannelCreatedDate(new Date());
        channel.setChannelDescription("this is a general channel");
        channel.setChannelUsers(userList);

        UUID messageUUID = UUID.randomUUID();
        message = new Message();
        message.setMessageId(messageUUID);
        message.setMessageContent("hello world");
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setTimestamp(new Date());

        UUID channelMessageUUID = UUID.randomUUID();
        channelMessage = new ChannelMessage();
        channelMessage.setMessageId(channelMessageUUID);
        channelMessage.setChannel(channel);
        channelMessage.setMessageContent("hello channel");
        channelMessage.setSender(sender);
        channelMessage.setTimestamp(new Date());


        // set up the Kafka consumer properties
        Map<String, Object> userConsumerProp =
                KafkaTestUtils.consumerProps("sender", "false", userEmbeddedKafka);

        userConsumerProp.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Map<String, Object> channelConsumerProp =
                KafkaTestUtils.consumerProps("sender", "false", channelEmbeddedKafka);

        channelConsumerProp.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create a Kafka consumer factory
        DefaultKafkaConsumerFactory<String, Message> userConsumerFactory =
                new DefaultKafkaConsumerFactory<>(userConsumerProp, new StringDeserializer(), new JsonDeserializer<>(Message.class));

        DefaultKafkaConsumerFactory<String, ChannelMessage> channelConsumerFactory =
                new DefaultKafkaConsumerFactory<>(channelConsumerProp, new StringDeserializer(), new JsonDeserializer<>(ChannelMessage.class));

        // set the topic that needs to be consumed
        ContainerProperties userContainerProperties = new ContainerProperties(USER_SENDER_TOPIC);

        ContainerProperties channelContainerProperties = new ContainerProperties(CHANNEL_SENDER_TOPIC);

        // create a Kafka MessageListenerContainer
        container = new KafkaMessageListenerContainer<>(userConsumerFactory, userContainerProperties);

        channelContainer = new KafkaMessageListenerContainer<>(channelConsumerFactory, channelContainerProperties);

        // create a thread safe queue to store the received message
        records = new LinkedBlockingQueue<>();

        channelRecords = new LinkedBlockingQueue<>();

        container.setupMessageListener((MessageListener<String, Message>) record -> records.add(record));

        channelContainer.setupMessageListener((MessageListener<String, ChannelMessage>) record -> channelRecords.add(record));

        // start the container and underlying message listener
        container.start();

        channelContainer.start();

        // wait until the container has the required number of assigned partitions
        ContainerTestUtils.waitForAssignment(container, userEmbeddedKafka.getPartitionsPerTopic());

        ContainerTestUtils.waitForAssignment(channelContainer, channelEmbeddedKafka.getPartitionsPerTopic());
    }

    @After
    public void tearDown() {
        // stop the container
        container.stop();
        channelContainer.stop();
    }

    @Test
    @Ignore
    public void sendMessageToUser() throws InterruptedException {

        kafkaTemplate.send(USER_SENDER_TOPIC, message);

        ConsumerRecord<String, Message> receivedForUser = records.poll(10, TimeUnit.SECONDS);assertThat(receivedForUser).isNotNull();

        assertThat(receivedForUser).isNotNull();

    }


    @Test
    @Ignore
    public void sendMessageToChannel() throws InterruptedException {

        channelKafkaTemplate.send(CHANNEL_SENDER_TOPIC, channelMessage);

        ConsumerRecord<String, ChannelMessage> receivedForChannel = channelRecords.poll(10, TimeUnit.SECONDS);

        assertThat(receivedForChannel).isNotNull();
    }
}