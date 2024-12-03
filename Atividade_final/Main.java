import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            System.out.println("6. Editar Participante");
            System.out.println("7. Editar Organizador");
            System.out.println("8. Editar Local");
            System.out.println("9. Editar Evento");
            System.out.println("10. Excluir Participante");
            System.out.println("11. Excluir Organizador");
            System.out.println("12. Excluir Local");
            System.out.println("13. Excluir Evento");
            System.out.println("14. Excluir participante do evento");
            System.out.println("15. Selecionar participantes"); //lista de participantes
            System.out.println("16. Selecionar organizadores"); //lista de organizadores
            System.out.println("17. Selecionar locais"); //lista de locais
            System.out.println("18. Selecionar evento"); //carrega as informações do evento e os participantes cadastrados
            System.out.println("19. Notifica Participante");
            System.out.println("20. Notifica Participante");

            try{
                System.out.print("\nDigite a opção desejada: ");
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

                        String dataEvento;

                        // Caso tenha usado nextInt ou métodos semelhantes antes
                        scanner.nextLine(); // Limpa o buffer antes de solicitar a data

                        while (true) {
                            System.out.println("Informe a data do evento (yyyy-MM-dd HH:mm:ss):");
                            dataEvento = scanner.nextLine();
                        
                            try {
                                // Usar um formatter rigoroso para validar o formato
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime dataEventoFormatada = LocalDateTime.parse(dataEvento, formatter);

                                // Verificar se a data já está no banco de dados
                                String verificarDataSQL = "SELECT COUNT(*) AS total FROM Evento WHERE DATE(data) = ? AND local_id = ?";
                                PreparedStatement stmVerificarData = con.prepareStatement(verificarDataSQL);
                                stmVerificarData.setDate(1, java.sql.Date.valueOf(dataEventoFormatada.toLocalDate())); // Passando apenas a data
                                stmVerificarData.setInt(2, localId);
                                ResultSet rsData = stmVerificarData.executeQuery();

                                if (rsData.next() && rsData.getInt("total") > 0) {
                                    System.out.println("Data já cadastrada para este local. Escolha outra data.");
                                } else {
                                    System.out.println("Data válida.");
                                    rsData.close();
                                    stmVerificarData.close();
                                    break; // Sai do loop se a data for válida e não estiver no banco
                                }

                                rsData.close();
                                stmVerificarData.close();
                            } catch (DateTimeParseException e) {
                                System.out.println("Data inválida! Por favor, use o formato yyyy-MM-dd HH:mm:ss.");
                            } catch (SQLException e) {
                                System.out.println("Erro ao verificar a data no banco de dados: " + e.getMessage());
                            }
                        }

                        //Verificar se já existe algum evento cadastrado no dia: 
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
                                int vagasDisponiveis = rsEvento.getInt("vagas");
                                System.out.println("Evento encontrado: " + descricaoEvento);
                                System.out.println("Vagas disponíveis: " + vagasDisponiveis);
                                rsEvento.close();
                                stmBuscarEvento.close();
                    
                                if (vagasDisponiveis > 0) {
                                    // Verificar se a associação já existe
                                    String verificarDuplicataSQL = "SELECT * FROM evento_participante WHERE evento_id = ? AND participante_id = ?";
                                    PreparedStatement stmVerificarDuplicata = con.prepareStatement(verificarDuplicataSQL);
                                    stmVerificarDuplicata.setInt(1, IdEvento);
                                    stmVerificarDuplicata.setInt(2, IdParticipante);
                                    ResultSet rsDuplicata = stmVerificarDuplicata.executeQuery();
                    
                                    if (rsDuplicata.next()) {
                                        System.out.printf("O participante '%s' já está associado a este evento. Operação cancelada.", nomeParticipante);
                                        break; // Adicionado o break aqui
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
                                                
                                                // Atualizar o número de vagas disponíveis
                                                String atualizarVagasSQL = "UPDATE evento SET vagas = vagas - 1 WHERE id = ?";
                                                PreparedStatement stmAtualizarVagas = con.prepareStatement(atualizarVagasSQL);
                                                stmAtualizarVagas.setInt(1, IdEvento);
                                                int linhasAfetadas = stmAtualizarVagas.executeUpdate();
                                                
                                                if (linhasAfetadas > 0) {
                                                    System.out.println("Número de vagas atualizado com sucesso.");
                                                } else {
                                                    System.out.println("Erro ao atualizar o número de vagas.");
                                                }
                                                
                                                stmAtualizarVagas.close();
                                                break; // Adicionado o break aqui
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
                                } else {
                                    System.out.println("Não há vagas disponíveis para este evento.");
                                    break; // Adicionado o break aqui
                                }
                            } else {
                                System.out.println("Evento não encontrado. Tente novamente.");
                            }
                    
                            rsEvento.close();
                            stmBuscarEvento.close();
                        }
                    
                        // Fecha a conexão fora dos loops, garantindo que seja chamado
                        con.close();
                    
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                    }
                    
                    break;
                case 6:
                    try {
                        System.out.println("Informe o ID do participante que deseja alterar:");
                        int idParticipante = scanner.nextInt();
                
                        // Pedir os novos dados para atualização
                        System.out.println("Informe o novo nome (ou pressione Enter para manter o mesmo):");
                        scanner.nextLine(); // Limpar o buffer
                        String novoNome = scanner.nextLine();
                
                        System.out.println("Informe o novo telefone (ou pressione Enter para manter o mesmo):");
                        String novoTelefone = scanner.nextLine();
                
                        // Conexão com o banco de dados
                        Connection con = DriverManager.getConnection(url, user, password);
                
                        // Montar a consulta SQL dinamicamente
                        StringBuilder sql = new StringBuilder("UPDATE participante SET ");
                        boolean adicionarNome = !novoNome.isEmpty();
                        boolean adicionarTelefone = !novoTelefone.isEmpty();
                
                        if (adicionarNome) {
                            sql.append("nome = ?");
                        }
                        if (adicionarTelefone) {
                            if (adicionarNome) {
                                sql.append(", ");
                            }
                            sql.append("telefone = ?");
                        }
                        sql.append(" WHERE id = ?");
                
                        // Preparar o Statement
                        PreparedStatement ps = con.prepareStatement(sql.toString());
                
                        int index = 1;
                
                        // Definir os parâmetros dinamicamente
                        if (adicionarNome) {
                            ps.setString(index++, novoNome);
                        }
                        if (adicionarTelefone) {
                            ps.setString(index++, novoTelefone);
                        }
                        ps.setInt(index, idParticipante); // Definir o ID
                
                        // Executar o UPDATE
                        int rowsUpdated = ps.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("Participante atualizado com sucesso!");
                        } else {
                            System.out.println("Nenhum participante encontrado com o ID fornecido.");
                        }
                
                        // Fechar recursos
                        ps.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        System.out.println("Informe o ID do organizador que deseja alterar:");
                        int idOrganizador = scanner.nextInt();
                
                        // Pedir os novos dados para atualização
                        System.out.println("Informe o novo nome (ou pressione Enter para manter o mesmo):");
                        scanner.nextLine(); // Limpar o buffer
                        String novoNomeOrganizador = scanner.nextLine();
                
                        System.out.println("Informe o novo email (ou pressione Enter para manter o mesmo):");
                        String novoEmailOrganizador = scanner.nextLine();
                
                        Connection con = DriverManager.getConnection(url, user, password);
                
                        StringBuilder sqlOrganizador = new StringBuilder("UPDATE organizador SET ");
                        boolean atualizarNomeOrg = !novoNomeOrganizador.isEmpty();
                        boolean atualizarEmailOrg = !novoEmailOrganizador.isEmpty();
                
                        if (atualizarNomeOrg) {
                            sqlOrganizador.append("nome = ?");
                        }
                        if (atualizarEmailOrg) {
                            if (atualizarNomeOrg) {
                                sqlOrganizador.append(", ");
                            }
                            sqlOrganizador.append("email = ?");
                        }
                        sqlOrganizador.append(" WHERE id = ?");
                
                        PreparedStatement psOrganizador = con.prepareStatement(sqlOrganizador.toString());
                
                        int indexOrg = 1;
                        if (atualizarNomeOrg) {
                            psOrganizador.setString(indexOrg++, novoNomeOrganizador);
                        }
                        if (atualizarEmailOrg) {
                            psOrganizador.setString(indexOrg++, novoEmailOrganizador);
                        }
                        psOrganizador.setInt(indexOrg, idOrganizador);
                
                        int rowsUpdatedOrg = psOrganizador.executeUpdate();
                        if (rowsUpdatedOrg > 0) {
                            System.out.println("Organizador atualizado com sucesso!");
                        } else {
                            System.out.println("Nenhum organizador encontrado com o ID fornecido.");
                        }
                
                        psOrganizador.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    }
                    break;               
                case 8:
                    try {
                        System.out.println("Informe o ID do local que deseja alterar:");
                        int idLocal = scanner.nextInt();
                
                        System.out.println("Informe a nova descrição (ou pressione Enter para manter o mesmo):");
                        scanner.nextLine(); // Limpar o buffer
                        String novaDescricaoLocal = scanner.nextLine();
                
                        System.out.println("Informe o novo número de vagas (ou pressione Enter para manter o mesmo):");
                        String novasVagas = scanner.nextLine();
                
                        Connection con = DriverManager.getConnection(url, user, password);
                
                        StringBuilder sqlLocal = new StringBuilder("UPDATE local SET ");
                        boolean atualizarDescricaoLocal = !novaDescricaoLocal.isEmpty();
                        boolean atualizarVagas = !novasVagas.isEmpty();
                
                        if (atualizarDescricaoLocal) {
                            sqlLocal.append("descricao = ?");
                        }
                        if (atualizarVagas) {
                            if (atualizarDescricaoLocal) {
                                sqlLocal.append(", ");
                            }
                            sqlLocal.append("vagas = ?");
                        }
                        sqlLocal.append(" WHERE id = ?");
                
                        PreparedStatement psLocal = con.prepareStatement(sqlLocal.toString());
                
                        int indexLocal = 1;
                        if (atualizarDescricaoLocal) {
                            psLocal.setString(indexLocal++, novaDescricaoLocal);
                        }
                        if (atualizarVagas) {
                            psLocal.setInt(indexLocal++, Integer.parseInt(novasVagas));
                        }
                        psLocal.setInt(indexLocal, idLocal);
                
                        int rowsUpdatedLocal = psLocal.executeUpdate();
                        if (rowsUpdatedLocal > 0) {
                            System.out.println("Local atualizado com sucesso!");
                        } else {
                            System.out.println("Nenhum local encontrado com o ID fornecido.");
                        }
                
                        psLocal.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    }
                    break;               
                case 9:
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        int vagasEvento;
                    
                        // Solicitar ID do evento a ser atualizado
                        System.out.println("Informe o ID do evento a ser atualizado:");
                        int eventoId = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer após o nextInt
                    
                        // Verificar se o evento existe
                        String buscarEventoSQL = "SELECT * FROM Evento WHERE id = ?";
                        PreparedStatement stmBuscarEvento = con.prepareStatement(buscarEventoSQL);
                        stmBuscarEvento.setInt(1, eventoId);
                        ResultSet rsEvento = stmBuscarEvento.executeQuery();
                    
                        if (rsEvento.next()) {
                            System.out.println("Evento encontrado: " + rsEvento.getString("descricao"));
                    
                            // Atualizar dados do evento
                            System.out.println("Informe a nova descrição do evento (pressione Enter para manter a descrição atual):");
                            String descricaoEvento = scanner.nextLine();
                            if (descricaoEvento.isEmpty()) {
                                descricaoEvento = rsEvento.getString("descricao");  // Mantém a descrição atual se o usuário pressionar Enter
                            }
                    
                            // Solicitar nova data do evento
                            String dataEvento;
                            while (true) {
                                System.out.println("Informe a nova data do evento (yyyy-MM-dd HH:mm:ss) ou pressione Enter para manter a data atual:");
                                dataEvento = scanner.nextLine();
                                
                                if (dataEvento.isEmpty()) {
                                    dataEvento = rsEvento.getString("data").toString();  // Mantém a data atual se o usuário pressionar Enter
                                    break;
                                }
                    
                                try {
                                    // Validar o formato da data
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    LocalDateTime dataEventoFormatada = LocalDateTime.parse(dataEvento, formatter);
                    
                                    // Verificar se a nova data já está no banco de dados
                                    String verificarDataSQL = "SELECT COUNT(*) AS total FROM Evento WHERE DATE(data) = ? AND local_id = ?";
                                    PreparedStatement stmVerificarData = con.prepareStatement(verificarDataSQL);
                                    stmVerificarData.setDate(1, java.sql.Date.valueOf(dataEventoFormatada.toLocalDate()));
                                    stmVerificarData.setInt(2, rsEvento.getInt("local_id"));
                                    ResultSet rsData = stmVerificarData.executeQuery();
                    
                                    if (rsData.next() && rsData.getInt("total") > 0) {
                                        System.out.println("Data já cadastrada para este local. Escolha outra data.");
                                    } else {
                                        System.out.println("Data válida.");
                                        rsData.close();
                                        stmVerificarData.close();
                                        break;
                                    }
                    
                                    rsData.close();
                                    stmVerificarData.close();
                                } catch (DateTimeParseException e) {
                                    System.out.println("Data inválida! Por favor, use o formato yyyy-MM-dd HH:mm:ss.");
                                } catch (SQLException e) {
                                    System.out.println("Erro ao verificar a data no banco de dados: " + e.getMessage());
                                }
                            }
                    
                            // Solicitar nova quantidade de vagas
                            System.out.println("Informe o novo número de vagas para o evento (pressione Enter para manter o número atual):");
                            String vagasInput = scanner.nextLine();
                            if (vagasInput.isEmpty()) {
                                vagasEvento = rsEvento.getInt("vagas");  // Mantém o número de vagas atual se o usuário pressionar Enter
                            } else {
                                vagasEvento = Integer.parseInt(vagasInput);
                                
                                // Verificar se o número de vagas excede o limite disponível no local
                                String buscarLocalSQL = "SELECT * FROM Local WHERE id = ?";
                                PreparedStatement stmBuscarLocal = con.prepareStatement(buscarLocalSQL);
                                stmBuscarLocal.setInt(1, rsEvento.getInt("local_id"));
                                ResultSet rsLocal = stmBuscarLocal.executeQuery();
                    
                                if (rsLocal.next()) {
                                    int vagasDisponiveis = rsLocal.getInt("vagas");
                                    if (vagasEvento > vagasDisponiveis) {
                                        System.out.println("Número de vagas excede o limite do local. O número de vagas será ajustado para " + vagasDisponiveis);
                                        vagasEvento = vagasDisponiveis;  // Ajusta o número de vagas para o limite disponível
                                    }
                                }
                    
                                rsLocal.close();
                                stmBuscarLocal.close();
                            }
                    
                            // Atualizar o evento no banco de dados
                            String atualizarEventoSQL = "UPDATE Evento SET descricao = ?, data = ?, vagas = ? WHERE id = ?";
                            PreparedStatement stmAtualizarEvento = con.prepareStatement(atualizarEventoSQL);
                    
                            // Convertendo a string da data para LocalDateTime
                            LocalDateTime dataEventoParsed = LocalDateTime.parse(dataEvento, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    
                            stmAtualizarEvento.setString(1, descricaoEvento);
                            stmAtualizarEvento.setTimestamp(2, Timestamp.valueOf(dataEventoParsed));
                            stmAtualizarEvento.setInt(3, vagasEvento);
                            stmAtualizarEvento.setInt(4, eventoId);
                    
                            if (stmAtualizarEvento.executeUpdate() > 0) {
                                System.out.println("Evento atualizado com sucesso!");
                            } else {
                                System.out.println("Erro ao atualizar o evento.");
                            }
                    
                            // Fechar conexões
                            stmAtualizarEvento.close();
                            rsEvento.close();
                            stmBuscarEvento.close();
                        } else {
                            System.out.println("Evento não encontrado.");
                        }
                    
                        con.close();
                    
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                    }                   
                    break;
                case 10:
                    try {
                        System.out.println("Informe o ID do Participante que deseja excluir:");
                        int idParticipanteExcluir = scanner.nextInt();
                        
                        Connection con = DriverManager.getConnection(url, user, password);
                        
                        String deleteParticipanteSQL = "DELETE FROM participante WHERE id = ?";
                        PreparedStatement psExcluirParticipante = con.prepareStatement(deleteParticipanteSQL);
                        psExcluirParticipante.setInt(1, idParticipanteExcluir);
                
                        int rowsAffected = psExcluirParticipante.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Participante excluído com sucesso!");
                        } else {
                            System.out.println("Nenhum participante encontrado com o ID informado.");
                        }
                
                        psExcluirParticipante.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                    }
                    break;
                case 11:
                    try {
                        System.out.println("Informe o ID do Organizador que deseja excluir:");
                        int idOrganizadorExcluir = scanner.nextInt();
                        
                        Connection con = DriverManager.getConnection(url, user, password);
                        
                        String deleteOrganizadorSQL = "DELETE FROM organizador WHERE id = ?";
                        PreparedStatement psExcluirOrganizador = con.prepareStatement(deleteOrganizadorSQL);
                        psExcluirOrganizador.setInt(1, idOrganizadorExcluir);
                
                        int rowsAffected = psExcluirOrganizador.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Organizador excluído com sucesso!");
                        } else {
                            System.out.println("Nenhum organizador encontrado com o ID informado.");
                        }
                
                        psExcluirOrganizador.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                    }
                    break;
                case 12:
                    try {
                        System.out.println("Informe o ID do Local que deseja excluir:");
                        int idLocalExcluir = scanner.nextInt();
                        
                        Connection con = DriverManager.getConnection(url, user, password);
                        
                        String deleteLocalSQL = "DELETE FROM local WHERE id = ?";
                        PreparedStatement psExcluirLocal = con.prepareStatement(deleteLocalSQL);
                        psExcluirLocal.setInt(1, idLocalExcluir);
                
                        int rowsAffected = psExcluirLocal.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Local excluído com sucesso!");
                        } else {
                            System.out.println("Nenhum local encontrado com o ID informado.");
                        }
                
                        psExcluirLocal.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                    }
                    break;               
                case 13:
                    try {
                        System.out.println("Informe o ID do Evento que deseja excluir:");
                        int idEventoExcluir = scanner.nextInt();
                        
                        Connection con = DriverManager.getConnection(url, user, password);
                        
                        String deleteEventoSQL = "DELETE FROM evento WHERE id = ?";
                        PreparedStatement psExcluirEvento = con.prepareStatement(deleteEventoSQL);
                        psExcluirEvento.setInt(1, idEventoExcluir);
                
                        int rowsAffected = psExcluirEvento.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Evento excluído com sucesso!");
                        } else {
                            System.out.println("Nenhum evento encontrado com o ID informado.");
                        }
                
                        psExcluirEvento.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                    }
                    break;                
                case 14:
                    try {
                        System.out.println("Informe o ID do Evento:");
                        int idEvento = scanner.nextInt();
                        System.out.println("Informe o ID do Participante que deseja excluir do evento:");
                        int idParticipante = scanner.nextInt();
                        
                        Connection con = DriverManager.getConnection(url, user, password);
                        
                        // Excluir a associação entre o participante e o evento
                        String deleteEventoParticipanteSQL = "DELETE FROM evento_participante WHERE evento_id = ? AND participante_id = ?";
                        PreparedStatement psExcluirEventoParticipante = con.prepareStatement(deleteEventoParticipanteSQL);
                        psExcluirEventoParticipante.setInt(1, idEvento);
                        psExcluirEventoParticipante.setInt(2, idParticipante);
                        
                        int rowsAffected = psExcluirEventoParticipante.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Associação entre participante e evento excluída com sucesso!");
                            
                            // Atualizar o número de vagas disponíveis
                            String atualizarVagasSQL = "UPDATE evento SET vagas = vagas + 1 WHERE id = ?";
                            PreparedStatement psAtualizarVagas = con.prepareStatement(atualizarVagasSQL);
                            psAtualizarVagas.setInt(1, idEvento);
                            
                            int vagasAtualizadas = psAtualizarVagas.executeUpdate();
                            if (vagasAtualizadas > 0) {
                                System.out.println("Número de vagas atualizado com sucesso!");
                            } else {
                                System.out.println("Erro ao atualizar o número de vagas.");
                            }
                            
                            psAtualizarVagas.close();
                        } else {
                            System.out.println("Nenhuma associação encontrada com os IDs informados.");
                        }
                    
                        psExcluirEventoParticipante.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro inesperado: " + e.getMessage());
                    }
                    break;                
                case 15:
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("SELECT * FROM participante;");
                        ResultSet sql = stm.executeQuery();
                    
                        while (sql.next()) {
                            int id = sql.getInt("id");
                            String nome = sql.getString("nome");
                            String telefone = sql.getString("telefone");
                            int notificacoesDb = sql.getInt("notificacao_id");
                    
                            // Usa o método estático para criar uma instância de Notifica
                            Notifica notificacao = Notifica.fromInt(notificacoesDb);
                    
                            Participante participante = new Participante(id, nome, telefone, notificacao);
                            System.out.println(participante);
                        }
                    
                        sql.close();
                        stm.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro de SQL: " + e.getMessage());
                    }
                    break;
                case 16:
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("SELECT * FROM organizador;");
                        ResultSet sql = stm.executeQuery();
                    
                        while (sql.next()) {
                            int id = sql.getInt("id");
                            String nome = sql.getString("nome");
                            String email = sql.getString("email");
                            int notificacoesDb = sql.getInt("notificacao_id");
                    
                            // Usa o método estático para criar uma instância de Notifica
                            Notifica notificacao = Notifica.fromInt(notificacoesDb);
                    
                            Organizador organizador = new Organizador(id, nome, email, notificacao);
                            System.out.println(organizador);
                        }
                    
                        sql.close();
                        stm.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro de SQL: " + e.getMessage());
                    }
                    break;
                case 17:
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("SELECT * FROM local;");
                        ResultSet sql = stm.executeQuery();
                    
                        while (sql.next()) {
                            int id = sql.getInt("id");
                            String descricao = sql.getString("descricao");
                            int vagas = sql.getInt("vagas");
                        
                            // Cria o Local
                            Local local = new Local(id, descricao, vagas);
                        
                            // Busca eventos associados a este local
                            PreparedStatement stmEventos = con.prepareStatement("SELECT * FROM evento WHERE local_id = ?");
                            stmEventos.setInt(1, id);
                            ResultSet eventosRs = stmEventos.executeQuery();
                        
                            while (eventosRs.next()) {
                                int eventoId = eventosRs.getInt("id");
                                String eventoDescricao = eventosRs.getString("descricao");
                                LocalDateTime data = eventosRs.getTimestamp("data").toLocalDateTime();
                                int vagasEvento = eventosRs.getInt("vagas");
                        
                                // Busca o organizador do evento
                                PreparedStatement stmOrganizador = con.prepareStatement("SELECT * FROM organizador WHERE id = ?");
                                stmOrganizador.setInt(1, eventosRs.getInt("organizador_id"));
                                ResultSet organizadorRs = stmOrganizador.executeQuery();
                        
                                Organizador organizador = null;
                                if (organizadorRs.next()) {
                                    int orgId = organizadorRs.getInt("id");
                                    String nomeOrg = organizadorRs.getString("nome");
                                    String emailOrg = organizadorRs.getString("email");
                        
                                    // Cria o organizador com os dados reais
                                    organizador = new Organizador(orgId, nomeOrg, emailOrg, null);
                                }
                        
                                organizadorRs.close();
                        
                                // Cria o evento e adiciona ao local
                                Evento evento = new Evento(eventoId, organizador, local, data, eventoDescricao, vagasEvento);
                                local.adicionarEvento(evento);
                            }
                        
                            eventosRs.close();
                        
                            // Exibe o Local com seus eventos
                            System.out.println(local);
                        }                                             
                    
                        sql.close();
                        stm.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro de SQL: " + e.getMessage());
                    }
                    break;
                case 18:
                    try (Connection con = DriverManager.getConnection(url, user, password)) {
                        // Solicita o ID do evento ao usuário
                        System.out.print("Informe o ID do evento: ");
                        int eventoId = Integer.parseInt(System.console().readLine()); // Usando console para entrada de dados
                    
                        // Consulta SQL para informações do evento
                        String sqlEvento = "SELECT e.descricao AS evento_descricao, e.data AS evento_data, e.vagas, " +
                                            "o.nome AS organizador_nome, l.descricao AS local_descricao " +
                                            "FROM evento e " +
                                            "INNER JOIN organizador o ON e.organizador_id = o.id " +
                                            "INNER JOIN local l ON e.local_id = l.id " +
                                            "WHERE e.id = ?";
                    
                        try (PreparedStatement stmEvento = con.prepareStatement(sqlEvento)) {
                            stmEvento.setInt(1, eventoId);
                    
                            try (ResultSet eventoRs = stmEvento.executeQuery()) {
                                if (eventoRs.next()) {
                                    // Exibe informações do evento
                                    String eventoDescricao = eventoRs.getString("evento_descricao");
                                    String eventoData = eventoRs.getString("evento_data");
                                    int vagas = eventoRs.getInt("vagas");
                                    String organizadorNome = eventoRs.getString("organizador_nome");
                                    String localDescricao = eventoRs.getString("local_descricao");
                    
                                    System.out.println("Informações do Evento (ID: " + eventoId + "):");
                                    System.out.println("Descrição: " + eventoDescricao);
                                    System.out.println("Data: " + eventoData);
                                    System.out.println("Vagas: " + vagas);
                                    System.out.println("Organizador: " + organizadorNome);
                                    System.out.println("Local: " + localDescricao);
                                    System.out.println();
                    
                                    // Consulta SQL para os participantes do evento
                                    String sqlParticipantes = "SELECT p.id, p.nome, p.telefone " +
                                                            "FROM participante p " +
                                                            "INNER JOIN evento_participante ep ON ep.participante_id = p.id " +
                                                            "WHERE ep.evento_id = ?";
                    
                                    try (PreparedStatement stmParticipantes = con.prepareStatement(sqlParticipantes)) {
                                        stmParticipantes.setInt(1, eventoId);
                    
                                        try (ResultSet participantesRs = stmParticipantes.executeQuery()) {
                                            System.out.println("Participantes do Evento:");
                    
                                            boolean encontrouParticipantes = false;
                    
                                            while (participantesRs.next()) {
                                                encontrouParticipantes = true;
                    
                                                int participanteId = participantesRs.getInt("id");
                                                String participanteNome = participantesRs.getString("nome");
                                                String telefone = participantesRs.getString("telefone");
                    
                                                System.out.println("ID: " + participanteId + ", Nome: " + participanteNome + ", Telefone: " + telefone);
                                            }
                    
                                            if (!encontrouParticipantes) {
                                                System.out.println("Nenhum participante encontrado para este evento.");
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("Evento não encontrado com o ID fornecido.");
                                }
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao conectar ou executar consulta: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("ID do evento inválido.");
                    }
                    break;
                    case 19: // Notifica Participante
                    System.out.println("Digite o ID do participante que deseja notificar:");
                    int idParticipante = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer

                    // Conectar ao banco para buscar os dados do participante
                    try (Connection con = DriverManager.getConnection(url, user, password)) {
                        String sql = "SELECT * FROM participante WHERE id = ?";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setInt(1, idParticipante);
                        ResultSet rs = pst.executeQuery();

                        if (rs.next()) {
                            String nomeParticipante = rs.getString("nome");
                            String telefone = rs.getString("telefone");

                            // Supondo que a notificação do participante seja via telefone (NotificaTelefone)
                            Notifica notifica = new NotificaTelefone(1, "Você foi notificado para o evento!", telefone);
                            notifica.enviar(); // Envia a notificação

                            // Inserir a notificação no banco de dados, incluindo o telefone no campo 'destino'
                            String insertNotificaSQL = "INSERT INTO notifica (texto, destino) VALUES (?, ?)";
                            PreparedStatement psInserirNotificacao = con.prepareStatement(insertNotificaSQL);
                            psInserirNotificacao.setString(1, notifica.getTexto());
                            psInserirNotificacao.setString(2, telefone); // Definindo o telefone como destino
                            psInserirNotificacao.executeUpdate(); // Executa a inserção

                            System.out.println("Notificação enviada para o participante " + nomeParticipante + " no telefone " + telefone);
                        } else {
                            System.out.println("Participante não encontrado.");
                        }
                        rs.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    }
                    break;

                case 20: // Notifica Organizador
                    System.out.println("Digite o ID do organizador que deseja notificar:");
                    int idOrganizador = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer

                    // Conectar ao banco para buscar os dados do organizador
                    try (Connection con = DriverManager.getConnection(url, user, password)) {
                        String sql = "SELECT * FROM organizador WHERE id = ?";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setInt(1, idOrganizador);
                        ResultSet rs = pst.executeQuery();

                        if (rs.next()) {
                            String nomeOrganizador = rs.getString("nome");
                            String email = rs.getString("email");

                            // Supondo que a notificação do organizador seja via e-mail (NotificaEmail)
                            Notifica notifica = new NotificaEmail(1, "Você tem uma nova atualização no evento!", email);
                            notifica.enviar(); // Envia a notificação

                            // Inserir a notificação no banco de dados, incluindo o e-mail no campo 'destino'
                            String insertNotificaSQL = "INSERT INTO notifica (texto, destino) VALUES (?, ?)";
                            PreparedStatement psInserirNotificacao = con.prepareStatement(insertNotificaSQL);
                            psInserirNotificacao.setString(1, notifica.getTexto());
                            psInserirNotificacao.setString(2, email); // Definindo o e-mail como destino
                            psInserirNotificacao.executeUpdate(); // Executa a inserção

                            System.out.println("Notificação enviada para o organizador " + nomeOrganizador + " no e-mail " + email);
                        } else {
                            System.out.println("Organizador não encontrado.");
                        }
                        rs.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                    }
                    break;
            }
        } while (menu != 0);
        scanner.close();
    }
}