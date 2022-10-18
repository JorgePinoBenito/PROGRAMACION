
public abstract class Canino {

	private int npatas=0;

	public Canino(int npatas) {
		this.npatas = npatas;
	}

	public int getNpatas() {
		return npatas;
	}

	public void setNpatas(int npatas) {
		this.npatas = npatas;
	}

	abstract void cazar();
	
	public void dormir()
	{
		System.out.println("zzzzzzzzz");
	}

}
