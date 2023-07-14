package it.polito.tdp.exam.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.exam.db.BaseballDAO;

public class Model {
	private BaseballDAO dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	public Model() {
		this.dao= new BaseballDAO();
		this.grafo = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
	}
	public List<String> allTeams(){

		return this.dao.getAllTeamName();
	}
	
	public void buildGrafo( String nome) {
		List<Integer> vertici = this.dao.getVertici(nome);
		Graphs.addAllVertices(this.grafo, vertici);
		

		for (int i=0; i<vertici.size();i++) {
			for(int j =i+1; j< vertici.size(); j++) {
				int salrio1 = this.dao.getSalaryTeamYear(vertici.get(i), nome);
				int salrio2 = this.dao.getSalaryTeamYear(vertici.get(j), nome);
				int delta = Math.abs(salrio1-salrio2);
				
				Graphs.addEdgeWithVertices(this.grafo, vertici.get(i), vertici.get(j),delta );

				
			}
	}
	System.out.println(this.grafo.vertexSet().size()+"-"+this.grafo.edgeSet().size());
		
	}

	public List<String> getPesoArco(){
		List<String> result = new ArrayList<>();
		for (DefaultWeightedEdge edge: this.grafo.edgeSet()) {
			result.add(this.grafo.getEdgeSource(edge)+"-->"+ this.grafo.getEdgeTarget(edge)+ "   "+ this.grafo.getEdgeWeight(edge)+"\n");
		}
		Collections.sort(result);
		Collections.reverse(result);
		return result;
	}
	
	public Set<Integer> getVertici() {
		return this.grafo.vertexSet();
	}
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	
    public List<Dettaglio> getDettagli(int anno) {
        List<Dettaglio> result = new ArrayList<Dettaglio>();
        List<Integer> adiacenti = Graphs.neighborListOf(this.grafo, anno);


        for(Integer nodo : adiacenti) {
            DefaultWeightedEdge arco = this.grafo.getEdge(anno, nodo);
            result.add(new Dettaglio(nodo, (int)this.grafo.getEdgeWeight(arco)) );
        }
        Collections.sort(result);
        return result;
    }
	
}



 
