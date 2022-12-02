package bot.api.model;

import java.util.ArrayList;

public class Dados {

    private String mensagem;
    private ArrayList<String> telefones;

    public Dados() {
    }

    public Dados(String mensagem, ArrayList<String> telefones) {
        this.mensagem = mensagem;
        this.telefones = telefones;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ArrayList<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<String> telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return "Dados{" +
                "messagem='" + mensagem + '\'' +
                ", telefones=" + telefones +
                '}';
    }
}
