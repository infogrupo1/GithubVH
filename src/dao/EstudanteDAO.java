/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.ConexaoComBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Estudante;

/**
 *
 * @author 201811640014
 */
public class EstudanteDAO {
    //realiza insert no BD
    public boolean gravarEstudante(Estudante e){
        //criar conexão com BD usando método estático
        Connection conn = ConexaoComBD.conexao();
        //instrução para o BD
        String sql = "INSERT INTO estudante(nome, telefone) VALUES (?, ?);";
        try {
            //criação do statement de comunicação
            PreparedStatement ps =
                    conn.prepareStatement(sql);
            //definir os valores dos "?"
            ps.setString(1, e.getNome());
            ps.setString(2, e.getTelefone());
            //executar a instrução SQL
            ps.executeUpdate();
            //encerrar a conexão
            ps.close();
            conn.close();
            //retorno de ok
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;        
    }
    
    //recupera todos os cursos cadastrados
    public List<Estudante> recuperaTodosEstudantes(){
        Connection conexao = ConexaoComBD.conexao();
        //instrução sql
        String sql = "SELECT * FROM estudante;";
        try {
            //statement de conexão
            PreparedStatement ps = conexao.prepareStatement(sql);
            //recebe a tabela de retorno do banco de dados em um formato java
            ResultSet rs = ps.executeQuery();
            //criar lista de retorno
            List<Estudante> lista = new ArrayList<>();
            
            //tratar o retorno do banco
            
            while(rs.next()){
                //criar um objeto modelo do tipo do retorno 
                Estudante e = new Estudante();
                e.setIdEstudante(rs.getInt(1));
                e.setNome(rs.getString(2));
                e.setTelefone(rs.getString(3));
                
                lista.add(e);
            }
            //retornar lista preenchida 
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //retorna null em caso de excessao
        return null;
    }    
    
    public List<Estudante> pesquisaEstudante(String pesquisa){
   //metodo que pesquisa os cursps a partir de um critério de seleção  
    Connection conexao = ConexaoComBD.conexao();
        //instrução sql
        String sql = "SELECT * FROM estudante " + "WHERE nome LIKE ?;";
        
        try {
            //statement de conexao
            PreparedStatement ps = conexao.prepareStatement(sql);
            //define criterio de pesquisa (o "?")
            ps.setString(1, "%"+pesquisa+"%");
            //recebe a tabela de retorno do banco de dados em um formato java
            ResultSet rs = ps.executeQuery();
            //criar lista de retorno
            List<Estudante> lista = new ArrayList<>();
            
            //tratar o retorno do banco
            
            while(rs.next()){
                //criar um objeto modelo do tipo do retorno 
                Estudante e = new Estudante();
                e.setIdEstudante(rs.getInt(1));
                e.setNome(rs.getString(2));
                e.setTelefone(rs.getString(3));
                
                lista.add(e);
            }
            //retornar lista preenchida 
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //retorna null em caso de excessao
        return null;
    }    
    
    //método responsável pela exclusao de um curso
    public boolean excluirEstudante(int idEstudante){
        //conexão com o banco
        Connection conexao = ConexaoComBD.conexao();
        //sql a ser executado
        String sql = "DELETE FROM ESTUDANTE WHERE idEstudante = ? ;";
        try {
            //criar transporteda instruçao para o banco
            PreparedStatement ps = conexao.prepareStatement(sql);
            //definir os parametros (?)
            ps.setInt(1, idEstudante);
            //executar asql
            ps.execute();
            //retornar um ok
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //retorno falha
        return false;
    }
}
