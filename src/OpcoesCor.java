

public enum OpcoesCor {
	BRANCO(1), CINZA(2), PRETO(3);
	
	private final int cor;

	private OpcoesCor(int cor) {
		this.cor = cor;
	}

	public int getCor() {
		return cor;
	}
	
}
