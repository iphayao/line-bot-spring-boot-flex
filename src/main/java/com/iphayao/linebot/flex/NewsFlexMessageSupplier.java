package com.iphayao.linebot.flex;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.*;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexGravity;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

import java.util.function.Supplier;

import static java.util.Arrays.asList;

public class NewsFlexMessageSupplier implements Supplier<FlexMessage> {
    @Override
    public FlexMessage get() {
        final Box headerBlock = createHeaderBlock();
        final Image heroBlock = createHeroBlock();
        final Box bodyBlock = createBodyBlock();
        final Box footerBlock =  createFooterBlock();
        final Bubble bubble = Bubble.builder()
                .header(headerBlock)
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .build();
        return new FlexMessage("News", bubble);
    }

    private Box createHeaderBlock() {
        return Box.builder()
                .layout(FlexLayout.HORIZONTAL)
                .contents(asList(
                        Text.builder()
                                .text("NEWS DIGEST")
                                .weight(Text.TextWeight.BOLD)
                                .color("#aaaaaa")
                                .size(FlexFontSize.SM).build()
                )).build();
    }

    private Image createHeroBlock() {
        return Image.builder()
                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/news.png")
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(Image.ImageAspectRatio.R20TO13)
                .aspectMode(Image.ImageAspectMode.Cover)
                .build();
    }

    private Box createBodyBlock() {
        final Box imageBlock = createThumbnailsBox();
        final Box heightLightBlock = createNewsBlock();
        return Box.builder()
                .layout(FlexLayout.HORIZONTAL)
                .spacing(FlexMarginSize.MD)
                .contents(asList(imageBlock, heightLightBlock))
                .build();
    }

    private Box createThumbnailsBox() {
        final Image imagesContent1 = Image.builder()
                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/thumbnail1.png")
                .aspectMode(Image.ImageAspectMode.Cover)
                .aspectRatio(Image.ImageAspectRatio.R4TO3)
                .size(Image.ImageSize.SM)
                .gravity(FlexGravity.BOTTOM)
                .build();
        final Image imagesContent2 = Image.builder()
                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/thumbnail2.png")
                .aspectMode(Image.ImageAspectMode.Cover)
                .aspectRatio(Image.ImageAspectRatio.R4TO3)
                .size(Image.ImageSize.SM)
                .margin(FlexMarginSize.MD)
                .build();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .flex(1)
                .contents(asList(imagesContent1, imagesContent2))
                .build();
    }

    private Box createNewsBlock() {
        final Separator separator = Separator.builder().build();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .flex(2)
                .contents(asList(
                        Text.builder()
                                .text("7 Things to Know for Today")
                                .gravity(FlexGravity.TOP)
                                .size(FlexFontSize.XS)
                                .flex(1)
                                .build(),
                        separator,
                        Text.builder()
                                .text("Hay fever goes wild")
                                .gravity(FlexGravity.CENTER)
                                .size(FlexFontSize.XS)
                                .flex(2)
                                .build(),
                        separator,
                        Text.builder()
                                .text("LINE Pay Begins Barcode Payment Service")
                                .gravity(FlexGravity.CENTER)
                                .size(FlexFontSize.XS)
                                .flex(2)
                                .build(),
                        separator,
                        Text.builder()
                                .text("LINE Adds LINE Wallet")
                                .gravity(FlexGravity.BOTTOM)
                                .size(FlexFontSize.XS)
                                .flex(1)
                                .build()
                ))
                .build();
    }

    private Box createFooterBlock() {
        return Box.builder()
                .layout(FlexLayout.HORIZONTAL)
                .contents(asList(
                        Button.builder()
                                .action(new URIAction("more", "https://example.com"))
                                .build()
                )).build();
    }
}
