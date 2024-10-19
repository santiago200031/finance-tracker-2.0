package org.finance;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import org.finance.services.BTCFinanceService;
import org.finance.services.DekaFinanceService;

@RegisterAiService(
        tools = {DekaFinanceService.class, BTCFinanceService.class}
)
public interface FinanceBot {

    @SystemMessage("You are a BaseFinance Bot that helps to check how the finances are going.")
    String sendMessage(@UserMessage String request);

}
