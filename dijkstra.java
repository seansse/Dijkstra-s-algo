import java.util.PriorityQueue;
import java.util.ArrayList;

public static class Pair implements Comparable<Pair> {
      private int id;
      private int length;
      Pair(int id, int length) {
          this.id = id;
          this.length = length;
      }
      public int getLength() {
          return this.length;
      }
      public int getId() {
          return this.id;
      }
      @Override
      public int compareTo(Pair p) {
          return this.length - p.length;
      }
  }
  
public static Pair[] dijkstraList(ArrayList<ArrayList<Pair>> adjList, int playgrounds, int startNode) {
      Pair[] length = new Pair[playgrounds];
      PriorityQueue<Pair> pq = new PriorityQueue<>();
      for (int l = 0; l < playgrounds; l++) {
          if (l == startNode - 1) {
              length[l] = new Pair(l, 0);
              pq.offer(length[l]);
          } else {
              length[l] = new Pair(l, Integer.MAX_VALUE);
              pq.offer(length[l]);
          }
      }
      while (!pq.isEmpty()) {
          Pair vertice = pq.poll();
          int size = adjList.get(vertice.getId()).size();
          ArrayList<Pair> list = adjList.get(vertice.getId());
          for(int m = 0; m < size; m++) {
              int checkId = list.get(m).getId();
              int checkLength = length[vertice.getId()].getLength() + list.get(m).getLength();
              if (length[checkId].getLength() > checkLength) {
                  pq.remove(length[checkId]);
                  length[checkId] = new Pair(checkId, checkLength);
                  pq.offer(length[checkId]);
              }
          }
      }
      return length;
  }
