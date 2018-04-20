

public class Jogo {
	
	private int cont = 0;
	private int n = (int)(Math.random()*100); //entre 0 e 99
	private boolean fim = false;
	private int jogados[] = {-1, -1, -1, -1, -1, -1, -1};
	
	public Jogo() {
		super();
	}

	public int geraNewNum() {
		int n = (int)(Math.random()*100); //entre 0 e 1
		return n;
	}
	
	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public int getN() {
		return this.n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public boolean isFim() {
		return fim;
	}

	public void setFim(boolean fim) {
		this.fim = fim;
	}

	public int[] getJogados() {
		return this.jogados;
	}

	public void setJogados(int[] jogados) {
		this.jogados = jogados;
	}

	public String verificaSorteio(int num) {
		String texto1 = "", texto2 = "", resultado = "";
		this.cont++;
		
			if(this.n < num )
				 texto1="O número sorteado é menor e ";
			else if(this.n > num)
				 texto1="O número sorteado é maior e ";
			 if (this.n != num) {
				 if(Math.abs(this.n - num) <= 5)
					 texto2 = "tá quente!";
				 else
					 texto2 = "tá frio!";
			 }
			 resultado = cont + "ª Tentativa!\n" + texto1 + texto2 + "" ;
			 if(this.n == num && this.cont <=6) {
				  resultado = "Parabéns!\nVocê acertou na " + cont + "ª tentativa";
				  this.fim = true;
			 }
			 if(this.n != num && this.cont == 6) {
				 resultado = "Você esgotou o número de tentativas!\nO número sorteado foi: " + n;
				 this.fim = true;
			 }
			return resultado;
	}
	
	public boolean validaNum(int num) {
		if (num < 0 || num > 99)
			return false;
		else 
			
			return true;
	}
	
	public boolean verificaRepetido(int num) {
		//System.out.println(jogados.length);
		boolean flag = false;
		for(int i=0; i < jogados.length; i++) {
			if (num == jogados[i]) {
				flag = true;
				break;
			}else {
				if(jogados[i] == -1) {
					jogados[i] = num;
					break;
				}
			}
		}
		return flag;
	}
}
