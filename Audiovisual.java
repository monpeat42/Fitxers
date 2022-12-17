

public class Audiovisual {
    private enum Genere{
        Action, Adventure, Animation, Comedy, Crime, Drama, Music,
        Short, Thriller;
    }
    private enum Tipo{
        Movie, Series;
    }
    
    private Tipo type;
    private Genere gender;
    private String titol;
	private int IMD;
    
	public Audiovisual(String type, String gender, String titol, int IMD) {
        this.type = Tipo.valueOf(type);
        this.gender = Genere.valueOf(gender);
        this.titol = titol;
        this.IMD = IMD;
    }

    public Tipo getType() {
        return type;
    }

    public void setType(Tipo type) {
        this.type = type;
    }

    public Genere getGender() {
        return gender;
    }

    public void setGender(Genere gender) {
        this.gender = gender;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public int getIMD() {
        return IMD;
    }

    public void setIMD(int IMD) {
        this.IMD = IMD;
    }  
}

