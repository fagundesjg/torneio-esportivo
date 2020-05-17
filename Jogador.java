
public class Jogador implements Comparable<Jogador> {

    private int premios, pontos, vitorias;
    private String nome;

    public Jogador(String nome) {
        this.nome = nome;
        this.premios = 0;
        this.pontos = 0;
        this.vitorias = 0;
    }

    public Jogador(String nome, int premios, int pontos, int vitorias) {
        this.nome = nome;
        this.premios = premios;
        this.pontos = pontos;
        this.vitorias = vitorias;
    }

    public int getPremios() {
        return premios;
    }

    public void setPremios(int premios) {
        this.premios = premios;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int compareTo(Jogador o) {
        int result = compareInt(this.pontos, o.getPontos());
        if (result == 0) {
            result = compareInt(this.vitorias, o.getVitorias());
            if (result == 0) {
                return this.nome.compareTo(o.getNome());
            }
        }
        return result;
    }

    private int compareInt(int a, int b) {
        if (a == b) {
            return 0;
        } else if (a > b) {
            return 1;
        } else {
            return -1;
        }
    }
    
    public String toString() {
        return String.format("Nome: %-15s Pontos: %-10d Prêmios: %-10d Vitórias: %-3d", this.nome, this.pontos, this.premios, this.vitorias);
    }

}
