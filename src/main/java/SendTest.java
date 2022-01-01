import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.Broadcast;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

import java.util.concurrent.ExecutionException;

public class SendTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LineMessagingClient client = LineMessagingClient.builder("Ew5TFZG0DrVHo98SMnl88Fck9u2W7NsZiw4yMU0vVZs/9O1LU5JN0bMilfOvgKBhFxUqoUiVAjf1S1jNBmoXhINK2UPq2VtOtCNo5LKVOYeCVCt7Qyqs+1vpRmzEjznlxdKsnBDhesCS/7U7cnKeuQdB04t89/1O/w1cDnyilFU=").build();
        TextMessage textMessage = TextMessage.builder().text("HELLO").build();
        client.broadcast(new Broadcast(textMessage)).get();
        PushMessage pushMessage = new PushMessage("Ua54a5505fcbf303b59a856c6bbec5518", textMessage);
        BotApiResponse botApiResponse = client.pushMessage(pushMessage).get();
        System.out.println(botApiResponse);
//        FlexMessage message= FlexMessage.builder().contents().build()
    }
}
