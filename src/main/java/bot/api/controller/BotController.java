package bot.api.controller;

import bot.api.model.Dados;
import bot.api.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/bot")
public class BotController {


    @Autowired
    private BotService botService;

    // Recendo ordem para abrir o bot e enviar a img do qrcode
    @GetMapping("/iniciar")
    public String abrirBot() {
        return botService.abrirBot();
    }

    // Recebendo ordem para inciar o envio
    @PostMapping("/ligar")
    public String ligarBot(@RequestBody Dados dados) {
        return botService.ligarBoot(dados);
    }

}
