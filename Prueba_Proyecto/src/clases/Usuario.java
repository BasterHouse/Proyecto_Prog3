package clases;

public class Usuario implements Comparable<Usuario>{
	protected int id;
	protected String nick;
	protected String gmail;
	protected String contraseña;

	
	
	public Usuario(int id, String nick, String gmail, String contraseña) {
		super();
		this.id = id;
		this.nick = nick;
		this.gmail = gmail;
		this.contraseña = contraseña;
	}
	
	public Usuario() {
		super();
		this.id = 00;
		this.nick = "";
		this.gmail = "";
		this.contraseña = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public int compareTo(Usuario o) {
		int res = 1;
		if (this.getNick().equals(o.getNick()) && this.getGmail().equals(o.getGmail()) && this.getContraseña().equals(o.getContraseña())) {
			res = 0;
		}
		return res;
	}



	
	
}
