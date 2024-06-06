package ver10_00;

public class Character {
	private boolean isAlive;
	private int number;
	private String name;
	private boolean isWolf;
	private String job = null;
	private String faction;
	private boolean isFox;
	private boolean isSeer;
	private boolean isShaman;
	private boolean isLunatic;
	private boolean isProtected;
	private boolean hasBeenExecuted;
	private boolean hasBeenKilled;
	private boolean hasBeenWatched;
	private boolean fakeWatch;
	private boolean wolfWatch;
	private boolean theLastGuarded;

	//isAliveのカプセル化
	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean getIsAlive() {
		return this.isAlive;
	}

	//numberのカプセル化
	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}

	//nameのカプセル化
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	//isWolfのカプセル化
	public void setIsWolf(boolean isWolf) {
		this.isWolf = isWolf;
	}

	public boolean getIsWolf() {
		return this.isWolf;
	}

	//jobのカプセル化
	public void setJob(String job) {
		this.job = job;
	}

	public String getJob() {
		return this.job;
	}

	//factionのカプセル化
	public void setFaction(String faction) {
		this.faction = faction;
	}

	public String getFaction() {
		return this.faction;
	}

	//isFoxのカプセル化
	public void setIsFox(boolean isFox) {
		this.isFox = isFox;
	}

	public boolean getIsFox() {
		return this.isFox;
	}

	//isSeerのカプセル化
	public void setIsSeer(boolean isSeer) {
		this.isSeer = isSeer;
	}

	public boolean getIsSeer() {
		return this.isSeer;
	}

	//isShamanのカプセル化
	public void setIsShaman(boolean isShaman) {
		this.isShaman = isShaman;
	}

	public boolean getIsShaman() {
		return this.isShaman;
	}

	//isLunaticのカプセル化
	public void setIsLunatic(boolean isLunatic) {
		this.isLunatic = isLunatic;
	}

	public boolean getIsLunatic() {
		return this.isLunatic;
	}

	//isProtectedのカプセル化
	public void setIsProtected(boolean isProtected) {
		this.isProtected = isProtected;
	}

	public boolean getIsProtected() {
		return this.isProtected;
	}

	//hasBeenExecutedのカプセル化
	public void setHasBeenExecuted(boolean hasBeenExecuted) {
		this.hasBeenExecuted = hasBeenExecuted;
	}

	public boolean getHasBeenExecuted() {
		return this.hasBeenExecuted;
	}

	//hasBeenKilledのカプセル化
	public void setHasBeenKilled(boolean hasBeenKilled) {
		this.hasBeenKilled = hasBeenKilled;
	}

	public boolean getHasBeenKilled() {
		return this.hasBeenKilled;
	}

	//hasBeenWatchedのカプセル化
	public void setHasBeenWatched(boolean hasBeenWatched) {
		this.hasBeenWatched = hasBeenWatched;
	}

	public boolean getHasBeenWatched() {
		return this.hasBeenWatched;
	}
	
	//fakeWatchのカプセル化
	public void setFakeWatch(boolean fakeWatch) {
		this.fakeWatch = fakeWatch;
	}

	public boolean getFakeWatched() {
		return this.fakeWatch;
	}
	
	//wolfWatchのカプセル化
	public void setWolfWatch(boolean wolfWatch) {
		this.wolfWatch = wolfWatch;
	}

	public boolean getWolfWatched() {
		return this.wolfWatch;
	}
	
	//theLastGuardedのカプセル化
	public void setTheLastGuarded(boolean theLastGuarded) {
		this.theLastGuarded = theLastGuarded;
	}

	public boolean getTheLastGuarded() {
		return this.theLastGuarded;
	}

	//名前のランダム化メソッド
	public String randomizedName() {
		int dice = new java.util.Random().nextInt(18);
		int dice2 = new java.util.Random().nextInt(18);
		String familyName = switch (dice) {
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
		String firstName = switch (dice2) {
		default -> "一郎";
		case 1 -> "次郎";
		case 2 -> "三郎";
		case 3 -> "四郎";
		case 4 -> "五郎";
		case 5 -> "悟";
		case 6 -> "浩二";
		case 7 -> "はじめ";
		case 8 -> "清麻呂";
		case 9 -> "エドワード";
		case 10 -> "トーマス";
		case 11 -> "太郎";
		case 12 -> "良";
		case 13 -> "潔高";
		case 14 -> "太一郎";
		case 15 -> "竜馬";
		case 16 -> "健志";
		case 17 -> "純一";
		};
		String name = familyName + " " + firstName;
		return name;
	}

	//デフォルトのキャラクター設定（市民ですらない）
	public Character(int i) {
		this.setIsAlive(true);
		this.setNumber(i + 1);
		this.setName(randomizedName());
	}

	//人狼のキャラクターステータスメソッド
	public void wolf() {
		this.setIsWolf(true);
		this.setJob("人狼");
		this.setFaction("人狼陣営");
	}

	//妖狐のキャラクターステータスメソッド
	public void fox() {
		this.setIsWolf(false);
		this.setIsFox(true);
		this.setJob("妖狐");
		this.setFaction("市民陣営");
	}

	//占い師のキャラクターステータスメソッド
	public void seer() {
		this.setIsSeer(true);
		this.setJob("占い師");
		this.setFaction("市民陣営");
	}

	//霊媒師のキャラクターステータスメソッド
	public void shaman() {
		this.setIsShaman(true);
		this.setJob("霊媒師");
		this.setFaction("市民陣営");
	}

	//狂人のキャラクターステータスメソッド
	public void lunatic() {
		this.setIsLunatic(true);
		this.setJob("狂人");
		this.setFaction("市民陣営");
		int job = new java.util.Random().nextInt(2);
		switch (job) {
		case 0 -> this.setIsSeer(true);
		case 1 -> this.setIsShaman(true);
		}
	}

	//市民のキャラクターステータスメソッド
	public void civillian() {
		this.setJob("市民");
		this.setFaction("市民陣営");
	}

}
