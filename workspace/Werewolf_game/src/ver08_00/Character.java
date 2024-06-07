package ver08_00;

import java.util.ArrayList;

public class Character {
	private boolean isAlive;
	private int number;
	private boolean isWolf;
	private String name;
	private String job;
	private String faction;
	private boolean isSeer;
	private boolean hasJob;
	private boolean hasChecked;
	private boolean hasWolvenChecked;
	private boolean isProtected;
	private boolean wasProtected;
	private boolean isLunatic;
	private boolean isPsychic;
	private boolean isLastDead;
	ArrayList<Integer> list = new ArrayList<Integer>();
	private boolean isFox;
	
	
	public Character(int i) {
		this.setisAlive(false);
		this.setisWolf(false);
		this.setNumber(i + 1);
		this.setHasJob(false);
		}
	
	//名前のランダム化メソッド
	public String randomizedName() {
		int dice = new java.util.Random().nextInt(18);
		String name = switch(dice) {
		default -> "田中";
		case 1 -> "鈴木";
		case 2 -> "高橋";
		case 3 -> "伊藤";
		case 4 -> "一条";
		case 5 -> "五条";
		case 6 -> "佐藤";
		case 7 -> "斎藤";
		case 8 -> "天宮";
		case 9 -> "ジョンソン";
		case 10 -> "エジソン";
		case 11 -> "菊池";
		case 12 -> "小川";
		case 13 -> "伊地知";
		case 14 -> "森永";
		case 15 -> "坂本";
		case 16 -> "高田";
		case 17 -> "加藤";
		};
		return name;
	}
	
	
	//変数のカプセル化
	
	//isAliveのカプセル化
	public void setisAlive(boolean isAlive) { this.isAlive = isAlive; }
	public boolean getisAlive() { return this.isAlive; }
	
	//numberのカプセル化
	public void setNumber(int number) { this.number = number; }
	public int getNumber() { return this.number; }
	
	//isWolfのカプセル化
	public void setisWolf(boolean isWolf) { this.isWolf = isWolf; }
	public boolean getisWolf() { return this.isWolf; }
	
