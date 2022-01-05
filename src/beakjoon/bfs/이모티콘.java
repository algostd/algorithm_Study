import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static int S;
	private static int[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		visited = new int[1002][1002];
		
		
		//BFS
		Queue<Emo> qu = new LinkedList<Emo>();
		qu.add(new Emo(1, 0, 0));
		visited[1][0] = 1;
		
		while (!qu.isEmpty()) {
			Emo cur = qu.poll();
			if (cur.screen == S) {
				System.out.println(cur.time);
				return;
			}
		
			//1. 화면에 있는 이모티콘을 클립보드에 복사한다.
			qu.add(new Emo(cur.screen, cur.screen, cur.time + 1));
			
			//2. 클립보드에 있는 이모티콘을 화면에 붙여넣는다.
			if (cur.clip > 0 && cur.screen + cur.clip <= S) {
				
				if (visited[cur.screen + cur.clip][cur.clip] == 0) {
					qu.add(new Emo(cur.screen + cur.clip, cur.clip, cur.time + 1));
					visited[cur.screen + cur.clip][cur.clip] = 1;
				}
			}
			
			//3. 화면에 있는 이모티콘을 하나 삭제한다.
			if (cur.screen > 0 && visited[cur.screen - 1][cur.clip] == 0) {
				qu.add(new Emo(cur.screen - 1, cur.clip, cur.time + 1));
				visited[cur.screen - 1][cur.clip] = 1;
			}
		}
	}
}

class Emo {
	int screen;
	int clip;
	int time;
	
	Emo(int screen, int clip, int time) {
		this.screen = screen;
		this.clip = clip;
		this.time = time;
	}
}