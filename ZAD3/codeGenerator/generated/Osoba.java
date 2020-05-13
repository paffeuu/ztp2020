public class Osoba {
	private String imie = "Jan";
	private String nazwisko;
	private int rok;

	private static final Osoba INSTANCE = new Osoba();
	private Osoba() {}

	public static Osoba getInstance() {
		return INSTANCE;
	}

	public String getImie() {
		return imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public int getRok() {
		return rok;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}

}