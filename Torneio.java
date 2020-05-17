
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Torneio {

    private Date inicio, fim;
    private int duracao, premio;
    private String cidade, tipo, vencedor;

    public Torneio(Date inicio, int duracao, int premio, String cidade, String tipo, String vencedor) {
        this.inicio = inicio;
        this.duracao = duracao;
        this.premio = premio;
        this.cidade = cidade;
        this.tipo = tipo;
        this.vencedor = vencedor;

        Calendar cal = Calendar.getInstance();
        cal.setTime(inicio);
        cal.add(Calendar.DATE, duracao);
        this.fim = cal.getTime();
    }

    public Date getInicio() {
        return this.inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return this.fim;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getPremio() {
        return this.premio;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVencedor() {
        return this.vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("Inicio: %-10s | Fim: %-10s | Cidade: %-10s | Vencedor: %s", dateFormat.format(this.inicio),dateFormat.format(this.fim), this.cidade, this.vencedor);
    }

}
