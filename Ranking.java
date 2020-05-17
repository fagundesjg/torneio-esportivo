
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Ranking {

    private ArrayList<Torneio> torneios;
    private HashMap<String, Jogador> jogadores;

    static int getPontos(String entrada) {
        if (entrada.equals("Grand Slam")) {
            return 2000;
        } else {
            return Integer.parseInt(entrada.replace("ATP", ""));
        }
    }

    public Ranking(ArrayList<Torneio> torneios) {
        this.torneios = torneios;
        this.jogadores = new HashMap<String, Jogador>();
        for (Torneio t : torneios) {
            if (!this.jogadores.containsKey(t.getVencedor())) {
                this.jogadores.put(t.getVencedor(), new Jogador(t.getVencedor(), t.getPremio(), getPontos(t.getTipo()), 1));
            } else {
                Jogador jogador = this.jogadores.get(t.getVencedor());
                jogador.setPremios(jogador.getPremios() + t.getPremio());
                jogador.setPontos(jogador.getPontos() + getPontos(t.getTipo()));
                jogador.setVitorias(jogador.getVitorias() + 1);
                this.jogadores.put(t.getVencedor(), jogador);
            }
        }
    }

    public Ranking() {
        this.torneios = new ArrayList<Torneio>();
    }

    public ArrayList<Jogador> ordenadosPorPonto() {
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>(this.jogadores.values());
        Collections.sort(jogadores, Comparator.comparing(Jogador::getPontos));
        return jogadores;
    }

    public ArrayList<Jogador> ordenadosPorPremios() {
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>(this.jogadores.values());
        Collections.sort(jogadores, Comparator.comparing(Jogador::getPremios));
        return jogadores;
    }

    public ArrayList<Torneio> getTorneios() {
        return torneios;
    }

    public ArrayList<Jogador> getJogadores() {
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>(this.jogadores.values());
        Collections.sort(jogadores);
        return jogadores;
    }

    public ArrayList<Torneio> getTorneiosConflitantes(Torneio torneio) {
        ArrayList<Torneio> conflitantes = new ArrayList<Torneio>();

        for (Torneio t: this.torneios) {
            if(t != torneio) {
                if(!(torneio.getInicio().after(t.getFim()) || torneio.getFim().before(t.getInicio()))) {
                    conflitantes.add(t);
                }
            }
        }

        return conflitantes;
    }
}
