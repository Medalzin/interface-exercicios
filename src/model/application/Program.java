package model.application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.OnlinePaymentService;
import model.services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println("Entre com os dados do contrato: ");
        System.out.print("Numero: ");
        int number = scanner.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        scanner.nextLine();
        LocalDate date = LocalDate.parse(scanner.nextLine(), fmt);
        System.out.print("Valor do contrato: ");
        double totalValue = scanner.nextDouble();

        Contract contrato = new Contract(number, date, totalValue);

        System.out.print("Entre com o numero de parcelas: ");
        int parcelas = scanner.nextInt();

        ContractService contractService = new ContractService(new PaypalService());

        contractService.processContract(contrato, parcelas);

        System.out.println("Parcelas: ");
        for (Installment installments : contrato.getInstallments()){
            System.out.println(installments);
        }

        scanner.close();
    }
}
