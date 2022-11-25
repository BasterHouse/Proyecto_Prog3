package Tests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Clases.Usuario;


public class TestUsuario {
	protected String nick = "nick";
    protected String gmail = "gmail";
    protected String contraseña = "contraseña";
    protected Usuario usuario;

    @Before
    public void iniciar() {
        usuario =new Usuario();
        usuario.setContraseña(contraseña);
        usuario.setGmail(gmail);
        usuario.setNick(nick);
    }

    @Test
    public void testUsuarioStringStringString() {
    	Usuario usuario2=new Usuario("nick", "gmail", "contraseña");
		assertNotNull(usuario2);
		assertEquals(usuario2.getContraseña(), "contraseña");
		assertEquals(usuario2.getGmail(), "gmail");
		assertEquals(usuario2.getNick(), "nick");
    }

    @Test
    public void testUsuario() {
        Usuario usuario2 = new Usuario();
        assertNotNull(usuario2);
        assertEquals(usuario2.getContraseña(), "");
        assertEquals(usuario2.getGmail(), "");
        assertEquals(usuario2.getNick(), "");
    }

    @Test
    public void testGetNick() {
        assertEquals(usuario.getNick(), nick);
    }

    @Test
    public void testSetNick() {
        String newnick = nick;
        assertEquals(usuario.getNick(), nick);
        usuario.setNick(newnick);
        assertEquals(usuario.getNick(), newnick);

    }

    @Test
    public void testGetGmail() {
        assertEquals(usuario.getGmail(), gmail);
    }

    @Test
    public void testSetGmail() {
        String newgmail = gmail;
        assertEquals(usuario.getGmail(), gmail);
        usuario.setGmail(newgmail);
        assertEquals(usuario.getGmail(), newgmail);
    }

    @Test
    public void testGetContraseña() {
        assertEquals(usuario.getContraseña(), contraseña);
    }

    @Test
    public void testSetContraseña() {
        String newcontraseña = contraseña;
        assertEquals(usuario.getContraseña(), contraseña);
        usuario.setContraseña(newcontraseña);
        assertEquals(usuario.getContraseña(), newcontraseña);

    }

    @Test
    public void testToString() {
        String toString="Usuario [nick=" + nick + ", gmail=" + gmail + ", contraseña=" + contraseña + "]";
        assertEquals(usuario.toString(),toString);
    }

}