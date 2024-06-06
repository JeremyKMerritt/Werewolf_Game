package ver10_00;

public class GamePhase {

	public static int vote(Character[] players, int playerCount, int wolfCount, boolean debug) {

		//初期設定
		int victims = 0;
		int purge = 0;
		if (debug == true) {
			System.out.println("初期の市民の数は" + playerCount + "です。");
			System.out.println("初期の人狼の数は" + wolfCount + "です。");
		}

		//ゲーム終了条件
		for (int rounds = 1; (playerCount - victims) > (wolfCount - purge) && (wolfCount - purge) > 0; rounds++) {

			//朝の処理フェイズ
			System.out.println(rounds + "日目の朝が来ました。");
			for (Character player : players) {
				if (player.getHasBeenKilled() == true) {
					System.out.println(player.getName() + "さんは昨夜亡くなりました。");
					player.setHasBeenKilled(false);
				}
			}

			//霊媒師フェイズ
			for (Character player : players) {
				if (player.getHasBeenExecuted() == true) {
					for (Character Shaman : players) {
						if (Shaman.getIsShaman() == true && Shaman.getIsAlive() == true) {
							if(Shaman.getIsLunatic() == true || Shaman.getIsWolf() == true) {
								int a = new java.util.Random().nextInt(2);
								String fake = switch(a) {
								case 0 -> "市民";
								case 1 -> "人狼";
								default -> "市民";
								};
								System.out.println(Shaman.getName() + "「先日追放された" + player.getName() + "さんは"
								+ fake + "でした。」");
							} else {
						System.out.println(Shaman.getName() + "「先日追放された" + player.getName() + "さんは"
								+ player.getFaction() + "でした。」");
							}
						}
					}
					player.setHasBeenExecuted(false);
				}
			}

			//占い師の報告フェイズ
			for (Character player : players) {
				if (player.getHasBeenWatched() == true) {
					for (Character Seer : players) {
						if (Seer.getIsSeer() == true && Seer.getIsAlive() == true && Seer.getIsWolf() == false && Seer.getIsLunatic() == false) {
							System.out.println(Seer.getName() + "「先日占った結果、" + player.getName() + "さんは"
									+ player.getFaction() + "でした。」");
						}
					}
					player.setHasBeenWatched(false);
				}
				if (player.getFakeWatched() == true) {
					for(Character Lunatic : players) {
						if(Lunatic.getIsLunatic() == true && Lunatic.getIsAlive() == true) {
							int a = new java.util.Random().nextInt(2);
							String fake = switch(a) {
							case 0 -> "市民陣営";
							case 1 -> "人狼陣営";
							default -> "市民陣営";
							};
							
							//デバッグ用
							if(debug == true) {
								System.out.println(Lunatic.getName() + "「狂人の偽占いの結果、" + player.getName() + "さんは" + fake + "でした」");
								player.setFakeWatch(false);
							} else {
							System.out.println(Lunatic.getName() + "「先日占った結果、" + player.getName() + "さんは" + fake + "でした」");
							player.setFakeWatch(false);
							}
						}
					}
				}
				if (player.getWolfWatched() == true) {
					for(Character wolf : players) {
						if(wolf.getIsWolf() == true && wolf.getIsAlive() == true && wolf.getIsSeer() == true) {
							int a = new java.util.Random().nextInt(2);
							String fake = switch(a) {
							case 0 -> "市民陣営";
							case 1 -> "人狼陣営";
							default -> "市民陣営";
							};
							
							//デバッグ用
							if(debug == true) {
								System.out.println(wolf.getName() + "「人狼の偽占いの結果、" + player.getName() + "さんは" + fake + "でした」");
								player.setFakeWatch(false);
							} else {
							System.out.println(wolf.getName() + "「先日占った結果、" + player.getName() + "さんは" + fake + "でした」");
							player.setFakeWatch(false);
							}
						}
					}
				}
			}

			//投票フェイズ
			for (Character player : players) {
				if (player.getIsAlive() == true) {
					System.out.println(player.getNumber() + "番の" + player.getName());
				}
			}
			System.out.println("今日追放する村人の番号を入力してください");
			for (boolean votecheck = false; votecheck == false;) {
				System.out.print(">");
				int vote = new java.util.Scanner(System.in).nextInt() - 1;
				if (players[vote].getIsAlive() == true) {
					System.out.println(rounds + "日目は" + players[vote].getNumber() + "番の" + players[vote].getName()
							+ "さんが追放されました。");
					players[vote].setIsAlive(false);
					players[vote].setHasBeenExecuted(true);
					victims++;
					votecheck = true;
					if (players[vote].getIsWolf() == true) {
						if (debug == true) {
							System.out.println("人狼が追放されました。");
						}
						purge++;
					}
					if (players[vote].getIsFox() == true) {
						if (debug == true) {
							System.out.println("妖狐が追放されました。");
						}
						;
					}
				} else {
					System.out.println(players[vote].getNumber() + "番目のプレイヤーは追放の対象にはできません。\n改めて今日の対象を選んでください。");
				}
			}
			
			//追放した段階でゲーム終了条件を満たしたか確認
			if((playerCount - victims) > (wolfCount - purge) && (wolfCount - purge) > 0) {

			//騎士の防御フェイズ
			int protect = 0;
			for (boolean checked = false; checked == false ; ) {
				System.out.println("今晩、人狼の攻撃から防衛する対象を選んでください。");
				System.out.print(">");
				int protection = new java.util.Scanner(System.in).nextInt() - 1;
				if(players[protection].getTheLastGuarded() == true) {
					System.out.println("昨日防衛した対象を連続で守ることはできません。");
				} else {
					for(Character player : players) {
						player.setTheLastGuarded(false);
					}
					players[protection].setIsProtected(true);
					players[protection].setTheLastGuarded(true);
					protect = protection;
					checked = true;
				}
			}

			//占い師の占いフェイズ
			for (Character seer : players) {
				if (seer.getIsSeer() == true && seer.getIsAlive() == true) {
					if (seer.getIsLunatic() == true) {
						for (boolean checked = false; checked == false;) {
							int seered = new java.util.Random().nextInt(playerCount);
							if (players[seered].getIsAlive() == true && players[seered].getName() != seer.getName()) {
								players[seered].setFakeWatch(true);
								checked = true;
							}
						}
					} else if (seer.getIsWolf() == true) {
						for (boolean checked = false; checked == false;) {
							int seered = new java.util.Random().nextInt(playerCount);
							if (players[seered].getIsAlive() == true && players[seered].getName() != seer.getName()) {
								players[seered].setWolfWatch(true);
								checked = true;
							}
						}
					} else {
						for (boolean checked = false; checked == false;) {
							int seered = new java.util.Random().nextInt(playerCount);
							if (players[seered].getIsAlive() == true) {
								players[seered].setHasBeenWatched(true);
								if (players[seered].getIsFox() == true) {
									players[seered].setIsAlive(false);
									players[seered].setHasBeenKilled(true);
								}
								checked = true;
							} else {
								if (debug == true) {
									System.out.println(players[seered].getName() + "は占いの対象にできません。");
								}
								;
							}
						}
					}
				}
			}
			


			//人狼の襲撃フェイズ
			for (boolean assualt = false; assualt == false;) {
				int maul = new java.util.Random().nextInt(playerCount);
				if (players[maul].getIsAlive() == false || players[maul].getIsWolf() == true) {
					if (debug == true) {
						System.out.println(players[maul].getName() + "は襲撃の対象にできません。");
					}
					;
				} else if (players[maul].getIsFox() == true || players[maul].getIsProtected() == true) {
					if (debug == true) {
						System.out.println("人狼は" + players[maul].getName() + "を襲撃しましたが、失敗しました。");
					}
					assualt = true;
				} else {
					players[maul].setIsAlive(false);
					players[maul].setHasBeenKilled(true);
					victims++;
					assualt = true;
				}
			}
			players[protect].setIsProtected(false);
			}
		}
		return purge;
	}

	public static void finale(Character[] players, boolean debug) {
		System.out.println("ゲーム終了");
		boolean win = false;
		for (Character player : players) {
			System.out.println(player.getName() + "さんの役職は" + player.getJob() + "でした。");
			if (player.getIsFox() == true && player.getIsAlive() == true) {
				System.out.println("妖狐の勝ちです。");
				win = true;
			} else if (player.getIsWolf() == true && player.getIsAlive() == true) {
				System.out.println("人狼陣営の勝ちです。");
				win = true;
			} else {
				;
			}
		}
		if (win == false) {
			System.out.println("市民陣営の勝ちです。");
			win = true;
		}
	}
}
