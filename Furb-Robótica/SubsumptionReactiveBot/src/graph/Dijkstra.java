package graph;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
	private static final int INFINITO = Integer.MAX_VALUE;
	private List<Node> s = new ArrayList();
	private List<Node> q = new ArrayList();
	private Node dest, orig;
	private static final Node nil = new Node("nil");
	private String paiMatriz = "", dMatriz = "", cabecalhoMatriz = "";

	public Dijkstra(Graph graph, Node orig, Node dest) {
		// Se tiver um destino, seta ele no atributo
		if (dest != null) {
			this.dest = dest;
		}
		
		this.orig = orig;

		dijkstra(graph, orig);
	}

	private void initialize(Graph graph, Node orig) {
		for (Node node : graph.getNodes()) {
			node.setDist(INFINITO);
			node.setStatus("ABERTO");
			node.setParent(nil);
			q.add(node);
		}

		// Origem inicia com a distância 0
		orig.setDist(0);
	}

	public void dijkstra(Graph graph, Node orig) {
		// Inicializa o grafo para aplicar o algoritmo
		initialize(graph, orig);

		while (!q.isEmpty()) {
			// Pega o vértice não explorado com menor distância
			Node u = extractMin(q);

			// Para o loop se tiver um destino determinado e o vértice u for ele
			if (this.dest != null)
				if (u.equals(this.dest)) {
					// Limpa q e para o loop
					q.clear();
					break;
				}

			// Seta o vértice na lista dos explorados e altera o status dele
			s.add(u);
			u.setStatus("EXPLORADO");

			// Remove o vértive u da lista de não explorados
			q.remove(u);

			// Relaxa cada vértice não explorado adjacente a v
			for (Node v : u.getAdjs(graph.isDirected()))
				if (!v.getStatus().equals("EXPLORADO"))
					relax(u, v);
		}

		// Monta matriz de roteamento
		setMatrizRoteamento(graph);
	}

	private void relax(Node u, Node v) {
		Edge edgeUV = u.getEdgeByNode(v);
		float wuv = edgeUV.getValue();

		// Se a distância do vérice adjacente for maior do que a distância de u + o vértice que leva até v
		// Atualiza a distância dele
		if (v.getDist() > (u.getDist() + wuv)) {
			// Atualiza valores do vértice
			v.setDist(u.getDist() + wuv);
			v.setParent(u);
		}
	}

	/**
	 * Utiliza stream na lista q para verificar qual o vértice com menor distância.
	 * @param q - lista de vértices não fechados
	 * @return Vértice não explorado com menor distância
	 */
	private Node extractMin(List<Node> q) {
		return Collections.min(q);
	}

	public void printMatrizRoteamento() {
		System.out.println("\n\t" + cabecalhoMatriz);
		System.out.println("pai:\t" + paiMatriz);
		System.out.println("d:\t" + dMatriz + "\n");
	}

	public void setMatrizRoteamento(Graph grafo) {
		cabecalhoMatriz = "";
		paiMatriz = "";
		dMatriz = "";

		for (Node v : grafo.getNodes()) {
			if (v.getDist() == INFINITO) {
				cabecalhoMatriz += v.getId() + "\t";
				dMatriz += "- \t";
				paiMatriz += "- \t";
			} else {
				cabecalhoMatriz += v.getId() + "\t";
				paiMatriz += v.getParent().getId() + "\t";

				// Seta as casas decimais só quando são significantes
				// dMatriz += v.getDistancia() + "\t";
				if (v.getDist() == (long) v.getDist()) {
					dMatriz += String.format("%d", (long) v.getDist()) + "\t";
				} else {
					dMatriz += String.format("%s", v.getDist()) + "\t";
				}
			}
		}
	}

	/**
	 * Constroi o caminho de vértices da origem ao destino. Para isso, baseia-se
	 * no pai de cada vértice. Ou seja, a partir do destino, pega o pai até
	 * chegar na origem.
	 * 
	 * @param destino vértice de destino do caminho.
	 * @return List de vértices até o destino.
	 */
	public List<Node> getCaminho(Node destino) {
		List caminho = new ArrayList();
		boolean chegou = false;
		Node atual = destino.getParent();

		// Enquanto não chegar ao destino
		while (!chegou) {
			if (atual.getParent() == null)
				System.out.println("Pá, de ruim no " + atual.getId());

			// Se o vértice atual não for igual ao da origem
			if (!atual.equals(this.orig)) {
				// Add o vértice atual na lista de caminho
				caminho.add(atual);

				// O atual será seu pai
				atual = atual.getParent();
			}
			// Quando chegar no destino, add o atual na lista e sai do loop
			else {
				caminho.add(atual);
				chegou = true;
			}
		}

		// Inverte ordem da lista visto que o caminho é baseado no pai dos  vértices
		Collections.reverse(caminho);
		return caminho;
	}

	// Construtor vazio.
	public Dijkstra() {
	}
}
