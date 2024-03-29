package br.com.neurolight.cadastroaluno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public AlunoDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Aluno aluno) {
        ContentValues values = new ContentValues();

        values.put("nome",aluno.getNome());
        values.put("cpf",aluno.getCpf());
        values.put("telefone",aluno.getTelefone());

        return banco.insert("Aluno",null,values);
    }

    public void deletar(Aluno a) {
        banco.delete("Aluno","id = ?", new String[]{a.getId().toString()});
    }

    public void alterar(Aluno aluno) {

        ContentValues values = new ContentValues();

        values.put("nome",aluno.getNome());
        values.put("cpf",aluno.getCpf());
        values.put("telefone",aluno.getTelefone());

        banco.update("Aluno", values, "id = ?", new String[]{aluno.getId().toString()});

    }

    public List<Aluno> obterTodos() {
        List<Aluno> alunos = new ArrayList<>();
        Cursor cursor = banco.query("Aluno", new String[]{"id", "nome", "cpf", "telefone"}, null, null, null, null, null);

        while(cursor.moveToNext()) {
            Aluno a = new Aluno();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            a.setTelefone(cursor.getString(3));

            alunos.add(a);
        }

        return alunos;
    }

}
