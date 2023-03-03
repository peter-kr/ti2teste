package bd_carros;

import java.util.Scanner;

public class Principal {
	
	// Menu inicial
	private static boolean menu (Scanner sc, DAO dao) {
		System.out.println("Escolha uma opção:\n");
		System.out.println("1) Listar carros");
		System.out.println("2) Inserir um carro");
		System.out.println("3) Excluir um carro");
		System.out.println("4) Atualizar um carro");
		System.out.println("5) Pesquisar carros por nome do proprietário");
		System.out.println("6) Pesquisar carros por modelo");
		System.out.println("7) Sair");
		String res = sc.nextLine();
		switch (res) {
			case "1":
				System.out.println("\nLista de carros cadastrados:\n");
				Carro[] carros = dao.getCarros();
				for(int i=0; i<carros.length; i++) {
					System.out.println(carros[i].toString());
				}
				System.out.println("\nPressione enter para continuar...");
				sc.nextLine();
				return true;
				
			case "2":
				Carro carro = new Carro();
				System.out.println("\nDigite os dados do carro que você deseja inserir.\n");
				System.out.println("Id:");
				carro.setId(sc.nextInt());
				sc.nextLine();
				System.out.println("Nome do proprietário:");
				carro.setNomeDono(sc.nextLine());
				System.out.println("Modelo do carro:");
				carro.setModelo(sc.nextLine());
				System.out.println("Placa do carro:");
				carro.setPlaca(sc.nextLine());
				if(dao.inserirCarro(carro)) {
					System.out.println("\nCarro inserido com sucesso:\n" + carro.toString());
					System.out.println("\nPressione enter para continuar...");
					sc.nextLine();
				}
				return true;
				
			case "3":
				System.out.println("\nDigite a id do carro que você deseja excluir:");
				int id = sc.nextInt();
				sc.nextLine();
				if(dao.excluirCarro(id)) {
					System.out.println("\nCarro excluído com sucesso.");
					System.out.println("\nPressione enter para continuar...");
					sc.nextLine();
				}
				return true;
				
			case "4":
				Carro carro2 = new Carro();
				System.out.println("\nDigite os dados do carro que você deseja alterar.\n");
				System.out.println("Id:");
				carro2.setId(sc.nextInt());
				sc.nextLine();
				System.out.println("Nome do proprietário:");
				carro2.setNomeDono(sc.nextLine());
				System.out.println("Modelo do carro:");
				carro2.setModelo(sc.nextLine());
				System.out.println("Placa do carro:");
				carro2.setPlaca(sc.nextLine());
				if(dao.atualizarCarro(carro2)) {
					System.out.println("\nCarro alterado com sucesso:\n" + carro2.toString());
					System.out.println("\nPressione enter para continuar...");
					sc.nextLine();
				}
				return true;
				
			case "5":
				String dono;
				System.out.println("\nDigite o nome que você deseja pesquisar:");
				dono = sc.nextLine();
				System.out.println("\nLista de carros de " + dono + ":\n");
				Carro[] carros2 = dao.getCarroNome(dono);
				for(int i=0; i<carros2.length; i++) {
					System.out.println(carros2[i].toString());
				}
				System.out.println("\nPressione enter para continuar...");
				sc.nextLine();
				return true;
				
			case "6":
				String modelo;
				System.out.println("\nDigite o nome do modelo que você deseja pesquisar:");
				modelo = sc.nextLine();
				System.out.println("\nLista de carros do tipo " + modelo + ":\n");
				Carro[] carros3 = dao.getCarroModelo(modelo);
				for(int i=0; i<carros3.length; i++) {
					System.out.println(carros3[i].toString());
				}
				System.out.println("\nPressione enter para continuar...");
				sc.nextLine();
				return true;
				
			case "7":
				System.out.println("\nSaindo...");
				return false;
				
			default:
				System.out.println("\nSeleção inválida.\n");
				return true;
				
		}
	}

	// Main
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();
		
		Scanner sc = new Scanner(System.in);
		
		while(menu(sc, dao)) {
			
		}
		
		
		sc.close();
		dao.close();
		
	}

}
