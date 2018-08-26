package com.iphayao.linebot.flex;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.*;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

import java.util.function.Supplier;

import static java.util.Arrays.asList;

public class RestaurantMenuFlexMessageSupplier implements Supplier<FlexMessage> {
    @Override
    public FlexMessage get() {
        final Image heroBlock = createHeroBlock();
        final Box bodyBlock = createBodyBlock();
        final Box footerBlock = createFooterBox();

        final Bubble bubble = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .build();
        return new FlexMessage("Restaurant Menu", bubble);
    }

    private Image createHeroBlock() {
        return Image.builder()
                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/berger.png")
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(Image.ImageAspectRatio.R20TO13)
                .aspectMode(Image.ImageAspectMode.Cover)
                .action(new URIAction("label", "http://example.com"))
                .build();
    }

    private Box createBodyBlock() {
        final Text title = Text.builder()
                .text("Brown's Burger")
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.XL)
                .build();
        final Box menus = createMenusBox();
        final Box recipe = createRecipeBox();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.MD)
                .contents(asList(title, menus, recipe))
                .build();
    }

    private Box createRecipeBox() {
        final Box recipe = Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.XS)
                .contents(asList(
                        Text.builder()
                                .text("Source, Onions, Pickles, Lettuce & Cheese")
                                .size(FlexFontSize.XS)
                                .color("#aaaaaa")
                                .flex(1)
                                .build())).build();
        return recipe;
    }

    private Box createMenusBox() {
        final Box menu1 = Box.builder()
                .layout(FlexLayout.BASELINE)
                .contents(asList(
                        Icon.builder()
                                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/regular.png").build(),
                        Text.builder().text("$10.5")
                                .weight(Text.TextWeight.BOLD)
                                .margin(FlexMarginSize.SM)
                                .flex(0)
                                .build(),
                        Text.builder().text("400kcl")
                                .size(FlexFontSize.SM)
                                .align(FlexAlign.END)
                                .color("#aaaaaa")
                                .build()

                ))
                .build();
        final Box menu2 = Box.builder()
                .layout(FlexLayout.BASELINE)
                .contents(asList(
                        Icon.builder()
                                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/large.png").build(),
                        Text.builder().text("$15.5")
                                .weight(Text.TextWeight.BOLD)
                                .margin(FlexMarginSize.SM)
                                .flex(0)
                                .build(),
                        Text.builder().text("550kcl")
                                .size(FlexFontSize.SM)
                                .align(FlexAlign.END)
                                .color("#aaaaaa")
                                .build()

                ))
                .build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.SM)
                .contents(asList(menu1, menu2))
                .build();
    }

    private Box createFooterBox() {
        final Spacer spacer = Spacer.builder().size(FlexMarginSize.XXL).build();
        final Button button = Button.builder()
                .style(Button.ButtonStyle.PRIMARY)
                .color("#905c44")
                .action(new URIAction("Add to Cart", "http://example.com"))
                .build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(asList(spacer, button))
                .build();
    }
}
