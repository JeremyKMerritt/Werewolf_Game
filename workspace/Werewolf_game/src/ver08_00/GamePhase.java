package ver08_00;

import java.util.ArrayList;

public class GamePhase {
	
	public Character[] vote(int wolfCount, int playerCount, Character[] players, int foxCount) {
		//投票
		int victims = 0; //犠牲者の数
		int purged = 0; //吊られた狼の数
		int wolfCO = 0; //人狼が妖狐を噛んだ時のデータ保存用
		for (; (wolfCount - purged) > 0 && (playerCount - victims) > (wolfCount - purged);) {
			System.out.println("残りの市民の数は" + (playerCount - victims) + "です。");
			System.out.println("残りの人狼の数は" + (wolfCount - purged) + "です。");
			System.out.println("残りの妖狐の数は" + foxCount + "です。");
			System.out.println("");
			for (int i = 0; i < playerCount; i++) {
				if (players[i].getisAlive() == true) {
					System.out.println(players[i].getNumber() + "番、" + players[i].getName());
				}
			}
			
			for(boolean i = false; i == false;) {
				System.out.println("");
				System.out.println("投票先の番号を選んでください。");
				System.out.print(">");
				int vote = new java.util.Scanner(System.in).nextInt() - 1;
				System.out.println("");
				if(players[vote].getisAlive() == false) {
					System.out.println("既に死亡している方へ投票することはできません。");
					continue;
				} else {
					//投票先のプレイヤーは死亡
					players[vote].setisAlive(false);
					i = true;
					
					//デバッグ用：結果報告
					System.out.println("昨夜吊られたのは、" + players[vote].getName() + "です。");
					if (players[vote].getisWolf() == true) {
						purged++;
						victims++;
						System.out.println("人狼が吊れました");
						System.out.println("");
					} else if(players[vote].getisFox() == true){
						System.out.println("妖狐が吊れました");
						foxCount--;
						victims++;
					} else {
						System.out.println("市民が吊れました");
						victims++;
					}
					players[vote].setisLastDead(true); //投票された人を最近吊られた人として一時的に登録
				}
				
			}
		
			//試合は継続するかどうかのチェック
			if ((wolfCount - purged) > 0 && (playerCount - victims) > (wolfCount - purged)) {
				//騎士（プレイヤーが任意の防衛対象を設定する)フェイズ
				for (int i = 0; i < playerCount; i++) {
					if (players[i].getisAlive() == true) {
						System.out.println(players[i].getNumber() + "番、" + players[i].getName());
					}
				}
				System.out.println("");
				System.out.println("今晩の襲撃から守りたいプレイヤーの番号を選んでください。");
				
				int protection = 0;
				for(boolean check = false; check == false ;) {
				System.out.print(">");
				protection = new java.util.Scanner(System.in).nextInt() - 1;
				System.out.println("");
				
				//連続ガード確認
					if(players[protection].getwasProtected() == true) {
						System.out.println("同じ対象を連続で守ることはできません。");
						continue;
					} else {
						check = true;
					}
				}
				
				//投票先のプレイヤーは防衛
				System.out.println(players[protection].getName() + "さんを今晩の防衛対象とします。");
				for(int y = 0 ; y < playerCount ; y++) {
					players[y].setwasProtected(false);
				}
				players[protection].setisProtected(true);
				
				//オオカミに襲われるフェイズ
				
				boolean mauled = false; //襲撃判定の初期設定
				for(;mauled == false;) { //人狼の襲撃が成功するまでの間
					//生存者および非人狼プレイヤーを集めた襲撃リストを作成
					int target = new java.util.Random().nextInt(playerCount);
					if (players[target].getisAlive() == true && players[target].getisWolf() == false && players[target].getisSeer() == false) {
						if (players[target].getisProtected() == true || players[target].getisFox() == true) {
							System.out.println("昨夜の襲撃は発生しませんでした。");
							if(players[target].getisFox()) {
								wolfCO = target;
							}
							mauled = true;
							break;
						} else {
						System.out.println("襲撃の対象になったのは、" + (target + 1) + "番です。");
						players[target].setisAlive(false);
						victims++;
						mauled = true;
						}
					} else if((playerCount - victims) <= 3){
						if (players[target].getisAlive() == true && players[target].getisWolf() == false && players[target].getisProtected() == false || players[target].getisFox() == false) {
							System.out.println("襲撃の対象になったのは、" + (target + 1) + "番です。");
							players[target].setisAlive(false);
							victims++;
							mauled = true;
						}
					}else {
						System.out.println((target + 1) + "番の方は襲撃の対象にできません。");
						continue;
					}
				}
				players[protection].setisProtected(false);
				players[protection].setwasProtected(true);
			}
			
			//霊媒師の結果フェイズ
			if ((wolfCount - purged) > 0 && (playerCount - victims) > (wolfCount - purged)) {
				for(int i = 0; i < playerCount ; i++) { 
					if(players[i].getisPsychic() == true && players[i].getisAlive() == true) { //まず霊媒師であってなおかつ生きている人を探す
						if(players[i].getisWolf() == true || players[i].getisLunatic() == true ) { //この霊媒師が人狼であるかないかを照合。
									players[i].fakePsychic(players, playerCount); //デタラメな霊媒結果を言い渡す
						}else {
							for(int y = 0; y < playerCount ; y++) { //LastDead直近でなくなったプレイヤーを検索
								if(players[y].getisLastDead() == true) {
									System.out.println(players[i].getName() + "「霊媒結果は、" + players[y].getName() + "さんが"+ players[y].getFaction() + "でした。」");
								}
							}
						}	
					}
				}
			//LastDeadから排除
			for(int y = 0; y < playerCount ; y++) { //LastDead直近でなくなったプレイヤーを検索
				if(players[y].getisLastDead() == true) {
					players[y].setisLastDead(false); //用済みになったのでLastDeadリストから排除
				}
			}
		}
			
			//占い師の占いフェイズ
			ArrayList<String> word = new ArrayList<String>();
			if ((wolfCount - purged) > 0 && (playerCount - victims) > (wolfCount - purged)) {
				for(int i = 0; i < playerCount ; i++) { 
					if(players[i].getisSeer() == true && players[i].getisAlive() == true) { //まず占い師であってなおかつ生きている人を探す
						if(players[i].getisWolf() == true || players[i].getisLunatic() == true ) { //この占い師が人狼であるかないかを照合。
							boolean checked = false; //偽占い判定の初期設定
							for(;checked == false;) { //偽占い師の占いが成功するまでの間
								//生存者および非占い済みプレイヤーに焦点を当てた偽占い対象を選ぶ
								int target = new java.util.Random().nextInt(playerCount);
								if (players[target].getisAlive() == true && players[i].gethasChecked(target) == false && players[target] != players[i]) {
									players[i].fakeSeer(players, target, playerCount, word); //デタラメな占い結果を言い渡す
									players[i].sethasChecked(target);
									checked = true;
								} else if((playerCount - victims) <= 4){					
									if (players[target].getisAlive() == true && players[target] != players[i]) {
										word.add(players[i].getName() + "「占い結果は、" + players[target].getName() + "さんが人狼でした。」"); //デタラメな占い結果を言い渡す
										checked = true;
									}	
								}else {
									System.out.println(players[target].getNumber() + "番の方は偽占いの対象にできません。");
									continue;
								}
							}
						}else {
							boolean checked = false; //占い判定の初期設定
							for(;checked == false;) { //占い師の占いが成功するまでの間
								//生存者および非占い済みプレイヤーに焦点を当てた占い対象を選ぶ
								int target = new java.util.Random().nextInt(playerCount);
								if (players[target].getisAlive() == true && players[i].gethasChecked(target) == false && players[target] != players[i]) {
									word.add(players[i].getName() + "「占い結果は、" + players[target].getName() + "さんが" + players[target].getFaction() + "でした。」");
									if(players[target].getisFox() == true ) {
										players[target].setisAlive(false);
										System.out.println("襲撃の対象になったのは、" + (target + 1) + "番です。");
										foxCount--;
									}
									players[i].sethasChecked(target);
									checked = true;
								} else if((playerCount - victims) <= 4){					
									if (players[target].getisAlive() == true && players[target] != players[i]) {
										word.add(players[i].getName() + "「占い結果は、" + players[target].getName() + "さんが" + players[target].getFaction() + "でした。」");
										checked = true;
									}
								}else {
									System.out.println(players[target].getNumber() + "番の方は占いの対象にできません。");
									continue;
								}
							}	
						}
					}
				}	
			}
			//占い結果をまとめて報告
			for(String f : word) {
				System.out.println(f);
			}
			//人狼が妖狐を報告するか否か
			if((wolfCount - purged) >= 2 && (playerCount - victims) <= 5 && wolfCO != 0 && players[wolfCO].getisAlive() == true ) {
				for (int i = 0 ; i < playerCount ; i++ ) {
					if(players[i].getisWolf() == true && players[i].getisAlive() == true) {
						System.out.println(players[i].getName() + "「人狼CO," + players[wolfCO].getName() + "さんが妖狐です。」");
						break;
					} else {
						;
					}
				}
			}
		}
		return players;
	}
	
	public void end(int playerCount, Character[] result) {
		//終了フェイズ
				int fox = 0;
				int wolf = 0;
				System.out.println("ゲーム終了");
				for(int i = 0; i < playerCount ; i++) {
					if(
						result[i].getisFox() == true && result[i].getisAlive() == true){
						fox++;
					} else if(
						result[i].getisWolf() == true && result[i].getisAlive() == true) {
						wolf++;
					} else {
						;
					}
					System.out.println(result[i].getName() + "さんの役職は"+ result[i].getJob() + "でした。");
				}	
				if(fox >= 1) {
					System.out.println("妖狐陣営の勝ちです。");
				} else if (wolf >= 1) {
					System.out.println("人狼陣営の勝ちです。");
				} else {
					System.out.println("市民陣営の勝ちです。");
				}
	}	
}
