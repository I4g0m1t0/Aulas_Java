import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Menu {
    
    static ArrayList<Curso> cursos = new ArrayList<Curso>();
    static ArrayList<Aluno> alunos = new ArrayList<Aluno>();
    static ArrayList<Professor> professores = new ArrayList<Professor>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        
        try{
            do{
                System.out.println("\nSelecione uma opção:");
                System.out.println("\n[1] Cadastrar Professor");
                System.out.println("\n[2] Cadastrar Curso");
                System.out.println("\n[3] Cadastrar Aluno");
                System.out.println("\n[4] Listar Professores");
                System.out.println("\n[5] Listar Cursos");
                System.out.println("\n[6] Listar Alunos");
                System.out.println("\n[7] Encerrar");
                System.out.println("\nOpção: ");
                opcao = scanner.nextInt();

                if (opcao < 1 || opcao > 7) {
                    System.out.println("\nOpção inválida. Selecione uma opção válida."); //Para o caso de o usuário digitar uma opção inválida
                }

                switch (opcao) {
                    case 1:
                        System.out.println("Digite o ID do professor: ");
                        int IdProfessor = scanner.nextInt();
                        System.out.println("Digite o nome do professor: ");
                        String nomeProfessor = scanner.next();
                        System.out.println("Digite o departamento do professor: ");
                        String Departamento = scanner.next();

                        Professor professor = new Professor(IdProfessor, nomeProfessor, Departamento);
                        professores.add(professor);
                        break;
                    case 2:
                        System.out.println("Digite o ID do curso: ");
                        int IdCurso = scanner.nextInt();
                        System.out.println("Digite o nome do curso: ");
                        String nomeCurso = scanner.next();
                        System.out.println("Digite a carga horária do curso: ");
                        int CH = scanner.nextInt();
                        System.out.println("Digite o Id do professor do curso: ");
                        int IdProfessorCurso = scanner.nextInt();

                        Professor professorCurso = buscarProfessor(IdProfessorCurso);

                        if (professorCurso != null){
                            Curso curso = new Curso(IdCurso, nomeCurso, CH, professorCurso);
                            cursos.add(curso);
                        } else {
                            System.out.println("Professor não encontrado! Faça o cadastro novamente");
                        }
                        break;
                    case 3:
                        System.out.println("Digite o ID do aluno: ");
                        int IdAluno = scanner.nextInt();
                        System.out.println("Digite o nome do aluno: ");
                        String nomeAluno = scanner.next();
                        
                        //Como fiz para comparar a string https://www.alura.com.br/artigos/como-converter-string-para-date-em-java?srsltid=AfmBOor8xaPmxiB0JvWtRicWGnvk_55Q-qb5qlmhRxhOv7SghQOK01ej
                        
                        String DtNascAluno;

                        while (true) {
                            System.out.println("Digite a data de nascimento do aluno no formato dd/MM/yyyy: ");
                            DtNascAluno = scanner.next();

                            try {
                                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //Formato padrão
                                LocalDate data = LocalDate.parse(DtNascAluno, formato); //Compara a string com o formato
                                formato.format(data); //Formata a string
                                break; //Sai do loop se o formato estiver correto
                            } catch (DateTimeParseException e) {
                                System.out.println("Formato inválido ou data inexistente. Tente novamente."); //Captura a exceção e informa o usuário
                            }
                        }
                        

                        System.out.println("Digite o CPF do aluno (O cpf deve conter 11 caracteres): ");
                        String CpfAluno = scanner.next();

                        while (validaCPF(CpfAluno) != true) {
                            System.out.println("Digite o CPF do aluno (O cpf deve conter 11 caracteres): ");
                            CpfAluno = scanner.next();
                        }
                        
                        System.out.println("Digite o Id do curso do aluno: ");
                        int IdCursoAluno = scanner.nextInt();
                        calcularIdade(DtNascAluno); //Calcula a idade do aluno, mas só funciona se a string for informada como dd//MM//yyyy

                        Curso cursoAluno = buscaCurso(IdCursoAluno);

                        if (cursoAluno != null) {
                            Aluno aluno = new Aluno(IdAluno, nomeAluno, DtNascAluno, CpfAluno, cursoAluno);
                            alunos.add(aluno);
                            System.out.println("Aluno cadastrado.");
                        } else {
                            System.out.println("Curso não encontrado! Faça o cadastro novamente");
                        }
                        break;
                    case 4:
                        if (professores == null) {
                            System.out.println("Ainda não existem professores cadastrados");
                        } else {
                            listarProfessores();
                        }
                        break;
                    case 5: 
                        if (cursos == null) {
                            System.out.println("Ainda não existem cursos cadastrados");
                        } else {
                            listarCursos();
                        }
                        break;
                    case 6:
                        if (alunos == null) {
                            System.out.println("Ainda não existem alunos cadastrados");
                        } else {
                            listarAlunos();
                        }
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

    private static Professor buscarProfessor(int id) {
        for (Professor professor : professores) {
            if (professor.IdProfessor.equals(id)) {
                return professor;
            }
        }
        return null;
    }

    private static Curso buscaCurso(int id) {
        for (Curso curso : cursos) {
            if (curso.IdCurso.equals(id)) {
                return curso;
            }
        }
        return null;
    }

    private static void listarProfessores() {
        System.out.println("Professores cadastrados:");
        for (Professor professor : professores) {
            System.out.println("\nNome do professor: " + professor.Nome + 
                                " Departamento: " + professor.Departamento);

            Integer contagemCurso = 0;
            Integer contagemAlunos = 0;

            for (Curso curso : cursos) {
                if (curso.IdProfessor.IdProfessor.equals(professor.IdProfessor)) {
                    contagemCurso++;
                    for (Aluno aluno : alunos) {
                        if (aluno.IdCurso.equals(curso.IdCurso)) { // Ajustado para verificar o IdCurso
                            contagemAlunos++;
                        }
                    }
                }   
            }

            System.out.println("Contagem de cursos desse professor: " + contagemCurso);
            System.out.println("Contagem de cursos desse professor: " + contagemAlunos);

            //Desafio 03
            int contagemCHCurso = 0;

            for (Curso curso : cursos) {
                if (curso.IdProfessor.IdProfessor.equals(professor.IdProfessor)) {
                    contagemCHCurso += curso.CH;
                }
            }

            System.out.println("A carga horária total desse professor é: " + contagemCHCurso);
        }
    }

    private static void listarCursos() {
        System.out.println("Cursos cadastrados:");
        for (Curso curso : cursos) {
            System.out.println("\nNome do curso: " + curso.Nome + 
                                " Carga horária: " + curso.CH + 
                                " Nome do professor: " + curso.IdProfessor.Nome);
        }
    }

    private static void listarAlunos() {
        System.out.println("Alunos cadastrados:");
        for (Aluno aluno : alunos) {
            System.out.println("\nNome do aluno: " + aluno.Nome + 
                                " Data de nascimento: " + aluno.DtNasc + 
                                " CPF: " + aluno.CPF + 
                                " Nome do curso: " + aluno.IdCurso.Nome);
        }
    }

    // cálculo da idade baseada no vídeo da URL https://www.youtube.com/watch?v=SU3Tp_y_0es

    private static void calcularIdade(String DtNascAluno){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //Formatação

        try {
            LocalDate dataConvertida = LocalDate.parse(DtNascAluno, formatter); //Formata a string para date
            LocalDate dataAtual = LocalDate.now(); //Pega a data de hoje
            Period idade = Period.between(dataConvertida, dataAtual); //Calcula o tempo que passou entre o dia de hoje e o dia de nascimento

            System.out.println("A idade do aluno é: " + idade.getYears() + " anos");
        } catch (Exception e) {
            System.out.println("Formato de data inválido. Por favor, tente novamente.");
        }
    }

    private static boolean validaCPF(String CPF) {
        if (CPF.length() != 11) {
            return false;
        } else {
            return true;
        }     
    }
}
