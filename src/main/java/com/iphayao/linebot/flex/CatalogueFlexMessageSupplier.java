package com.iphayao.linebot.flex;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.*;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Carousel;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexGravity;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

public class CatalogueFlexMessageSupplier implements Supplier<FlexMessage> {
    @Override
    public FlexMessage get() {
        final Bubble bubble1 = createBubble("Arm Chair, White",
                "49.99",
                "https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/bubble1.png",
                false);
        final Bubble bubble2 = createBubble("Metal Desk Lamp",
                "11.99",
                "https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/bubble2.png",
                true);
        final Bubble seeMore = createSeeMoreBubble();
        final Carousel carousel = Carousel.builder()
                .contents(asList(bubble1, bubble2, seeMore))
                .build();
        return new FlexMessage("Catalogue", carousel);
    }

    private Bubble createBubble(String title, String price, String imageURL, Boolean isOutOfStock) {
        final Image heroBlock = createHeroBlock(imageURL);
        final Box bodyBlock = createBodyBlock(title, price, isOutOfStock);
        final Box footerBlock = createFooterBlock(isOutOfStock);
        return Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .build();
    }

    private Bubble createSeeMoreBubble() {
        return Bubble.builder()
                .body(Box.builder()
                        .layout(FlexLayout.VERTICAL)
                        .spacing(FlexMarginSize.SM)
                        .contents(asList(
                                Button.builder()
                                        .flex(1)
                                        .gravity(FlexGravity.CENTER)
                                        .action(new URIAction("See more", "http://example.com"))
                                        .build()
                        )).build()
                )
                .build();
    }

    private Image createHeroBlock(String imageURL) {
        return Image.builder()
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(Image.ImageAspectRatio.R20TO13)
                .aspectMode(Image.ImageAspectMode.Cover)
                .url(imageURL)
                .build();
    }

    private Box createBodyBlock(String title, String price, Boolean isOutOfStock) {
        final Text titleBlock = Text.builder()
                .text(title)
                .wrap(true)
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.XL).build();
        final Box priceBlock = Box.builder()
                .layout(FlexLayout.BASELINE)
                .contents(asList(
                        Text.builder().text("$" + price.split("\\.")[0])
                                .wrap(true)
                                .weight(Text.TextWeight.BOLD)
                                .size(FlexFontSize.XL)
                                .flex(0)
                                .build(),
                        Text.builder().text("." + price.split("\\.")[1])
                                .wrap(true)
                                .weight(Text.TextWeight.BOLD)
                                .size(FlexFontSize.SM)
                                .flex(0)
                                .build()
                )).build();
        final Text outOfStock = Text.builder()
                .text("Temporarily out of stock")
                .wrap(true)
                .size(FlexFontSize.XXS)
                .margin(FlexMarginSize.MD)
                .color("#FF5551")
                .build();

        FlexComponent[] flexComponents = {titleBlock, priceBlock};
        List<FlexComponent> listComponent = new ArrayList<>(Arrays.asList(flexComponents));
        if(isOutOfStock) {
            listComponent.add(outOfStock);
        }

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.SM)
                .contents(listComponent)
                .build();
    }

    private Box createFooterBlock(Boolean isOutOfStock) {
        final Button addToCartEnableButton = Button.builder()
                .style(Button.ButtonStyle.PRIMARY)
                .action(new URIAction("Add to Cart", "http://example.com"))
                .build();
        final Button addToCartDisableButton = Button.builder()
                .style(Button.ButtonStyle.PRIMARY)
                .color("#aaaaaa")
                .action(new URIAction("Add to Cart", "http://example.com"))
                .build();
        final Button addToWishlistButton = Button.builder()
                .action(new URIAction("Add to wishlist", "http://example.com"))
                .build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.SM)
                .contents(asList((!isOutOfStock) ? addToCartEnableButton : addToCartDisableButton, addToWishlistButton))
                .build();
    }
}
