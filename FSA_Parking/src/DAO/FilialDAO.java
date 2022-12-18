
package DAO;

import DTO.FilialDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;




public class FilialDAO {

    
    public void CadastrarFilial(FilialDTO filial){
        
        String sql = "INSERT INTO filial (Campus, endereco, telefone, cep) VALUES (?, ?, ?, ?)";
        
        Connection conn;
        conn = null;
        
        PreparedStatement pstm = null;
        
        try { 
            //Cria Conexão com Banco de DADOS
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setString(1, filial.getCampus());
            pstm.setString(2, filial.getEndereco());
            pstm.setString(3, filial.getTelefone());
            pstm.setString(4, filial.getCep());
           
            
            
            boolean execute = pstm.execute();
            
            JOptionPane.showMessageDialog(null, "Filail Cadastrada com Sucesso!");
            
        } catch(Exception e) { 
            
             JOptionPane.showMessageDialog(null, "Ocorreu algum erro ao tentar Cadastrar: \n" + e);
        }finally { 
            try { 
                if (pstm!=null) { 
                pstm.close();
            }
            if (conn!=null){
                conn.close();
            }
        } catch (Exception e){ 
            e.printStackTrace();
        }
        
    }
    }
    
    public List<FilialDTO> ListarDadosFilial(){
        
        List<FilialDTO> listadoFilial=new ArrayList<>();
        
        try {
            String sql="SELECT * FROM `filial`";
            
            Connection conexao = ConexaoDAO.CreateConnectiontoMySQL();
            
            Statement statement=conexao.createStatement();
            
            ResultSet resultado=statement.executeQuery(sql);
            
            while(resultado.next()){
                
                FilialDTO filial=new FilialDTO();
                
                filial.setCampus(resultado.getString("Campus"));
                filial.setTelefone(resultado.getString("Telefone"));
                filial.setEndereco(resultado.getString("endereco"));
                filial.setCep(resultado.getString("Cep"));
                
                
                listadoFilial.add(filial);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "LISTAR FILIAL\n" + ex);
        }
        
        return listadoFilial;
    }
    
    public List<FilialDTO> ListarDadosFilialCampus(FilialDTO filial2){
        
        List<FilialDTO> listadoFilial=new ArrayList<>();
        
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet resultado = null;
        
        try {
            String sql = "SELECT * FROM filial where Campus = ?;";
            
            conexao = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = (PreparedStatement) conexao.prepareStatement(sql);
            
            pstm.setString(1, filial2.getCampus());
               
            resultado=pstm.executeQuery();
            
            while(resultado.next()){
                
                FilialDTO filial=new FilialDTO();
                
                filial.setCampus(resultado.getString("Campus"));
                filial.setTelefone(resultado.getString("Telefone"));
                filial.setEndereco(resultado.getString("endereco"));
                filial.setCep(resultado.getString("cep"));
                
                listadoFilial.add(filial);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "LISTAR FILIAL\n" + ex);
        }
        
        return listadoFilial;
    }
    
    public void AlterarDadosFilial(FilialDTO filial){ 
        
        
        String sql = "Update filial set Campus = ?, endereco =?, Telefone = ?, CEP =? where Campus =? ";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try{ 
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, filial.getCampus());
            System.out.println(filial.getCampus());
            pstm.setString(2, filial.getEndereco());
            System.out.println(filial.getEndereco());
            pstm.setString(3, filial.getTelefone());
            System.out.println(filial.getTelefone());
            pstm.setString(4, filial.getCep());
            pstm.setString(5, filial.getCampus2());
            
            pstm.execute();
            
            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
        }catch(Exception e) { 
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ALTERAR DADOS FILIAL\n" + e);
        }
    }
    
    public void DeletarDadosFilial(FilialDTO filial) { 
        String sql = "Delete  from filial where Campus = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try { 
            
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, filial.getCampus());
            
            pstm.execute();
            
        }catch(Exception erro) { 
            erro.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel deletar\n" + erro);
        }
    } 
  
    
}
