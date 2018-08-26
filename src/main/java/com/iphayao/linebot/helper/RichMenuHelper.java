package com.iphayao.linebot.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.objectmapper.ModelObjectMapper;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.model.richmenu.RichMenu;
import com.linecorp.bot.model.richmenu.RichMenuIdResponse;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.fasterxml.jackson.core.JsonParser.Feature.*;
import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.google.common.util.concurrent.Futures.getUnchecked;
import static javax.activation.FileTypeMap.getDefaultFileTypeMap;

@Slf4j
@LineMessageHandler
public class RichMenuHelper {
    @Autowired
    private LineMessagingClient lineMessagingClient;

    public static boolean createRichMenu(LineMessagingClient client, String pathYaml, String pathImage, String userId) {
        try {
            String richMenuId = createRichMenu(client, pathYaml);
            imageUploadRichMenu(client, richMenuId, pathImage);
            linkToUser(client, richMenuId, userId);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean deleteRichMenu(LineMessagingClient client, String userId) {
        try {
            RichMenuIdResponse richMenuIdResponse = getUnchecked(client.getRichMenuIdOfUser(userId));
            deletedRichMenu(client, richMenuIdResponse.getRichMenuId());
            unlinkUser(client, userId);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static String createRichMenu(LineMessagingClient client,
                                         String path) throws IOException {
        RichMenu richMenu = loadYaml(path);

        RichMenuIdResponse richMenuResponse = getUnchecked(client.createRichMenu(richMenu));
        log.info("Successfully finished");
        log.info("{}", richMenuResponse);

        return richMenuResponse.getRichMenuId();
    }

    private static void imageUploadRichMenu(LineMessagingClient client,
                                            String richMenuId, String path) throws IOException {
        String contentType = getDefaultFileTypeMap().getContentType(path);
        log.info("Content-type: {}", contentType);

        byte[] bytes = Files.readAllBytes(Paths.get(path));

        BotApiResponse botApiResponse = getUnchecked(client.setRichMenuImage(richMenuId, contentType, bytes));
        log.info("Successfully finished");
        log.info("{}", botApiResponse);
    }

    private static void deletedRichMenu(LineMessagingClient client, String richMenuId) {
        BotApiResponse botApiResponse = getUnchecked(client.deleteRichMenu(richMenuId));
        log.info("Successfully deleted");
        log.info("{}", botApiResponse);
    }

    private static void linkToUser(LineMessagingClient client, String richMenuId, String userId) {
        BotApiResponse botApiResponse = getUnchecked(client.linkRichMenuIdToUser(userId, richMenuId));
        log.info("Successfully linked {} to user {}", richMenuId, userId);
        log.info("{}", botApiResponse);
    }

    private static void unlinkUser(LineMessagingClient client, String userId) {
        BotApiResponse botApiResponse = getUnchecked(client.unlinkRichMenuIdFromUser(userId));
        log.info("Successfully unlinked user {}", userId);
        log.info("{}", botApiResponse);
    }

    private static RichMenu loadYaml(String path) throws IOException {
        final Yaml YAML = new Yaml();
        final ObjectMapper OBJECT_MAPPER = ModelObjectMapper
                .createNewObjectMapper()
                .configure(ALLOW_UNQUOTED_FIELD_NAMES, true)
                .configure(ALLOW_COMMENTS, true)
                .configure(ALLOW_SINGLE_QUOTES, true)
                .configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, true)
                .configure(INDENT_OUTPUT, true);

        Object yamlAsObject;
        try(FileInputStream is = new FileInputStream(path)) {
            yamlAsObject = YAML.load(is);
        }

        return OBJECT_MAPPER.convertValue(yamlAsObject, RichMenu.class);
    }
}
