# CSE 464 Project Part 3 – Graph Search Refactoring

## Student Information
- Name: Vamika Negi
- ASU ID: 1226812251

## Repository Information
- Repository: https://github.com/vamika27/CSE4642026-vnegi3
- Branch: refactor
- Pull Request: https://github.com/vamika27/CSE4642026-vnegi3/pull/1

---

## Project Overview
This project refactors an existing graph search system using software design patterns.
The implementation improves modularity, extensibility, and maintainability by applying structured refactoring techniques and introducing Template, Strategy, and Factory patterns.

Additionally, a Random Walk search algorithm was implemented with dead-end handling and restart logic to ensure valid path discovery when possible.

---

## Build Instructions
mvn clean package

## Run Instructions
java -cp target/CSE4642026-vnegi3-1.0-SNAPSHOT.jar graph.Main

---

## Refactoring Commits

Refactor 1 – Format & Clean Project  
https://github.com/vamika27/CSE4642026-vnegi3/commit/ea1c14c

Refactor 2 – Extract Node Validation  
https://github.com/vamika27/CSE4642026-vnegi3/commit/8687b42

Refactor 3 – Extract Path Reconstruction  
https://github.com/vamika27/CSE4642026-vnegi3/commit/6843adc

Refactor 4 – Extract Neighbor Lookup  
https://github.com/vamika27/CSE4642026-vnegi3/commit/322d27c

Refactor 5 – Extract Frontier Selection  
https://github.com/vamika27/CSE4642026-vnegi3/commit/76743be

---

## Template Pattern

The Template Pattern is implemented through the GraphSearchTemplate class.
It defines a consistent structure for all graph traversal algorithms, including:

- Input validation
- Frontier creation and management
- Node expansion
- Path construction

This allows BFS, DFS, and other algorithms to share a common framework while customizing specific steps.

Commit:
https://github.com/vamika27/CSE4642026-vnegi3/commit/4b0d7ef

---

## Strategy Pattern

The Strategy Pattern enables dynamic selection of graph search algorithms at runtime.

Components:
- SearchStrategy interface
- BFSStrategy, DFSStrategy, RandomWalkStrategy implementations
- SearchStrategyFactory for algorithm selection

This design decouples algorithm selection from execution logic, improving flexibility and scalability.

Commit:
https://github.com/vamika27/CSE4642026-vnegi3/commit/76d87d3

---

## Factory Pattern

The Factory Pattern is implemented in SearchStrategyFactory.

- Encapsulates logic for selecting the appropriate search strategy
- Returns the correct strategy based on the chosen algorithm (BFS, DFS, Random Walk)
- Reduces coupling between the Graph class and algorithm implementations

---

## Random Walk Search

The Random Walk algorithm performs traversal by randomly selecting unvisited neighbors.

Features:
- Random neighbor selection
- Tracks visited nodes to avoid cycles
- Detects dead ends
- Restarts traversal when no unvisited neighbors remain

This ensures that the algorithm continues attempting until a valid path is found if one exists.

Commits:
- Initial implementation:
  https://github.com/vamika27/CSE4642026-vnegi3/commit/2dba610

- Dead-end restart fix:
  https://github.com/vamika27/CSE4642026-vnegi3/commit/b229947

---

## Example Execution

Using input file:
sample_inputs/input1.dot

Expected outputs:

BFS:
a -> e -> f -> h

DFS:
a -> e -> g -> h

Random Walk:
Performs multiple attempts and restarts when dead ends are encountered until a valid path is found.

---

## Pull Request Review

Pull Request:
https://github.com/vamika27/CSE4642026-vnegi3/pull/1

Includes:
- Refactoring commits with clear separation of concerns
- Template Pattern implementation
- Strategy Pattern implementation
- Factory Pattern integration
- Random Walk algorithm with restart logic
- Inline PR comments explaining design decisions

---

## Summary

This project demonstrates:
- Effective use of refactoring techniques
- Application of multiple design patterns
- Clean separation of concerns
- Improved maintainability and extensibility
- Robust handling of edge cases in graph traversal
