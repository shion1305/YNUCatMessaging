package com.shion1305.ynu_cat;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.*;
import com.linecorp.bot.model.response.BotApiResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class SendTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, URISyntaxException {
        LineMessagingClient client = LineMessagingClientManager.getClient();
        URI uri = new URI("https://lh3.googleusercontent.com/nXIbLSL0vr73g9Nsw_ZhNODGbKQ8AnQGhNLn0ldXjY_zVPZnk4nrGdluTBXKOKcgGipm0z9sjsD2vySc6wWkRRtnjUXH7VhJJPkoSJ4JFEQ-6npicjEup0L5ZWjBof8ddpLIiFNUsavQGVqkgInNi8COHnnXxJigFJwQ5fs5BhB4laiQkA31bsh6CUxUB97ODziDH3cmnjw4g8xUWEzmyeDbkXDq5K0q0l1m4ba0QntYJGvACa_B-HMhZ8s5o256fj-woASaUccvZQGZeiQQlZAMmFk90sP-xyQ9P5_E3pzhtsK8KpwWKY3CTzsWEgz2iGGj8YcmSF_zIaeWTaNdt07GtQlyv8drPdfqcSi696wMmhccW9_qapspSOx-mqXBSHuAdAz5Obr8-ywm9pEwMWe8G3ZM7Sck0U3ASndLGXrgIt8MmbiIu4tFxiXYRjAnA_x4A85Rgtb0JIEfiSXB8-6SnlppIdm7qYSM5QnG0FbUKDcXIwCzK7DyAmrb__QCZ2xTXk1Gqt_tD_-M_DK6fpXKdZKDTbqJD-oIvde-4fqRtX9_AFzOX178Y1yHqxdeqzwuIO0BI5uibWXjfpiScfKZWbVSjl3nowHS1gmUEBT9d6XaBPwGG-4Y_PKNfupEc5cAqa1DYkWh0FE7v3qIwNzMJpIy5ES7LcSx3d9HpmloE4gx3LzcT_bDd3iBaz_KSeyebTq5W4H4vAom4eHp9KR1=w1809-h1358-no?authuser=0");
        Bubble bubble = Bubble.builder().hero(Image.builder().url(uri).size("full").aspectRatio(4, 3).aspectMode(Image.ImageAspectMode.Cover).build())
                .body(Box.builder().layout(FlexLayout.VERTICAL).contents(Text.builder().text("本日当番のお知らせ").gravity(FlexGravity.CENTER).weight(Text.TextWeight.BOLD).size(FlexFontSize.XL).align(FlexAlign.CENTER).color("#FFFFFF").build()).backgroundColor("#5AC2D9").build()).build();
        FlexMessage message = FlexMessage.builder().contents(bubble).altText("猫サークル 当番のお知らせ").build();
        PushMessage pushMessage = new PushMessage("Ua54a5505fcbf303b59a856c6bbec5518", message);
        BotApiResponse botApiResponse = client.pushMessage(pushMessage).get();
        System.out.println(botApiResponse);
    }
}
