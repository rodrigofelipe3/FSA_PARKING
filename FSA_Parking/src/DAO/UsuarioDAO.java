package DAO;

import DTO.UsuarioDTO;
import DTO.VeiculoDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection conn;

    UsuarioDTO usuariodto2 = new UsuarioDTO();
    
    //CADASTRAR USUARIO NO BD
    public void CadastrarUsuario(UsuarioDTO usuariodto){
        
        String sql = "INSERT INTO usuarios(nome, cpf, cargo, usuario, senha, tipo, endereco, telfone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        conn = null;
        
        PreparedStatement pstm = null;
        
        try { 
            //Cria Conexão com Banco de DADOS
            conn = ConexaoDAO.CreateConnectiontoMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setString(1, usuariodto.getNome());
            pstm.setString(2, usuariodto.getCpf());
            pstm.setString(3, usuariodto.getCargo());
            pstm.setString(4, usuariodto.getUsuario());
            pstm.setString(5, usuariodto.getSenha());
            pstm.setString(6, usuariodto.getTipo());
            pstm.setString(7, usuariodto.getEndereco());
            pstm.setString(8, usuariodto.getTelefone());
            
            
            boolean execute = pstm.execute();
            
            JOptionPane.showMessageDialog(null, "Usuario Cadastrado com Sucesso!");
            
        } catch(Exception e) { 
            
             JOptionPane.showMessageDialog(null, "Ocorreu algum erro ao tentar Cadastrar: " + e);
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
    
    //AUTENTICAR USUARIO NO BD
    public ResultSet autenticarUsuario(UsuarioDTO usuariodto) throws Exception {

        conn = ConexaoDAO.CreateConnectiontoMySQL();

        try {
            //Recebe o Usuario e senha cadastrado no SQL
            String sql = "select * from usuarios where usuario = ? and senha = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, usuariodto.getUsuario());
            pstm.setString(2, usuariodto.getSenha());

            ResultSet rs = pstm.executeQuery();

            return rs;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "USUARIODAO:" + erro);
            return null;
        }

    }
    
    //PEGAR O TIPO DE USUARIO NO BD PARA AUTENTICAÇÃO
    public List<UsuarioDTO> getTipo(UsuarioDTO usuariodto1) {

        String sql = "select nome, tipo, cargo from usuarios where usuario = ?";

        //Classe que vai armazenar todos os GETS
        List<UsuarioDTO> usuariodto = new ArrayList();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco
        ResultSet rset = null;

        try {
            conn = ConexaoDAO.CreateConnectiontoMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, usuariodto1.getUsuario());
           

            rset = pstm.executeQuery();

            while (rset.next()) {

                UsuarioDTO usuario = new UsuarioDTO();

                //Recuperar o tipo
                
                usuario.setNome(rset.getString("nome"));
                usuario.setCargo(rset.getString("cargo"));
                usuario.setTipo(rset.getString("tipo"));

                usuariodto.add(usuario);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuariodto;

    }
    
    //PEGAR OS DADOS DO USUARIO NO BD
    public List<UsuarioDTO> getDados(UsuarioDTO usuariodto1) {

        String sql = "select * from usuarios where usuario = ? and senha = ?";

        //Classe que vai armazenar todos os GETS
        List<UsuarioDTO> usuariodto = new ArrayList();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco
        ResultSet rset = null;

        try {
            conn = ConexaoDAO.CreateConnectiontoMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, usuariodto1.getUsuario());
            pstm.setString(2, usuariodto1.getSenha());

            rset = pstm.executeQuery();

            while (rset.next()) {

                UsuarioDTO usuario = new UsuarioDTO();

                //Recuperar o tipo
                usuario.setId_usuario(rset.getInt("id"));
                usuario.setUsuario(rset.getString("usuario"));
                usuario.setSenha(rset.getString("senha"));
                usuario.setTipo(rset.getString("tipo"));

                usuariodto.add(usuario);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuariodto;

    }

}
