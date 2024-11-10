package DAO;

import DTO.AlunosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AlunosDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<AlunosDTO> lista = new ArrayList<>();

    public void cadastrarAlunos(AlunosDTO objalunosdto) {

        String sql = "insert into alunos (matricula,nome,cpf,telefone,rg,dt_nascimento,nome_mae,nome_pai,endereco) values (?,?,?,?,?,?,?,?,?)";

        conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, objalunosdto.getMatricula());
            pstm.setString(2, objalunosdto.getNome());
            pstm.setString(3, objalunosdto.getCpf());
            pstm.setString(4, objalunosdto.getTelefone());
            pstm.setString(5, objalunosdto.getRg());
            pstm.setString(6, objalunosdto.getDt_nascimento());
            pstm.setString(7, objalunosdto.getNome_mae());
            pstm.setString(8, objalunosdto.getNome_pai());
            pstm.setString(9, objalunosdto.getEndereco());

            pstm.execute();
            pstm.close();

            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro em AlunosDAO - Cadastrar" + erro);
        }
    }

    public ArrayList<AlunosDTO> listarAlunos() {

        String sql = "select * from alunos";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareCall(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                AlunosDTO objalunosDTO = new AlunosDTO();
                objalunosDTO.setId_alunos(rs.getInt("id"));
                objalunosDTO.setMatricula(rs.getInt("matricula"));
                objalunosDTO.setNome(rs.getString("nome"));
                objalunosDTO.setCpf(rs.getString("cpf"));
                objalunosDTO.setTelefone(rs.getString("telefone"));
                objalunosDTO.setRg(rs.getString("rg"));
                objalunosDTO.setDt_nascimento(rs.getString("dt_nascimento"));
                objalunosDTO.setNome_mae(rs.getString("nome_mae"));
                objalunosDTO.setNome_pai(rs.getString("nome_pai"));
                objalunosDTO.setEndereco(rs.getString("endereco"));

                lista.add(objalunosDTO);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro em AlunosDAO - Listar" + erro);
        }

        return lista;
    }
    
    public void excluirAlunos(AlunosDTO objalunosdto){
      String sql = "delete from alunos where id = ?";
      conn = new ConexaoDAO().conectaBD();
      
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objalunosdto.getId_alunos());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro AlunosDAO - excluirAlunos" + erro);
        }
    }
}