	//nameのカプセル化
	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }
	
	//jobのカプセル化
	public void setJob(String job) { this.job = job; }
	public String getJob() { return this.job; }
	
	//factionのカプセル化
	public void setFaction(String faction) { this.faction = faction; }
	public String getFaction() { return this.faction; }
	
	//isSeerのカプセル化
	public void setisSeer(boolean seer) { this.isSeer = seer; }
	public boolean getisSeer() { return this.isSeer; }
	
	//hasJobのカプセル化
	public void setHasJob(boolean hasJob) { this.hasJob = hasJob; }
	public boolean getHasJob() { return this.hasJob; }
	
	//hasCheckedのカプセル化
	public void sethasChecked(boolean hasChecked) { this.hasChecked = hasChecked; }
	public boolean gethasChecked() { return this.hasChecked; }
	
	//hasWolvenCheckedのカプセル化
	public void sethasWolvenChecked(boolean hasWolvenChecked) { this.hasWolvenChecked = hasWolvenChecked; }
	public boolean gethasWolvenChecked() { return this.hasWolvenChecked; }
	
	//isProtectedのカプセル化
	public void setisProtected(boolean isProtected) { this.isProtected = isProtected; }
	public boolean getisProtected() { return this.isProtected; }
	
	//wasProtectedのカプセル化
	public void setwasProtected(boolean wasProtected) { this.wasProtected = wasProtected; }
	public boolean getwasProtected() { return this.wasProtected; }
	
	//isLunaticのカプセル化
	public void setisLunatic(boolean isLunatic) { this.isLunatic = isLunatic; }
	public boolean getisLunatic() { return this.isLunatic; }
	
	//isPsychicのカプセル化
	public void setisPsychic(boolean isPsychic) { this.isPsychic = isPsychic; }
	public boolean getisPsychic() { return this.isPsychic; }
	
	//isLastDeadのカプセル化
	public void setisLastDead(boolean isLastDead) { this.isLastDead = isLastDead; }
	public boolean getisLastDead() { return this.isLastDead; }
	
	//isFoxのカプセル化
	public void setisFox(boolean isFox) { this.isFox = isFox; }
	public boolean getisFox() { return this.isFox; }
	
	
	
	public void civilian(int i) {
		this.setJob("市民");
		this.setFaction("市民");
		this.setisAlive(true);
		if(this.getName() == null) {
		this.setName(randomizedName() + (i + 1));
		this.setHasJob(false);
		}
	}
	
	public void wolf(int i) {
		this.setisWolf(true);
		this.setJob("人狼");
		this.setFaction("人狼");
		this.setHasJob(true);
		if(this.getName() == null) {
		this.setName(randomizedName() + (i + 1));
		this.setisAlive(true);
		}
		int job = new java.util.Random().nextInt(3);
		switch(job) {
			case 0 -> this.setisSeer(true);
			case 1 -> this.setisPsychic(true);
			case 2 -> this.setHasJob(true);
		}
	}
	
	public void seer(int i) {
		this.setisSeer(true);
		this.setJob("占い師");
		this.setFaction("市民");
		this.setHasJob(true);
		this.setisAlive(true);
		if(this.getName() == null) {
		this.setName(randomizedName() + (i + 1));
		}
	}
	
	public void wolvenSeer(int i) {
		this.setisWolf(true);
		this.setisSeer(true);
		this.setJob("占い師騙りの人狼");
		this.setFaction("人狼");
		this.setHasJob(true);
		this.setisAlive(true);
		if(this.getName() == null) {
		this.setName(randomizedName() + (i + 1));
		}
	}
	
	public void fakeSeer(Character[] players, int target, int playerCount, ArrayList<String> word) {
		int survivers = 0;
		for(int i = 0; i < playerCount ; i++) {
			if(players[i].getisAlive() == true) {
				survivers++;
			}
		}
		if(survivers <= 3) {
			word.add(this.getName() + "「偽占い結果は、" + players[target].getName() + "さんが人狼でした。」");
		}else if(players[target].getisSeer() == true){ 
			word.add(this.getName() + "「偽占い結果は、" + players[target].getName() + "さんが人狼でした。」");
		}else {
			String lie = switch(new java.util.Random().nextInt(2)) {
			default -> this.getName() + "「偽占い結果は、" + players[target].getName() + "さんが市民でした。」";
			case 1 -> this.getName() + "「偽占い結果は、" + players[target].getName() + "さんが人狼でした。」";
			};
		word.add(lie);
		}
	}
	
	public void lunatic(int i) {
		this.setJob("狂人");
		this.setFaction("市民");
		this.setisAlive(true);
		if(this.getName() == null) {
			this.setName(randomizedName() + (i + 1));
		}
		this.setHasJob(true);
		this.setisLunatic(true);
		
		//騙る役職はこのメソッドでランダムに追加する
		int job = new java.util.Random().nextInt(3);
		switch(job) {
			case 0 -> this.setisSeer(true);
			case 1 -> this.setisPsychic(true);
			case 2 -> this.setHasJob(true);
		}
	}
	
	public void psychic(int i) {
		this.setJob("霊媒師");
		this.setFaction("市民");
		this.setisAlive(true);
		if(this.getName() == null) {
		this.setName(randomizedName() + (i + 1));
		}
		this.setHasJob(true);
		this.setisPsychic(true);
	}
	
	public void fakePsychic(Character[] players, int playerCount) {
		for(int i = 0; i < playerCount ; i++) {
			if(players[i].getisLastDead() == true) {
				String lie = switch(new java.util.Random().nextInt(2)) {
					default -> this.getName() + "「偽霊媒結果は、" + players[i].getName() + "さんが市民でした。」";
					case 1 -> this.getName() + "「偽霊媒結果は、" + players[i].getName() + "さんが人狼でした。」";
				};
			System.out.println(lie);
			}
		}
	}
	
	public void fox(int i) {
		this.setJob("妖狐");
		this.setFaction("市民");
		this.setisAlive(true);
		if(this.getName() == null) {
		this.setName(randomizedName() + (i + 1));
		}
		this.setHasJob(true);
		this.setisFox(true);
	}
	
	//占い師の占い先が重複しないためのリスト
	//カプセル化のようにそれぞれ、gethasChecked()やsethasChecked()といったメソッドを作ったほうが無難か？
	public boolean gethasChecked(int target) {

		if( this.list.contains(target)) { //これだと共有されているリスト扱いにならないか？
			return true;	
		} else {
			return false;
		}
	}
	
	public void sethasChecked(int target) {
			this.list.add(target); 
	}
}
