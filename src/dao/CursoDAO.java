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

/**
 *
 * @author 201811640014
 */
public class CursoDAO {
  
    
    //realiza insert no BD
    public boolean gravarCurso(Curso c){
        //criar conexão com BD usando método estático
        Connection conn = ConexaoComBD.conexao();
        //instrução para o BD
        String sql = "INSERT INTO curso(nome, area, cargahoraria, valorcurso, valormensal, numvagas, cod) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            //criação do statement de comunicação
            PreparedStatement ps =
                    conn.prepareStatement(sql);
            //definir os valores dos "?"
            ps.setString(1, c.getNome());
            ps.setString(2, c.getArea());
            ps.setInt(3, c.getCargaHoraria());
            ps.setDouble(4, c.getValorCurso());
            ps.setDouble(5, c.getValorMensal());
            ps.setInt(6, c.getNumVagas());
            ps.setString(7, c.getCod());
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
    public List<Curso> recuperaTodosCursos(){
        Connection conexao = ConexaoComBD.conexao();
        //instrução sql
        String sql = "SELECT * FROM curso;";
        try {
            //statement de conexão
            PreparedStatement ps = conexao.prepareStatement(sql);
            //recebe a tabela de retorno do banco de dados em um formato java
            ResultSet rs = ps.executeQuery();
            //criar lista de retorno
            List<Curso> lista = new ArrayList<>();
            
            //tratar o retorno do banco
            
            while(rs.next()){
                //criar um objeto modelo do tipo do retorno 
                Curso c = new Curso();
                c.setIdCurso(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setArea(rs.getString(3));
                c.setCargaHoraria(rs.getInt(5));
                c.setValorCurso(rs.getDouble(6));
                c.setValorMensal(rs.getDouble(7));
                c.setCod(rs.getString(8));
                lista.add(c);
            }
            //retornar lista preenchida 
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //retorna null em caso de excessao
        return null;
    }    
    
    public List<Curso> pesquisaCursos(String pesquisa){
   //metodo que pesquisa os cursps a partir de um critério de seleção  
    Connection conexao = ConexaoComBD.conexao();
        //instrução sql
        String sql = "SELECT * FROM curso " + "WHERE nome LIKE ?;";
        
        try {
            //statement de conexao
            PreparedStatement ps = conexao.prepareStatement(sql);
            //define criterio de pesquisa (o "?")
            ps.setString(1, "%"+pesquisa+"%");
            //recebe a tabela de retorno do banco de dados em um formato java
            ResultSet rs = ps.executeQuery();
            //criar lista de retorno
            List<Curso> lista = new ArrayList<>();
            
            //tratar o retorno do banco
            
            while(rs.next()){
                //criar um objeto modelo do tipo do retorno 
                Curso c = new Curso();
                c.setIdCurso(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setArea(rs.getString(3));
                c.setCargaHoraria(rs.getInt(5));
                c.setValorCurso(rs.getDouble(6));
                c.setValorMensal(rs.getDouble(7));
                c.setCod(rs.getString(8));
                lista.add(c);
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
    public boolean excluirCurso(int idCurso){
        //conexão com o banco
        Connection conexao = ConexaoComBD.conexao();
        //sql a ser executado
        String sql = "DELETE FROM CURSO WHERE idCurso = ? ;";
        try {
            //criar transporteda instruçao para o banco
            PreparedStatement ps = conexao.prepareStatement(sql);
            //definir os parametros (?)
            ps.setInt(1, idCurso);
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

