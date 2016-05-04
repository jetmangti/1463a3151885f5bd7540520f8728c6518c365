
public class gameSetting 
{
	protected int gameMode = 0; //0 - AI, 1-Human
	protected boolean stoneFreezing = false;
	protected int i_var = 0;
	protected int b_var = 0;
	protected int size = 0;
	protected int instancies = 1;
	protected int aiMode = 1; //1-1st ai script, 2- 2nd ai script 
	
	public int getGameMode() {
		return gameMode;
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}

	public boolean isStoneFreezing() {
		return stoneFreezing;
	}

	public void setStoneFreezing(boolean stoneFreezing) {
		this.stoneFreezing = stoneFreezing;
	}

	public int getI_var() {
		return i_var;
	}

	public void setI_var(int i_var) {
		this.i_var = i_var;
	}

	public int getB_var() {
		return b_var;
	}

	public void setB_var(int b_var) {
		this.b_var = b_var;
	}
	
	public void setInstancies(int instancies) 
	{
		this.instancies = instancies;
	}

	public int getInstancies() {
		return this.instancies;
	}

	public int getSize() {
		return size;
	}
	public int getAiMode()
	{
		return this.aiMode;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
