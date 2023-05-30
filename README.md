# Problema da Cobertura de Vértices

Imagine um conjunto de salas interligadas por corredores. Um guarda postado numa sala é capaz de vigiar todos os corredores que convergem sobre a sala. 
Queremos determinar o menor número de guardas capaz de vigiar todos os corredores. Esta é uma instância do problema da cobertura mínima por vértices.

## Cobertura por vértices
 
Dizemos que um conjunto C de vértices de um grafo cobre uma aresta vw do grafo se v pertence a C ou w pertence a C (ou ambos).

Uma cobertura por vértices (= vertex cover) de um grafo, ou simplesmente cobertura, é um conjunto de vértices que cobre todas as arestas. Em outras palavras, uma cobertura é um conjunto de vértices que contém pelo menos uma das pontas de cada aresta.

A figura abaixo mostra um exemplo de cobertura de vértices:

![Exemplo](https://github.com/PUCRS-Poli-ES-ALAV/12-vertex-cover/blob/main/images/ex-grafo-cobertura-grafos.jpg)

## ALgoritmo: Cobertura por vértices

A algortimos abaixo computa a cobertura de vértices C de um grafo G.
 
![Algortimo](https://github.com/PUCRS-Poli-ES-ALAV/12-vertex-cover/blob/main/images/algor-cobertura-vertices.jpg)

1. Utilizando sua classe grafo implementada/recuperada na aula anterior, implemente e teste o algoritmo acima, para grafos conexos com 7, 12 e 20 nodos, 

