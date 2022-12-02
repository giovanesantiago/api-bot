package bot.api.service;

import bot.api.bot.Bot;
import bot.api.model.Dados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotService {

    @Autowired
    private static Dados dados;
    @Autowired
    Bot bot;

    // Abrindo bot
    public String abrirBot() {
        return bot.abrirBot();
    }

    // Inciando o envio de mensagens
    public String ligarBoot(Dados dados) {
        return bot.ligarBot(dados);
    }


}
