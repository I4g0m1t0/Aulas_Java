import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    
    static ArrayList<Categoria> categorias = new ArrayList<Categoria>();
    static ArrayList<Tutor> tutores = new ArrayList<Tutor>();
    static ArrayList<Animal> animais = new ArrayList<Animal>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        
        try{
            do{
                System.out.println("\nSelecione uma opção:");
                System.out.println("\n[1] Cadastrar Categoria");
                System.out.println("\n[2] Cadastrar Tutor");
                System.out.println("\n[3] Cadastrar Animal");
                System.out.println("\n[4] Listar Categoria");
                System.out.println("\n[5] Listar Tutor");
                System.out.println("\n[6] Listar Animal");
                System.out.println("\n[7] Encerrar");
                System.out.println("\nOpção: ");
                opcao = scanner.nextInt();

                if (opcao < 1 || opcao > 7) {
                    System.out.println("\nOpção inválida. Selecione uma opção válida."); //Para o caso de o usuário digitar uma opção inválida
                }

                switch (opcao) {
                    case 1:
                        System.out.println("Digite o ID da categoria: ");
                        int IdCategoria = scanner.nextInt();
                        System.out.println("Digite o nome da categoria: ");
                        String Descricao = scanner.next();

                        Categoria categoria = new Categoria(IdCategoria, Descricao);
                        categorias.add(categoria);
                        break;
                    case 2:
                        System.out.println("Digite o ID do tutor: ");
                        int IdTutor = scanner.nextInt();
                        System.out.println("Digite o nome do tutor: ");
                        String nomeTutor = scanner.next();
                        System.out.println("Digite o telefone do tutor: ");
                        String telefoneTutor = scanner.next();
                        System.out.println("Digite o email do tutor: ");
                        String emailTutor = scanner.next();

                        Tutor tutor = new Tutor(IdTutor, nomeTutor, telefoneTutor, emailTutor);
                        tutores.add(tutor);
                        break;
                    case 3:
                        System.out.println("Digite o ID do animal: ");
                        int IdAnimal = scanner.nextInt();
                        System.out.println("Digite o nome do animal: ");
                        String nomeAnimal = scanner.next();
                        System.out.println("Digite a raça do animal: ");
                        String racaAnimal = scanner.next();
                        System.out.println("Digite peso em kg do animal: ");
                        Double pesoAnimal = scanner.nextDouble();
                        System.out.println("Digite o Id da categoria do animal: ");
                        int IdCategoriaAnimal = scanner.nextInt();
                        System.out.println("Digite o Id do tutor do animal: ");
                        int IdTutorAnimal = scanner.nextInt();

                        Categoria categoriaAnimal = buscarCategoria(IdCategoriaAnimal);
                        Tutor tutorAnimal = buscaTutor(IdTutorAnimal);

                        if (categoriaAnimal != null && tutorAnimal != null) {
                            Animal animal = new Animal(IdAnimal, nomeAnimal, racaAnimal, pesoAnimal, categoriaAnimal, tutorAnimal);
                            animais.add(animal);
                            System.out.println("Animal cadastrado.");
                        } else {
                            System.out.println("Categoria ou tutor não encontrados.");
                        }
                        break;
                    case 4:
                        listarCategorias();
                        break;
                    case 5: 
                        listarTutores();
                        break;
                    case 6:
                        listarAnimais();
                        break;
                    case 7:
                        System.out.println("Encerrando...");
                        break;
                    default:
                        break;
                }

            } while (opcao != 7);
        } catch (Exception e) {
            System.out.println("\nVocê digitou valores inválidos. Execute novamente.");
        }
        finally {
            scanner.close();
        }
    }

    private static Categoria buscarCategoria(int id) {
        for (Categoria categoria : categorias) {
            if (categoria.IdCategoria.equals(id)) {
                return categoria;
            }
        }
        return null;
    }

    private static Tutor buscaTutor(int id) {
        for (Tutor tutor : tutores) {
            if (tutor.IdTutor.equals(id)) {
                return tutor;
            }
        }
        return null;
    }

    private static void listarCategorias() {
        System.out.println("Categorias cadastradas:");
        for (Categoria categoria : categorias) {
            System.out.println("ID: " + categoria.IdCategoria + ", Descrição: " + categoria.Descricao);

            Integer contagemAnimais = 0;
            
            for (Animal animal : animais){
                if (animal.IdCategoria.IdCategoria.equals(categoria.IdCategoria)) {
                    contagemAnimais++;
                }
            }
            System.out.println("Contagem de animais nessa categoria: " + contagemAnimais);
        }
    }

    private static void listarTutores() {
        System.out.println("Tutores cadastrados:");
        for (Tutor tutor : tutores) {
            System.out.println("ID: " + tutor.IdTutor + ", Nome: " + tutor.Nome);

            Integer contagemAnimais = 0;

            for (Animal animal : animais) {
                if (animal.IdTutor.IdTutor.equals(tutor.IdTutor)) {
                    contagemAnimais++;
                }
            }

            System.out.println("Contagem de animais desse tutor: " + contagemAnimais);
        }
    }

    private static void listarAnimais() {
        System.out.println("Animais cadastrados:");
        for (Animal animal : animais) {
            System.out.println("ID: " + animal.IdAnimal +
                               ", Nome: " + animal.Nome +
                               ", Nome do Tutor: " + animal.IdTutor.Nome +
                               ", Descrição da Categoria: " + animal.IdCategoria.Descricao);
        }
    }
}
