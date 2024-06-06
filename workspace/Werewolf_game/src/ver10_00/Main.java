package ver10_00;

//ver10_00
//ver10_00の目的は、ver08にて完成したゲームのクソコードを綺麗に書き直すこと。
//ver10からはオブジェクト指向を意識した設計を行い、それぞれの役職をもったプレイヤーが自分の役割にしたがって動くプログラム制作を目指す。
//ver08で作ったメソッドや変数は参考にしてもいいが、基本的には１から作り直すこととする。

//次回のver11ではプレイヤーは騎士と占い師の中から任意で役職を選べるようにして、
//選ばれなかった役職はNPCが負うようにしたい。（騎士の行動パターンはどうすべきだろうか。）


public class Main {

	public static void main(String[] args) {
		int playerCount;
		int wolfCount;
		
		//デバッグモード用変数
		boolean debug = false;
		System.out.println("デバッグモードを有効にしますか？");
		System.out.println("y/n");
		String debugMode = new java.util.Scanner(System.in).nextLine();
		if (debugMode.equals("y")){
			System.out.println("デバッグモードを有効にします。");
			debug = true; //デバッグモードを消したいときはこの列をCTRL+/でコメントアウトさせる。
		} else {
			System.out.println("デバッグモードを無効にします。");
			debug = false;
		}
		
		//タイトル
		System.out.println("=独りプレイ用人狼ゲーム=");
		System.out.println("Press Anykey to Start the Game.");
		String anykey = new java.util.Scanner(System.in).nextLine();
		
		//ルール
		System.out.println("-=ルール説明=-");
		System.out.println("NPCの中に人狼がまぎれており、\n毎晩一人ずつ村人をを襲います。\n貴方には毎朝NPCの中から1人選びその者を村から追放していかなければなりません。\n村人が全滅する前に人狼を全て排除し村の平和を取り戻しましょう。");
		System.out.println("人狼の数が無実な村人の数と同数かそれ以上になればあなたの負け、\nその前に人狼を全滅させればあなたの勝ちとなります。");
		System.out.println("Press Anykey to Start the Game");
		anykey = new java.util.Scanner(System.in).nextLine();
		
		//NPC数の設定
		System.out.println("登場するNPCの数を入力してください。");
		System.out.println("その内の4分の1が人狼(最低1人)、残りが市民となります。");
		System.out.println("また、4分の1の確率で妖狐がまぎれてしまいます。\n妖狐が最後まで生き延びると妖狐の勝ちとなりますので注意してください。\n※妖狐は人狼に攻撃ではやられませんが、占い師に占われると死亡してしまいます。");
		System.out.print("登場するNPCの数>");
		playerCount = new java.util.Scanner(System.in).nextInt();
		wolfCount = playerCount/4;
		if (wolfCount < 0) { wolfCount = 1;}
		Character[] players = new Character[playerCount];
		
		//Character生成
		for (int i = 0; i < playerCount ; i++) {
			players[i] = new Character(i);
			System.out.println(players[i].getNumber() + "番の" + players[i].getName() + "さんが生成されました。");
		}
		
		//人狼生成
		for (int wolves = 0 ;wolves < wolfCount ;) {
			int pick = new java.util.Random().nextInt(playerCount);
			if (players[pick].getJob() == null) {
				players[pick].wolf();
				//デバッグ用
				if(debug == true) {
				System.out.println(players[pick].getName() + "さんが" + players[pick].getJob() + "に選ばれました。");
				}
				wolves++;
			} else {
				if (debug == true) {//デバッグ用の表示
					System.out.println(players[pick].getName() + "は人狼にはなれません。");
				}
				;
			}
		}
		
		//妖狐生成
		int spawnFox = new java.util.Random().nextInt(100);
		if (spawnFox <= 24) {
			for (int theFox = 0; theFox<1 ;) {
				int pick = new java.util.Random().nextInt(playerCount);
				if(players[pick].getJob() == null) {
					players[pick].fox();
					//デバッグ用
					if(debug == true) {
					System.out.println(players[pick].getName() + "さんが" + players[pick].getJob() + "に選ばれました。");
					}
					theFox++;
				} else {
					if (debug == true) {//デバッグ用の表示
						System.out.println(players[pick].getName() + "は妖狐にはなれません。");
					}
					;
				}
			}
		} else {
			if (debug == true) {//デバッグ用の表示
				System.out.println("賽の目は" + spawnFox +"だったので妖狐が紛れ込むことはありませんでした。");
			}
			;
		}
		
		//占い師生成
		for (int theSeer = 0; theSeer<1 ;) {
			int pick = new java.util.Random().nextInt(playerCount);
			if(players[pick].getJob() == null) {
				players[pick].seer();
				//デバッグ用
				if(debug == true) {
				System.out.println(players[pick].getName() + "さんが" + players[pick].getJob() + "に選ばれました。");
				}
				theSeer++;
			} else {
				if (debug == true) {//デバッグ用の表示
					System.out.println(players[pick].getName() + "は占い師にはなれません。");
				}
				;
			}
		}
		
		//霊媒師生成
		for (int theShaman = 0; theShaman<1 ;) {
			int pick = new java.util.Random().nextInt(playerCount);
			if(players[pick].getJob() == null) {
				players[pick].shaman();
				//デバッグ用
				if(debug == true) {
				System.out.println(players[pick].getName() + "さんが" + players[pick].getJob() + "に選ばれました。");
				}
				theShaman++;
			} else {
				if (debug == true) {//デバッグ用の表示
					System.out.println(players[pick].getName() + "は霊媒師にはなれません。");
				}
				;
			}
		}
		
		//狂人生成
		for (int theLunatic = 0; theLunatic<1 ;) {
			int pick = new java.util.Random().nextInt(playerCount);
			if(players[pick].getJob() == null) {
				players[pick].lunatic();
				//デバッグ用
				if(debug == true) {
				System.out.println(players[pick].getName() + "さんが" + players[pick].getJob() + "に選ばれました。");
				}
				theLunatic++;
			} else {
				if (debug == true) {//デバッグ用の表示
					System.out.println(players[pick].getName() + "は狂人にはなれません。");
				}
				;
			}
		}
		
		//市民生成
		for (Character player : players) {
			if (player.getJob() == null) {
				player.civillian();
			}
		}
		
		int purge = GamePhase.vote(players, playerCount, wolfCount, debug);
		GamePhase.finale(players, debug);
	}
}
