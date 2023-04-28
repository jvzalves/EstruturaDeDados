package entities;

import enums.TipoDeConta;

public class Conta {

	private String name;
	private String email;
	protected Double balance;
	private Integer cpf;
	private TipoDeConta tipoDeConta;

	public Conta() {

	}

	public Conta(String name, String email, Integer cpf, TipoDeConta tipoDeConta) {
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.tipoDeConta = tipoDeConta;
		this.balance = 0.0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getBalance() {
		return balance;
	}

	public Integer getCpf() {
		return cpf;
	}

	public TipoDeConta getTipoDeConta() {
		return tipoDeConta;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public double withDraw(double value) {
		double tax = tipoDeConta == TipoDeConta.Corrente ? 5.0 : 0.0;
		double totalValue = value + tax;
		if (balance >= totalValue) {
			balance -= totalValue;
			return value;
		} else {
			return 0.0;
		}
	}

    @Override
    public String toString() {
    	return 
        "Nome: " + getName() + "\n" +
    	"Email: " + getEmail() + "\n" +
    	"Tipo de Conta: " + getTipoDeConta();
    	}
}	