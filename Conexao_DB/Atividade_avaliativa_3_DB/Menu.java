import java.util.Scanner;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Menu {

    public static void main(String[] args) throws Exception {
        final String url = "jdbc:mysql://localhost:3306/escola";
        final String user = "root";
        final String password = "";
        
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
                        try{
                            System.out.println("Digite o nome do professor: ");
                            String nomeProfessor = scanner.next();
                            System.out.println("Digite o departamento do professor: ");
                            String Departamento = scanner.next();

                            Connection con = DriverManager.getConnection(url, user, password);
                            Statement stm = con.createStatement();
                            boolean sql = stm.execute("insert into professor "
                                        + "(Nome, Departamento) values "
                                        + "('"+nomeProfessor+"', '"+Departamento+"')");
                            if (!sql) {
                                System.out.println("Falha na execução");
                            }
                            con.close();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        try{
                            System.out.println("Digite o nome do curso: ");
                            String nomeCurso = scanner.next();
                            System.out.println("Digite a carga horária do curso: ");
                            int CH = scanner.nextInt();
                            System.out.println("Digite o Id do professor do curso: ");
                            int IdProfessorCurso = scanner.nextInt();

                            Connection con = DriverManager.getConnection(url, user, password);
                            Statement stm = con.createStatement();
                            ResultSet sql = stm.executeQuery("SELECT * FROM professor WHERE IdProfessor = '"+IdProfessorCurso+"';");

                            if (sql.next()){
                                boolean query = stm.execute("insert into curso "
                                            + "(nomeCurso, CH, IdProfessorCurso) values "
                                            + "('"+nomeCurso+"', '"+CH+"', '"+IdProfessorCurso+"')");
                                if (!query) {
                                    System.out.println("Curso inserido com sucesso!");
                                } else {
                                    System.out.println("Falha na execução.");
                                }
                            } else {
                                System.out.println("Professor não encontrado! Faça o cadastro novamente");
                            }
                            con.close();
                        } catch (SQLException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        try{
                            System.out.println("Digite o nome do aluno: ");
                            String nomeAluno = scanner.next();

                            System.out.println("Digite a data de nascimento do aluno no formato dd/MM/yyyy: ");
                            String DtNascAluno = scanner.next();

                            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato esperado
                            LocalDate data = LocalDate.parse(DtNascAluno, formato);
                            String dataFormatada = data.toString(); //formato yyyy-MM-dd

                            System.out.println("Digite o CPF do aluno (O cpf deve conter 11 caracteres): ");
                            String CpfAluno = scanner.next();

                            while (validaCPF(CpfAluno) != true) {
                                System.out.println("Digite o CPF do aluno (O cpf deve conter 11 caracteres): ");
                                CpfAluno = scanner.next();
                            }
                            
                            System.out.println("Digite o Id do curso do aluno: ");
                            int IdCursoAluno = scanner.nextInt();

                            Connection con = DriverManager.getConnection(url, user, password);
                            Statement stm = con.createStatement();
                            ResultSet sql = stm.executeQuery("SELECT * FROM curso WHERE IdCurso = '"+IdCursoAluno+"';");

                            if (sql.next()){
                                boolean query = stm.execute("insert into aluno "
                                            + "(nomeAluno, DtNascAluno, CpfAluno, IdCursoAluno) values "
                                            + "('"+nomeAluno+"', '"+dataFormatada+"', '"+CpfAluno+"', '"+IdCursoAluno+"')");
                                if (!query) {
                                    System.out.println("Aluno inserido com sucesso!");
                                } else {
                                    System.out.println("Falha na execução.");
                                }
                            } else {
                                System.out.println("Curso não encontrado! Faça o cadastro novamente");
                            }
                            con.close();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            Connection con = DriverManager.getConnection(url, user, password);
                            Statement stm = con.createStatement();
                            ResultSet sql = stm.executeQuery("SELECT * FROM professor;");
                            while (sql.next()) {
                                System.out.println(new Professor(
                                    sql.getInt("IdProfessor"),
                                    sql.getString("Nome"),
                                    sql.getString("Departamento")  // Assumindo que a coluna se chama 'departamento'
                                ));
                            }
                            con.close();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5: 
                        try {
                            Connection con = DriverManager.getConnection(url, user, password);
                            Statement stm = con.createStatement();
                            ResultSet sql = stm.executeQuery("SELECT * FROM curso;");
                            while (sql.next()) {
                            // Recuperando os dados do curso
                                Integer idCurso = sql.getInt("IdCurso");
                                String nomeCurso = sql.getString("Nome");
                                Integer chCurso = sql.getInt("CH");
                                
                                // Recuperando o ID do Professor associado ao curso
                                Integer professorId = sql.getInt("IdProfessor");
                                
                                // Criando o objeto Professor com o ID recuperado
                                Statement stmProfessor = con.createStatement();
                                ResultSet professorResult = stmProfessor.executeQuery("SELECT * FROM professor WHERE IdProfessor = " + professorId);
                                Professor professor = null;

                                if (professorResult.next()) {
                                    // Criando o objeto Professor com os dados recuperados
                                    professor = new Professor(
                                        professorResult.getInt("IdProfessor"),
                                        professorResult.getString("Nome"),
                                        professorResult.getString("Departamento")
                                    );
                                }

                                // Criando o objeto Curso com o objeto Professor
                                Curso curso = new Curso(idCurso, nomeCurso, chCurso, professor);

                                // Exibindo o curso
                                System.out.println(curso);
                            }
                            con.close();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        try {
                            Connection con = DriverManager.getConnection(url, user, password);
                            Statement stm = con.createStatement();
                            ResultSet sql = stm.executeQuery("SELECT * FROM aluno;");
                        
                            while (sql.next()) {
                                // Recuperando os dados do aluno
                                Integer IdAluno = sql.getInt("IdAluno");
                                String Nome = sql.getString("Nome");
                                Date DtNasc = sql.getDate("DtNasc");  // Alterado para Date
                                String CPF = sql.getString("CPF");
                        
                                // Recuperando o ID do curso associado ao aluno
                                Integer cursoId = sql.getInt("IdCurso");
                        
                                // Criando o objeto Curso com o ID recuperado
                                Statement stmCurso = con.createStatement();
                                ResultSet cursoResult = stmCurso.executeQuery("SELECT * FROM curso WHERE IdCurso = " + cursoId);
                                Curso curso = null;
                        
                                if (cursoResult.next()) {
                                    // Criando o objeto Curso com os dados recuperados
                                    curso = new Curso(
                                        cursoResult.getInt("IdCurso"),  // Usando IdCurso
                                        cursoResult.getString("Nome"),
                                        cursoResult.getInt("CH"),
                                        null  // Aqui você pode criar o Professor, se necessário
                                    );
                                }
                        
                                // Criando o objeto Aluno com o curso associado
                                Aluno aluno = new Aluno(IdAluno, Nome, DtNasc, CPF, curso);
                        
                                // Exibindo os dados do aluno
                                System.out.println(aluno);
                                
                                // Fechar ResultSet e Statement de curso
                                cursoResult.close();
                                stmCurso.close();
                            }
                            
                            // Fechar Statement e ResultSet de aluno
                            sql.close();
                            stm.close();
                            con.close();
                        
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
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

    private static boolean validaCPF(String CPF) {
        if (CPF.length() != 11) {
            return false;
        } else {
            return true;
        }     
    }
}
