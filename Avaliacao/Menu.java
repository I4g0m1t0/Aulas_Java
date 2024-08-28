import java.util.Arrays; //Para usar arrays
import java.util.HashMap; //Para usar o map
import java.util.Scanner; //Para usar o scanner

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Variável para selecionar a opção do menu
        int opcao;

        //Criando o Map
        HashMap<String, Character> map = new HashMap<>();

        //Variáveis para cadastro do aluno
        String aluno;
        char nota;

        //Array para os conceitos e para contar 
        Character[] notas = {'O', 'B', 'S', 'I'}; //Vetor para comparar com a nota inserida
        Integer[] contador = {0, 0, 0, 0}; //Vetor para contar

        //Para o loop das notas na hora de inserir
        boolean notaCerta = false;

        //Para a busca do conceito
        char conceitoBusca;
        boolean notaBusca = false;

        try {
            do{
                System.out.println("\nSelecione uma opção:");
                System.out.println("\n[1] Inserir um novo aluno");
                System.out.println("\n[2] Exibir os alunos");
                System.out.println("\n[3] Exibir a quantidade de cada conceito");
                System.out.println("\n[4] Exibir os alunos de acordo com o conceito");
                System.out.println("\n[5] Encerrrar");
                System.out.println("\nOpção: ");
                opcao = scanner.nextInt();

                if (opcao < 1 || opcao > 5) {
                    System.out.println("\nOpção inválida. Selecione uma opção válida."); //Para o caso de o usuário digitar uma opção inválida
                }

                switch (opcao) {
                    case 1:
                            System.out.println("\nDigite o nome do aluno: ");
                            aluno = scanner.next().toUpperCase(); //Escaneia o nome do aluno e transforma em maiúsculo
                            
                            do {
                                System.out.println("\nDigite o conceito do aluno: ");
                                nota = Character.toUpperCase(scanner.next().charAt(0)); //Escaneia o conceito do aluno e transforma em maiúsculo
                                
                                //If para a verificação do conceito digitado, caso não seja uma entrada válida, solicita novamente o conceito
                                if (Arrays.asList(notas).contains(nota)) {
                                    notaCerta = true;
                                    // Adiciona o aluno e a nota ao HashMap
                                    map.put(aluno, nota);

                                    if (nota == 'O') {
                                        contador[0]++; //Adiciona +1 para o array na posição 0, respectivamente do conceito O
                                    } else if (nota == 'B') {
                                        contador[1]++; //Adiciona +1 para o array na posição 1, respectivamente do conceito B
                                    } else if (nota == 'S'){
                                        contador[2]++; //Adiciona +1 para o array na posição 2, respectivamente do conceito S
                                    } else if (nota == 'I'){
                                        contador[3]++; //Adiciona +1 para o array na posição 3, respectivamente do conceito I
                                    }
                                } else {
                                    System.out.println("Conceito inválido, digite novamente.");
                                }
                            } while (!notaCerta);
                            break;           
                    case 2:
                        if (map.isEmpty()) {
                            System.out.println("\nNenhum aluno inserido ainda."); //Para o caso de nenhum aluno ter sido cadastrado
                        } else {
                            System.out.println("\nAlunos e conceitos inseridos:");
                            for (String key : map.keySet()) {
                                System.out.println("Aluno: " + key + ", Conceito: " + map.get(key));
                            }
                        }
                        break;
                    case 3:
                        if (map.isEmpty()) {
                            System.out.println("\nNenhum aluno inserido ainda."); //Para o caso de nenhum aluno ter sido cadastrado
                        } else {
                            System.out.println("\nO número de conceitos O é igual a: " + contador[0]);
                            System.out.println("O número de conceitos B é igual a: " + contador[1]);
                            System.out.println("O número de conceitos S é igual a: " + contador[2]);
                            System.out.println("O número de conceitos I é igual a: " + contador[3]);
                        }
                        break;
                    case 4:
                        if (map.isEmpty()) {
                            System.out.println("\nNenhum aluno inserido ainda."); //Para o caso de nenhum aluno ter sido cadastrado
                        } else {
                            do {
                                System.out.println("\nDigite o conceito que você quer que seja procurado: ");
                                conceitoBusca = Character.toUpperCase(scanner.next().charAt(0));
                                
                                if (Arrays.asList(notas).contains(conceitoBusca)) {
                                    notaBusca = true;
                                } else {
                                    System.out.println("Conceito inválido, digite novamente.");
                                }
                            } while (!notaBusca);

                            boolean encontrouAluno = false; //Para verificar se algum aluno foi encontrado

                            System.out.println("\nAlunos com o conceito " + conceitoBusca + ":");
                            for (String key : map.keySet()) {
                                if (map.get(key) == conceitoBusca) {
                                    System.out.println("Aluno: " + key + ", Conceito: " + map.get(key));
                                    encontrouAluno = true; //Aluno com o conceito foi encontrado
                                }
                            }
                            if (!encontrouAluno) {
                                System.out.println("\nNenhum aluno com esse conceito foi inserido ainda.");
                            }
                        }
                        break;
                    case 5:
                        System.out.println("\nAcabou...");
                        break;
                }
            } while (opcao != 5);
        } catch (Exception e) {
            System.out.println("\nVocê digitou valores inválidos. Execute novamente.");
        }
        finally {
            scanner.close();
        }
    }
}
