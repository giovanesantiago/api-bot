package bot.api.bot;

import bot.api.model.Dados;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Bot {
    // driver/chromedriver.exe


    // Driver
    WebDriver driver;


    // Construtor para iniciar a configuração do driver
    public Bot() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/bot/api/bot/driver/chromedriver.exe");
    }

    // Abrir bot
    public String abrirBot() {
        System.out.println();

        // Iniciando driver e abrindo whatsapp
        driver = new ChromeDriver();
        driver.get("https://web.whatsapp.com/");
        System.out.println("bot ligado");

        // Verificando carregando do qr code
        Boolean existe = false;
        while (!existe) {
            existe = testeDeCarregamento("//*[@id=\"app\"]/div/div/div[3]/div[1]/div/div[2]/div/canvas");
        }
        System.out.println("Qr Code");

        // Aqui precisa retornar a imagem do qrcode
        return "ok";
    }

    // Ligar bot
    public String ligarBot(Dados dados) {
        // Verificando se o qr ja foi lido e o whatsapp aberto
        Boolean existe = false;
        while (!existe) {
            existe = testeDeCarregamento("//*[@id=\"side\"]/div[1]/div/div/div[2]");
        }
        enviarMensagem(dados.getMensagem(), dados.getTelefones());
        // Sinalizando que mudou
        System.out.println("Mudou");
        driver.close();
        return "Bot ligado";
    }


    public String enviarMensagem(String mesagem, ArrayList<String> telefones) {
        // Laço para enviar mensagem
        for (String telefone: telefones) {
            try {
                // Abrindo conversa
                driver.get("https://web.whatsapp.com/send/?phone=55" + telefone);
                // verificando se a conversa ja abriu
                Boolean existe = false;
                while (!existe) {
                    existe = testeDeCarregamento("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]");
                }
                Thread.sleep(500);
                // Clicando na cx de mensagem
                driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]" +
                        "/div[1]/div/div[1]/p")).click();
                Thread.sleep(500);
                // Array com cada letra da mensagem
                String[] arrayTxt = mesagem.split("");
                // Digitando a primeira letra
                driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]" +
                        "/div/div[2]/div[1]/div/div[1]/p")).sendKeys(arrayTxt[0]);
                Thread.sleep(10);
                // Digitando letra por letra
                for (int i = 1; i < mesagem.length(); i++) {
                    driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/" +
                            "div[2]/div[1]/div/div/p/span")).sendKeys(arrayTxt[i]);

                    Thread.sleep(10);
                }
                Thread.sleep(500);
                // Enviando mensagem
                driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span" +
                        "[2]/div/div[2]/div[2]/button")).click();
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return "ok";
    }

    // Pesquisa de elemento para verificação se esta na pagina certa antes da execução
    public Boolean testeDeCarregamento (String xPath) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        Boolean existe = driver.findElements(By.xpath(xPath)).size() > 0;
        /*if (existe == false) {
            existe = driver.findElements(By.xpath(xPath)).size() > 0;
        }*/
        return existe;
    }



}
