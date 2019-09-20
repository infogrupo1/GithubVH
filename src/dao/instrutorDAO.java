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
import model.Curso;
import model.Instrutor;

/**
 *
 * @author 201811640014
 */
public class instrutorDAO {
    //realiza insert no BD
    public boolean gravarInstrutor(Instrutor i){
        //criar conexão com BD usando método estático
        Connection conn = ConexaoComBD.conexao();
        //instrução para o BD
        String sql = "INSERT INTO instrutor(nome, cpf, rg, telefone, datanascimento, dataadmissao) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            //criação do statement de comunicação
            PreparedStatement ps =
                    conn.prepareStatement(sql);
            //definir os valores dos "?"
            ps.setString(1, i.getNome());
            ps.setString(2, i.getCpf());
            ps.setString(3, i.getRg());
            ps.setString(4, i.getTelefone());
            ps.setDate(5, i.getDataadmissao());
            ps.setDate(6, i.getDatanascimento());
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
    public List<Instrutor> recuperaTodosInstrutores(){
        Connection conexao = ConexaoComBD.conexao();
        //instrução sql
        String sql = "SELECT * FROM instrutor;";
        try {
            //statement de conexão
            PreparedStatement ps = conexao.prepareStatement(sql);
            //recebe a tabela de retorno do banco de dados em um formato java
            ResultSet rs = ps.executeQuery();
            //criar lista de retorno
            List<Instrutor> lista = new ArrayList<>();
            
            //tratar o retorno do banco
            
            while(rs.next()){
                //criar um objeto modelo do tipo do retorno 
                Instrutor i = new Instrutor();
                i.setIdInstrutor(rs.getInt(1));
                i.setNome(rs.getString(2));
                i.setCpf(rs.getString(3));
                i.setRg(rs.getString(4));
                i.setTelefone(rs.getString(5));
                i.setDataadmissao(rs.getDate(6));
                i.setDatanascimento(rs.getDate(7));
                
                lista.add(i);
            }
            //retornar lista preenchida 
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //retorna null em caso de excessao
        return null;
    }    
    
    public List<Instrutor> pesquisaInstrutor(String pesquisa){
   //metodo que pesquisa os cursps a partir de um critério de seleção  
    Connection conexao = ConexaoComBD.conexao();
        //instrução sql
        String sql = "SELECT * FROM instrutor " + "WHERE nome LIKE ?;";
        
        try {
            //statement de conexao
            PreparedStatement ps = conexao.prepareStatement(sql);
            //define criterio de pesquisa (o "?")
            ps.setString(1, "%"+pesquisa+"%");
            //recebe a tabela de retorno do banco de dados em um formato java
            ResultSet rs = ps.executeQuery();
            //criar lista de retorno
            List<Instrutor> lista = new ArrayList<>();
            
            //tratar o retorno do banco
            
            while(rs.next()){
                //criar um objeto modelo do tipo do retorno 
                Instrutor i = new Instrutor();
                i.setIdInstrutor(rs.getInt(1));
                i.setNome(rs.getString(2));
                i.setCpf(rs.getString(3));
                i.setRg(rs.getString(4));
                i.setTelefone(rs.getString(5));
                i.setDataadmissao(rs.getDate(6));
                i.setDatanascimento(rs.getDate(7));
                
                lista.add(i);
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
    public boolean excluirInstrutor(int idInstrutor){
        //conexão com o banco
        Connection conexao = ConexaoComBD.conexao();
        //sql a ser executado
        String sql = "DELETE FROM INSTRUTOR WHERE idInstrutor = ? ;";
        try {
            //criar transporteda instruçao para o banco
            PreparedStatement ps = conexao.prepareStatement(sql);
            //definir os parametros (?)
            ps.setInt(1, idInstrutor);
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
