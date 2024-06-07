package ver08_00;

//ver08_00の目的
//ver07にて一通りゲームを完成させたが、いくつか作りこみが甘い部分がある。
//ここいらで一旦、バグの修正をしつつ、ゲームとして成り立たせるためのNPCの仕様や文字表示の手順をいじっていきたい。

//以下確認されている問題点⇩
//真占い師が妖狐を占った直後に狐の死亡報告が出てしまうため、誰が真占い師なのかそれでわかってしまう。(ArrayList<String>で結果をaddして一通りの流れが終わったら拡張for文で報告、その後にリストを削除すればいいのではないだろうか？)
//妖狐が偽占い師のデタラメ占いで死んでいる可能性あり？※要検証
//jobとfactionに分けて、ゲーム中は市民か人狼かのfaction,ゲーム終了時の役職開示はjobでわけるようにしたい。
//妖狐の立ち回りをかんがえたい。※人狼ゲームにおける妖狐の立ち回りの勉強が必要。
//人狼が妖狐を勝たせないために人狼COからの妖狐を報告するといった工夫がほしい。(人狼が妖狐をmaulしたときに、対象をリストに追加。生存者が一定の人数に達したときなおかつリストに入っている妖狐のプレイヤーが生存している場合に人狼の数が複数なら、潜伏していない人狼が人狼COして妖狐を報告する。)
//騎士の連続ガードはなくしたい。（霊媒師のLastDeadの仕様と同じでガードしたらそのプレイヤーをboolean型のlastGuardに追加。次回の防衛対象が決まった直後にlastGuardが初期化される仕組みにする。）



public class Main {

	public static void main(String[] args) {
		
		//初期設定
		System.out.println("プレイヤー数を入力してください⇩");
		System.out.print(">");
		int playerCount = new java.util.Scanner(System.in).nextInt(); //プレイヤーの最大数
		int wolfCount = playerCount / 4; //狼の最大数
		Character[] players = new Character[playerCount]; //市民の最大数
		int aggressors = 0; //実際の狼の数を定義する際に使う
		int foxCount = 0; //妖狐のカウント
		
		
		System.out.println("初期の市民の数は" + playerCount + "です。");
		System.out.println("初期の人狼の数は" + wolfCount + "です。");
		System.out.println("初期の妖狐の数は1です。");
		
		//NPCの仮作成
		for(int i = 0; i < playerCount ; i++) { //NPC作成フェイズ（この段階では実際に参加するNPCではガワだけを作る）
			players[i] = new Character(i);
			System.out.println(players[i].getNumber() + "番目のプレイヤーを生成します。");
		}
		
		//NPCの本作成
		for( ; aggressors < wolfCount ;) { //人狼作成フェイズ（作成されたNPCの中から人狼をピックする）
			int pick = new java.util.Random().nextInt(playerCount);
			if(players[pick].getHasJob() != true) {
				if(aggressors == 0 && wolfCount > 1) {
					players[pick].wolvenSeer(pick);
					aggressors++;
				}else {
					players[pick].wolf(pick);		
					aggressors++;
				}	
				System.out.println(players[pick].getName() + "は" + players[pick].getJob() +"に選ばれました");
			}else {
				System.out.println(players[pick].getName() + "は人狼にはなれません");
			}
		}
		
		
		//妖狐作成フェイズ
		for (foxCount = 0; foxCount < 1 ; ) { //
			int pick = new java.util.Random().nextInt(playerCount);
			if(players[pick].getHasJob() != true) {
				players[pick].fox(pick);
				System.out.println(players[pick].getName() + "は"+ players[pick].getJob() + "に選ばれました");
				foxCount++;
			}else {
				System.out.println(players[pick].getName() + "は妖狐にはなれません");
			}
		}
		
		//占い師作成フェイズ
		for (int seerCount = 0; seerCount < 1 ; ) { //占い師の役職が満たされない限りランダムで1人選び、役職をもっていなかったら占い師に任命させる。
			int pick = new java.util.Random().nextInt(playerCount);
			if(players[pick].getHasJob() != true) {
				players[pick].seer(pick);
				System.out.println(players[pick].getName() + "は"+ players[pick].getJob() + "に選ばれました");
				seerCount++;
			}else {
				System.out.println(players[pick].getName() + "は占い師にはなれません");
			}
		}
		
		//狂人作成フェイズ
		for (int lunaticCount = 0; lunaticCount < 1 ; ) { //狂人の役職が満たされない限りランダムで1人選び、役職をもっていなかったら占い師に任命させる。
			int pick = new java.util.Random().nextInt(playerCount);
			if(players[pick].getHasJob() != true) {
				players[pick].lunatic(pick);
				System.out.println(players[pick].getName() + "は"+ players[pick].getJob() + "に選ばれました");
				lunaticCount++;
			}else {
				System.out.println(players[pick].getName() + "は狂人にはなれません");
			}
		}
		
		//霊媒師作成フェイズ
		for (int psychicCount = 0; psychicCount < 1 ; ) { //狂人の役職が満たされない限りランダムで1人選び、役職をもっていなかったら占い師に任命させる。
			int pick = new java.util.Random().nextInt(playerCount);
			if(players[pick].getHasJob() != true) {
				players[pick].psychic(pick);
				System.out.println(players[pick].getName() + "は"+ players[pick].getJob() + "に選ばれました");
				psychicCount++;
			}else {
				System.out.println(players[pick].getName() + "は霊媒師にはなれません");
			}
		}
		
		//役職を得られなかった余りのCharacterを全員市民にする
		for(int i = 0; i < playerCount ; i++) {
			if(players[i].getHasJob() != true) {
				players[i].civilian(i);
				System.out.println(players[i].getName() + "は" + players[i].getJob() + "に選ばれました。");
			}
		}
		
		GamePhase gamePhase = new GamePhase();
//		gamePhase.vote(wolfCount, playerCount, players, foxCount);
		Character[] result = gamePhase.vote(wolfCount, playerCount, players, foxCount);
		gamePhase.end(playerCount, result);
	}

}
