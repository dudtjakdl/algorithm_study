package Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
/*
 * 문제 출처 : Softeer
 * 문제 링크 : https://softeer.ai/practice/info.do?eventIdx=1&psProblemId=804
 * 걸린 시간 : 100분
 */
public class SOFTEER_플레이페어_암호 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		String msg = sc.nextLine(); // 메세지
		String key = sc.nextLine(); // 키
		char[][] map = new char[5][5]; // 5×5크기의 표
		boolean[] isChecked = new boolean[26]; // 알파벳이 표에 들어갔는지 여부 체크
		int[][] alpha = new int[26][2]; // 각 알파벳이 위치한 행,열 위치값
		isChecked[9] = true; // J는 사용 안되므로 true 표시

        int idx = 0; // 키 문자열의 인덱스
        int row = 0; // 행
        int col = 0; // 열
        
        // 키 문자열의 문자들 표에 넣기
        while (idx < key.length()) {
            char ch = key.charAt(idx++); // 넣을 문자
            
            // 현재 문자가 아직 표에 들어가지 않았을 경우
            if (!isChecked[(int)ch-65]) {
                map[row][col] = ch; // 표에 넣기
                isChecked[(int)ch-65] = true; // 사용 체크
                
                alpha[(int)ch-65][0] = row; // 넣은 문자의 행 위치값 저장
                alpha[(int)ch-65][1] = col; // 넣은 문자의 열 위치값 저장
                
                // 다음 문자를 넣을 위치값 변경
                if (col == 4) {
                	row++;
                	col = 0;
                }
                else col++;
            }
        }
        
        // 남은 문자 순서대로 표에 넣기
        for (int i = 0; i < 26; i++) {
        	// 아직 표에 넣지 않은 문자일 경우
        	if (!isChecked[i]) {
        		map[row][col] = (char)(i + 65); // 문자 표에 넣기
       
        		alpha[i][0] = row; // 넣은 문자의 행 위치값 저장
        		alpha[i][1] = col; // 넣은 문자의 열 위치값 저장

        		// 다음 문자를 넣을 위치값 변경
                if (col == 4) {
                	row++;
                	col = 0;
                }
                else col++;
        	}
        }
        
        Stack<Character> stack = new Stack<>(); // 메세지 문자를 하나씩 넣을 스택
        
        // 메세지의 뒤부터 문자 하나씩 스택에 push
        for (int i = msg.length()-1; i >= 0; i--) {
        	stack.push(msg.charAt(i));
        }
        
        List<char[]> pairs = new ArrayList<>(); // 문자쌍 리스트
        
        // 스택이 빌때까지 반복
        while (!stack.isEmpty()) {
        	char top = stack.pop(); // 문자쌍의 첫번재 문자
        	char pair; // 문자쌍의 두번재 문자
        	
        	// 스택이 비었을 경우 -> 강제로 쌍 맞추기
        	if (stack.size() == 0) {
        		pair = 'X';
        	}
        	// 문자쌍의 문자가 서로 같을 경우
        	else if (stack.peek() == top) {
        		pair = (top == 'X') ? 'Q' : 'X';
        	}
        	// 문자쌍의 문자가 서로 다를 경우
        	else {
        		pair = stack.pop();
        	}
        	
        	pairs.add(new char[] {top, pair}); // 완성된 문자쌍 리스트에 추가
        }
        
        // 각 문자쌍의 문자들 변환하기
        for (int i = 0; i < pairs.size(); i++) {
        	int left = pairs.get(i)[0] - 65; // 문자쌍의 첫번재 문자
        	int right = pairs.get(i)[1] - 65; // 문자쌍의 두번재 문자
        	
        	// 같은 행에 존재할 경우
        	if (alpha[left][0] == alpha[right][0]) {
        		pairs.get(i)[0] = map[alpha[left][0]][(alpha[left][1]+1)%5];
        		pairs.get(i)[1] = map[alpha[right][0]][(alpha[right][1]+1)%5];
        	}
        	// 같은 열에 존재할 경우
        	else if (alpha[left][1] == alpha[right][1]) {
        		pairs.get(i)[0] = map[(alpha[left][0]+1)%5][alpha[left][1]];
        		pairs.get(i)[1] = map[(alpha[right][0]+1)%5][alpha[right][1]];
        	}
        	// 서로 다른 행, 열에 존재할 경우
        	else {
        		pairs.get(i)[0] = map[alpha[left][0]][alpha[right][1]];
        		pairs.get(i)[1] = map[alpha[right][0]][alpha[left][1]];
        	}
        }
        
        // 암호화된 결과 출력
        for (int i = 0; i < pairs.size(); i++) {
        	System.out.print(pairs.get(i)[0]);
        	System.out.print(pairs.get(i)[1]);
        }
        
	}

}
