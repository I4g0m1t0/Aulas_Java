import java.util.Scanner;
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
                            System.out.println("Professor não encontrado!");
                        }
                        break;
                    case 3:
                        System.out.println("Digite o ID do aluno: ");
                        int IdAluno = scanner.nextInt();
                        System.out.println("Digite o nome do aluno: ");
                        String nomeAluno = scanner.next();
                        System.out.println("Digite a data de nascimento do aluno: ");
                        String DtNascAluno = scanner.next();
                        System.out.println("Digite o CPF do aluno: ");
                        String CpfAluno = scanner.next();
                        System.out.println("Digite o Id do curso do aluno: ");
                        int IdCursoAluno = scanner.nextInt();

                        Curso cursoAluno = buscaCurso(IdCursoAluno);

                        if (cursoAluno != null) {
                            Aluno aluno = new Aluno(IdAluno, nomeAluno, DtNascAluno, CpfAluno, cursoAluno);
                            alunos.add(aluno);
                            System.out.println("Aluno cadastrado.");
                        } else {
                            System.out.println("Curso não encontrado!.");
                        }
                        break;
                    case 4:
                        listarProfessores();
                        break;
                    case 5: 
                        listarCursos();
                        break;
                    case 6:
                        listarAlunos();
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
            System.out.println("Nome do professor: " + professor.Nome + 
                                "\nDepartamento: " + professor.Departamento);

            Integer contagemCurso = 0;

            for (Curso curso : cursos) {
                if (curso.IdProfessor.IdProfessor.equals(professor.IdProfessor)) {
                    contagemCurso++;
                }
            }

            System.out.println("Contagem de cursos desse professor: " + contagemCurso);
        }
    }

    private static void listarCursos() {
        System.out.println("Cursos cadastrados:");
        for (Curso curso : cursos) {
            System.out.println("Nome do curso: " + curso.Nome + 
                                "\nCarga horária: " + curso.CH + 
                                "\nNome do professor: " + curso.IdProfessor.Nome);
        }
    }

    private static void listarAlunos() {
        System.out.println("Alunos cadastrados:");
        for (Aluno aluno : alunos) {
            System.out.println("Nome do aluno: " + aluno.Nome + 
                                "\nData de nascimento: " + aluno.DtNasc + 
                                "\nCPF: " + aluno.CPF + 
                                "\nNome do curso: " + aluno.IdCurso.Nome);
        }
    }
}
