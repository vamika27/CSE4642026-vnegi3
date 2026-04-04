# CSE 464 Project – Graph Processing System

## Student Information
Name: Vamika Negi  
Course: CSE 464 – Software QA and Testing  
Repository: https://github.com/vamika27/CSE4642026-vnegi3  

---

## Project Overview

This project implements a directed graph processing system in Java. The system reads graphs defined in DOT format, stores them internally, and supports multiple operations for manipulating and exporting graph data.

The implementation includes:

- Parsing DOT graph files
- Adding nodes to the graph
- Adding edges to the graph
- Preventing duplicate nodes and edges
- Exporting graph data to text format
- Exporting graph data to DOT format
- Generating graphical visualizations using Graphviz
- Automated unit testing using JUnit
- Maven-based build and testing

---

## Development Environment

The following tools were used to implement and test the project:

Operating System: macOS  
Programming Language: Java (JDK 17)  
IDE: IntelliJ IDEA  
Build Tool: Maven  
Testing Framework: JUnit 5  
Visualization Tool: Graphviz  
Version Control: Git and GitHub  

Graphviz is used to generate graphical PNG representations of graphs from DOT files.

---

## Project Structure

The project follows the standard Maven directory structure:

CSE4642026-vnegi3  
│  
├── pom.xml  
├── README.md  
│  
├── sample_inputs  
│   ├── input1.dot  
│   ├── outputGraph.txt  
│   ├── output1.dot  
│   ├── graph_output.dot  
│   └── graph_output.png  
│  
├── src  
│   ├── main  
│   │   └── java  
│   │       └── graph  
│   │           ├── Graph.java  
│   │           └── GraphParser.java  
│   │  
│   └── test  
│       └── java  
│           └── graph  
│               └── GraphTest.java  

---

## Feature 1 – Parse DOT Graph

API:

parseGraph(String filepath)

This method reads a DOT file and constructs a directed graph. The parser processes each line of the DOT file and extracts node labels and directed edges.

Example DOT input:

digraph {
a -> b;
b -> c;
}

Nodes and edges are stored internally in the Graph class.

The graph can be printed using the toString() method which outputs:

- number of nodes
- node labels
- number of edges
- edge list

Example output:

Number of nodes: 8  
Nodes: [a, b, c, d, e, f, g, h]  
Number of edges: 9  
Edges:  
a -> b  
b -> c  

Unit tests verify that parsing correctly identifies node and edge counts.

---

## Feature 2 – Add Node Operations

APIs:

addNode(String label)  
addNodes(String[] labels)

These functions allow nodes to be added to the graph dynamically.

Nodes are stored using a LinkedHashSet which prevents duplicates.

Example:

graph.addNode("a")  
graph.addNode("a")

Result:

Node count remains 1 because duplicate nodes are ignored.

Unit tests verify node insertion and duplicate prevention.

---

## Feature 3 – Add Edge Operations

API:

addEdge(String srcLabel, String dstLabel)

This method creates a directed edge between two nodes.

Behavior:

- Automatically creates nodes if they do not exist
- Prevents duplicate edges
- Stores edges in an adjacency map

Example:

graph.addEdge("a","b")  
graph.addEdge("a","b")

Result:

Edge count remains 1 because duplicate edges are ignored.

Unit tests verify that edges are correctly added and duplicates are prevented.

---

## Feature 4 – Output Graph Representations

The graph can be exported in multiple formats.

### Text Output

API:

outputGraph(String filepath)

This writes the graph’s textual representation to a file.

Example output:

Number of nodes: 8  
Nodes: [a,b,c,d,e,f,g,h]  
Number of edges: 9  
Edges:  
a -> b  

---

### DOT Output

API:

outputDOTGraph(String filepath)

This writes the graph in DOT format.

Example:

digraph {
a -> b;
b -> c;
}

---

### Graph Visualization

API:

outputGraphics(String filepath, String format)

This method generates graphical visualizations using Graphviz.

Process:

1. Generate a DOT file
2. Call Graphviz
3. Produce a PNG image

Example command executed internally:

dot -Tpng graph_output.dot -o graph_output.png

---

## Unit Testing

JUnit 5 tests were written to verify all features.

Tests include:

- testParseGraph
- testAddNode
- testAddNodes
- testAddEdge
- testOutputGraph
- testOutputDOTGraph
- testOutputGraphics

Run tests with:

mvn test

Result:

Tests run: 7  
Failures: 0  
Errors: 0  
BUILD SUCCESS

---

## Maven Build

The project uses Maven for build and dependency management.

Compile and run tests:

mvn clean test

Package the project:

mvn package

Successful builds produce:

target/CSE4642026-vnegi3-1.0-SNAPSHOT.jar

---

## Repository

GitHub Repository:

https://github.com/vamika27/CSE4642026-vnegi3

The repository contains:

- source code
- unit tests
- Maven configuration
- example graph input files
- generated graph outputs

Commits correspond to the implemented features:

Feature 1 – DOT parsing  
Feature 2 – unit tests  
Feature 3 – edge operations  
Feature 4 – graph output and visualization

---

## How to Run

Clone the repository:

git clone https://github.com/vamika27/CSE4642026-vnegi3

Navigate to the project directory:

cd CSE4642026-vnegi3

Run tests:

mvn clean test

Build project:

mvn package

Sample input file:

sample_inputs/input1.dot

Generated outputs:

sample_inputs/outputGraph.txt  
sample_inputs/output1.dot  
sample_inputs/graph_output.png  

---

## Conclusion

This project implements a directed graph processing system capable of parsing DOT graphs, performing node and edge operations, exporting graphs to multiple formats, and generating visualizations using Graphviz.

All features are verified through automated unit tests and the project builds successfully using Maven.

