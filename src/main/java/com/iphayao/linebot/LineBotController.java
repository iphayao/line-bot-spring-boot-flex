package com.iphayao.linebot;

import com.iphayao.linebot.flex.*;
import com.iphayao.linebot.helper.RichMenuHelper;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@LineMessageHandler
public class LineBotController {
    @Autowired
    private LineMessagingClient lineMessagingClient;

    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {
        log.info(event.toString());
        TextMessageContent message = event.getMessage();
        handleTextContent(event.getReplyToken(), event, event.getMessage());
    }

    private void handleTextContent(String token, Event event, TextMessageContent content) throws IOException {
        String text = content.getText();
        String userId = event.getSource().getUserId();

        log.info("Got text message from {} : {} ", token, text );
        switch (text) {
            case "Flex": {
                String pathImageFlex = new ClassPathResource("richmenu/richmenu-flexs.jpg").getFile().getAbsolutePath();
                String pathConfigFlex = new ClassPathResource("richmenu/richmenu-flexs.yml").getFile().getAbsolutePath();
                RichMenuHelper.createRichMenu(lineMessagingClient, pathConfigFlex, pathImageFlex, userId);
                break;
            }
            case "Flex Back": {

                RichMenuHelper.deleteRichMenu(lineMessagingClient, userId);
                break;
            }

            case "Flex Restaurant": {
                this.reply(token, new RestaurantFlexMessageSupplier().get());
                break;
            }
            case "Flex Menu": {
                this.reply(token, new RestaurantMenuFlexMessageSupplier().get());
                break;
            }
            case "Flex Receipt": {
                this.reply(token, new ReceiptFlexMessageSupplier().get());
                break;
            }
            case "Flex News": {
                this.reply(token, new NewsFlexMessageSupplier().get());
                break;
            }
            case "Flex Ticket": {
                this.reply(token, new TicketFlexMessageSupplier().get());
                break;
            }
            case "Flex Catalogue": {
                this.reply(token, new CatalogueFlexMessageSupplier().get());
                break;
            }
            default:
                log.info("Return echo message %s : %s", token, text);
                this.replyText(token, text);
        }
    }

    private void replyText(@NonNull String replyToken, @NonNull String message) {
        if(replyToken.isEmpty()) {
            throw new IllegalArgumentException("replyToken is not empty");
        }

        if(message.length() > 1000) {
            message = message.substring(0, 1000 - 2) + "...";
        }
        this.reply(replyToken, new TextMessage(message));
    }

    private void reply(@NonNull String replyToken, @NonNull Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

    private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
        try {
            BotApiResponse response = lineMessagingClient.replyMessage(
                    new ReplyMessage(replyToken, messages)
            ).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
