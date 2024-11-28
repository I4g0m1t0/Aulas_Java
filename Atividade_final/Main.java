import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.sql.Timestamp;


public class Main {

    public static void main(String[] args) throws Exception {
        final String url = "jdbc:mysql://localhost:3306/atividade_final_java";
        final String user = "root";
        final String password = "";
        
        Scanner scanner = new Scanner(System.in);
        int menu = 0;
        do {
            System.out.println("\nDigite a opção desejada: ");
            System.out.println("1. Inserir Participante");
            System.out.println("2. Inserir Organizador");
            System.out.println("3. Inserir Local");
            System.out.println("4. Inserir Evento");
            System.out.println("5. Inserir participante no evento");
            System.out.println("6. UPDATE COM STATEMENT");
            System.out.println("7. UPDATE COM PREP STATEMENT");
            System.out.println("8. DELETE COM STATEMENT");
            System.out.println("9. DELETE COM PREP STATEMENT");
            System.out.println("10. SELECT COM STATEMENT");
            System.out.println("11. SELECT COM PREP STATEMENT");
            System.out.println("12. INSERT COM STATEMENT");
            try{
                menu = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            switch (menu) {
                case 1:
                    try {
                        System.out.println("Informe o nome do participante");
                            String nome = scanner.next();
                            System.out.println("Informe o telefone do participante");
                            String telefone = scanner.next();
                            
                            Connection con = DriverManager.getConnection(url, user, password);
                            PreparedStatement stm = con.prepareStatement("INSERT INTO participante "
                                + "(nome, telefone) VALUES "
                                + "(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                            stm.setString(1, nome);
                            stm.setString(2, telefone);
                            if (stm.executeUpdate() > 0) {
                                ResultSet rs = stm.getGeneratedKeys();

                                if (rs.next()) {
                                    System.out.println(new Participante(
                                        rs.getInt(1),
                                        nome,
                                        telefone
                                    )); 
                                }
                            }
                            con.close();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    break;
                case 2:
                    try {
                        System.out.println("Informe o nome do organizador");
                        String nome = scanner.next();
                        System.out.println("Informe o email do organizador");
                        String email = scanner.next();
                        
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("INSERT INTO organizador (nome, email) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                        
                        stm.setString(1, nome);
                        stm.setString(2, email);
                        
                        if (stm.executeUpdate() > 0) {
                            ResultSet rs = stm.getGeneratedKeys();

                            if (rs.next()) {
                                System.out.println(new Organizador(
                                    rs.getInt(1),
                                    nome,
                                    email
                                )); 
                            }
                        }
                        con.close();
                    } catch (SQLException e) {
                         System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Informe a descrição do local:");
                        scanner.nextLine(); // Para limpar o buffer caso venha de um nextInt()
                        String descricaoLocal = scanner.nextLine();
                        
                        System.out.println("Informe a quantidade de vagas do local:");
                        int vagas = scanner.nextInt();
                    
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement(
                            "INSERT INTO local (descricao, vagas) VALUES (?, ?)", 
                            PreparedStatement.RETURN_GENERATED_KEYS
                        );
                    
                        stm.setString(1, descricaoLocal);
                        stm.setInt(2, vagas);
                    
                        if (stm.executeUpdate() > 0) {
                            ResultSet rs = stm.getGeneratedKeys();
                            if (rs.next()) {
                                // Cria o objeto Local com os valores retornados
                                Local novoLocal = new Local(
                                    rs.getInt(1), // Obtém o ID gerado automaticamente
                                    descricaoLocal,
                                    vagas
                                );
                                System.out.println("Local cadastrado com sucesso!");
                                System.out.println(novoLocal);
                            }
                            rs.close();
                        }
                        stm.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        int organizadorId, localId, vagasEvento;

                        // Verificação do Organizador
                        while (true) {
                            System.out.println("Informe o ID do Organizador:");
                            organizadorId = scanner.nextInt();
                            
                            String buscarOrganizadorSQL = "SELECT * FROM Organizador WHERE id = ?";
                            PreparedStatement stmBuscarOrganizador = con.prepareStatement(buscarOrganizadorSQL);
                            
                            stmBuscarOrganizador.setInt(1, organizadorId);
                            ResultSet rsOrganizador = stmBuscarOrganizador.executeQuery();
                            
                            if (rsOrganizador.next()) {
                                System.out.println("Organizador encontrado: " + rsOrganizador.getString("nome"));
                                break; // Sai do loop se o organizador for encontrado
                            } else {
                                System.out.println("Organizador não encontrado. Tente novamente.");
                            }

                            rsOrganizador.close();
                            stmBuscarOrganizador.close();
                        }

                        // Verificação do Local
                        while (true) {
                            System.out.println("Informe o ID do Local:");
                            localId = scanner.nextInt();
                            String buscarLocalSQL = "SELECT * FROM Local WHERE id = ?";
                            PreparedStatement stmBuscarLocal = con.prepareStatement(buscarLocalSQL);
                            stmBuscarLocal.setInt(1, localId);
                            ResultSet rsLocal = stmBuscarLocal.executeQuery();

                            if (rsLocal.next()) {
                                System.out.println("Local encontrado: " + rsLocal.getString("descricao"));
                                break; // Sai do loop se o local for encontrado
                            } else {
                                System.out.println("Local não encontrado. Tente novamente.");
                            }

                            rsLocal.close();
                            stmBuscarLocal.close();
                        }

                        // Dados do Evento
                        System.out.println("Informe a data do evento (yyyy-MM-dd HH:mm:ss):");
                        scanner.nextLine(); // Limpar o buffer
                        String dataEvento = scanner.nextLine();

                        System.out.println("Informe a descrição do evento:");
                        String descricaoEvento = scanner.nextLine();

                        //Vagas do evento
                        while (true) {
                            String buscarLocalSQL = "SELECT * FROM Local WHERE id = ?";
                            PreparedStatement stmBuscarLocal = con.prepareStatement(buscarLocalSQL);
                            stmBuscarLocal.setInt(1, localId);
                            ResultSet rsLocal = stmBuscarLocal.executeQuery();

                            if (rsLocal.next()) {
                                System.out.println("Local selecionado: " + rsLocal.getString("descricao"));
                                int vagasDisponiveis = rsLocal.getInt("vagas");
                                System.out.println("Vagas disponíveis no local: " + vagasDisponiveis);
                        
                                // Solicitar o número de vagas para o evento
                                System.out.println("Informe a quantidade de vagas do evento:");
                                vagasEvento = scanner.nextInt();
                        
                                // Verificar se o número de vagas do evento é permitido
                                if (vagasEvento <= vagasDisponiveis) {
                                    System.out.println("Número de vagas aceito.");
                                    break; // Sai do loop se as vagas forem válidas
                                } else {
                                    System.out.println("Número de vagas excede o limite do local. Tente novamente.");
                                }
                            } else {
                                System.out.println("Local não encontrado. Tente novamente.");
                            }
                        
                            rsLocal.close();
                            stmBuscarLocal.close();
                        }

                        // Preparando a inserção do Evento
                        String inserirEventoSQL = "INSERT INTO Evento (organizador_id, local_id, data, descricao, vagas) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement stmInserirEvento = con.prepareStatement(inserirEventoSQL, PreparedStatement.RETURN_GENERATED_KEYS);
                        
                        // Convertendo String para LocalDateTime
                        LocalDateTime dataEventoParsed = LocalDateTime.parse(dataEvento, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                        stmInserirEvento.setInt(1, organizadorId);
                        stmInserirEvento.setInt(2, localId);
                        stmInserirEvento.setTimestamp(3, Timestamp.valueOf(dataEventoParsed));
                        stmInserirEvento.setString(4, descricaoEvento);
                        stmInserirEvento.setInt(5, vagasEvento);

                        if (stmInserirEvento.executeUpdate() > 0) {
                            ResultSet rsEvento = stmInserirEvento.getGeneratedKeys();
                            if (rsEvento.next()) {
                                System.out.println("Evento cadastrado com sucesso! ID: " + rsEvento.getInt(1));
                            }
                            rsEvento.close();
                        }

                        // Fechar conexões
                        stmInserirEvento.close();
                        con.close();

                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);

                        int IdEvento, IdParticipante;
                        String nomeParticipante = "", descricaoEvento = "";

                        // Verificar o participante
                        while (true) {
                            System.out.println("Informe o ID do Participante:");
                            IdParticipante = scanner.nextInt();
                            
                            String buscarParticipanteSQL = "SELECT * FROM participante WHERE id = ?";
                            PreparedStatement stmBuscarParticipante = con.prepareStatement(buscarParticipanteSQL);
                            stmBuscarParticipante.setInt(1, IdParticipante);
                            ResultSet rsParticipante = stmBuscarParticipante.executeQuery();

                            if (rsParticipante.next()) {
                                nomeParticipante = rsParticipante.getString("nome");
                                System.out.println("Participante encontrado: " + nomeParticipante);
                                rsParticipante.close();
                                stmBuscarParticipante.close();
                                break;
                            } else {
                                System.out.println("Participante não encontrado. Tente novamente.");
                            }

                            rsParticipante.close();
                            stmBuscarParticipante.close();
                        }

                        // Verificar o evento
                        while (true) {
                            System.out.println("Informe o ID do evento que ele irá participar:");
                            IdEvento = scanner.nextInt();
                            String buscarEventoSQL = "SELECT * FROM evento WHERE id = ?";
                            PreparedStatement stmBuscarEvento = con.prepareStatement(buscarEventoSQL);
                            stmBuscarEvento.setInt(1, IdEvento);
                            ResultSet rsEvento = stmBuscarEvento.executeQuery();

                            if (rsEvento.next()) {
                                descricaoEvento = rsEvento.getString("descricao");
                                System.out.println("Evento encontrado: " + descricaoEvento);
                                rsEvento.close();
                                stmBuscarEvento.close();
                                break;
                            } else {
                                System.out.println("Evento não encontrado. Tente novamente.");
                            }

                            rsEvento.close();
                            stmBuscarEvento.close();
                        }

                        // Verificar se a associação já existe
                        String verificarDuplicataSQL = "SELECT * FROM evento_participante WHERE evento_id = ? AND participante_id = ?";
                        PreparedStatement stmVerificarDuplicata = con.prepareStatement(verificarDuplicataSQL);
                        stmVerificarDuplicata.setInt(1, IdEvento);
                        stmVerificarDuplicata.setInt(2, IdParticipante);
                        ResultSet rsDuplicata = stmVerificarDuplicata.executeQuery();

                        if (rsDuplicata.next()) {
                            System.out.printf("O participante '%s' já está associado a este evento. Operação cancelada.", nomeParticipante);
                        } else {
                            // Inserir a associação apenas se não for duplicada
                            
                            // Confirmação antes de inserir
                            System.out.printf("Deseja inserir o participante '%s' no evento '%s'? (s/n): ", nomeParticipante, descricaoEvento);
                            String confirmacao = scanner.next();
                            
                            if (confirmacao.equalsIgnoreCase("s")) {
                                String InserirEventoParticipanteSql = "INSERT INTO evento_participante (evento_id, participante_id) VALUES (?, ?)";
                                PreparedStatement stmInserirEventoParticipante = con.prepareStatement(InserirEventoParticipanteSql, PreparedStatement.RETURN_GENERATED_KEYS);
                            
                                stmInserirEventoParticipante.setInt(1, IdEvento);
                                stmInserirEventoParticipante.setInt(2, IdParticipante);
                            
                                if (stmInserirEventoParticipante.executeUpdate() > 0) {
                                    System.out.println("Participante associado ao evento com sucesso!");
                                } else {
                                    System.out.println("Erro ao associar participante ao evento.");
                                }
                            
                                stmInserirEventoParticipante.close();
                            } else {
                                System.out.println("Operação cancelada.");
                            }
                        }

                        rsDuplicata.close();
                        stmVerificarDuplicata.close();
                        con.close();

                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                    }
                    break;
                case 6:
                    // try {
                    //     System.out.println("Informe o ID de alteração: ");
                    //     int id = scanner.nextInt();
                    //     Connection con = DriverManager.getConnection(url, user, password);
                    //     Statement stm = con.createStatement();
                    //     ResultSet rs = stm.executeQuery("SELECT * FROM funcionarios WHERE id = " + id);
                    //     if(!rs.next()) {
                    //         throw new Exception("Id inválido");
                    //     }
                    //     Funcionario funcionario = new Funcionario(
                    //         rs.getInt("id"),
                    //         rs.getString("nome"),
                    //         rs.getString("cpf"),
                    //         rs.getDate("data_nascimento"),
                    //         rs.getString("matricula")
                    //     );
                    //     con.close();
                    //     System.out.println("Informe o nome do funcionário (Deixar vazio para manter)");
                    //     String nome = scanner.next();
                    //     if (nome.length() > 0){
                    //         funcionario.setNome(nome);
                    //     }
                    //     System.out.println("Informe o CPF do funcionário (Deixar vazio para manter)");
                    //     String cpf = scanner.next();
                    //     if (cpf.length() > 0){
                    //         funcionario.setCpf(cpf);
                    //     }
                    //     System.out.println("Informe a Data de Nascimento do funcionário (Deixar vazio para manter)");
                    //     String dataNascimento = scanner.next();
                    //     if (dataNascimento.length() > 0){
                    //         funcionario.setDataNascimento(Date.valueOf(dataNascimento));
                    //     }
                    //     System.out.println("Informe o Matricula do funcionário (Deixar vazio para manter)");
                    //     String matricula = scanner.next();
                    //     if (matricula.length() > 0){
                    //         funcionario.setMatricula(matricula);
                    //     }
                        

                    //     con = DriverManager.getConnection(url, user, password);
                    //     stm = con.createStatement();
                    //     boolean sql = stm.execute("UPDATE funcionarios SET "
                    //         + " nome = '" + funcionario.getNome() + "'"
                    //         + ", cpf = '" + funcionario.getCpf() + "'"
                    //         + ", data_nascimento = '" + funcionario.getDataNascimento() + "'"
                    //         + ", matricula = '" + funcionario.getMatricula() + "'"
                    //         + " WHERE id = " + funcionario.getId());
                    //     if(!sql) {
                    //         System.out.println("Falha na execução");
                    //     }
                    //     con.close();
                    // } catch (SQLException e) {
                    //     System.out.println(e.getMessage());
                    // }
                    break;
                    case 7:
                    // try {
                    //     System.out.println("Informe o ID de alteração: ");
                    //     int id = scanner.nextInt();
                    //     Connection con = DriverManager.getConnection(url, user, password);
                    //     Statement stm = con.createStatement();
                    //     ResultSet rs = stm.executeQuery("SELECT * FROM funcionarios WHERE id = " + id);
                    //     if(!rs.next()) {
                    //         throw new Exception("Id inválido");
                    //     }
                    //     Funcionario funcionario = new Funcionario(
                    //         rs.getInt("id"),
                    //         rs.getString("nome"),
                    //         rs.getString("cpf"),
                    //         rs.getDate("data_nascimento"),
                    //         rs.getString("matricula")
                    //     );
                    //     con.close();
                    //     System.out.println("Informe o nome do funcionário (Deixar vazio para manter)");
                    //     String nome = scanner.next();
                    //     if (nome.length() > 0){
                    //         funcionario.setNome(nome);
                    //     }
                    //     System.out.println("Informe o CPF do funcionário (Deixar vazio para manter)");
                    //     String cpf = scanner.next();
                    //     if (cpf.length() > 0){
                    //         funcionario.setCpf(cpf);
                    //     }
                    //     System.out.println("Informe a Data de Nascimento do funcionário (Deixar vazio para manter)");
                    //     String dataNascimento = scanner.next();
                    //     if (dataNascimento.length() > 0){
                    //         funcionario.setDataNascimento(Date.valueOf(dataNascimento));
                    //     }
                    //     System.out.println("Informe o Matricula do funcionário (Deixar vazio para manter)");
                    //     String matricula = scanner.next();
                    //     if (matricula.length() > 0){
                    //         funcionario.setMatricula(matricula);
                    //     }
                    

                    //     con = DriverManager.getConnection(url, user, password);
                    //     PreparedStatement pStm = con.prepareStatement("UPDATE funcionarios SET "
                    //         + " nome = ?"
                    //         + ", cpf = ?"
                    //         + ", data_nascimento = ?"
                    //         + ", matricula = ?"
                    //         + " WHERE id = ?");
                    //     pStm.setString(1, funcionario.getNome());
                    //     pStm.setString(2, funcionario.getCpf());
                    //     pStm.setDate(3, funcionario.getDataNascimento());
                    //     pStm.setString(4, funcionario.getMatricula());
                    //     pStm.setInt(5, funcionario.getId());
                    //     if (pStm.executeUpdate() <= 0) {
                    //         System.out.println("Falha na execução.");
                    //     }
                    //     con.close();
                    // } catch (Exception e) {
                    //     System.out.println(e.getMessage());
                    // }
                    break;
                case 8:
                    // try {
                    //     System.out.println("Informe o ID de exclusão: ");
                    //     int id = scanner.nextInt();
                    //     Connection con = DriverManager.getConnection(url, user, password);
                    //     Statement stm = con.createStatement();
                    //     ResultSet rs = stm.executeQuery("SELECT * FROM funcionarios WHERE id = " + id);
                        
                    //     if(!rs.next()) {
                    //         throw new Exception("Id inválido");
                    //     }
                    //     Funcionario funcionario = new Funcionario(
                    //         rs.getInt("id"),
                    //         rs.getString("nome"),
                    //         rs.getString("cpf"),
                    //         rs.getDate("data_nascimento"),
                    //         rs.getString("matricula")
                    //     );
                    //     stm = con.createStatement();
                    //     boolean sql = stm.execute("DELETE FROM funcionarios "
                    //         + " WHERE id = " + funcionario.getId());
                    //     if(!sql) {
                    //         System.out.println("Falha na execução");
                    //     }
                    //     con.close();
                    // } catch (SQLException e) {
                    //     System.out.println(e.getMessage());
                    // }
                    break;
                case 9:
                //     try {
                //         System.out.println("Informe o ID de exclusão: ");
                //         int id = scanner.nextInt();
                //         Connection con = DriverManager.getConnection(url, user, password);
                //         Statement stm = con.createStatement();
                //         ResultSet rs = stm.executeQuery("SELECT * FROM funcionarios WHERE id = " + id);
                        
                //         if(!rs.next()) {
                //             throw new Exception("Id inválido");
                //         }
                //         Funcionario funcionario = new Funcionario(
                //             rs.getInt("id"),
                //             rs.getString("nome"),
                //             rs.getString("cpf"),
                //             rs.getDate("data_nascimento"),
                //             rs.getString("matricula")
                //         );
                //         PreparedStatement pStm = con.prepareStatement("DELETE FROM funcionarios WHERE id = ?");
                //         pStm.setInt(1, funcionario.getId());
                //         if(pStm.executeUpdate() <= 0) {
                //             System.out.println("Falha na execução.");
                //         }
                //         con.close();
                //     } catch (Exception e) {
                //         System.out.println(e.getMessage());
                //     }
                //     break;
                // default:
                //     System.out.println("Operação inválida.");
                    break;
                case 10:
                    // try {
                    //     Connection con = DriverManager.getConnection(url, user, password);
                    //     Statement stm = con.createStatement();
                    //     ResultSet sql = stm.executeQuery("SELECT * FROM funcionarios;");
                    //     while(sql.next()) {
                    //         System.out.println(new Funcionario(
                    //             sql.getInt("id"),
                    //             sql.getString("nome"),
                    //             sql.getString("cpf"),
                    //             sql.getDate("data_nascimento"),
                    //             sql.getString("matricula")
                    //         ));
                    //     }
                    //     con.close();
                    // } catch (SQLException e) {
                    //     System.out.println(e.getMessage());
                    // }
                    break;
                case 11:
                    // try {
                    //     Connection con = DriverManager.getConnection(url, user, password);
                    //     PreparedStatement stm = con.prepareStatement("SELECT * FROM funcionarios;");
                    //     ResultSet sql = stm.executeQuery();
                    //     while(sql.next()) {
                    //         System.out.println(new Funcionario(
                    //             sql.getInt("id"),
                    //             sql.getString("nome"),
                    //             sql.getString("cpf"),
                    //             sql.getDate("data_nascimento"),
                    //             sql.getString("matricula")
                    //         ));
                    //     }
                    //     con.close();
                    // } catch (SQLException e) {
                    //     System.out.println(e.getMessage());
                    // }
                    break;
                case 12:
                    // try {
                    //     System.out.println("Informe o nome do funcionário");
                    //     String nome = scanner.next();
                    //     System.out.println("Informe o CPF do funcionário");
                    //     String cpf = scanner.next();
                    //     System.out.println("Informe a Data de Nascimento do funcionário");
                    //     String dataNascimento = scanner.next();
                    //     System.out.println("Informe o Matricula do funcionário");
                    //     String matricula = scanner.next();
                        
                        
                    //     Connection con = DriverManager.getConnection(url, user, password);
                    //     Statement stm = con.createStatement();
                    //     boolean sql = stm.execute("INSERT INTO funcionarios "
                    //         + "(nome, cpf, data_nascimento, matricula) VALUES "
                    //         + "('"+nome+"', '"+cpf+"', '"+dataNascimento+"', '"+matricula+"')");
                    //     if(!sql) {
                    //         System.out.println("Falha na execução");
                    //     }
                    //     con.close();
                    // } catch (SQLException e) {
                    //     System.out.println(e.getMessage());
                    // }
                    break;
            }
        } while (menu != 0);
        scanner.close();
    }
}
