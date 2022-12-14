import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class gameMain  extends Thread{
	public static boolean check =false;

	public static void main(String[] args) throws IOException {
		
		
		int choice = 0;	// while문 반복 Trigger
		
		// 버퍼 할당
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		bw.write("□□■■■■■□□■■□□□□■■□□□□■■□□□■■■■■□□■■□\n");
		bw.write("□■■□□□■■□■■■□□□■■□□□□■■□□■■■□■■■□■■□\n");
		bw.write("□■■■■■■□□■■□□□■■■■□□□■■■□□■■■■■□□■■■\n");
		bw.write("□□■■■■■□□■■□□■■■□■■□□■■□□□□■■□□□□■■□\n");
		bw.write("□□□□□□□□□□□□■■■□□■■■□■■□■■■■■■■■■■■□\n");
		bw.write("□□■■■■■■■■■□□□□□□□□□□■■□□□□□□□□□□■■□\n");
		bw.write("□□■■□□□□□■■□□□■■□□□□□■■□□□□■■■■■■■□□\n");
		bw.write("□□■■□□□□□■■□□□■■□□□□□□□□□□■■■■□■■■■□\n");
		bw.write("□□■■■■■■■■■□□□■■■■■■■■■□□□□■■■■■■■□□\n");
		bw.flush();	// 버퍼를 비우면서 쌓여있던 내용 출력
					
		Scanner scan = new Scanner(System.in);
		
		
		while(choice == 0) {
			
			showMenu();		// 메뉴 
			choice = Integer.parseInt(br.readLine());
			
			switch (choice) {
			
				case 1:
					// 게임 실행 메서드
					Correct correct = new Correct();
					
					correct.setCount(0);
					correct.setScore(0);
					correct.setSolution(true);
					Sign sign = new Sign();
					int answer = 0;
					Random rand = new Random();
					char[] mark = { '+', '-', '÷', '*' };
					Scanner sc = new Scanner(System.in);
					Timer tm = new Timer();
					tm.setDaemon(true);
					tm.start();
					sign.start(); // 안내문
					
					
					while (correct.isSolution()) { //오답이면 트루를 반환하고 끝냄
						
						
						
						int a = (int) (Math.random() * 9 + 1); //랜덤값
						int b = (int) (Math.random() * 9 + 1);//랜덤값 지정
						char randomMark = mark[rand.nextInt(4)];
						switch (randomMark) {
						case '*':
							System.out.println(a + "*" + b + "=");
							answer = sc.nextInt();
							if (answer == a * b) {
								correct.true1(); // Correct 클래스로 넘어가서 정답을 리턴
								break;
							} else {
								correct.false1();// Correct 클래스로 넘어가서 틀림을 리턴
								break;
							}
						case '+':
							System.out.println(a + "+" + b + "=");
							answer = sc.nextInt();
							if (answer == a + b) {
								correct.true1();
								break;
							} else {
								correct.false1();
								break;
							}
						case '-':
							System.out.println(a + "-" + b + "=");
							answer = sc.nextInt();
							if (answer == a - b) {
								correct.true1();
								break;
							} else {
								correct.false1();
								break;
							}
						case '/':
							if (a % b == 0) {
								System.out.println(a + "÷" + b + "=");
								answer = sc.nextInt();
								if (answer == a / b) {
									correct.true1();
									break;
								} else {
									correct.false1();
									break;
								}
							} else {
								break;
							}

						}
						if (correct.getCount()==5) {
							System.out.println("☆2단계☆");
							while (correct.isSolution() && correct.getCount()<10) {
								int c = (int) (Math.random() * 9 + 11);
								int d = (int) (Math.random() * 9 + 11);
								char randomMark2 = mark[rand.nextInt(4)];
								switch (randomMark2) {
								case '*':
									System.out.println(c + "*" + d + "=");
									answer = sc.nextInt();
									if (answer == c * d) {
										correct.true2();
										break;
									} else {
										correct.false2();
										break;
									}
								case '+':
									System.out.println(c + "+" + d + "=");
									answer = sc.nextInt();
									if (answer == c + d) {
										correct.true2();
										break;
									} else {
										correct.false2();
										break;
									}
								case '-':
									System.out.println(c + "-" + d + "=");
									answer = sc.nextInt();
									if (answer == c - d) {
										correct.true2();
										break;
									} else {
										correct.false2();
										break;
									}

								case '/':
									if (c % d == 0) {
										System.out.println(c + "÷" + d + "=");
										answer = sc.nextInt();
										if (answer == c / d) {
											correct.true2();
											break;
										} else {
											correct.false2();
											break;
										}
									} else {
										break;
									}
									
								}
							}
						} 
						
						if (correct.getCount()==10) {
							System.out.println("☆3단계"+ "☆");
							while (correct.isSolution()) {
								int c = (int) (Math.random() * 9 + 11);
								int d = (int) (Math.random() * 9 + 11);
								int e = (int) (Math.random() * 9 + 11);
								char randomMark2 = mark[rand.nextInt(4)];
								switch (randomMark2) {
								case '*':
									System.out.println(c + "*" + d +"+"+ e +"=");
									answer = sc.nextInt();
									if (answer == c * d + e) {
										correct.true2();
										break;
									} else {
										correct.false2();
										break;
									}
								case '+':
									System.out.println(c + "*" + "("+d+ "+"+ e+")"+"=");						
									answer = sc.nextInt();
									if (answer == c * (d+e)) {
										correct.true2();
										break;
									} else {
										correct.false2();
										break;
									}
								case '-':
									System.out.println("("+c +"-"+ d+")"+"*"+e+ "=");
									answer = sc.nextInt();
									if (answer == (c - d)*e) {
										correct.true2();
										break;
									} else {
										correct.false2();
										break;
									}

								case '/':
									if (c % d == 0) {
										System.out.println(c + "÷" + d +"*"+e+ "=");
										answer = sc.nextInt();
										if (answer == c / d *e) {
											correct.true2();
											break;
										} else {
											correct.false2();
											break;
										}
									} else {
										break;
										
									}
									
								}
							}
						} 
					

					}
					System.out.println("총점수:" + correct.getScore());
				
				
				
				
					choice = 0;
					break;
				
				case 2:
					// 랭크보드 메서드
					Board b = new Board();	// Board형 b를 생성한다. (클래스 Board 의 생성자)
					
					bw.write("===== 명예의 전당 =====\n");
					bw.write("순위\t점수\t이름\n");
					bw.write("=======================\n");
					bw.flush();
					b.selectRanker();	// 랭커들의 정보 보여줌
				
					choice = 0;
					break;
				
				case 3 :
					// 도움말 - Help
					bw.write("     제한 시간 내에 주어진 암산 문제를 해결하세요\n");
					bw.write(" 5문제의 정답을 맞힐경우 난이도가 다음 단계로 조정됩니다\n");
					bw.write("높은 난이도의 문제를 해결하여 랭크보드에 이름을 올려보세요!\n");
					bw.write("      아무 키나 입력하여 메뉴 화면으로 이동합니다\n");
					bw.flush();
					
					String input = br.readLine();
				
					if(input != null) {
						
						choice = 0;
					}
				
					break;
				
				default:
				
					choice = 0;
				
			}
			
		}
		
		bw.close();	
	}// end of main
	// 메뉴
	public static void showMenu() throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		bw.write("\n");
		bw.write("┌────────┐   ┌────────┐   ┌────────┐\n");
		bw.write("│1 게임시작│   │2 랭크보드│   │3 게임방법│\n");
		bw.write("└────────┘   └────────┘   └────────┘\n");
		bw.write("\n");
		bw.flush();
		
	}
}

