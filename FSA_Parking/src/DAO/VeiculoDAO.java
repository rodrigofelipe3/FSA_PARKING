
package DAO;


import DTO.VeiculoDTO;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class VeiculoDAO  {
    
    
   //Resetar o ID da tabela; 
   public void resetarID() {
        
        String sql = ("set @autoid :=0;");
        String sql2 = ("update veiculo set id = @autoid := (@autoid+1);");
        String sql3 = ("alter table veiculo auto_increment = 1;");
        
        Connection conn = null;
        PreparedStatement pstm = null;
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;
        
        try { 
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = conn.prepareStatement(sql);
            pstm1 = conn.prepareStatement(sql2);
            pstm2 = conn.prepareStatement(sql3);
                       
            
            boolean execute = pstm.execute();
            boolean execute1 = pstm1.execute();
            boolean execute2 = pstm2.execute();
            
        }catch(Exception e) { 
            
            //e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "RESET ID: " + e);
        }
    }
   
   //Cadastrar o veiculo no BD
   public void CadastrarVeiculo(VeiculoDTO veiculodto) { 
        
        String sql = "INSERT INTO veiculo(nome, tipo, modelo, ano, cor, placa, entrada, telefone, horaentrada, valor) VALUES (?, ?, ?, ?, ?, ? , ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstm = null; 
        
        //Criar Conexão com banco de dados
        try { 
            
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            
            //Preparando para Executar a Query;
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setString(1, veiculodto.getNome());
            pstm.setString(2, veiculodto.getTipo());
            pstm.setString(3, veiculodto.getModelo());
            pstm.setString(4, veiculodto.getAno());
            pstm.setString(5, veiculodto.getCor());
            pstm.setString(6, veiculodto.getPlaca());
            pstm.setString(7, veiculodto.getEntrada());
            pstm.setString(8, veiculodto.getTelefone());
            pstm.setString(9, veiculodto.getHoraentrada());
            pstm.setFloat(10, veiculodto.getValor());
            
            boolean execute = pstm.execute();
            JOptionPane.showMessageDialog(null, "Veiculo Cadastrado no Sistema");
            
        } catch (Exception e) { 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "VEICULODAO: " + e);
        }finally { 
            try { 
                if (pstm!=null) { 
                pstm.close();
            }
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException e){ 
            e.printStackTrace();
        }
    }
        
    }
   
   //Buscar dados do veiculo/proprietário no BD
   public List<VeiculoDTO> getVeiculo() { 
        
        String sql = "SELECT * FROM veiculo";
        
        List<VeiculoDTO> veiculo = new ArrayList();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        //Classe que vai recuperar os dados do banco
        ResultSet rset = null;
        
        try { 
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            rset = pstm.executeQuery();
            
            while(rset.next()) { 
                VeiculoDTO veiculodto = new VeiculoDTO();
                
                //Recuperar o ID;
                veiculodto.setId(Integer.toString(rset.getInt("id")));
                veiculodto.setNome(rset.getString("nome"));
                veiculodto.setTipo(rset.getString("tipo"));
                veiculodto.setModelo(rset.getString("modelo"));
                veiculodto.setAno(rset.getString("ano"));
                veiculodto.setTelefone(rset.getString("telefone"));
                veiculodto.setPlaca(rset.getString("placa"));
                veiculodto.setCor(rset.getString("cor"));
                veiculodto.setEntrada(rset.getString("entrada"));
                veiculodto.setValor(rset.getFloat("valor"));
                veiculodto.setHoraentrada(rset.getString("horaentrada"));
                
                //Recuperar o nome
                
                
                veiculo.add(veiculodto);
                
            } 
        }catch (Exception e) { 
            e.printStackTrace();
            } finally { 
            try { 
                if (rset!=null){ 
                    rset.close();            
                }
                if (pstm != null) { 
                    pstm.close();
                }
                if ( conn != null) { 
                    conn.close();
                }
                } catch(Exception e) { 
                    e.printStackTrace();
                }
            }
        return veiculo;
        
    }
   
   //Buscar dados do veiculo/proprietário no BD por NOME
   public List<VeiculoDTO> getDadosProprietarioPorNome(VeiculoDTO veiculodto2) { 
        
        String sql = "SELECT * FROM veiculo where nome =?";
        
        List<VeiculoDTO> veiculo = new ArrayList();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        //Classe que vai recuperar os dados do banco
        ResultSet rset = null;
        
        try { 
            
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setString(1, veiculodto2.getNome());
            
            rset = pstm.executeQuery();
            
            while(rset.next()) { 
                VeiculoDTO veiculodto = new VeiculoDTO();
                
                //Recuperar o ID;
                veiculodto.setId(Integer.toString(rset.getInt("id")));
                veiculodto.setNome(rset.getString("nome"));
                veiculodto.setTipo(rset.getString("tipo"));
                veiculodto.setModelo(rset.getString("modelo"));
                veiculodto.setAno(rset.getString("ano"));
                veiculodto.setTelefone(rset.getString("telefone"));
                veiculodto.setPlaca(rset.getString("placa"));
                veiculodto.setCor(rset.getString("cor"));
                veiculodto.setEntrada(rset.getString("entrada"));
                veiculodto.setValor(rset.getFloat("valor"));
                veiculodto.setHoraentrada(rset.getString("horaentrada"));
                //Recuperar o nome
                
                
                veiculo.add(veiculodto);
                
            } 
        }catch (Exception e) { 
            e.printStackTrace();
            } finally { 
            try { 
                if (rset!=null){ 
                    rset.close();            
                }
                if (pstm != null) { 
                    pstm.close();
                }
                if ( conn != null) { 
                    conn.close();
                }
                } catch(Exception e) { 
                    e.printStackTrace();
                }
            }
        return veiculo;
        
    }
   
    public List<VeiculoDTO> PegarDadosPeloNome(String nome) { 
        
        String sql = "SELECT * FROM veiculo where nome =?";
        
        List<VeiculoDTO> veiculo = new ArrayList();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        //Classe que vai recuperar os dados do banco
        ResultSet rset = null;
        
        try { 
            
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setString(1, nome);
            
            rset = pstm.executeQuery();
            
            while(rset.next()) { 
                VeiculoDTO veiculodto = new VeiculoDTO();
                
                //Recuperar o ID;
                veiculodto.setId(Integer.toString(rset.getInt("id")));
                veiculodto.setNome(rset.getString("nome"));
                veiculodto.setTipo(rset.getString("tipo"));
                veiculodto.setModelo(rset.getString("modelo"));
                veiculodto.setAno(rset.getString("ano"));
                veiculodto.setPlaca(rset.getString("placa"));
                veiculodto.setCor(rset.getString("cor"));
                veiculodto.setEntrada(rset.getString("entrada"));
                
                //Recuperar o nome
                
                
                veiculo.add(veiculodto);
                
            } 
        }catch (Exception e) { 
            e.printStackTrace();
            } finally { 
            try { 
                if (rset!=null){ 
                    rset.close();            
                }
                if (pstm != null) { 
                    pstm.close();
                }
                if ( conn != null) { 
                    conn.close();
                }
                } catch(Exception e) { 
                    e.printStackTrace();
                }
            }
        return veiculo;
        
    }
    
   //Buscar dados do veiculo/proprietário no BD por ID
   public List<VeiculoDTO> getDadosProprietarioPorID (VeiculoDTO veiculodto2) { 
        
        String sql = "SELECT * FROM veiculo where id =?";
        
        List<VeiculoDTO> veiculo = new ArrayList();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        //Classe que vai recuperar os dados do banco
        ResultSet rset = null;
        
        try { 
            
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setInt(1, veiculodto2.getUltimoid());
            
            rset = pstm.executeQuery();
            
            while(rset.next()) { 
                VeiculoDTO veiculodto = new VeiculoDTO();
                
                //Recuperar o ID;
                veiculodto.setId(Integer.toString(rset.getInt("id")));
                veiculodto.setNome(rset.getString("nome"));
                veiculodto.setTipo(rset.getString("tipo"));
                veiculodto.setModelo(rset.getString("modelo"));
                veiculodto.setAno(rset.getString("ano"));
                veiculodto.setTelefone(rset.getString("telefone"));
                veiculodto.setPlaca(rset.getString("placa"));
                veiculodto.setCor(rset.getString("cor"));
                veiculodto.setEntrada(rset.getString("entrada"));
                veiculodto.setValor(rset.getFloat("valor"));
                veiculodto.setHoraentrada(rset.getString("horaentrada"));
                
                //Recuperar o nome
                
                
                veiculo.add(veiculodto);  
             
                
                
                
            } 
            
            
        }catch (Exception e) { 
            e.printStackTrace();
            } finally { 
            try { 
                if (rset!=null){ 
                    rset.close();            
                }
                if (pstm != null) { 
                    pstm.close();
                }
                if ( conn != null) { 
                    conn.close();
                }
                } catch(Exception e) { 
                    e.printStackTrace();
                }
            }
        return veiculo;
        
    }
   
   //Apagar dados do Veiculo/Proprietário selecionado no BD
   public void DeletarDadosVeiculo(int id){
       String sql = "DELETE FROM veiculo WHERE id = ?";
       
       Connection conn = null;
       PreparedStatement pstm = null;
       
       try { 
           conn = ConexaoDAO.CreateConnectiontoMySQL();
           pstm = conn.prepareStatement(sql);
           
           pstm.setInt(1, id);
           
          boolean execute = pstm.execute();
          
       } catch(SQLException erro) { 
           erro.printStackTrace();
           JOptionPane.showMessageDialog(null, "DELETAR VEICULO: " + erro);
       } catch (ClassNotFoundException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   //Pegar o ID do Banco de Dados
   public List<VeiculoDTO> getUltimoID() { 
        
        String sql = "select * from veiculo";
        
        List<VeiculoDTO> veiculo = new ArrayList();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        //Classe que vai recuperar os dados do banco
        ResultSet rset = null;
        
        try { 
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            rset = pstm.executeQuery();
            
            while(rset.next()) { 
                VeiculoDTO veiculodto = new VeiculoDTO();
                
                //Recuperar o ID;
                veiculodto.setUltimoid(rset.getInt("id"));
                             
                veiculo.add(veiculodto);
                
            } 
        }catch (Exception e) { 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel retirar os dados\n" + e);
            } finally { 
            try { 
                if (rset!=null){ 
                    rset.close();            
                }
                if (pstm != null) { 
                    pstm.close();
                }
                if ( conn != null) { 
                    conn.close();
                }
                } catch(Exception e) { 
                    e.printStackTrace();
                }
            }
        return veiculo;
        
    }
   
  public void AlterarVeiculo(VeiculoDTO veiculodto){ 

        String sql = "Update veiculo set nome = ?, telefone =?, modelo =?, ano = ?, cor = ?, tipo = ?,  placa =? where id = ? ";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try{ 
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, veiculodto.getNome());
            pstm.setString(2, veiculodto.getTelefone());
            pstm.setString(3, veiculodto.getModelo());
            pstm.setString(4, veiculodto.getAno());
            pstm.setString(5, veiculodto.getCor());
            pstm.setString(6, veiculodto.getTipo());
            pstm.setString(7, veiculodto.getPlaca());
            pstm.setInt(8, veiculodto.getUltimoid());

            pstm.execute();
            
            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
        }catch(Exception e) { 
            
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "ALTERAR DADOS VEICULODAO\n" + e);
        }
    }
   
  public void CadastrarNovoValor(VeiculoDTO veiculodto){ 
        
        String sql = "Update veiculo set valor = ? where id = ? ";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try{ 
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = conn.prepareStatement(sql);
            
            pstm.setFloat(1, veiculodto.getValor());
            pstm.setInt(2, veiculodto.getUltimoid());
            
            pstm.execute();
            
        }catch(Exception e) { 
            
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "ALTERAR DADOS VEICULODAO\n" + e);
        }
    }
  
   public boolean GerarComprovanteEntrada(String nome) { 
        
        String sql = """
                     SELECT
                          *,
                          veiculo.`id` AS veiculo_id,
                          veiculo.`nome` AS veiculo_nome,
                          veiculo.`tipo` AS veiculo_tipo,
                          veiculo.`modelo` AS veiculo_modelo,
                          veiculo.`ano` AS veiculo_ano,
                          veiculo.`cor` AS veiculo_cor,
                          veiculo.`placa` AS veiculo_placa,
                          veiculo.`entrada` AS veiculo_entrada,
                          veiculo.`telefone` AS veiculo_telefone,
                          veiculo.`valor` AS veiculo_valor,
                          veiculo.`horaentrada` AS veiculo_horaentrada,
                          veiculo.`horasaida` AS veiculo_horasaida
                     FROM
                           veiculo where nome = ?""";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try { 
            
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setString(1, nome);
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
           /* InputStream caminhorelatorio = VeiculoDTO.class.getResourceAsStream("/Romprovante/comprovante_Est.jasper");
            JasperReport report = JasperCompileManager.compileReport(caminhorelatorio);
            JasperPrint jasperprint = JasperFillManager.fillReport(caminhorelatorio, new HashMap(), jrRS);
            JasperViewer.viewReport(jasperprint, false);*/
            //CaminhoRELATORIO
            
            InputStream caminhorelatorio = getClass().getClassLoader().getResourceAsStream("C:\\Users\\Rodrigo\\Documents\\NetBeansProjects\\FSA_Parking\\src\\Relatorio\\comprovantel_Est.jasper");
            JasperPrint jasperprint = JasperFillManager.fillReport("C:\\Users\\Rodrigo\\Documents\\NetBeansProjects\\FSA_Parking\\src\\Relatorio\\comprovantel_Est.jasper", new HashMap(), ConexaoDAO.CreateConnectiontoMySQL());
            JasperExportManager.exportReportToPdfFile(jasperprint, "C:/Users/Rodrigo/Documents/NetBeansProjects/FSA_Parking/src/Relatorio/relatorioentrada.pdf");
            File file = new File("C:/Users/Rodrigo/Documents/NetBeansProjects/FSA_Parking/src/Relatorio/relatorioentrada.pdf");
            try { 
                Desktop.getDesktop().open(file);
            }catch (IOException erro) { 
                erro.printStackTrace();
                JOptionPane.showMessageDialog(null, erro);
            }
           return true;
        }catch (Exception e) { 
            e.printStackTrace();
            return false;
            } finally { 
            try { 
               
                if (pstm != null) { 
                    pstm.close();
                }
                if ( conn != null) { 
                    conn.close();
                }
                } catch(Exception e) { 
                    e.printStackTrace();
                }
            }
        
        
    }
  
   public void CadastrarValorAtualizado(float valorbase){ 
        
        String sql = "Update veiculo set valor = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try{ 
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = conn.prepareStatement(sql);
            
            pstm.setFloat(1, valorbase);
          
            
            pstm.execute();
            
        }catch(Exception e) { 
            
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
   
   public void CadastrarValorAtualizadoID(float valorbase, int id){ 
        
        String sql = "Update veiculo set valor = ? where id = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try{ 
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = conn.prepareStatement(sql);
            
            pstm.setFloat(1, valorbase);
            System.out.println(id);
            pstm.setFloat(2, id);
            
            
            pstm.execute();
            
        }catch(Exception e) { 
            
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
  
}
