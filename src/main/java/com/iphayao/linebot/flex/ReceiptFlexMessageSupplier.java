package com.iphayao.linebot.flex;

import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Separator;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

import java.util.function.Supplier;

import static java.util.Arrays.asList;

public class ReceiptFlexMessageSupplier implements Supplier<FlexMessage> {
    @Override
    public FlexMessage get() {
        final Separator separator = Separator.builder().margin(FlexMarginSize.XXL).build();
        final Box headerBlock = createBodyHeaderBox();
        final Box itemBlock = createBodyItemBlock();
        final Box summaryBlock = createBodySummaryBlock();
        final Box footerBlock = createBodyFooterBlock();

        final Box bodyBlock = Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(asList(
                        headerBlock,
                        separator,
                        itemBlock,
                        separator,
                        summaryBlock,
                        separator,
                        footerBlock))
                .build();

        final Bubble bubble = Bubble.builder()
                .body(bodyBlock)
                .build();
        return new FlexMessage("ALT", bubble);
    }

    private Box createBodyHeaderBox() {
        final Text bodyHeaderText = Text.builder()
                .text("RECEIPT")
                .weight(Text.TextWeight.BOLD)
                .color("#1db446")
                .size(FlexFontSize.SM)
                .build();
        final Text bodyTitleHeaderText = Text.builder()
                .text("Brown's Store")
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.XXL)
                .margin(FlexMarginSize.MD)
                .build();
        final Text bodyTitleHeaderDetail = Text.builder()
                .text("Silom, Bangkok")
                .size(FlexFontSize.XS)
                .color("#aaaaaa")
                .wrap(true)
                .build();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(asList(
                        bodyHeaderText,
                        bodyTitleHeaderText,
                        bodyTitleHeaderDetail))
                .build();
    }

    private Box createBodyItemBlock() {
        final Separator separator = Separator.builder().margin(FlexMarginSize.XXL).build();
        final Box item1 = createItem("Energy Drink", "$2.99");
        final Box item2 = createItem("Chewing Gum", "$0.99");
        final Box item3 = createItem("Bottled Water", "$3.33");
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .margin(FlexMarginSize.XXL)
                .spacing(FlexMarginSize.SM)
                .contents(asList(
                        item1,
                        item2,
                        item3))
                .build();
    }

    private Box createBodySummaryBlock() {
        final Separator separator = Separator.builder().margin(FlexMarginSize.XXL).build();
        final Box items = createItem("ITEMS", "3");
        final Box total = createItem("TOTAL", "$7.31");
        final Box cash = createItem("CASH", "$8.00");
        final Box change = createItem("CHANGE", "$0.69");
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .margin(FlexMarginSize.XXL)
                .spacing(FlexMarginSize.SM)
                .contents(asList(
                        items,
                        total,
                        cash,
                        change))
                .build();
    }

    private Box createBodyFooterBlock() {
        return Box.builder()
                .layout(FlexLayout.HORIZONTAL)
                .margin(FlexMarginSize.MD)
                .contents(asList(
                        Text.builder()
                                .text("PAYMENT ID")
                                .size(FlexFontSize.XS)
                                .color("#aaaaaa")
                                .flex(0)
                                .build(),
                        Text.builder()
                                .text("#743289384279")
                                .size(FlexFontSize.XS)
                                .color("#aaaaaa")
                                .align(FlexAlign.END)
                                .build()
                ))
                .build();
    }

    private Box createItem(String name, String price) {
        return Box.builder()
                .layout(FlexLayout.HORIZONTAL)
                .contents(asList(
                        Text.builder()
                                .text(name)
                                .size(FlexFontSize.SM)
                                .color("#555555")
                                .flex(0)
                                .build(),
                        Text.builder()
                                .text(price)
                                .size(FlexFontSize.SM)
                                .color("#111111")
                                .align(FlexAlign.END)
                                .build()
                )).build();
    }
}
