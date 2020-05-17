
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static ArrayList<Torneio> lerArquivo(String path) {
        ArrayList<Torneio> torneios = new ArrayList<Torneio>();
        try {
            File arq = new File(path);
            Scanner in = new Scanner(arq);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            while (in.hasNextLine()) {
                String linha = in.nextLine();
                String campos[] = linha.split("/");

                Date inicio = dateFormat.parse(campos[0]);
                int duracao = Integer.parseInt(campos[1]);
                String cidade = campos[2];
                String tipo = campos[3];
                int premio = Integer.parseInt(campos[4].replace(" $", ""));
                String vencedor = campos[5];

                torneios.add(new Torneio(inicio, duracao, premio, cidade, tipo, vencedor));
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao abrir arquivo: " + e.getMessage());
        } catch (ParseException ex) {
            System.err.println("Erro fazer parse de data: " + ex.getMessage());
        }
        return torneios;
    }

    public static int menu() {
        int opt;
        Scanner in = new Scanner(System.in);

        System.out.println("\n(1) Jogador que possui mais pontos no ranking");
        System.out.println("(2) Jogador que possui mais prêmios no ranking");
        System.out.println("(3) Ranking atual dos jogadores");
        System.out.println("(4) Total de torneios com datas conflitantes");
        System.out.println("(5) Torneios conflitantes");
        System.out.println("(6) Sair");
        System.out.print("Digite aqui: ");
        opt = in.nextInt();
        return opt;
    }

    public static void main(String[] args) {
        ArrayList<Torneio> torneios = lerArquivo("input.txt");
        Ranking ranking = new Ranking(torneios);
        int opt;
        Jogador j;
        ArrayList<Jogador> jogadores;

        do {
            opt = menu();
            switch (opt) {
                case 1:
                    j = ranking.ordenadosPorPonto().get(ranking.getJogadores().size() - 1);
                    System.out.println(String.format("\nMelhor jogador por pontos: %s | %d pontos.", j.getNome(), j.getPontos()));
                    break;
                case 2:
                    j = ranking.ordenadosPorPremios().get(ranking.getJogadores().size() - 1);
                    System.out.println(String.format("\nMelhor jogador por prêmios: %s | %d prêmios.", j.getNome(), j.getPremios()));
                    break;
                case 3:
                    jogadores = ranking.getJogadores();
                    Collections.reverse(jogadores);
                    System.out.println("\n# RANKING\n------------------------------------------------");
                    for (Jogador jog : jogadores) {
                        System.out.println(jog);
                    }
                    System.out.println("------------------------------------------------");
                    break;
                case 4:
                    int totalConflitantes = 0;
                    for (Torneio t : torneios) {
                        if (ranking.getTorneiosConflitantes(t).size() > 0) {
                            totalConflitantes++;
                        }
                    }
                    System.out.println("\nTotal de torneios com datas conflitantes: " + totalConflitantes);
                    break;
                case 5:
                    int i = 1;
                    System.out.println("\nLista de torneios conflitantes\n");
                    for (Torneio t : torneios) {
                        System.out.println(String.format("#%d - %s", i++, t));
                        ArrayList<Torneio> conflitantes = ranking.getTorneiosConflitantes(t);
                        System.out.println("CONFLITOS: ");
                        for (Torneio conf : conflitantes) {
                            System.out.println(conf);
                        }
                        System.out.println("------------------------------------------------------------------------------------");
                    }
                    break;
                case 6:
                    System.out.println("\n>> Programa encerrado.");
            }
        } while (opt != 6);

    }
}
