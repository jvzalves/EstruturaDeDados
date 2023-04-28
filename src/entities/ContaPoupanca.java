package entities;

import enums.TipoDeConta;

public class ContaPoupanca extends Conta {

	private Double interestRate;

	public ContaPoupanca(String name, String email, Double balance, Integer cpf, Double interestRate) {
		super(name, email, cpf, TipoDeConta.Poupança);
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public void updateBalance() {
		balance += balance * interestRate;
	}

}