import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


/**
 * 
 * 아기상어
 * 13360 KB
 * 92 ms
 *
 * 1. 입력부 
 *  - 맵 생성
 *  - 아기상어 위치 저장
 * 2. 아기상어 사냥 시뮬레이션 (BFS) 
 *  - 아기상어 위치 기준으로 맵 탐색하면서 먹을 수 있는 물고기 찾기 ( 찾으면 배열에 저장 )
 *  - 먹을 수 있는 물고기 배열에서 최적값 찾기 (가장 위에 또는 왼쪽)
 * 3. 사냥 후 결과 처리
 *  - 아무것도 못먹었으면 종료
 *  - 먹었으면 상어 레벨업, 시간 저장
 */
public class Beakjoon_16236 {

	static int N;
	static int sharkR, sharkC;
	static int[][] map;
	static boolean[][] isVisited;
	static int sharkSize = 2;
	static int fishEaten = 0;
	static Queue<int[]> q = new ArrayDeque<>();
	static int[][] eatableFish = new int[40][2]; // 먹을 수 있는 물고기 저장 배열 (가장 많아봤자 20 x 20 에서 가장 위 또는 왼쪽에 있는 줄 40마리 (실제로는 40마리도 안됨)일것임)

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // (2 ≤ N ≤ 20)
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, index = 0; j < N; j++, index += 2) {
				map[i][j] = str.charAt(index) - '0';
				if (map[i][j] == 9) { // 상어 위치 입력받음
					sharkR = i;
					sharkC = j;
					map[i][j] = 0;
				}
			}
		} // --------------- 입력부 --------------------------
		
		isVisited = new boolean[N][N];
		int T = 0; // 최종 시간
		while (true) {
			int tempT = huntBFS(); // 상어 사냥 시작
			if (tempT == -1) { // 먹을게 없는 경우 
				break;
			} else { // 물고기를 먹은 경우 
				if (++fishEaten == sharkSize) { // 먹은 수와 상어의 사이즈가 같아지는 순간
					sharkSize++; // 사이즈 업
					fishEaten = 0; // 먹은 수 초기화
				}
				T += tempT;
			}
		}
		System.out.println(T);

	} // end of main
	
	static final int[] dr = {-1, 0, 0, 1}; // 하, 좌, 우, 상
	static final int[] dc = {0, -1, 1, 0};
	static int huntBFS() { // 아기 상어가 한 마리 먹는 동안의 BFS
		// 자원 초기화
		initVisited();
		q.clear();
		// 자원 초기화 끝

		// 초기 큐 상태
		q.offer(new int[] {sharkR, sharkC});
		isVisited[sharkR][sharkC] = true;
		int qSize, fishCount, time = 0;
		// BFS 시작
		while (!q.isEmpty()) {
			qSize = q.size();
			fishCount = 0;
			for (int i = 0; i < qSize; i++) { // 계층적으로 한 타임만큼 돌기
				int[] shark = q.poll();
				for (int d = 0; d < dr.length; d++) {
					int nr = shark[0] + dr[d];
					int nc = shark[1] + dc[d];
					if (0 <= nr && nr < N && 0 <= nc && nc < N && !isVisited[nr][nc] && map[nr][nc] <= sharkSize) { // 지나갈 수 있는 곳이면 확인
						isVisited[nr][nc] = true; // 방문처리
						if (0 < map[nr][nc] && map[nr][nc] < sharkSize) { // 맵에 물고기가 있고, 먹을 수 있으면 냠냠
							eatableFish[fishCount][0] = nr;
							eatableFish[fishCount][1] = nc;
							fishCount++;
						} else if (fishCount == 0) { // 먹을 수 있는 물고기가 없을때만 큐에 넣는게 유효하다. (못찾았을 때를 대비해서, 계속 큐에 넣어주기 (어차피 초기화됨)
							q.offer(new int[] {nr, nc});
						}
					}
				}
			}
			
			time++; // 현재 진행되고 있는 시간 (하나의 큐사이츠 = 1초)
			
			if (fishCount > 0) { // 만약 먹을 수 있는 물고기가 있다면
				int minR = Integer.MAX_VALUE;
				int minC = Integer.MAX_VALUE;
				for (int i = 0; i < fishCount; i++) { // 먹을 수 있는 물고기중에 가장 가까운거 찾기
					if (eatableFish[i][0] < minR) { // 우선 가장 위에있는 것 찾기
						minR = eatableFish[i][0];
						minC = eatableFish[i][1];
					} else if (eatableFish[i][0] == minR && eatableFish[i][1] < minC) { // 가장 위에 있는 것들중에서 왼쪽인거 먹기
						minC = eatableFish[i][1];
					}
				}
				map[minR][minC] = 0; // 물고기가 먹힌 자리는 0처리
				sharkR = minR; // 상어를 물고기 위치로 옮기기
				sharkC = minC;
				return time;
			}
		} 
		return -1;
	} // end of bfs

	static void initVisited() {
		int i, j;
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				isVisited[i][j] = false;
			}
		}
	}

} // end of class