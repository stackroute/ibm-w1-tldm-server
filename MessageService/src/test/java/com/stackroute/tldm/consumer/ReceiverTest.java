package com.stackroute.tldm.consumer;

import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.model.User;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class ReceiverTest {

    private static String USER_RECEIVER_TOPIC = "message_test";

    private static String CHANNEL_RECEIVER_TOPIC = "channel_test";


    private User sender;
    private User receiver;
    private List<User> userList;
    private Channel channel;
    private Message message;
    private ChannelMessage channelMessage;

    @Autowired
    private Receiver testReceiver;

    private KafkaTemplate<String, Message> userTemplate;

    private KafkaTemplate<String, ChannelMessage> channelTemplate;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    public ReceiverTest() {
        super();
    }

    @ClassRule
    public static KafkaEmbedded userEmbeddedKafka = new KafkaEmbedded(1, true, USER_RECEIVER_TOPIC);

    @ClassRule
    public static KafkaEmbedded channelEmbeddedKafka = new KafkaEmbedded(1, true, CHANNEL_RECEIVER_TOPIC);

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


        // set up the Kafka producer properties
        Map<String, Object> userSenderProperties =
                KafkaTestUtils.senderProps(userEmbeddedKafka.getBrokersAsString());

        Map<String, Object> channelSenderProperties =
                KafkaTestUtils.senderProps(userEmbeddedKafka.getBrokersAsString());

        // create a Kafka producer factory
        ProducerFactory<String, Message> userProducerFactory =
                new DefaultKafkaProducerFactory<>(userSenderProperties);

        ProducerFactory<String, ChannelMessage> channelProducerFactory =
                new DefaultKafkaProducerFactory<>(channelSenderProperties);

        // create a Kafka template
        userTemplate = new KafkaTemplate<>(userProducerFactory);
        channelTemplate = new KafkaTemplate<>(channelProducerFactory);
        // set the default topic to send to
        userTemplate.setDefaultTopic(USER_RECEIVER_TOPIC);
        channelTemplate.setDefaultTopic(CHANNEL_RECEIVER_TOPIC);

        // wait until the partitions are assigned
        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
                .getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer,
                    userEmbeddedKafka.getPartitionsPerTopic());
        }
        for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
                .getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer,
                    channelEmbeddedKafka.getPartitionsPerTopic());
        }
    }

    @Test
    @Ignore
    public void receive() {

        userTemplate.sendDefault(message);
        assertThat(testReceiver).isNotNull();

    }

    @Test
    @Ignore
    public void receiveGroupMessages() {

        channelTemplate.sendDefault(channelMessage);
        assertThat(testReceiver).isNotNull();
    }
}