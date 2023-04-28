package application;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import entities.Conta;
import entities.ContaPoupanca;
import entities.Lista;
import enums.TipoDeConta;

public class Program {

	static NumberFormat fmt = NumberFormat.getCurrencyInstance(); // imprimindo o saldo atualizado formatado como moeda
																	// brasileira
	private static Lista ListaContas = new Lista();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("------------------------------------------------------------");
		System.out.println("------------------Bem-vindo ao POBREBANK--------------------");
		System.out.println("------------------------------------------------------------");
		System.out.println("--------Entre com os dados para criar à sua conta-----------");
		System.out.println();
		System.out.print("Digite seu nome: ");
		String name = sc.nextLine();
		System.out.print("Insira seu Email: ");
		String email = sc.nextLine();
		System.out.print("Crie uma senha: ");
		int cpf = sc.nextInt();
		sc.nextLine(); // consome a quebra de linha deixada pelo nextInt()
		System.out.print("Escolha o tipo de conta: ");
		String tipoConta = sc.nextLine();

		Conta conta = new Conta(name, email, cpf, TipoDeConta.valueOf(tipoConta));
		ListaContas.insert(conta);
		System.out.println();
		System.out.print("Conta criada com sucesso!");
		System.out.println();
		terminal(conta);

	}

	private static void terminal(Conta conta) {
		System.out.println();
		System.out.println("| 1<---  Depósito          |");
		System.out.println("| 2<---  Saque             |");
		System.out.println("| 3<---  Consultar Conta   |");
		System.out.println("| 4<---  Sair              |");

		int option = sc.nextInt();

		if (option == 1) {
			System.out.print("Digite o valor a ser depositado:");
			double depositValue = sc.nextDouble();
			conta.deposit(depositValue); // chamando o método deposit() da instância conta

			System.out.println("Saldo atual: " + fmt.format(conta.getBalance()));
			System.out.println();
			terminal(conta);
		}

		else if (option == 2) {
			System.out.print("Digite o valor a ser sacado:");
			double withdrawValue = sc.nextDouble();
			boolean isContaCorrente = conta instanceof Conta; // verifica se a conta é corrente

			double withdrawnAmount = conta.withDraw(withdrawValue); // chamando o método withdraw() da instância conta e
																	// passando o valor booleano

			if (withdrawnAmount > 0) {
				if (conta instanceof ContaPoupanca) { // verifica se a conta é uma conta poupança
					((ContaPoupanca) conta).updateBalance(); // atualiza o saldo com juros
					System.out.println("Saldo atual: " + fmt.format(conta.getBalance())); // imprimindo o saldo
																							// atualizado em formato da
																							// moeda brasileira
				} else {
					if (isContaCorrente) { // verifica se a conta é corrente
						double tax = 5.0; // taxa de saque
						if (conta instanceof ContaPoupanca) {
							tax = 5.0; // se for uma conta poupança, a taxa é zero
						}
						withdrawnAmount -= tax;
					}
					System.out.println("Saque realizado com sucesso!");
					System.out.println();
					System.out.println("Saldo atual: " + fmt.format(conta.getBalance())); // imprimindo o saldo
																							// atualizado em formato da
																							// moeda brasileira
				}
			}
			System.out.println();
			terminal(conta);
		} else if (option == 3) {

			consultarConta(ListaContas);
			System.out.println();
			terminal(conta);

		} else if (option == 4) {

			sair();
		}

		else {
			System.out.println("Escolha uma operação válida!");
			terminal(conta);
		}
	}

	private static void consultarConta(Lista ListaContas) {
		if (ListaContas != null && ListaContas.getFirst() != null) {
			Locale ptBr = new Locale("pt", "BR");
			NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);

			ListaContas.last();
			do {
				Conta conta = (Conta) ListaContas.info();
				System.out.println(conta);
				System.out.println("Saldo atual: " + nf.format(conta.getBalance()));
				ListaContas.next();
			} while (ListaContas.getCurrent() != null);
		}
	}

	private static void sair() {
		System.out.println("Obrigado por usar o nosso banco!");

	}

}