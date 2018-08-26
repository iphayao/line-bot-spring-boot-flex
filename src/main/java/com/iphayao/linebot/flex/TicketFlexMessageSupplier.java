package com.iphayao.linebot.flex;

import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.*;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexGravity;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

import java.util.function.Supplier;

import static java.util.Arrays.asList;

public class TicketFlexMessageSupplier implements Supplier<FlexMessage> {
    @Override
    public FlexMessage get() {
        final Image heroBlock = createHeroBlock();
        final Box bodyBlock = createBodyBlock();

        final Bubble bubble = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .build();

        return new FlexMessage("Ticket", bubble);
    }

    private Image createHeroBlock() {
        return Image.builder()
                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/movie.png")
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(Image.ImageAspectRatio.R20TO13)
                .aspectMode(Image.ImageAspectMode.Cover)
                .build();
    }

    private Box createBodyBlock() {
        final Text titleBlock = createTitleBox();
        final Box previewBlock = createPreviewBox();
        final Box detailsBlock = createDetailsBox();
        final Box qrCodeBlock = createQRCodeBox();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.MD)
                .contents(asList(titleBlock, previewBlock, detailsBlock, qrCodeBlock))
                .build();
    }

    private Text createTitleBox() {
        return Text.builder()
                .text("BROWN'S ADVENTURE\\nIN MOVIE")
                .wrap(true)
                .weight(Text.TextWeight.BOLD)
                .gravity(FlexGravity.CENTER)
                .size(FlexFontSize.XL)
                .build();
    }

    private Box createPreviewBox() {
        final Icon goldStart = Icon.builder()
                .size(FlexFontSize.SM)
                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/gold_star.png")
                .build();
        final Icon grayStart = Icon.builder()
                .size(FlexFontSize.SM)
                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/gray_star.png")
                .build();
        final Text point = Text.builder()
                .text("4.0")
                .size(FlexFontSize.SM)
                .color("#999999")
                .margin(FlexMarginSize.MD)
                .build();
        return Box.builder()
                .layout(FlexLayout.BASELINE)
                .margin(FlexMarginSize.MD)
                .contents(asList(goldStart, goldStart, goldStart, goldStart, grayStart, point))
                .build();
    }

    private Box createDetailsBox() {
        final Box dateBlock = createDetailBlock("Date", "Monday 25, 9:00PM");
        final Box placeBlock = createDetailBlock("Place", "7 Floor, No.3");
        final Box seatsBlock = createDetailBlock("Seats", "C Row, 18 Seat");
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .margin(FlexMarginSize.LG)
                .spacing(FlexMarginSize.SM)
                .contents(asList(dateBlock, placeBlock, seatsBlock))
                .build();
    }

    private Box createDetailBlock(String title, String detail) {
        return Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(
                        asList(
                                Text.builder()
                                        .text(title)
                                        .color("#aaaaaa")
                                        .size(FlexFontSize.SM)
                                        .flex(1).build(),
                                Text.builder()
                                        .text(detail)
                                        .wrap(true)
                                        .color("#666666")
                                        .size(FlexFontSize.SM)
                                        .flex(4)
                                        .build()
                        )
                ).build();
    }

    private Box createQRCodeBox() {
        final Spacer spacer = Spacer.builder().build();
        final Image qrImage = Image.builder()
                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/qrcode.png")
                .aspectMode(Image.ImageAspectMode.Cover)
                .size(Image.ImageSize.XL)
                .build();
        final Text guidLine = Text.builder()
                .text("You can enter the theater by using this code instead of a ticket")
                .color("#aaaaaa")
                .wrap(true)
                .margin(FlexMarginSize.XXL)
                .size(FlexFontSize.XS)
                .build();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .margin(FlexMarginSize.XXL)
                .contents(asList(spacer, qrImage, guidLine))
                .build();
    }
}