class Board {
		// 이클립스와 DB 연동
		private Connection conn = null;
		// DBMS로 접속할 DB명 / url 양식 -> jdbc:mysql://(사용자명):(포트번호)/(사용할 데이터베이스)
		private String url = "jdbc:mysql://localhost:3306/team1";	
		private String user = "root";	// mysql 사용자	-> 본인이 돌려보고 싶으면 본인꺼 입력!!
		private String password = "1234";	// mysql 비밀번호 -> 본인이 돌려보고 싶으면 본인꺼 입력!!
		private String sql = "";	// 입력한 sql 문 -> 본인이 돌려보고 싶으면 본인꺼 입력!!
		
		
		// 생성자
		public Board() {
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, password);
				System.out.println("데이터 불러오는 중...");
				System.out.println();
				
			} catch (Exception e) {
				System.out.println("데이터 불러오기에 실패하였습니다.");
				System.out.println();
				
				try {
					conn.close();
				} catch (SQLException e1) {
					
				}
			}
		}
		
		// insertBoard() 설정
		
//		create table users (
//				user_name varchar(50) not null,
//				user_no int auto_increment primary key,
//			    score int not null
//			);
//		users 테이블은 다음과 같이 만들어져 있으니 참고!
		
		public void insertBoard(String u_name, String score) {
			
			sql = "insert into users values(?, ?, ?)";	// users 테이블에 값이 (?, ?, ?) 데이터를 삽입
			
			PreparedStatement pstmt = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, u_name);	// 데이터값 설정
				pstmt.setString(2,  null);
				pstmt.setString(3, score);
				
				int result = pstmt.executeUpdate();	// 데이터가 저장되면 result = 1 이 됨.
				
				if(result == 1) {	// 저장 성공 시 출력문구
					System.out.println("유저의 정보가 저장되었습니다.");
				}
				
			} catch (Exception e) {	// 저장 실패시 출력문구
				System.out.println("저장에 실패하였습니다.");
			} finally {
				try {
					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}
				} catch (Exception e2) {
					
				}
			}
		}
		// user_name 이라는 이름을 가진 유저의 정보 출력 
		public void selectUser(String user_name) {
			
			String sql = "select * from users where user_name = ?";	// users 테이블에서 user_name 이 ? 인 데이터의 모든 Attribute를 출력
			PreparedStatement pstmt = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_name);
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()) {
					System.out.println("유저 이름: " + rs.getString("user_name"));
					System.out.println("점수: " + rs.getInt("score"));
				}
			} catch (Exception e) {
				System.out.println("예외가 발생하였습니다.");
			} finally {
				try {
					if (pstmt!=null && pstmt.isClosed()) {
						pstmt.close();
					}
				} catch (Exception e2) {
					
				}
			}
		}
		// 상위 10명을 출력
		public void selectRanker() {
			// sql = users 테이블에서 데이터들을 score 를 기준으로 내림차순 정렬하되, 10개의 데이터만 출력
			String sql = "select * from users order by score desc limit 10";
			PreparedStatement pstmt = null;
			int rank = 1;
			
			try {
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					System.out.println(rank++ + "\t" + rs.getInt("score") + "\t" + rs.getString("user_name"));
//					System.out.println("유저 이름: " + rs.getString("user_name"));
//					System.out.println("점수: " + rs.getInt("score"));
				}
			} catch (Exception e) {
				System.out.println("예외가 발생하였습니다.");
			} finally {
				try {
					if (pstmt!=null && pstmt.isClosed()) {
						pstmt.close();
					}
				} catch (Exception e2) {
					
				}
			}
			
		}	
}


// 표지
class Sign{
	public void start() {
	System.out.println("==시간제한은 난이도별==");
	System.out.println("======60초입니다=======");
	System.out.println("========암산왕=========");
	System.out.println("==     게임시작     ===");
	}
}
// 정답과 오답을 판별하고 점수를 저장
class Correct {
	private static boolean solution = true;
	private static int count = 0;
	private static int score = 0;
	
	
	public void true1() {
		System.out.println("정답입니다.");
		count++;
		score += 100;
	}

	public void false1() {
		System.out.println("오답입니다.");
		solution = false;
	}

	public void true2() {
		System.out.println("정답입니다.");
		count++;
		score += 200;
	}

	public void false2() {
		System.out.println("오답입니다.");
		solution = false;
	}
	public void true3() {
		System.out.println("정답입니다.");
		count++;
		score += 300;
	}

	public void false3() {
		System.out.println("오답입니다.");
		solution = false;
	}

	public boolean isSolution() {
		return solution;
	}

	public void setSolution(boolean solution) {
		this.solution = solution;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}

