package bd_carros;

public class Carro {
	private int id;
	private String nomeDono;
	private String modelo;
	private String placa;
	
	public Carro () {
		this.id = -1;
		this.nomeDono = "";
		this.modelo = "";
		this.placa = "0000000";
	}
	
	public Carro (int id, String nomeDono, String modelo, String placa) {
		this.id = id;
		this.nomeDono = nomeDono;
		this.modelo = modelo;
		this.placa = placa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeDono() {
		return nomeDono;
	}

	public void setNomeDono(String nomeDono) {
		this.nomeDono = nomeDono;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", nomeDono=" + nomeDono + ", modelo=" + modelo + ", placa=" + placa;
	}
}
