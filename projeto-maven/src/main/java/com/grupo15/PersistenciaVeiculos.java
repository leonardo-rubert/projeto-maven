package com.grupo15;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class PersistenciaVeiculos {
  private static final String SAMPLE_CSV_FILE_PATH = "veiculos.dat";

  public List<Veiculo> carregaVeiculos() throws IOException {
    final List<Veiculo> lista = new ArrayList<Veiculo>();
    final Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
    final CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    for (final CSVRecord csvRecord : csvParser) {
      final String placa = csvRecord.get(0);
      final String marca = csvRecord.get(1);
      final String cor = csvRecord.get(2);

      final Veiculo veiculo = new Veiculo(placa, marca, cor);

      lista.add(veiculo);
    }

    csvParser.close();

    return lista;
  }

  public boolean persisteVeiculos(final List<Veiculo> lista) throws IOException {
    final String currDir = Paths.get("").toAbsolutePath().toString();
    final String completePath = currDir + "/" + SAMPLE_CSV_FILE_PATH;
    final Path path = Paths.get(completePath);
    final PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, StandardCharsets.UTF_8));
    for (final Veiculo veiculo : lista) {
      final String linha = veiculo.getPlaca() + "," + veiculo.getMarca() + "," + veiculo.getCor() + ","
          + veiculo.getCategoria();
      writer.println(linha);
    }
    return true;
  }
}