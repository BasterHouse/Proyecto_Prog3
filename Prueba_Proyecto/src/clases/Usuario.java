package clases;

public class Usuario implements Comparable<Usuario>{
	protected String nick;
	protected String gmail;
	protected String contraseña;
	protected int id;

	

	public Usuario(String nick, String gmail, String contraseña, int id) {
		super();
		this.nick = nick;
		this.gmail = gmail;
		this.contraseña = contraseña;
		this.id = id;
	}
	
	public Usuario() {
		super();
		this.nick = "";
		this.gmail = "";
		this.contraseña = "";
		this.id = 0;
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
		if (this.getId()==o.getId()) {
			res = 0;
		}
		return res;
	}



	
	
}
