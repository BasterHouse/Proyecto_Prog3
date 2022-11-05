package clases;

public class Usuario implements Comparable{
	protected String nick;
	protected String gmail;
	protected String contraseña;
	
	
	public Usuario(String nick, String gmail, String contraseña) {
		super();
		this.nick = nick;
		this.gmail = gmail;
		this.contraseña = contraseña;
	}
	
	public Usuario() {
		super();
		this.nick = "";
		this.gmail = "";
		this.contraseña = "";
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "Usuario [nick=" + nick + ", gmail=" + gmail + ", contraseña=" + contraseña + "]";
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
}
