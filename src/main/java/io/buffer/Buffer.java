package io.buffer;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Buffer {

    public static void main(String[] args) throws IOException {
        Pessoa p = new Pessoa();
        p.setNome("Anderson Piotto");
        p.setCpf("123456456123");

        /* usando uma biblioteca do google, chamada Gson,
         converte o objeto pessoa para uma string
         no formato Json*/
        String pessoaJson = new Gson().toJson(p);
        System.out.println("Pessoa no formato Json: " + pessoaJson);

        System.out.println("Escrevendo no arquivo... ");

        // definindo o caminho e nome do arquivo
        Path path = Paths.get("arquivos/meuArquivo.txt");

        BufferedWriter writer = null;
        try {
            // Por meio da classe Files, obtemos um escritor, para escrever no path
            writer = Files.newBufferedWriter(path);
            //escrevendo
            writer.write(pessoaJson, 0, pessoaJson.length());
            System.out.println("Escrito com sucesso!");
        } catch (IOException x) {
            System.out.println("IOException: " + x.getMessage());
        } finally {
            writer.close();
        }

        /* veja o arquivo gerado na pasta aquivos
         na raiz do projeto*/

        System.out.println("Carregando o arquivo...");
        BufferedReader reader = null;
        try {
            // Por meio da classe Files, obtemos um leitor, para ler do arquivo
            reader = Files.newBufferedReader(path);

            // fazendo a leitura
            String line =  reader.readLine();
            while (line  != null) {
                System.out.println(line);
                line =  reader.readLine();
            }
            System.out.println("Carregado com sucesso!");
        } catch (IOException x) {
            System.out.println("IOException: " + x.getMessage());
        } finally {
            reader.close();
        }
    }
}

