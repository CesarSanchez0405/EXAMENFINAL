/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DeadlockDetection1;

/**
 *
 * @author casm7
 */

import java.util.*;

public class DeadlockDetection1 {
    private final Map<String, List<String>> graph;
    private final Set<String> visited;
    private final Set<String> cycleNodes;

    public DeadlockDetection1() {
        graph = new HashMap<>();
        visited = new HashSet<>();
        cycleNodes = new HashSet<>();
    }

    public void addDependency(String from, String to) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.get(from).add(to);
    }

    public boolean hasCycle() {
        for (String node : graph.keySet()) {
            if (dfs(node)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(String node) {
        if (cycleNodes.contains(node)) {
            return true; // Retorna true cuando encuentra un ciclo
        }
        //Se agrego el if para corroborar que contenga el nodo, para evitar errores

        if (!visited.add(node)) {
            return false; // Retorna falso cuando ya visito ese nodo
        }

        cycleNodes.add(node);

        if (graph.containsKey(node)) {
            for (String neighbor : graph.get(node)) {
                if (dfs(neighbor)) {
                    return true; // Ciclo detectado
                }
            }
        }

        cycleNodes.remove(node);
        return false;
    }

    public static void main(String[] args) {
        DeadlockDetection1 detector = new DeadlockDetection1();

        // Agregar dependencias
        detector.addDependency("T1", "T2");
        detector.addDependency("T2", "T1");
        detector.addDependency("T3", "T2");

        // Detectar deadlock
        if (detector.hasCycle()) {
            System.out.println("Se detectó un deadlock");
        } else {
            System.out.println("No se detectó un deadlock");
        }
    }
}
