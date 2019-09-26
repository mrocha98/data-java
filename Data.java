package exercicio5;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/*Exercício 5. Crie uma classe para representar datas:
1. Represente uma data usando três atributos: o dia, o mês, e o ano;
2. Sua classe deve ter um construtor que inicializa os três atributos e verifica a validade dos
valores fornecidos;
3. Forneça um construtor sem parâmetros que inicializa a data com a data atual fornecida
pelo Sistema Operacional (Usar: https://goo.gl/LMRXik);
4. Forneça um método set() um get() para cada atributo;
5. Forneça o método toString() para retornar uma representação da data como string.
Considere que a data deve ser formatada mostrando o dia, o mês e o ano separados por
barra (/);
6. Forneça uma operação para avançar uma data para o dia seguinte;
7. Escreva um aplicativo de teste que demonstra as capacidades da classe; e
8. Garanta que uma instância desta classe sempre esteja em um estado consistente.*/

public class Data {
	private int dia;
	private int mes;
	private int ano;
	private static List<Integer> mesesCom31Dias = new ArrayList<>(List.of(1, 3, 5, 7, 8, 10, 12));

	public Data(int dia, int mes, int ano) {
		if (dataEhValida(dia, mes, ano)) {
			this.dia = dia;
			this.mes = mes;
			this.ano = ano;
		} else {
			System.out.println("Data fornecida é inválida, definindo para 01/01/2000");
			this.dia = 1;
			this.mes = 1;
			this.ano = 2000;
		}
	}

	public Data() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		this.dia = calendar.get(Calendar.DATE);
		this.mes = calendar.get(Calendar.MONTH) + 1;
		this.ano = calendar.get(Calendar.YEAR);
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		if (dataEhValida(dia, this.getMes(), this.getAno()))
			this.dia = dia;
		else
			System.out.println("Não foi possível setar o dia para " + dia + " pois a data não seria válida.");
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		if (dataEhValida(this.getDia(), mes, this.getAno()))
			this.mes = mes;
		else
			System.out.println("Não foi possível setar o mes para " + mes + " pois a data não seria válida.");
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		if (dataEhValida(this.getDia(), this.getMes(), ano))
			this.ano = ano;
		else
			System.out.println("Não foi possível setar o ano para " + ano + " pois a data não seria válida.");
	}

	public boolean anoEhBissexto(int ano) {
		return ((ano % 400 == 0) || ((ano % 4 == 0) && (ano % 100 != 0)));
	}

	public int getTotalDeDiasNoMes(int mes, int ano) {
		if (mes == 2) {
			if (anoEhBissexto(ano))
				return 29;
			return 28;
		}
		if (mesesCom31Dias.contains(mes))
			return 31;
		return 30;
	}

	public boolean diaEhValido(int dia) {
		return (dia > 0 && dia <= 31);
	}

	public boolean mesEhValido(int mes) {
		return (mes > 0 && mes <= 12);
	}

	public boolean anoEhValido(int ano) {
		return (ano > 0);
	}

	public boolean totalDeDiasEhValido(int dia, int mes, int ano) {
		return (dia <= getTotalDeDiasNoMes(mes, ano));
	}

	public boolean dataEhValida(int dia, int mes, int ano) {
		if (!diaEhValido(dia) || !mesEhValido(mes) || !anoEhValido(ano))
			return false;
		if (!totalDeDiasEhValido(dia, mes, ano))
			return false;
		return true;
	}

	public void avancaData() {
		int totalDeDiasNoMes = getTotalDeDiasNoMes(this.getMes(), this.getAno());
		boolean ultimoDiaDoMes = (this.getDia() == totalDeDiasNoMes);
		boolean ultimoDiaDoAno = (ultimoDiaDoMes && this.getMes() == 12);
		if (ultimoDiaDoAno) {
			this.ano++;
			this.mes = 1;
			this.dia = 1;
		} else if (ultimoDiaDoMes) {
			this.mes++;
			this.dia = 1;
		} else
			this.dia++;
	}

	@Override
	public String toString() {
		String zeroDia = "";
		String zeroMes = "";
		if (getDia() < 10)
			zeroDia = "0";
		if (getMes() < 10)
			zeroMes = "0";
		String data = (zeroDia + getDia() + "/" + zeroMes + getMes() + "/" + getAno());
		return data;
	}

}
