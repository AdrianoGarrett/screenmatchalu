package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=6585022c";

    public void exibeMenu(){
        System.out.println("Digite o nome da série");
        var nomeSerie =  leitura.nextLine().replace(" ", "+");

        var json = consumo.buscaDados( this.ENDERECO + nomeSerie + this.APIKEY);
        var dadosSerie = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for(int i = 1; i<= dadosSerie.totalTemporadas(); i++){
			json = consumo.buscaDados(this.ENDERECO + nomeSerie + "&season="+ i + this.APIKEY);

			temporadas.add(conversor.obterDados(json, DadosTemporada.class));

		}

        temporadas.forEach(t-> System.out.println(t));

    }

}
